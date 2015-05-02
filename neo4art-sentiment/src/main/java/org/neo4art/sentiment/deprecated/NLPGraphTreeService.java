/**
 * Copyright 2015 the original author or authors.
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

import java.util.HashMap;
import java.util.Map;

import opennlp.tools.parser.AbstractBottomUpParser;
import opennlp.tools.parser.Parse;

import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.sentiment.graphdb.NLPLegacyIndex;
import org.neo4art.sentiment.graphdb.NLPLabel;
import org.neo4art.sentiment.graphdb.NLPRelationship;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserterIndex;

/**
 * @author Lorenzo Speranzoni
 * @since 11 Apr 2015
 */
@Deprecated
public class NLPGraphTreeService {

  private Long lastLinkedListIndex;
  private Long nodeIndex;
  
  public NLPGraphTreeService() {
    this.nodeIndex = 0L;
    this.lastLinkedListIndex = null;
  }

  /**
   * 
   * @param parse
   */
  public void save(Parse parse) {
    
    build(saveRootNode(), parse);
  }

  /**
   * 
   * @param rootNodeId
   * @param parse
   */
  public void save(long rootNodeId, Parse parse) {
    
    build(rootNodeId, parse);
  }
  
  public long saveRootNode() {
    
    BatchInserter batchInserter = null; //Neo4ArtBatchInserterSingleton.getBatchInserterInstance();

    Map<String, Object> properties = new HashMap<String, Object>();
    
    properties.put("position", nodeIndex);
    properties.put("text", "-");
    properties.put("type", AbstractBottomUpParser.TOP_NODE);
    properties.put("sentiment", new String[] {});
     
    nodeIndex++;
    
    return batchInserter.createNode(properties, NLPLabel.Nlp);
  }
  
  /**
   * 
   * @param rootNodeId
   * @param parse
   */
  private void build(Long rootNodeId, Parse parse) {
    
    Long currentNodeId = null;
    
    if (parse.getType().equals(AbstractBottomUpParser.TOK_NODE)) {
      return;
    }
    
    Parse children[] = parse.getChildren();
    
    if (parse.getType().equals(AbstractBottomUpParser.TOP_NODE)) {
      
      currentNodeId = rootNodeId;
    }
    else {
      
      boolean isLeaf = isLeaf(children);
        
      Object[] wordFromDictionary = getWordFromDictionary(isLeaf, parse.getCoveredText());
          
      Map<String, Object> properties = new HashMap<String, Object>();
      
      properties.put("position" , nodeIndex);
      properties.put("text"     , isLeaf ? parse.getCoveredText() : "-");
      properties.put("type"     , parse.getType());
      properties.put("sentiment", (String[]) wordFromDictionary[0]);
      
      nodeIndex++;
      
      BatchInserter batchInserter = null; //Neo4ArtBatchInserterSingleton.getBatchInserterInstance();
      
      currentNodeId = batchInserter.createNode(properties, NLPLabel.Nlp);
      
      if (rootNodeId != null) {
        batchInserter.createRelationship(rootNodeId, currentNodeId, NLPRelationship.NLP_TREE, null);
      }
        
      if (isLeaf) {
        if (lastLinkedListIndex != null) {
          batchInserter.createRelationship(lastLinkedListIndex, currentNodeId, NLPRelationship.NEXT_NLP, null);
        }
        lastLinkedListIndex = currentNodeId;
        
        if (wordFromDictionary[1] != null) {
          batchInserter.createRelationship(currentNodeId, (Long) wordFromDictionary[1], NLPRelationship.IS_A_WORD, null);
        }
      }      
    }
    
    for (int i = 0; i < children.length; i++) {
      build(currentNodeId, children[i]);
    }
  }

  /**
   * @param isLeaf
   * @param coveredText
   * @return
   */
  private Object[] getWordFromDictionary(boolean isLeaf, String coveredText) {
    
    Long     nodeId      = null;
    String[] sentimentId = null;
    
    if (isLeaf) {
      
      BatchInserterIndex negationWordIndex = null; //ColourLegacyIndex.getBatchInserterIndex(NLPLabel.NegationWord);
      BatchInserterIndex negativeWordIndex = null; //ColourLegacyIndex.getBatchInserterIndex(NLPLabel.NegativeWord);
      BatchInserterIndex positiveWordIndex = null; //ColourLegacyIndex.getBatchInserterIndex(NLPLabel.PositiveWord);
      
      IndexHits<Long> indexHits = negationWordIndex.get("word", coveredText);
      
      if (indexHits.hasNext()) {
        
        nodeId      = indexHits.next();
        sentimentId = new String[] { "NOT" };
      }
      else {
        
        indexHits = negativeWordIndex.get("word",  coveredText);
        
        if (indexHits.hasNext()) {
          
          nodeId      = indexHits.next(); 
          sentimentId = new String[] { "-1" };
        }
        else {
          
          indexHits = positiveWordIndex.get("word",  coveredText);
          
          if (indexHits.hasNext()) {
            
            nodeId      = indexHits.next(); 
            sentimentId = new String[] { "1" };
          }
          else {
            
            sentimentId = new String[] { "0" };
          }
        }
      }
    }
    else {
      
      sentimentId = new String[] { };
    }
    
    return new Object[] { sentimentId, nodeId };
  }

  /**
   * 
   * @param children
   * @return
   */
  private boolean isLeaf(Parse[] children) {
    return children.length == 1 && children[0].getType().equals(AbstractBottomUpParser.TOK_NODE);
  }
}
