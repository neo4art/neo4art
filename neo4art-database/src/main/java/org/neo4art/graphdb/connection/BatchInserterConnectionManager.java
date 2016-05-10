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
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.Node;
import org.neo4art.graphdb.Relationship;
import org.neo4art.graphdb.transaction.DummyTransaction;
import org.neo4art.graphdb.transaction.GraphDatabaseTransaction;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

/**
 * @author Lorenzo Speranzoni
 * @since 14 Oct 2015
 */
class BatchInserterConnectionManager implements GraphDatabaseConnectionManager {
  
  private static Log logger = LogFactory.getLog(BatchInserterConnectionManager.class);
  
  private static BatchInserter                    batchInserter;

  public static final Map<String, String>         DEFAULT_GRAPHDB_CONFIG;

  static {
    
    DEFAULT_GRAPHDB_CONFIG = new HashMap<String, String>();
    DEFAULT_GRAPHDB_CONFIG.put("dbms.pagecache.memory", "16G");
  }

  protected BatchInserterConnectionManager() {
    
    newBatchInserterInstance(new File(NEO4J_STORE_DIR), DEFAULT_GRAPHDB_CONFIG);
  }

  protected BatchInserterConnectionManager(File storeDir) {
    
    newBatchInserterInstance(storeDir, DEFAULT_GRAPHDB_CONFIG);
  }

  protected BatchInserterConnectionManager(File storeDir, Map<String, String> config) {
    
    newBatchInserterInstance(storeDir, config);
  }

  private void newBatchInserterInstance(File storeDir, Map<String, String> config) {
    
    if (batchInserter == null) {
      try {
        batchInserter = BatchInserters.inserter(storeDir, config);
        logger.info("Batch inserter instance created. Neo4j store directory = " + storeDir);
      }
      catch (Exception e) {
        logger.error("Error creating batch inserter in store dir " + storeDir);
      }
    }    
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#getStoreDir()
   */
  @Override
  public String getStoreDir() {
    
    return batchInserter.getStoreDir();
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#close()
   */
  @Override
  public void close() {
    
    batchInserter.shutdown();
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createNode(org.neo4art.graphdb.Node)
   */
  @Override
  public long createNode(Node node) {
    
    long nodeId = batchInserter.createNode(node.getProperties(), node.getLabels());
    node.setNodeId(nodeId);
    
    return nodeId;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createRelationship(org.neo4art.graphdb.Relationship)
   */
  @Override
  public long createRelationship(Relationship relationship) {
    
    long relationshipId = batchInserter.createRelationship(relationship.getStartNode().getNodeId(), relationship.getEndNode().getNodeId(), relationship.getRelationshipType(), relationship.getProperties());
    relationship.setRelationshipId(relationshipId);
    
    return relationshipId;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createSchemaIndex(org.neo4j.graphdb.Label, java.lang.String)
   */
  @Override
  public IndexDefinition createSchemaIndex(Label label, String propertyKey) {
    
    return batchInserter.createDeferredSchemaIndex(label).on(propertyKey).create();
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#getTransaction()
   */
  @Override
  public GraphDatabaseTransaction getTransaction() {
    
    return new DummyTransaction();
  }

  /* (non-Javadoc)
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#executeCypherQuery(java.lang.String, java.util.Map)
   */
  @Override
  public Result executeCypherQuery(String query, Map<String, Object> parameters) {
    
    throw new RuntimeException(new IllegalAccessException("Method not allowed for this implementation."));
  }
}
