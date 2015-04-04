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

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graph.WikipediaLabel;
import org.neo4art.graph.WikipediaRelationship;
import org.neo4art.graph.util.Neo4ArtBatchInserterSingleton;
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
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserterIndex;

/**
 * @author Lorenzo Speranzoni
 * @since 3 Apr 2015
 */
public class WikipediaBatchInserterRepository implements WikipediaRepository {

  @Override
  public long addArtistPage(WikipediaArtistPage wikipediaArtistPage) {
    return addNode(wikipediaArtistPage);
  }

  @Override
  public long addArtworkPage(WikipediaArtworkPage wikipediaArtworkPage) {
    return addNode(wikipediaArtworkPage);
  }

  @Override
  public long addArtMovementPage(WikipediaArtMovementPage wikipediaArtMovementPage) {
    return addNode(wikipediaArtMovementPage);
  }
  
  @Override
  public long addMonumentPage(WikipediaMonumentPage wikipediaMonumentPage) {
    return addNode(wikipediaMonumentPage);
  }

  @Override
  public long addMuseumPage(WikipediaMuseumPage wikipediaMuseumPage) {
    return addNode(wikipediaMuseumPage);
  }
  
  @Override
  public long addReligiousBuildingPage(WikipediaReligiousBuildingPage wikipediaReligiousBuildingPage) {
    return addNode(wikipediaReligiousBuildingPage);
  }

  @Override
  public long addSettlementPage(WikipediaSettlementPage wikipediaSettlementPage) {
    return addNode(wikipediaSettlementPage);
  }
  
  @Override
  public long addCountryPage(WikipediaCountryPage wikipediaCountryPage) {
    return addNode(wikipediaCountryPage);
  }
  
  @Override
  public long addPage(WikipediaPage wikipediaPage) {
    return addNode(wikipediaPage);
  }

  @Override
  public long addCategory(WikipediaCategory wikipediaCategory) {
    return addNode(wikipediaCategory);
  }

  @Override
  public long addFile(WikipediaFile wikipediaFile) {
    return addNode(wikipediaFile);
  }

  @Override
  public long addProject(WikipediaProject wikipediaProject) {
    return addNode(wikipediaProject);
  }

  @Override
  public long addTemplate(WikipediaTemplate wikipediaTemplate) {
    return addNode(wikipediaTemplate);
  }

  @Override
  public long addGeneric(WikipediaGeneric wikipediaGeneric) {
    return addNode(wikipediaGeneric);
  }

  private long addNode(WikipediaElement wikipediaElement) {
    
    BatchInserter      batchInserter      = Neo4ArtBatchInserterSingleton.getBatchInserterInstance();
    BatchInserterIndex batchInserterIndex = Neo4ArtBatchInserterSingleton.getWikipediaBatchInserterIndexInstance();
    
    Map<String, Object> properties = new HashMap<String, Object>();
    
    properties.put("id"       , wikipediaElement.getId());
    properties.put("title"    , wikipediaElement.getTitle());
    properties.put("revision" , wikipediaElement.getRevision());
    properties.put("timestamp", wikipediaElement.getTimestamp());
    properties.put("hashcode" , wikipediaElement.getHashCode());
    
    System.out.println(properties.get("hashcode"));
    
    long node = batchInserter.createNode(properties, WikipediaLabel.Wikipedia, wikipediaElement.getLabel());
    
    batchInserterIndex.add(node, properties);
    
    return 1;
  }

  @Override
  public long addRelationship(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo, WikipediaRelationship wikipediaRelationship) {
    
    BatchInserter      batchInserter      = Neo4ArtBatchInserterSingleton.getBatchInserterInstance();
    BatchInserterIndex batchInserterIndex = Neo4ArtBatchInserterSingleton.getWikipediaBatchInserterIndexInstance();
    
    IndexHits<Long> nodeIdFrom = batchInserterIndex.get("title", wikipediaElementFrom.getTitle());
    IndexHits<Long> nodeIdTo   = batchInserterIndex.get("title", wikipediaElementTo  .getTitle());
    
    if (nodeIdFrom.hasNext() && nodeIdTo.hasNext()) {
      batchInserter.createRelationship(nodeIdFrom.next(), nodeIdTo.next(), wikipediaRelationship, null);
      return 1;
    }
    
    return 0;
  }

  @Override
  public void createDeferredIndexes() {
    
    BatchInserter batchInserter = Neo4ArtBatchInserterSingleton.getBatchInserterInstance();

    for (WikipediaLabel wikipediaLabel : WikipediaLabel.values()) {
      batchInserter.createDeferredSchemaIndex(wikipediaLabel).on("title").create();
    }
  }
}
