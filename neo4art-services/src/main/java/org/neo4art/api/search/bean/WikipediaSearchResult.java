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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lorenzo Speranzoni, Enrico De Benetti
 * @since 29 Apr 2015
 */
public class WikipediaSearchResult {

  private List<WikipediaSearchResultNode>         nodes;
  private List<WikipediaSearchResultRelationship> relationships;

  public WikipediaSearchResult() {
    
    this.nodes = new ArrayList<WikipediaSearchResultNode>();
    this.relationships = new ArrayList<WikipediaSearchResultRelationship>();
  }
  
  public List<WikipediaSearchResultNode> getNodes() {

    return nodes;
  }

  public void setNodes(List<WikipediaSearchResultNode> nodes) {
    
    this.nodes = nodes;
  }
  
  public void addNode(WikipediaSearchResultNode node) {
    
    this.nodes.add(node);
  }
  
  public List<WikipediaSearchResultRelationship> getRelationships() {

    return relationships;
  }

  public void setRelationships(List<WikipediaSearchResultRelationship> relationships) {

    this.relationships = relationships;
  }
  
  public void addRelationship(WikipediaSearchResultRelationship relationship) {
    
    this.relationships.add(relationship);
  }
}
