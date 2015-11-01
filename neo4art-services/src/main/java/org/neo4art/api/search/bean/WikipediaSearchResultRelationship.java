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
package org.neo4art.api.search.bean;

/**
 *
 * @author Lorenzo Speranzoni, Enrico De Benetti
 * @since 29 Apr 2015
 */
public class WikipediaSearchResultRelationship {

  private long   id;

  private long   source;
  private long   target;
  private int    value;
  private int    weight;
  private String linkName;

  public WikipediaSearchResultRelationship() {
    this.value = 1;
    this.weight = 1;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getSource() {
    return source;
  }

  public void setSource(long source) {
    this.source = source;
  }

  public long getTarget() {
    return target;
  }

  public void setTarget(long target) {
    this.target = target;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getLinkName() {
    return linkName;
  }

  public void setLinkName(String linkName) {
    this.linkName = linkName;
  }
}
