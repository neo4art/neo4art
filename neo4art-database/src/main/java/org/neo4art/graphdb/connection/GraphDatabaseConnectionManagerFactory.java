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

package org.neo4art.graphdb.connection;


/**
 * @author Lorenzo Speranzoni
 * @since 14 Oct 2015
 */
public class GraphDatabaseConnectionManagerFactory
{
  private static GraphDatabaseConnectionManager instance;
  
  public static enum GraphDatabaseConnectionType {
    
    GRAPH_DATABASE_SERVICE, BATCH_INSERTER
  }
  
  public static GraphDatabaseConnectionManager getInstance() {
    
    if (instance == null)
      throw new IllegalStateException("You must call getInstance(GraphDatabaseConnectionType) first.");
    
    return instance;
  }
  
  public static GraphDatabaseConnectionManager getInstance(GraphDatabaseConnectionType graphDatabaseConnectionType) {

    if (instance == null) {
      
      switch (graphDatabaseConnectionType) {
        
        case GRAPH_DATABASE_SERVICE:
          instance = new GraphDatabaseServiceConnectionManager();
          break;
          
        case BATCH_INSERTER:
          instance = new BatchInserterConnectionManager();
          break;
      }
    }

    return instance;
  }
}
