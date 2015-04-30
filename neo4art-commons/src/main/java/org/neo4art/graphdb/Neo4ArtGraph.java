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

package org.neo4art.graphdb;

import java.util.List;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Apr 2015
 */
public class Neo4ArtGraph
{
  private List<Neo4ArtNode> nodes;
  
  private List<Neo4ArtRelationship> relationships;
  
  public Neo4ArtGraph()
  {
  }
  
  public Neo4ArtGraph(List<Neo4ArtNode> nodes, List<Neo4ArtRelationship> relationships)
  {
    this.nodes = nodes;
    this.relationships = relationships;
  }

  public List<Neo4ArtNode> getNodes()
  {
    return nodes;
  }

  public void setNodes(List<Neo4ArtNode> nodes)
  {
    this.nodes = nodes;
  }

  public List<Neo4ArtRelationship> getRelationships()
  {
    return relationships;
  }

  public void setRelationships(List<Neo4ArtRelationship> relationships)
  {
    this.relationships = relationships;
  }
}
