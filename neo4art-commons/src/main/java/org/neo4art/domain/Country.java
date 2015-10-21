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

package org.neo4art.domain;

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Apr 2015
 */
public class Country implements Node {
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Country };

  private Long                 nodeId;

  private String               name;
  private String               nativeName;
  private String               commonName;
  private String               conventionalLongName;
  private String               type;
  
  private Coordinate           coordinate;

  public Country() {
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  public String getNativeName() {
    return nativeName;
  }

  public void setNativeName(String nativeName) {
    this.nativeName = nativeName;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getConventionalLongName() {
    return conventionalLongName;
  }

  public void setConventionalLongName(String conventionalLongName) {
    this.conventionalLongName = conventionalLongName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  @Override
  public Long getNodeId() {
    return this.nodeId;
  }

  @Override
  public void setNodeId(long nodeId) {
    this.nodeId = nodeId;
  }

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<String, Object>();

    if (this.name != null) {
      properties.put("cname", this.name);
    }
    if (this.commonName != null) {
      properties.put("commonName", this.commonName);
    }
    if (this.nativeName != null) {
      properties.put("nativeName", this.nativeName);
    }
    if (this.conventionalLongName != null) {
      properties.put("conventionalLongName", this.conventionalLongName);
    }
    if (this.type != null) {
      properties.put("type", this.type);
    }

    return properties;
  }

  @Override
  public Label[] getLabels() {
    return LABELS;
  }
}