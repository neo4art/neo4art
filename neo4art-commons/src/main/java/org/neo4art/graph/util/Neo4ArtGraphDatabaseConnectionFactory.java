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

package org.neo4art.graph.util;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class Neo4ArtGraphDatabaseConnectionFactory {

  public static final String NEO4J_STORE_DIR = System.getProperty("NEO4J_STORE_DIR","target/graph.db");
  
  public static final String NEO4J_PATH = System.getProperty("NEO4J_PATH","jdbc:neo4j:file:" + NEO4J_STORE_DIR);
  
  public static final String NEO4J_URL = System.getProperty("NEO4J_URL","jdbc:neo4j://localhost:7474");
  public static final String NEO4J_USR = System.getProperty("NEO4J_USR","neo4j");
  public static final String NEO4J_PWD = System.getProperty("NEO4J_PWD","neo4art");

  private static GraphDatabaseService graphDatabaseService;
  
  public static GraphDatabaseService getInstance() {
  
    if (graphDatabaseService == null) {
    
      graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(NEO4J_STORE_DIR);
    
      registerShutdownHook(graphDatabaseService);
    }
    
    return graphDatabaseService;
  }

  private static void registerShutdownHook(final GraphDatabaseService graphDatabaseService) {

    Runtime.getRuntime().addShutdownHook( new Thread() {
        
      @Override
      public void run() {
        graphDatabaseService.shutdown();
      }
    });
  }
}
