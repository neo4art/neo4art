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

import java.util.Set;

import org.neo4art.graphdb.Neo4ArtNode;

/**
 * @author Lorenzo Speranzoni
 * @since 19 Mar 2015
 */
public interface WikipediaElement extends Neo4ArtNode
{
  Long getId();

  String getTitle();

  Long getRevision();

  Long getTimestamp();

  int getHashCode();

  void setId(long id);

  void setTitle(String title);

  void setRevision(long revision);

  void setTimestamp(long timestamp);

  WikipediaType getType();

  /**
   * Here's where we have great fun with graph!
   * 
   * @param wikipediaElement
   * @return
   */
  WikipediaElement getRedirect();

  void setRedirect(WikipediaElement redirectText);

  Set<WikipediaElement> getLinks();

  Set<WikipediaCategory> getCategories();

  boolean addLink(WikipediaElement link);

  boolean addCategory(WikipediaCategory category);

  void setLinks(Set<WikipediaElement> links);

  void setCategories(Set<WikipediaCategory> categories);

  String[] getLinksAsArray();

  String[] getCategoriesAsArray();
}
