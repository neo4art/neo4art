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

import java.io.File;

import org.neo4art.graphdb.Node;
import org.neo4art.graphdb.Relationship;
import org.neo4art.graphdb.transaction.GraphDatabaseTransaction;
import org.neo4art.graphdb.transaction.TransactionWrapper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.IndexDefinition;

/**
 * @author Lorenzo Speranzoni
 * @since 19 Oct 2015
 */
class GraphDatabaseServiceConnectionManager implements GraphDatabaseConnectionManager {
  
  private static GraphDatabaseService graphDatabaseService;
  
  protected GraphDatabaseServiceConnectionManager() {
    
    newGraphDatabaseServiceInstance();
  }
  
  private void newGraphDatabaseServiceInstance() {
    
    if (graphDatabaseService == null) {
      
      graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(new File(NEO4J_STORE_DIR));
    }
  }
  
  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#getStoreDir()
   */
  @Override
  public String getStoreDir() {
    
    return NEO4J_STORE_DIR;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#close()
   */
  @Override
  public void close() {
    
    graphDatabaseService.shutdown();
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createNode(org.neo4art.graphdb.Node)
   */
  @Override
  public long createNode(Node node) {
    
    org.neo4j.graphdb.Node neo4jNode = graphDatabaseService.createNode(node.getLabels());
    
    for (String key : node.getProperties().keySet()) {
      neo4jNode.setProperty(key, node.getProperties().get(key));
    }
    
    return neo4jNode.getId();
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createRelationship(org.neo4art.graphdb.Relationship)
   */
  @Override
  public long createRelationship(Relationship relationship) {
    
    return 0;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createIndex(org.neo4art.graphdb.indexes.IndexType, java.lang.String)
   */
  @Override
  public IndexDefinition createSchemaIndex(Label label, String propertyKey) {
    
    IndexDefinition indexDefinition;
    
    try (GraphDatabaseTransaction tx = getTransactionManager()) {
      
      indexDefinition = graphDatabaseService.schema().indexFor(label).on(propertyKey).create();
      
      tx.success();
    }
    
    return indexDefinition;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#getTransactionManager()
   */
  @Override
  public GraphDatabaseTransaction getTransactionManager() {

    return new TransactionWrapper(graphDatabaseService);
  }
}
