/**
 * Copyright 2015 the oriinal author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.neo4art.sentiment.deprecated;

import java.util.Locale;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.TokenizerME;

import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4art.sentiment.graphdb.NLPLabel;
import org.neo4art.sentiment.util.NLPUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * @author Lorenzo Speranzoni
 * @since 7 Apr 2015
 */
@Deprecated
public class SentimentAnalysisPennTreeBackService {

  public SentimentAnalysisPennTreeBackService() {
  }
  
  /**
   * @param args
   */
  public long save(String text) {

    SentenceDetectorME sentenceDetectorModel = NLPUtils.getSentenceDetectorME(Locale.ENGLISH);
    TokenizerME sentenceTokenizerModel = NLPUtils.getSentenceTokenizerME(Locale.ENGLISH);
    Parser sentenceParserModel = NLPUtils.getSentenceParser(Locale.ENGLISH);
    
    NLPGraphTreeService nlpGraphTreeService = new NLPGraphTreeService();

    long rootNodeId = nlpGraphTreeService.saveRootNode();
    
    String[] sentences = sentenceDetectorModel.sentDetect(text);
    
    if (sentences != null && sentences.length > 0) {
      
      for (String sentence : sentences) {
        
        String[] tokenizedSentences = sentenceTokenizerModel.tokenize(sentence);
        
        if (tokenizedSentences != null && tokenizedSentences.length > 0) {
          
          sentence = "";
          
          for (String tokenizedSentence : tokenizedSentences) {
            
            sentence += tokenizedSentence + " ";
          }
            
          Parse[] parse = ParserTool.parseLine(sentence, sentenceParserModel, 1);

          nlpGraphTreeService.save(rootNodeId, parse[0]);
        }
      }
    }
    
    return rootNodeId;
  }

  public void compute(long rootNodeId) {
    
    String cql = "MATCH (root:" + NLPLabel.Nlp + ") " +
                 "WHERE id(root) = " + rootNodeId + " " +
                 "MATCH p=(root)-[:NLP_TREE*]->(to_be_updated:" + NLPLabel.Nlp + ")-[:NLP_TREE]->(leaf:" + NLPLabel.Nlp + ") " +
                 "WHERE length(to_be_updated.sentiment) = 0 " +
                 "WITH root, max(length(p)) AS update_depth " +
                 "MATCH p=(root)-[:NLP_TREE*]->(to_be_updated:" + NLPLabel.Nlp + ")-[:NLP_TREE]->(leaf:" + NLPLabel.Nlp + ") " +
                 "WHERE length(p) = update_depth " +
                 "WITH to_be_updated, leaf.position AS position, collect(leaf) AS leaves ORDER BY position " +
                 "WHERE all(leaf IN leaves WHERE length(leaf.sentiment) > 0) " +
                 "FOREACH (leaf IN leaves | SET to_be_updated.sentiment = to_be_updated.sentiment + leaf.sentiment) " +
                 "RETURN count(to_be_updated) AS result"; 
        
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
    
    try (Transaction tx = graphDatabaseService.beginTx()) {

      long updated = -1;
      
      while (updated != 0) {
        
        updated = (Long) graphDatabaseService.execute(cql).columnAs("result").next();
      }
      
      tx.success();
    }
  }
}
