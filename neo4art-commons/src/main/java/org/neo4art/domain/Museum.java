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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class Museum implements Node {

  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Museum };

  private Long                 nodeId;

  private String               name;
  private URL                  image;
  private String               location;
  private URL                  website;

  private Coordinates          coordinates;

  public Museum() {
  }

  public Museum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public URL getImage() {
    return image;
  }

  public void setImage(URL image) {
    this.image = image;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public URL getWebsite() {
    return website;
  }

  public void setWebsite(URL website) {
    this.website = website;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
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
      properties.put("name", this.name);
    }
    if (this.image != null) {
      properties.put("image", this.image.toString());
    }
    if (this.location != null) {
      properties.put("location", this.location);
    }
    if (this.website != null) {
      properties.put("website", this.website.toString());
    }
    if (this.coordinates != null && this.coordinates.getStatus() == Coordinates.COMPUTED) {
      properties.put("lon", this.coordinates.getLongitude());
      properties.put("lat", this.coordinates.getLatitude());
    }

    return properties;
  }

  @Override
  public Label[] getLabels() {
    return LABELS;
  }
}
