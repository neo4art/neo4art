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

import java.util.HashMap;
import java.util.Map;

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
public class Neo4ArtBatchInserterSingleton extends Neo4ArtGraphDatabase {

  private static BatchInserter              batchInserter;
  private static BatchInserterIndex         wikipediaBatchInserterIndex;
  private static BatchInserterIndexProvider batchInserterIndexProvider;
  
  /**
   * 
   * @return
   */
  public static BatchInserter getBatchInserterInstance() {
  
    if (batchInserter == null) {
      
      Map<String, String> config = new HashMap<>();
      config.put("dbms.pagecache.memory", "16G" );
      
      batchInserter = BatchInserters.inserter(NEO4J_STORE_DIR, config);
    }
    
    return batchInserter;
  }

  /**
   * 
   * @return
   */
  public static BatchInserterIndexProvider getBatchInserterIndexProviderInstance() {
    
    if (batchInserterIndexProvider == null) {
      if (batchInserter == null) {
        getBatchInserterInstance();
      }

      batchInserterIndexProvider = new LuceneBatchInserterIndexProvider(batchInserter);
    }
    
    return batchInserterIndexProvider;
  }    
  
  /**
   * 
   * @return
   */
  public static BatchInserterIndex getWikipediaBatchInserterIndexInstance() {
      
    if (wikipediaBatchInserterIndex == null) {
      if (batchInserter == null) {
        getBatchInserterInstance();
      }
      
      if (batchInserterIndexProvider == null) {
        getBatchInserterIndexProviderInstance();
      }
      
      wikipediaBatchInserterIndex = batchInserterIndexProvider.nodeIndex("wikipedia-title", MapUtil.stringMap("type", "exact"));
      wikipediaBatchInserterIndex.setCacheCapacity("hashcode", 20_000_000);
    }
    
    return wikipediaBatchInserterIndex;
  }
}
