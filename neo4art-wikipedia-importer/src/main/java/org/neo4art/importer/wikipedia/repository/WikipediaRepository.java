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

import org.neo4art.graph.WikipediaRelationship;
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

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public interface WikipediaRepository {

  long addArtistPage(WikipediaArtistPage wikipediaElement);
  long addArtworkPage(WikipediaArtworkPage wikipediaElement);
  long addArtMovementPage(WikipediaArtMovementPage wikipediaElement);
  long addMuseumPage(WikipediaMuseumPage wikipediaMuseumPage);
  long addMonumentPage(WikipediaMonumentPage wikipediaElement);
  long addReligiousBuildingPage(WikipediaReligiousBuildingPage wikipediaElement);

  long addSettlementPage(WikipediaSettlementPage wikipediaElement);
  long addCountryPage(WikipediaCountryPage wikipediaElement);

  long addPage(WikipediaPage wikipediaPage);
  long addCategory(WikipediaCategory wikipediaElement);
  long addFile(WikipediaFile wikipediaElement);
  long addProject(WikipediaProject wikipediaProject);
  long addTemplate(WikipediaTemplate wikipediaTemplate);
  long addGeneric(WikipediaGeneric wikipediaElement);
  
  long addRelationship(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo, WikipediaRelationship wikipediaRelationship);

  void createDeferredIndexes();
}
