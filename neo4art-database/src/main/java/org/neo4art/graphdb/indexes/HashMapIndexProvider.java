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

package org.neo4art.graphdb.indexes;

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Index;

/**
 * @author Lorenzo Speranzoni
 * @since 26 Oct 2015
 */
public class HashMapIndexProvider<K, V> {

  private Map<String, Index<K, V>> indexes;
  
  public HashMapIndexProvider() {
    
    this.indexes = new HashMap<String, Index<K, V>>();
  }
  
  /**
   * Returns an {@link Index} with the name {@code indexName}.
   * If such an index doesn't exist it will be created with a default initial capacity.
   * 
   * @param indexName
   * @return
   */
  public Index<K, V> getIndex(String indexName) {
    
    Index<K, V> index = this.indexes.get(indexName);
    
    if (index == null) {
      
      index = new HashMapIndex<K, V>(indexName, HashMapIndex.INITIAL_CAPACITY);
      
      indexes.put(indexName, index);
    }
      
    return index;
  }
}
