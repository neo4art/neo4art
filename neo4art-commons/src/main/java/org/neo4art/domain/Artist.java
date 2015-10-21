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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class Artist implements Node {

  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Artist };

  private Long                 nodeId;

  private String               name;

  private Date                 birthDate;
  private String               birthPlace;
  private Date                 deathDate;
  private String               deathPlace;

  private String               nationality;

  private List<Artwork>        artworks;
  private List<ArtMovement>    artMovements;

  public Artist() {
  }

  @Override
  public Long getNodeId() {
    return this.nodeId;
  }

  @Override
  public void setNodeId(long nodeId) {
    this.nodeId = nodeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  public Date getDeathDate() {
    return deathDate;
  }

  public void setDeathDate(Date deathDate) {
    this.deathDate = deathDate;
  }

  public String getDeathPlace() {
    return deathPlace;
  }

  public void setDeathPlace(String deathPlace) {
    this.deathPlace = deathPlace;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public List<Artwork> getArtworks() {
    return artworks;
  }

  public void setArtworks(List<Artwork> artworks) {
    this.artworks = artworks;
  }

  public List<ArtMovement> getArtMovements() {
    return artMovements;
  }

  public void setArtMovements(List<ArtMovement> artMovements) {
    this.artMovements = artMovements;
  }

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<String, Object>();

    if (this.name != null) {
      properties.put("name", this.name);
    }
    if (this.birthDate != null) {
      properties.put("birthDate", this.birthDate.getTime());
    }
    if (this.birthPlace != null) {
      properties.put("birthPlace", this.birthPlace);
    }
    if (this.deathDate != null) {
      properties.put("deathDate", this.deathDate.getTime());
    }
    if (this.deathPlace != null) {
      properties.put("deathPlace", this.deathPlace);
    }
    if (this.nationality != null) {
      properties.put("nationality", this.nationality);
    }

    return properties;
  }

  @Override
  public Label[] getLabels() {
    return LABELS;
  }
}
