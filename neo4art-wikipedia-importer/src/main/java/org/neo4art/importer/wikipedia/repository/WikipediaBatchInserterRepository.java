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

import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.importer.wikipedia.domain.WikipediaArtMovementPage;
import org.neo4art.importer.wikipedia.domain.WikipediaArtistPage;
import org.neo4art.importer.wikipedia.domain.WikipediaArtworkPage;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaCountryPage;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaFile;
import org.neo4art.importer.wikipedia.domain.WikipediaGeneric;
import org.neo4art.importer.wikipedia.domain.WikipediaMonumentPage;
import org.neo4art.importer.wikipedia.domain.WikipediaMuseumPage;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaProject;
import org.neo4art.importer.wikipedia.domain.WikipediaReligiousBuildingPage;
import org.neo4art.importer.wikipedia.domain.WikipediaSettlementPage;
import org.neo4art.importer.wikipedia.domain.WikipediaTemplate;
import org.neo4art.importer.wikipedia.graphdb.WikipediaIndexManager;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship;
import org.neo4j.graphdb.index.IndexHits;

/**
 * @author Lorenzo Speranzoni
 * @since 3 Apr 2015
 */
public class WikipediaBatchInserterRepository implements WikipediaRepository
{
  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addArtistPage(org.neo4art.importer.wikipedia.domain.WikipediaArtistPage)
   */
  @Override
  public long addArtistPage(WikipediaArtistPage wikipediaArtistPage)
  {
    long wikipediaArtistNodeId = addWikipediaNode(wikipediaArtistPage);

    long artistNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaArtistPage.getArtist());

