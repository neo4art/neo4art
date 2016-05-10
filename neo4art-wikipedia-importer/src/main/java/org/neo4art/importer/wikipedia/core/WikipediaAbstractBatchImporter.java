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
package org.neo4art.importer.wikipedia.core;

import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 10.05.2016
 */
abstract class WikipediaAbstractBatchImporter implements WikipediaImporter {

	protected void createIndexes(GraphDatabaseConnectionManager graphDatabaseConnectionManager) {
		
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.Wikipedia, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaArtistPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaArtMovementPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaArtworkPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaBookPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaColourPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaCountryPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaDocumentPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaMonumentPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaMuseumPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaReligiousBuildingPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaSettlementPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaFile, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaCategory, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaProject, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaTemplate, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaDisambiguationPage, "title");
      graphDatabaseConnectionManager.createSchemaIndex(WikipediaLabel.WikipediaRedirectPage, "title");
      
  }
}