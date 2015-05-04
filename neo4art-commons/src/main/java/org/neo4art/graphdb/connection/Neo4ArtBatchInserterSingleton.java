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

import org.neo4art.graphdb.Neo4ArtLegacyIndex;
import org.neo4art.graphdb.Neo4ArtLegacyIndexType;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserterIndex;
import org.neo4j.unsafe.batchinsert.BatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.BatchInserters;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class Neo4ArtBatchInserterSingleton extends Neo4ArtGraphDatabase
{
  private static final Map<String, String> LEGACY_INDEX_CONFIG_TYPE_EXACT                     = MapUtil.stringMap("type", "exact");
  private static final Map<String, String> LEGACY_INDEX_CONFIG_TYPE_FULLTEXT                  = MapUtil.stringMap("type", "fulltext");
  private static final Map<String, String> LEGACY_INDEX_CONFIG_TYPE_FULLTEXT_CASE_INSENSITIVE = MapUtil.stringMap("type", "exact", "to_lower_case", "true");

  private static BatchInserter                   batchInserter;
  private static BatchInserterIndexProvider      batchInserterIndexProvider;

  protected Neo4ArtBatchInserterSingleton()
  {
  }

  /**
   * 
   * @return
   */
  public static void createBatchInserterInstance()
  {
    getBatchInserterInstance();
  }

  /**
   * 
   * @return
   */
  public static void createBatchInserterInstance(Map<String, String> config)
  {
    getBatchInserterInstance(config);
  }

  /**
   * 
   * @return
   */
  public static void shutdownBatchInserterInstance()
  {
    if (batchInserterIndexProvider != null)
    {
      batchInserterIndexProvider.shutdown();
    }
  
    if (batchInserter != null)
    {
      batchInserter.shutdown();
  
      batchInserter = null;
    }
  }

  /**
   * 
   * @return
   * @return
   */
  private static BatchInserter getBatchInserterInstance()
  {
    Map<String, String> config = new HashMap<>();
    config.put("dbms.pagecache.memory", "16G");

    return getBatchInserterInstance(config);
  }

  /**
   * 
   * @return
   * @return
   */
  private static BatchInserter getBatchInserterInstance(Map<String, String> config)
  {
    if (batchInserter == null)
    {
      batchInserter = BatchInserters.inserter(NEO4J_STORE_DIR, config);
    }

    return batchInserter;
  }

  /**
   * 
   * @return
   */
  private static BatchInserterIndexProvider getBatchInserterIndexProviderInstance()
  {
    if (batchInserterIndexProvider == null)
    {
      batchInserterIndexProvider = new LuceneBatchInserterIndexProvider(getBatchInserterInstance());
    }
  
    return batchInserterIndexProvider;
  }

  /**
   * 
   * @param neo4ArtLegacyIndex
   */
  public static void createLegacyNodeIndex(Neo4ArtLegacyIndex neo4ArtLegacyIndex)
  {
    createLegacyNodeIndex(neo4ArtLegacyIndex, null, 0);
  }
  
  /**
   * 
   * @param neo4ArtLegacyIndex
   * @param cacheKey
   * @param cacheSize
   * @return
   */
  public static void createLegacyNodeIndex(Neo4ArtLegacyIndex neo4ArtLegacyIndex, String cacheKey, int cacheSize)
  {
    BatchInserterIndexProvider batchInserterIndexProvider = getBatchInserterIndexProviderInstance();

    BatchInserterIndex index = batchInserterIndexProvider.nodeIndex(neo4ArtLegacyIndex.getName(), getLegacyNodeIndexConfig(neo4ArtLegacyIndex.getType()));

    if (cacheSize != 0)
    {
      index.setCacheCapacity(cacheKey, cacheSize);
    }
  }

  /**
   * TODO it's not necessary to provide key and value: we could also just pass a Neo4ArtNode 'cause we already know the key (defined at index creation time) 
   * 
   * @param indexName
   * @param key
   * @param value
   * @return
   */
  public static IndexHits<Long> getFromLegacyNodeIndex(Neo4ArtLegacyIndex neo4ArtLegacyIndex, String key, Object value)
  {
    BatchInserterIndex batchInserterIndex = getBatchInserterIndexProviderInstance().nodeIndex(neo4ArtLegacyIndex.getName(), getLegacyNodeIndexConfig(neo4ArtLegacyIndex.getType()));
    
    if (batchInserterIndex == null)
    {
      throw new IllegalArgumentException("Index " + neo4ArtLegacyIndex.getName() + " doesn't exists. You must create it before getting something from it.");
    }    

    return batchInserterIndex.get(key, value);
  }

  /**
   * @param indexName 
   * @param node
   */
  public static void addToLegacyNodeIndex(Neo4ArtLegacyIndex neo4ArtLegacyIndex, Neo4ArtNode node)
  {
    BatchInserterIndex batchInserterIndex = getBatchInserterIndexProviderInstance().nodeIndex(neo4ArtLegacyIndex.getName(), getLegacyNodeIndexConfig(neo4ArtLegacyIndex.getType()));
    
    if (batchInserterIndex == null)
    {
      throw new IllegalArgumentException("Index " + neo4ArtLegacyIndex.getName() + " doesn't exists. You must create it before adding something to it.");
    }    
    
    batchInserterIndex.add(node.getNodeId(), node.getProperties());
  }

  /**
   * @param indexName
   */
  public static void flushLegacyNodeIndex(Neo4ArtLegacyIndex neo4ArtLegacyIndex)
  {
    BatchInserterIndex batchInserterIndex = getBatchInserterIndexProviderInstance().nodeIndex(neo4ArtLegacyIndex.getName(), getLegacyNodeIndexConfig(neo4ArtLegacyIndex.getType()));
    
    if (batchInserterIndex != null)
    {
      batchInserterIndex.flush();
    }
  }

  /**
   * @param neo4ArtLegacyIndexType
   * @return
   */
  private static Map<String, String> getLegacyNodeIndexConfig(Neo4ArtLegacyIndexType neo4ArtLegacyIndexType)
  {
    switch (neo4ArtLegacyIndexType)
    {
      case TYPE_EXACT:
        return LEGACY_INDEX_CONFIG_TYPE_EXACT;
        
      case TYPE_FULLTEXT:
        return LEGACY_INDEX_CONFIG_TYPE_FULLTEXT;
        
      case TYPE_FULLTEXT_CASE_INSENSITIVE:
        return LEGACY_INDEX_CONFIG_TYPE_FULLTEXT_CASE_INSENSITIVE;
    }
    
    throw new IllegalArgumentException("Unrecognized index type");
  }

  /**
   * 
   * @param node
   * @return
   */
  public static long createNode(Neo4ArtNode node)
  {
    BatchInserter batchInserter = getBatchInserterInstance();

    long nodeId = batchInserter.createNode(node.getProperties(), node.getLabels());
    
    node.setNodeId(nodeId);
    
    return nodeId;
  }
  
  /**
   * @param nodeId
   * @return
   */
  public static Map<String, Object> getNodeProperties(long nodeId)
  {
    BatchInserter batchInserter = getBatchInserterInstance();
    
    return batchInserter.getNodeProperties(nodeId);
  }

  /**
   * 
   * @param nodeId
   * @param propertyName
   * @param propertyValue
   */
  public static void setNodeProperty(long nodeId, String propertyName, Object propertyValue)
  {
    BatchInserter batchInserter = getBatchInserterInstance();
    
    batchInserter.setNodeProperty(nodeId, propertyName, propertyValue);
  }

  /**
   * 
   * @param properties
   * @param labels
   * @return
   */
  public static long createRelationship(long startNode, long endNode, RelationshipType relationshipType, Map<String, Object> properties)
  {
    BatchInserter batchInserter = getBatchInserterInstance();

    return batchInserter.createRelationship(startNode, endNode, relationshipType, properties);
  }

  /**
   * @return
   */
  public static String getStoreDir()
  {
    BatchInserter batchInserter = getBatchInserterInstance();
    
    return batchInserter.getStoreDir();
  }

  /**
   * @param label
   * @param property
   */
  public static void createDeferredSchemaIndex(Label label, String property)
  {
    BatchInserter batchInserter = getBatchInserterInstance();
    
    batchInserter.createDeferredSchemaIndex(label).on(property).create();
  }
}
