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
 * @since 11 Oct 2015
 */
public class Book implements Node {

  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Book };

  private Long                 nodeId;

  private String               name;
  private URL                  image;
  private String               author;
  private String               language;
  private String               genre;

  public Book() {
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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
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
    if (this.language != null) {
      properties.put("language", this.language);
    }
    if (this.author != null) {
      properties.put("author", this.author);
    }
    if (this.genre != null) {
      properties.put("genre", this.genre);
    }

    return properties;
  }

  @Override
  public Label[] getLabels() {
    return LABELS;
  }
}
