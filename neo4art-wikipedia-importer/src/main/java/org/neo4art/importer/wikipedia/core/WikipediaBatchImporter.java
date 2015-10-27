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

import info.bliki.wiki.dump.WikiXMLParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.importer.wikipedia.core.listener.WikipediaImporterListener;
import org.neo4art.importer.wikipedia.core.listener.WikipediaNodesBatchImporterListener;
import org.neo4art.importer.wikipedia.core.listener.WikipediaRelsBatchImporterListener;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.xml.sax.SAXException;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaBatchImporter implements WikipediaImporter {
  
  private static Log logger     = LogFactory.getLog(WikipediaBatchImporter.class);

  static final int   BATCH_SIZE = System.getenv("WIKIPEDIA_IMPORT_BATCH_SIZE") != null ? Integer.parseInt(System.getenv("WIKIPEDIA_IMPORT_BATCH_SIZE")) : 10_000;

  @Override
  public long importOrUpdateDump(File dumpFile) throws IOException, SAXException, ParserConfigurationException {
    logger.info("Wikipedia dump file import started...");

    long dumpImportStartDate = Calendar.getInstance().getTimeInMillis();

    long newNodes = 0;
    long newRelationships = 0;

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.BATCH_INSERTER);

    logger.info("Configuration: ");
    logger.info("------------------------------------------------");
    logger.info("Batch size " + BATCH_SIZE);
    logger.info("Store directory is " + graphDatabaseConnectionManager.getStoreDir());

    {
      WikipediaImporterListener wikipediaNodesImporterListener = new WikipediaNodesBatchImporterListener();
      wikipediaNodesImporterListener.setBatchSize(BATCH_SIZE);
      long parserForNodesStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForNodes = new WikiXMLParser(dumpFile, wikipediaNodesImporterListener);
      parserForNodes.parse();
      long parserForNodesEndDate = Calendar.getInstance().getTimeInMillis();
      wikipediaNodesImporterListener.flush();
      newNodes = wikipediaNodesImporterListener.getGraphCount();
      logger.info("Done! " + newNodes + " nodes created in " + (parserForNodesEndDate - parserForNodesStartDate) + " ms.");
    }

    {
      WikipediaImporterListener wikipediaRelsImporterListener = new WikipediaRelsBatchImporterListener();
      wikipediaRelsImporterListener.setBatchSize(BATCH_SIZE);
      long parserForRelsStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForRels = new WikiXMLParser(dumpFile, wikipediaRelsImporterListener);
      parserForRels.parse();
      long parserForRelsEndDate = Calendar.getInstance().getTimeInMillis();
      wikipediaRelsImporterListener.flush();
      newRelationships = wikipediaRelsImporterListener.getGraphCount();
      logger.info("Done! " + newRelationships + " relationships created in " + (parserForRelsEndDate - parserForRelsStartDate) + " ms.");
    }

    {
      long indexCreationStartDate = Calendar.getInstance().getTimeInMillis();
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
      long indexCreationEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Indexes created in " + (indexCreationEndDate - indexCreationStartDate)  + " ms.");
    }

    {
      long shutdownStartDate = Calendar.getInstance().getTimeInMillis();
      graphDatabaseConnectionManager.close();
      long shutdownEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Shutdown completed in " + (shutdownEndDate - shutdownStartDate) + " ms.");
    }

    long dumpImportEndDate = Calendar.getInstance().getTimeInMillis();
    logger.info("Wikipedia dump file import completed in " + (dumpImportEndDate - dumpImportStartDate) + " ms.");

    return newNodes + newRelationships;
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("java -cp neo4art-wikipedia-importer-<version>.jar org.neo4art.importer.wikipedia.core.WikipediaBatchImporter /path/to/wikipedia-dump.xml");
    }

    File wikipediaDump = new File(args[0]);

    if (!wikipediaDump.exists()) {
      throw new RuntimeException(new FileNotFoundException("File " + wikipediaDump.getAbsolutePath() + " not found."));
    }

    try {
      new WikipediaBatchImporter().importOrUpdateDump(wikipediaDump);
    }
    catch (Exception e) {
      throw new RuntimeException("Import failed: " + e.getMessage() + ".", e);
    }
  }
}