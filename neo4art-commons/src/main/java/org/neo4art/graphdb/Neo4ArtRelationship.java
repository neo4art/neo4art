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

import java.util.Map;

import org.neo4j.graphdb.RelationshipType;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Apr 2015
 */
public class Neo4ArtRelationship
{
  Neo4ArtNode         startNode;
  Neo4ArtNode         endNode;

  RelationshipType    relationshipType;

  Map<String, Object> properties;

  public Neo4ArtRelationship()
  {
    this.startNode = null;
    this.endNode = null;
    this.relationshipType = null;
    this.properties = null;
  }

  public Neo4ArtRelationship(Neo4ArtNode startNode, Neo4ArtNode endNode, RelationshipType relationshipType, Map<String, Object> properties)
  {
    this.startNode = startNode;
    this.endNode = endNode;
    this.relationshipType = relationshipType;
    this.properties = properties;
  }

  public Neo4ArtNode getStartNode()
  {
    return startNode;
  }

  public void setStartNode(Neo4ArtNode startNode)
  {
    this.startNode = startNode;
  }

  public Neo4ArtNode getEndNode()
  {
    return endNode;
  }

  public void setEndNode(Neo4ArtNode endNode)
  {
    this.endNode = endNode;
  }

  public RelationshipType getRelationshipType()
  {
    return relationshipType;
  }

  public void setRelationshipType(RelationshipType relationshipType)
  {
    this.relationshipType = relationshipType;
  }

  public Map<String, Object> getProperties()
  {
    return properties;
  }

  public void setProperties(Map<String, Object> properties)
  {
    this.properties = properties;
  }
}