    Neo4ArtBatchInserterSingleton.createRelationship(artistNodeId, wikipediaArtistNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addArtworkPage(org.neo4art.importer.wikipedia.domain.WikipediaArtworkPage)
   */
  @Override
  public long addArtworkPage(WikipediaArtworkPage wikipediaArtworkPage)
  {
    long wikipediaArtworkNodeId = addWikipediaNode(wikipediaArtworkPage);

    long artworkNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaArtworkPage.getArtwork());

    Neo4ArtBatchInserterSingleton.createRelationship(artworkNodeId, wikipediaArtworkNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    // TODO connect artwork to artist

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addArtMovementPage(org.neo4art.importer.wikipedia.domain.WikipediaArtMovementPage)
   */
  @Override
  public long addArtMovementPage(WikipediaArtMovementPage wikipediaArtMovementPage)
  {
    long wikipediaArtMovementNodeId = addWikipediaNode(wikipediaArtMovementPage);

    long artMovementNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaArtMovementPage.getArtMovement());

    Neo4ArtBatchInserterSingleton.createRelationship(artMovementNodeId, wikipediaArtMovementNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addMonumentPage(org.neo4art.importer.wikipedia.domain.WikipediaMonumentPage)
   */
  @Override
  public long addMonumentPage(WikipediaMonumentPage wikipediaMonumentPage)
  {
    long wikipediaMonumentNodeId = addWikipediaNode(wikipediaMonumentPage);

    long monumentNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaMonumentPage.getMonument());

    Neo4ArtBatchInserterSingleton.createRelationship(monumentNodeId, wikipediaMonumentNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addMuseumPage(org.neo4art.importer.wikipedia.domain.WikipediaMuseumPage)
   */
  @Override
  public long addMuseumPage(WikipediaMuseumPage wikipediaMuseumPage)
  {
    long wikipediaMuseumNodeId = addWikipediaNode(wikipediaMuseumPage);

    long museumNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaMuseumPage.getMuseum());

    Neo4ArtBatchInserterSingleton.createRelationship(museumNodeId, wikipediaMuseumNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addReligiousBuildingPage(org.neo4art.importer.wikipedia.domain.WikipediaReligiousBuildingPage)
   */
  @Override
  public long addReligiousBuildingPage(WikipediaReligiousBuildingPage wikipediaReligiousBuildingPage)
  {
    long wikipediaReligiousBuildingNodeId = addWikipediaNode(wikipediaReligiousBuildingPage);

    long religiousBuildingNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaReligiousBuildingPage.getReligiousBuilding());

    Neo4ArtBatchInserterSingleton.createRelationship(religiousBuildingNodeId, wikipediaReligiousBuildingNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addSettlementPage(org.neo4art.importer.wikipedia.domain.WikipediaSettlementPage)
   */
  @Override
  public long addSettlementPage(WikipediaSettlementPage wikipediaSettlementPage)
  {
    long wikipediaSettlementNodeId = addWikipediaNode(wikipediaSettlementPage);

    long settlementNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaSettlementPage.getSettlement());

    Neo4ArtBatchInserterSingleton.createRelationship(settlementNodeId, wikipediaSettlementNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addCountryPage(org.neo4art.importer.wikipedia.domain.WikipediaCountryPage)
   */
  @Override
  public long addCountryPage(WikipediaCountryPage wikipediaCountryPage)
  {
    long wikipediaCountryNodeId = addWikipediaNode(wikipediaCountryPage);

    long countryNodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaCountryPage.getCountry());

    Neo4ArtBatchInserterSingleton.createRelationship(countryNodeId, wikipediaCountryNodeId, WikipediaRelationship.DOCUMENTED_IN, null);

    return 3;
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addPage(org.neo4art.importer.wikipedia.domain.WikipediaPage)
   */
  @Override
  public long addPage(WikipediaPage wikipediaPage)
  {
    return addWikipediaNode(wikipediaPage);
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addCategory(org.neo4art.importer.wikipedia.domain.WikipediaCategory)
   */
  @Override
  public long addCategory(WikipediaCategory wikipediaCategory)
  {
    return addWikipediaNode(wikipediaCategory);
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addFile(org.neo4art.importer.wikipedia.domain.WikipediaFile)
   */
  @Override
  public long addFile(WikipediaFile wikipediaFile)
  {
    return addWikipediaNode(wikipediaFile);
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addProject(org.neo4art.importer.wikipedia.domain.WikipediaProject)
   */
  @Override
  public long addProject(WikipediaProject wikipediaProject)
  {
    return addWikipediaNode(wikipediaProject);
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addTemplate(org.neo4art.importer.wikipedia.domain.WikipediaTemplate)
   */
  @Override
  public long addTemplate(WikipediaTemplate wikipediaTemplate)
  {
    return addWikipediaNode(wikipediaTemplate);
  }

  /**
   * @see org.neo4art.importer.wikipedia.repository.WikipediaRepository#addGeneric(org.neo4art.importer.wikipedia.domain.WikipediaGeneric)
   */
  @Override
  public long addGeneric(WikipediaGeneric wikipediaGeneric)
  {
    return addWikipediaNode(wikipediaGeneric);
  }

  private long addWikipediaNode(WikipediaElement wikipediaElement)
  {
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(WikipediaIndexManager.WIKIPEDIA_LEGACY_INDEX, "hashcode", 20_000_000);

    long nodeId = Neo4ArtBatchInserterSingleton.createNode(wikipediaElement);

    Neo4ArtBatchInserterSingleton.addToLegacyNodeIndex(WikipediaIndexManager.WIKIPEDIA_LEGACY_INDEX, wikipediaElement);

    return nodeId;
  }

  @Override
  public long addRelationship(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo, WikipediaRelationship wikipediaRelationship)
  {
    IndexHits<Long> nodeIdFrom = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(WikipediaIndexManager.WIKIPEDIA_LEGACY_INDEX, "hashcode", wikipediaElementFrom.getHashCode());
    IndexHits<Long> nodeIdTo   = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(WikipediaIndexManager.WIKIPEDIA_LEGACY_INDEX, "hashcode", wikipediaElementTo.getHashCode());

    if (nodeIdFrom.hasNext() && nodeIdTo.hasNext())
    {
      Neo4ArtBatchInserterSingleton.createRelationship(nodeIdFrom.next(), nodeIdTo.next(), wikipediaRelationship, null);
      
      return 1;
    }

    return 0;
  }

  @Override
  public void createDeferredIndexes()
  {
    for (WikipediaLabel wikipediaLabel : WikipediaLabel.values())
    {
      Neo4ArtBatchInserterSingleton.createDeferredSchemaIndex(wikipediaLabel, "title");
    }
  }
}
