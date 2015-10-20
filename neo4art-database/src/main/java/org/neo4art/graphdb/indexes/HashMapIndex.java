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
 * @since 18 Oct 2015
 */
public class HashMapIndex<K, V> implements Index<K, V> {

  private String    name;
  private Map<K, V> index;

  public HashMapIndex(String name) {
    this.name = name;
    this.index = new HashMap<K, V>();
  }

  public HashMapIndex(String name, int initialCapacity) {
    this.name = name;
    this.index = new HashMap<K, V>(initialCapacity);
  }

  /**
   * @see org.neo4art.graphdb.Index#getName()
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * @see org.neo4art.graphdb.Index#add(java.lang.Object, java.lang.Object)
   */
  @Override
  public void add(K key, V value) {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null.");
    if (value == null)
      throw new IllegalArgumentException("value cannot be null.");
    
    this.index.put(key, value);
  }

  /**
   * @see org.neo4art.graphdb.Index#size()
   */
  @Override
  public int size() {
    return this.index.size();
  }

  /**
   * @see org.neo4art.graphdb.Index#clear()
   */
  @Override
  public void clear() {
    this.index.clear();
  }

  /**
   * @see org.neo4art.graphdb.Index#get(java.lang.Object)
   */
  @Override
  public V get(K key) {
    return this.index.get(key);
  }
}