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

package org.neo4art.importer.wikipedia.service;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.graph.WikipediaRelationship;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.repository.WikipediaGraphDatabaseServiceRepository;
import org.neo4art.importer.wikipedia.repository.WikipediaRepository;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * @author Lorenzo Speranzoni
 * @since 22 Mar 2015
 */
abstract class WikipediaAbstractElementManager implements WikipediaElementManager {

  protected WikipediaAbstractElementManager() {
  }
  
  @Override
  public long createRelationships(GraphDatabaseService graphDatabaseService, WikipediaElement wikipediaElement) {

    long newRelationships = 0;
    
    WikipediaRepository wikipediaRepository = new WikipediaGraphDatabaseServiceRepository(graphDatabaseService);
  
    if (CollectionUtils.isNotEmpty(wikipediaElement.getLinks())) {
      for (WikipediaElement wikipediaReferencedElement : wikipediaElement.getLinks()) {
        if (wikipediaRepository.addRelationship(wikipediaElement, wikipediaReferencedElement, WikipediaRelationship.REFERS) >= 0)
          newRelationships++;
      }
    }
    
    if (CollectionUtils.isNotEmpty(wikipediaElement.getCategories())) {
      for (WikipediaCategory wikipediaCategory : wikipediaElement.getCategories()) {
        if (wikipediaRepository.addRelationship(wikipediaElement, wikipediaCategory, WikipediaRelationship.BELONGS_TO) >= 0)
          newRelationships++;
      }
    }
    
    return newRelationships;
  }
}
