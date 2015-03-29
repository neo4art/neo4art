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
package org.neo4art.importer.wikipedia.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graph.util.Neo4ArtGraphDatabaseConnectionFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.service.WikipediaElementManager;
import org.neo4art.importer.wikipedia.service.WikipediaElementManagerFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * Default implementation of the importer listener.
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaNodesImporterListener extends WikipediaAbstractImporterListener implements WikipediaImporterListener {

  private static Log logger = LogFactory.getLog(WikipediaNodesImporterListener.class);
  
  @Override
  public void flush() {
    
    long newNodes = 0;
    
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseConnectionFactory.getInstance();
    
    try (Transaction tx = graphDatabaseService.beginTx()) {
      
      for (WikipediaElement wikipediaElement : this.wikipediaElementBuffer) {
        
        WikipediaElementManager wikipediaElementManager =
            WikipediaElementManagerFactory.getInstance(wikipediaElement.getType());
        
        newNodes += wikipediaElementManager.createNodes(graphDatabaseService, wikipediaElement);
      }
      
      tx.success();

      this.graphCount.addAndGet(newNodes);
      
      this.wikipediaElementBuffer.clear();
      
      logger.debug(newNodes + " new nodes created.");
    }
  }
}
