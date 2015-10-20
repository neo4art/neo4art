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

package deprecated;

import java.util.List;

import org.neo4art.graphdb.Node;
import org.neo4art.graphdb.Relationship;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Apr 2015
 */
@Deprecated
public class Graph
{
  private List<Node> nodes;
  
  private List<Relationship> relationships;
  
  public Graph()
  {
  }
  
  public Graph(List<Node> nodes, List<Relationship> relationships)
  {
    this.nodes = nodes;
    this.relationships = relationships;
  }

  public List<Node> getNodes()
  {
    return nodes;
  }

  public void setNodes(List<Node> nodes)
  {
    this.nodes = nodes;
  }

  public List<Relationship> getRelationships()
  {
    return relationships;
  }

  public void setRelationships(List<Relationship> relationships)
  {
    this.relationships = relationships;
  }
}