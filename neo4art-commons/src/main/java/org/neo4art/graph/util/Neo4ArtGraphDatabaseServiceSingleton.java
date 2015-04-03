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
public class Neo4ArtGraphDatabaseServiceSingleton extends Neo4ArtGraphDatabase {

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
