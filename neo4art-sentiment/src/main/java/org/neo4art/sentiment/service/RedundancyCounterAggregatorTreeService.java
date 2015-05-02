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

package org.neo4art.sentiment.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4art.literature.domain.Document;
import org.neo4art.sentiment.bean.RedundancyStatistics;
import org.neo4art.sentiment.domain.RedundancyCounter;
import org.neo4art.sentiment.graphdb.RedundancyTreeLabel;
import org.neo4art.sentiment.repository.RedundancyCounterAggregatorTreeRepository;
import org.neo4art.sentiment.repository.RedundancyCounterRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * @author Lorenzo Speranzoni
 * @since 28 Apr 2015
 */
public class RedundancyCounterAggregatorTreeService implements RedundancyCounterService
{
  public RedundancyCounterAggregatorTreeService()
  {
  }
  
  /**
   * @see org.neo4art.sentiment.service.RedundancyCounterService#createIndexes()
   */
  @Override
  public void createIndexes()
  {
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();

    try (Transaction tx = graphDatabaseService.beginTx())
    {
      graphDatabaseService.schema().indexFor(RedundancyTreeLabel.RedundancyTree).on(RedundancyCounter.REDUNDANT_PATH_PROPERTY_NAME).create();
      
      //graphDatabaseService.execute("CREATE INDEX ON :" + RedundancyTreeLabel.RedundancyTree + "(" + RedundancyCounter.REDUNDANT_PATH_PROPERTY_NAME + ");");
      
      tx.success();
    }
  }
  
  /**
   * @see org.neo4art.sentiment.service.RedundancyCounterService#computeRedundancyCount(java.util.List, int, int)
   */
  @Override
  public RedundancyStatistics computeRedundancyCount(List<Document> documents, int minWordCount, int maxWordCount)
  {
    RedundancyStatistics redundancyStatistics = null;
    
    if (!CollectionUtils.isEmpty(documents))
    {
      GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
      
      RedundancyCounterRepository redundancyCounterRepository = new RedundancyCounterAggregatorTreeRepository(); 
      
      try (Transaction tx = graphDatabaseService.beginTx())
      {
        long redundancyRootNodeId = redundancyCounterRepository.createRedundancyRootNode();
        
        for (Document document : documents)
        {
          redundancyCounterRepository.countRedundancies(document, redundancyRootNodeId, minWordCount, maxWordCount);
        }
        
        redundancyStatistics = redundancyCounterRepository.loadRedundancyResult(redundancyRootNodeId);
        
        tx.success();
      }
    }
    
    return redundancyStatistics;
  }
}
