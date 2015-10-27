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

package org.neo4art.importer.wikipedia.repository;

import org.neo4art.graphdb.Relationship;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.graphdb.WikipediaIndexes;
import org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship;

/**
 * @author Lorenzo Speranzoni
 * @since 15 Oct 2015
 */
public class WikipediaDefaultRepository implements WikipediaRepository {
  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#createNodes(org.neo4art.importer.wikipedia.domain.WikipediaElement)
   */
  @Override
  public long createNodes(WikipediaElement wikipediaElement) {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();

    graphDatabaseConnectionManager.createNode(wikipediaElement);

    switch (wikipediaElement.getType()) {

      case ARTIST_PAGE:
      case ARTWORK_PAGE:
      case ART_MOVEMENT_PAGE:
      case MONUMENT_PAGE:
      case MUSEUM_PAGE:
      case RELIGIOUS_BUILDING_PAGE:
      case SETTLEMENT_PAGE:
      case COUNTRY_PAGE:
      case BOOK_PAGE:
      case DOCUMENT_PAGE:
      case COLOUR_PAGE:
      case PAGE:
        addNodeToWikipediaIndex(wikipediaElement);
        break;

      case CATEGORY:
      case FILE:
      case PROJECT:
      case TEMPLATE:
      case GENERIC:
        break;
    }

    return 1;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#createRelationship(org.neo4art.importer.wikipedia.domain.WikipediaElement, org.neo4art.importer.wikipedia.domain.WikipediaElement,
   *      org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship)
   */
  @Override
  public long createRelationship(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo, WikipediaRelationship wikipediaRelationship) {

    Long wikipediaElementFromId = wikipediaElementFrom.getNodeId();
    Long wikipediaElementToId = wikipediaElementTo.getNodeId();

    try {
      if (wikipediaElementFromId == null) {
        wikipediaElementFromId = (Long) WikipediaIndexes.getInstance().getIndex(WikipediaIndexes.INDEX_FOR_WIKIPEDIA_TITLE).get(wikipediaElementFrom.getTitle());
        if (wikipediaElementFromId != null) {
          wikipediaElementFrom.setNodeId(wikipediaElementFromId);
        }
      }
      if (wikipediaElementToId == null) {
        wikipediaElementToId = (Long) WikipediaIndexes.getInstance().getIndex(WikipediaIndexes.INDEX_FOR_WIKIPEDIA_TITLE).get(wikipediaElementTo.getTitle());
        if (wikipediaElementToId != null) {
          wikipediaElementTo.setNodeId(wikipediaElementToId);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    if (wikipediaElementFromId != null && wikipediaElementToId != null) {
      GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();
      return graphDatabaseConnectionManager.createRelationship(new Relationship(wikipediaElementFrom, wikipediaElementTo, wikipediaRelationship, null));
    }
    else {
      return 0;
    }
  }

  /**
   * 
   * @param wikipediaElement
   * @return
   */
  @Override
  public boolean addNodeToWikipediaIndex(WikipediaElement wikipediaElement) {
    WikipediaIndexes.getInstance().getIndex(WikipediaIndexes.INDEX_FOR_WIKIPEDIA_TITLE).add(wikipediaElement.getTitle(), wikipediaElement.getNodeId());
    return true;
  }
}
