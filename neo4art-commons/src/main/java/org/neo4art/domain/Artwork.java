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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class Artwork implements Node {

  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Artwork };

  private Long                 nodeId;

  private String               title;
  private URL                  url;

  private String               type;
  private String               year;
  private Date                 completionDate;
  private String               catalogue;
  private String               subject;
  private Settlement           city;
  private Museum               museum;

  private String               imageFile;

  private Coordinates          coordinates;

  private Artist               artist;

  public Artwork() {
  }

  public Artwork(String title) {
    setTitle(title);
  }

  @Override
  public Long getNodeId() {
    return this.nodeId;
  }

  @Override
  public void setNodeId(long nodeId) {
    this.nodeId = nodeId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Date getCompletionDate() {
    return completionDate;
  }

  public void setCompletionDate(Date completionDate) {
    this.completionDate = completionDate;
  }

  public String getCatalogue() {
    return catalogue;
  }

  public void setCatalogue(String catalogue) {
    this.catalogue = catalogue;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Settlement getCity() {
    return city;
  }

  public void setCity(Settlement city) {
    this.city = city;
  }

  public Museum getMuseum() {
    return museum;
  }

  public void setMuseum(Museum musuem) {
    this.museum = musuem;
  }

  public String getImageFile() {
    return this.imageFile;
  }

  public void setImageFile(String imageFile) {
    this.imageFile = imageFile;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<String, Object>();

    if (this.title != null) {
      properties.put("title", this.title);
    }
    if (this.url != null) {
      properties.put("url", this.url.toString());
    }
    if (this.type != null) {
      properties.put("type", this.type);
    }
    if (this.year != null) {
      properties.put("year", this.year);
    }
    if (this.completionDate != null) {
      properties.put("completionDate", this.completionDate);
    }
    if (this.catalogue != null) {
      properties.put("catalogue", this.catalogue);
    }
    if (this.subject != null) {
      properties.put("subject", this.subject);
    }
    if (this.city != null && this.city.getName() != null) {
      properties.put("city", this.city.getName());
    }
    if (this.museum != null && this.museum.getName() != null) {
      properties.put("museum", this.museum.getName());
    }
    if (this.coordinates != null) {
      properties.put("lat", this.coordinates.getLatitude());
      properties.put("lon", this.coordinates.getLongitude());
    }

    return properties;
  }

  @Override
  public Label[] getLabels() {
    return LABELS;
  }
}
