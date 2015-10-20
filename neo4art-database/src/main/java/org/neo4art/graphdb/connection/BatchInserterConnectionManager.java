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

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Index;
import org.neo4art.graphdb.Node;
import org.neo4art.graphdb.Relationship;
import org.neo4art.graphdb.indexes.HashMapIndex;
import org.neo4art.graphdb.indexes.IndexAlreadyExistsException;
import org.neo4art.graphdb.indexes.IndexNotFoundException;
import org.neo4art.graphdb.transaction.DummyTransaction;
import org.neo4art.graphdb.transaction.GraphDatabaseTransaction;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

/**
 * @author Lorenzo Speranzoni
 * @since 14 Oct 2015
 */
class BatchInserterConnectionManager implements GraphDatabaseConnectionManager {
  
  private static BatchInserter                    batchInserter;
  private static Map<String, Index<String, Long>> indexes;

  public static final Map<String, String>         DEFAULT_GRAPHDB_CONFIG;
  public static final int                         DEFAULT_INDEX_INITIAL_CAPACITY = 5_000_000;

  static {
    
    DEFAULT_GRAPHDB_CONFIG = new HashMap<String, String>();
    DEFAULT_GRAPHDB_CONFIG.put("dbms.pagecache.memory", "16G");
  }

  protected BatchInserterConnectionManager() {
    
    newBatchInserterInstance(NEO4J_STORE_DIR, DEFAULT_GRAPHDB_CONFIG);
  }

  protected BatchInserterConnectionManager(String storeDir) {
    
    newBatchInserterInstance(storeDir, DEFAULT_GRAPHDB_CONFIG);
  }

  protected BatchInserterConnectionManager(String storeDir, Map<String, String> config) {
    
    newBatchInserterInstance(storeDir, config);
  }

  private void newBatchInserterInstance(String storeDir, Map<String, String> config) {
    
    if (batchInserter == null) {
      batchInserter = BatchInserters.inserter(storeDir, config);
    }
    
    if (indexes == null) {
      indexes = new HashMap<String, Index<String, Long>>();
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
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#createIndex(java.lang.String)
   */
  @Override
  public Index<String, Long> createIndex(String name) throws IndexAlreadyExistsException {
    
    if (indexes.containsKey(name))
      throw new IndexAlreadyExistsException("Index with name " + name + " already existes.");
    
    Index<String, Long> index = new HashMapIndex<String, Long>(name, DEFAULT_INDEX_INITIAL_CAPACITY);
    indexes.put(name, index);
    
    return index;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#getIndex(java.lang.String)
   */
  @Override
  public Index<String, Long> getIndex(String name) throws IndexNotFoundException {
    
    Index<String, Long> index = indexes.get(name);
    
    if (index == null)
      throw new IndexNotFoundException("Index with name " + name + " doesn't exist.");
    
    return index;
  }

  /**
   * @see org.neo4art.graphdb.connection.GraphDatabaseConnectionManager#getTransactionManager()
   */
  @Override
  public GraphDatabaseTransaction getTransactionManager() {
    
    return new DummyTransaction();
  }
}
