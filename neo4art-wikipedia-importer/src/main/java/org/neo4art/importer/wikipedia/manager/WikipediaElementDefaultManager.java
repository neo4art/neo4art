/**
bnnhhhhgy * Copyright 2015 the original author or authors.
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

package org.neo4art.importer.wikipedia.manager;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship;
import org.neo4art.importer.wikipedia.repository.WikipediaDefaultRepository;
import org.neo4art.importer.wikipedia.repository.WikipediaRepository;

/**
 * @author Lorenzo Speranzoni
 * @since 18 Oct 2015
 */
public class WikipediaElementDefaultManager implements WikipediaElementManager {

  /**
   * @see org.neo4art.importer.wikipedia.manager.WikipediaElementManager#createNodes(org.neo4art.importer.wikipedia.domain.WikipediaElement)
   */
  @Override
  public long createNodes(WikipediaElement wikipediaElement) {

    WikipediaRepository wikipediaRepository = new WikipediaDefaultRepository();

    return wikipediaRepository.createNodes(wikipediaElement);
  }

  @Override
  public long createRelationships(WikipediaElement wikipediaElement) {

    long newRelationships = 0;

    WikipediaRepository wikipediaRepository = new WikipediaDefaultRepository();

    if (wikipediaElement.getRedirect() != null) {
      if (wikipediaRepository.createRelationship(wikipediaElement, wikipediaElement.getRedirect(), WikipediaRelationship.REDIRECTS_TO) > 0)
        newRelationships++;
    }

    if (CollectionUtils.isNotEmpty(wikipediaElement.getLinks())) {
      for (WikipediaElement wikipediaReferencedElement : wikipediaElement.getLinks()) {
        if (wikipediaRepository.createRelationship(wikipediaElement, wikipediaReferencedElement, WikipediaRelationship.REFERS) > 0)
          newRelationships++;
      }
    }

    if (CollectionUtils.isNotEmpty(wikipediaElement.getCategories())) {
      for (WikipediaCategory wikipediaCategory : wikipediaElement.getCategories()) {
        if (wikipediaRepository.createRelationship(wikipediaElement, wikipediaCategory, WikipediaRelationship.BELONGS_TO) > 0)
          newRelationships++;
      }
    }

    return newRelationships;
  }
}