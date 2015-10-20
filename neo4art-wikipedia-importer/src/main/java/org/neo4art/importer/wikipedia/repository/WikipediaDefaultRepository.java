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
import org.neo4art.importer.wikipedia.domain.WikipediaArtElement;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.graphdb.WikipediaIndex;
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

    long nodesAndRelationshipsCreated = 0;

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
        nodesAndRelationshipsCreated += createNodesForWikipiaArtPage((WikipediaArtElement) wikipediaElement);
        break;

      case PAGE:
        nodesAndRelationshipsCreated += createNodesForWikipediaPage((WikipediaPage) wikipediaElement);
        break;

      case CATEGORY:
      case FILE:
      case PROJECT:
      case TEMPLATE:
      case GENERIC:
        nodesAndRelationshipsCreated += createNodesForOtherWikipediaResources(wikipediaElement);
        break;
    }

    return nodesAndRelationshipsCreated;
  }

  /**
   * 
   * @param wikipediaArtistPage
   * @return
   */
  private long createNodesForWikipiaArtPage(WikipediaArtElement wikipediaArtistPage) {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();

    graphDatabaseConnectionManager.createNode(wikipediaArtistPage.getArtNode());
    graphDatabaseConnectionManager.createNode(wikipediaArtistPage);
    graphDatabaseConnectionManager.createRelationship(new Relationship(wikipediaArtistPage.getArtNode(), wikipediaArtistPage, WikipediaRelationship.DOCUMENTED_IN, null));

    addNodeToWikipediaIndex(wikipediaArtistPage);

    return 3;
  }

  /**
   * 
   * @param wikipediaPage
   * @return
   */
  private long createNodesForWikipediaPage(WikipediaPage wikipediaPage) {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();

    graphDatabaseConnectionManager.createNode(wikipediaPage);

    addNodeToWikipediaIndex(wikipediaPage);

    return 1;
  }

  /**
   * 
   * @param wikipediaElement
   * @return
   */
  private long createNodesForOtherWikipediaResources(WikipediaElement wikipediaElement) {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();

    graphDatabaseConnectionManager.createNode(wikipediaElement);

    return 1;
  }

  /**
   * 
   * @param wikipediaElement
   * @return
   */
  @SuppressWarnings("unchecked")
  private boolean addNodeToWikipediaIndex(WikipediaElement wikipediaElement) {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();

    try {
      graphDatabaseConnectionManager.getIndex(WikipediaIndex.INDEX_FOR_WIKIPEDIA_TITLE).add(wikipediaElement.getTitle().toLowerCase(), wikipediaElement.getNodeId());
    }
    catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#createRelationship(org.neo4art.importer.wikipedia.domain.WikipediaElement, org.neo4art.importer.wikipedia.domain.WikipediaElement,
   *      org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship)
   */
  @Override
  @SuppressWarnings("unchecked")
  public long createRelationship(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo, WikipediaRelationship wikipediaRelationship) {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance();

    Long wikipediaElementFromId = wikipediaElementFrom.getNodeId();
    Long wikipediaElementToId = wikipediaElementTo.getNodeId();

    try {
      if (wikipediaElementFromId == null) {
        wikipediaElementFromId = (Long) graphDatabaseConnectionManager.getIndex(WikipediaIndex.INDEX_FOR_WIKIPEDIA_TITLE).get(wikipediaElementFrom.getTitle().toLowerCase());
        if (wikipediaElementFromId != null) {
          wikipediaElementFrom.setNodeId(wikipediaElementFromId);
        }
      }
      if (wikipediaElementToId == null) {
        wikipediaElementToId = (Long) graphDatabaseConnectionManager.getIndex(WikipediaIndex.INDEX_FOR_WIKIPEDIA_TITLE).get(wikipediaElementTo.getTitle().toLowerCase());
        if (wikipediaElementToId != null) {
          wikipediaElementTo.setNodeId(wikipediaElementToId);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    if (wikipediaElementFromId != null && wikipediaElementToId != null) {
      return graphDatabaseConnectionManager.createRelationship(new Relationship(wikipediaElementFrom, wikipediaElementTo, wikipediaRelationship, null));
    }
    else {
      return 0;
    }
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#createDeferredIndexes()
   */
  @Deprecated
  @Override
  public void createDeferredIndexes() {
    throw new RuntimeException(new IllegalAccessException("Method not allowed for this implemetation."));
  }
}
