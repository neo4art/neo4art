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
package org.neo4art.importer.wikipedia.core.listener;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.graphdb.transaction.GraphDatabaseTransaction;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.manager.WikipediaElementDefaultManager;

/**
 * Default implementation of the importer listener.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaInMemoryBatchImporterListener extends WikipediaAbstractImporterListener implements WikipediaImporterListener {

  private static Log logger = LogFactory.getLog(WikipediaInMemoryBatchImporterListener.class);
  
  public WikipediaInMemoryBatchImporterListener() {
    super();
  }

  /**
   * @see org.neo4art.importer.wikipedia.core.listener.WikipediaAbstractImporterListener#flush()
   */
  @Override
  public void flush() {
    
    long graphElementsCreated = 0;
    long flushStartDate = Calendar.getInstance().getTimeInMillis();
    
    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.EMBEDDED_DATABASE);

    try (GraphDatabaseTransaction tx = graphDatabaseConnectionManager.getTransaction()) {
      
      for (WikipediaElement wikipediaElement : this.wikipediaElementBuffer) {
        graphElementsCreated += persistNodes(wikipediaElement);
      }
      
      for (WikipediaElement wikipediaElement : this.wikipediaElementBuffer) {
        graphElementsCreated += persistRelationships(wikipediaElement);
      }
      
      tx.success();
    }
    
    long flushEndDate = Calendar.getInstance().getTimeInMillis();
    this.graphCount.addAndGet(graphElementsCreated);
    logger.debug(graphElementsCreated + " new graph database elements created for " + this.wikipediaElementBuffer.size() + " wikipedia elements in " + (flushEndDate - flushStartDate) + " ms.");
    this.wikipediaElementBuffer.clear();
  }
  
  /**
   * 
   * @param wikipediaElement
   * @return
   */
  private long persistNodes(WikipediaElement wikipediaElement) {
    
    try {
      
      return new WikipediaElementDefaultManager().createNodes(wikipediaElement);
    }
    catch (Exception e) {
      
      logger.error("Error creating nodes for wikipedia element { " + wikipediaElement.getProperties() + "}. Cause: " + e.getCause().getMessage());
      
      return 0;
    }
  }
  
  /**
   * 
   * @param wikipediaElement
   * @return
   */
  private long persistRelationships(WikipediaElement wikipediaElement) {
    
    try {
      
      return new WikipediaElementDefaultManager().createRelationships(wikipediaElement);
    }
    catch (Exception e) {
      
      logger.error("Error creating relationships for wikipedia element { " + wikipediaElement.getProperties() + " }. Cause: " + e.getCause().getMessage());
      
      return 0;
    }
  }

  /**
   * @see org.neo4art.importer.wikipedia.core.listener.WikipediaAbstractImporterListener#persist(org.neo4art.importer.wikipedia.domain.WikipediaElement)
   */
  @Override
  protected long persist(WikipediaElement wikipediaElement) {
    
    throw new RuntimeException(new IllegalAccessException("Method not allowed in this implementation. Use 'persistNodes' and 'persistRepationships' instead."));
  }
}
