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
public class Relationship {
  
  Long                relationshipId;

  Node                startNode;
  Node                endNode;

  RelationshipType    relationshipType;

  Map<String, Object> properties;

  public Relationship() {
    this.relationshipId = null;
    this.startNode = null;
    this.endNode = null;
    this.relationshipType = null;
    this.properties = null;
  }

  public Relationship(Node startNode, Node endNode, RelationshipType relationshipType, Map<String, Object> properties) {
    this.relationshipId = null;
    this.startNode = startNode;
    this.endNode = endNode;
    this.relationshipType = relationshipType;
    this.properties = properties;
  }

  public Long getRelationshipId() {
    return relationshipId;
  }

  public void setRelationshipId(Long relationshipId) {
    this.relationshipId = relationshipId;
  }

  public Node getStartNode() {
    return startNode;
  }

  public void setStartNode(Node startNode) {
    this.startNode = startNode;
  }

  public Node getEndNode() {
    return endNode;
  }

  public void setEndNode(Node endNode) {
    this.endNode = endNode;
  }

  public RelationshipType getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(RelationshipType relationshipType) {
    this.relationshipType = relationshipType;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }
}
