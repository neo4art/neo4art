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
package org.neo4art.importer.wikipedia.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.neo4j.graphdb.Label;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public abstract class WikipediaAbstractElement implements WikipediaElement {

  private Long                   nodeId;

  private Long                   id;
  private String                 title;
  private Long                   revision;
  private Long                   timestamp;

  private WikipediaElement       redirect;

  private Set<WikipediaElement>  links;
  private Set<WikipediaCategory> categories;

  protected WikipediaAbstractElement() {
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
  public Long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public Long getRevision() {
    return revision;
  }

  @Override
  public void setRevision(long revision) {
    this.revision = revision;
  }

  @Override
  public Long getTimestamp() {
    return timestamp;
  }

  @Override
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public WikipediaElement getRedirect() {
    return redirect;
  }

  public void setRedirect(WikipediaElement redirect) {
    this.redirect = redirect;
  }

  @Override
  public Set<WikipediaElement> getLinks() {
    return links;
  }

  @Override
  public boolean addLink(WikipediaElement wikipediaElement) {

    if (CollectionUtils.isEmpty(this.links)) {
      this.links = new HashSet<WikipediaElement>();
    }

    return this.links.add(wikipediaElement);
  }

  @Override
  public void setLinks(Set<WikipediaElement> links) {
    this.links = links;
  }

  @Override
  public Set<WikipediaCategory> getCategories() {
    return categories;
  }

  @Override
  public boolean addCategory(WikipediaCategory wikipediaCategory) {

    if (CollectionUtils.isEmpty(categories)) {
      this.categories = new HashSet<WikipediaCategory>();
    }

    return this.categories.add(wikipediaCategory);
  }

  @Override
  public void setCategories(Set<WikipediaCategory> categories) {
    this.categories = categories;
  }

  @Override
  public abstract WikipediaType getType();

  @Override
  public String[] getLinksAsArray() {

    if (CollectionUtils.isNotEmpty(this.links)) {

      List<String> result = new ArrayList<String>();

      Iterator<WikipediaElement> iterator = this.links.iterator();

      while (iterator.hasNext()) {
        result.add(iterator.next().getTitle());
      }

      return result.toArray(new String[result.size()]);
    }

    return null;
  }

  @Override
  public String[] getCategoriesAsArray() {

    if (CollectionUtils.isNotEmpty(this.categories)) {

      List<String> result = new ArrayList<String>();

      Iterator<WikipediaCategory> iterator = this.categories.iterator();

      while (iterator.hasNext()) {
        result.add(iterator.next().getTitle());
      }

      return result.toArray(new String[result.size()]);
    }

    return null;
  }

  @Override
  public Map<String, Object> getProperties() {

    Map<String, Object> properties = new HashMap<String, Object>();

    if (this.id != null) {
      properties.put("id", this.id);
    }
    if (this.title != null) {
      properties.put("title", this.title);
    }
    if (this.revision != null) {
      properties.put("revision", this.revision);
    }
    if (this.timestamp != null) {
      properties.put("timestamp", this.timestamp);
    }

    return properties;
  }

  @Override
  public abstract Label[] getLabels();
}
