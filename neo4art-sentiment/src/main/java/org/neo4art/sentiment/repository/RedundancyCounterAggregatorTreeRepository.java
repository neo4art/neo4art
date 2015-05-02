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

package org.neo4art.sentiment.repository;

import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4art.literature.domain.Document;
import org.neo4art.sentiment.bean.RedundancyStatistics;
import org.neo4art.sentiment.domain.NLP;
import org.neo4art.sentiment.domain.RedundancyCounter;
import org.neo4art.sentiment.graphdb.NLPRelationship;
import org.neo4art.sentiment.graphdb.RedundancyTreeLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Apr 2015
 */
public class RedundancyCounterAggregatorTreeRepository implements RedundancyCounterRepository
{
  /**
   * @see org.neo4art.sentiment.repository.RedundancyCounterRepository#createRedundancyRootNode()
   */
  @Override
  public long createRedundancyRootNode()
  {
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
    
    Node redundancyRootNode = graphDatabaseService.createNode(RedundancyTreeLabel.RedundancyTree);
    
    return redundancyRootNode.getId();
  }

  /**
   * @see org.neo4art.sentiment.repository.RedundancyCounterRepository#countRedundancies(org.neo4art.literature.domain.Document, long, int, int)
   */
  @Override
  public void countRedundancies(Document document, long redundancyRootNodeId, int minWordCount, int maxWordCount)
  {
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
    
    //TODO We can try to optime this statement by adding a vertex to the last nlp sentence node, to make the computation of p easier
    
    String cql = "MATCH (document:" + document.getLabels()[0].name() + ")-[r:" + NLPRelationship.TOKENIZED_IN_POS + "]->(startingNode:Nlp) " +
                 "WHERE id(document) = " + document.getNodeId() + " " +
                 "WITH r.nlpSentenceLength -1 as nlpPathLength, startingNode " +
                 "MATCH p = (startingNode:Nlp)-[r*]->() " +
                 "WHERE length(p) = nlpPathLength " +
                 "UNWIND nodes(p) AS wordsInDocument " +
                 "MATCH redundantWordsPath = (wordsInDocument)-[*" + (minWordCount - 1) + ".." + (maxWordCount - 1) + "]->() " +
                 "WITH reduce(redundantWords = \"\", redundantWord in nodes(redundantWordsPath) | redundantWords + lower(redundantWord." + NLP.POS_PROPERTY_NAME + ") + \" \") as redundantPath " +
                 "MERGE (redundancyNode:" + RedundancyTreeLabel.RedundancyTree + "{" + RedundancyCounter.REDUNDANT_PATH_PROPERTY_NAME + ": redundantPath}) " +
                 "ON CREATE SET redundancyNode." + RedundancyCounter.REDUNDANCY_COUNTER_PROPERTY_NAME + " = 1 " +
                 "ON MATCH SET redundancyNode." + RedundancyCounter.REDUNDANCY_COUNTER_PROPERTY_NAME + "=redundancyNode." + RedundancyCounter.REDUNDANCY_COUNTER_PROPERTY_NAME + " + 1";
    
    System.out.println(cql);
    
    graphDatabaseService.execute(cql);
  }

  /**
   * @see org.neo4art.sentiment.repository.RedundancyCounterRepository#loadRedundancyResult(long)
   */
  @Override
  public RedundancyStatistics loadRedundancyResult(long redundancyRootNodeId)
  {
    return null;
  }
}
