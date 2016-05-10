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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.importer.wikipedia.core.listener.WikipediaImporterListener;
import org.neo4art.importer.wikipedia.core.listener.WikipediaNodesBatchImporterListener;
import org.neo4art.importer.wikipedia.core.listener.WikipediaRelsBatchImporterListener;
import org.xml.sax.SAXException;

import info.bliki.wiki.dump.WikiXMLParser;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaBufferedBatchImporter extends WikipediaAbstractBatchImporter implements WikipediaImporter {
  
  private static Log logger     = LogFactory.getLog(WikipediaBufferedBatchImporter.class);

  @Override
  public long importDump(File dumpFile) throws IOException, SAXException, ParserConfigurationException {
  	return importDump(dumpFile, null);
  }
  
  @Override
  public long importDump(File dumpFile, File storeDir) throws IOException, SAXException, ParserConfigurationException {
    logger.info("Wikipedia dump file import started...");

    long dumpImportStartDate = Calendar.getInstance().getTimeInMillis();

    long newNodes = 0;
    long newRelationships = 0;

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.BATCH_INSERTER, storeDir);

    logger.info("Configuration: ");
    logger.info("------------------------------------------------");
    logger.info("Batch size " + NumberFormat.getInstance(Locale.ITALY).format(WikipediaImporterListener.BATCH_SIZE));
    logger.info("Store directory is " + graphDatabaseConnectionManager.getStoreDir());
    logger.info("");

    {
      logger.info("Creation of Wikipedia nodes started...");
      WikipediaImporterListener wikipediaNodesImporterListener = new WikipediaNodesBatchImporterListener();
      wikipediaNodesImporterListener.setBatchSize(WikipediaImporterListener.BATCH_SIZE);
      long parserForNodesStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForNodes = new WikiXMLParser(dumpFile, wikipediaNodesImporterListener);
      parserForNodes.parse();
      long parserForNodesEndDate = Calendar.getInstance().getTimeInMillis();
      wikipediaNodesImporterListener.flush();
      newNodes = wikipediaNodesImporterListener.getGraphCount();
      logger.info("Done! " + NumberFormat.getInstance(Locale.ITALY).format(newNodes) + " nodes created in " + (parserForNodesEndDate - parserForNodesStartDate) + " ms.");
    }

    {
      logger.info("Creation of Wikipedia relationships started...");
      WikipediaImporterListener wikipediaRelsImporterListener = new WikipediaRelsBatchImporterListener();
      wikipediaRelsImporterListener.setBatchSize(WikipediaImporterListener.BATCH_SIZE);
      long parserForRelsStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForRels = new WikiXMLParser(dumpFile, wikipediaRelsImporterListener);
      parserForRels.parse();
      long parserForRelsEndDate = Calendar.getInstance().getTimeInMillis();
      wikipediaRelsImporterListener.flush();
      newRelationships = wikipediaRelsImporterListener.getGraphCount();
      logger.info("Done! " + NumberFormat.getInstance(Locale.ITALY).format(newRelationships) + " relationships created in " + (parserForRelsEndDate - parserForRelsStartDate) + " ms.");
    }

    {
      logger.info("Creation of Wikipedia indexes started...");
      long indexCreationStartDate = Calendar.getInstance().getTimeInMillis();
      createIndexes(graphDatabaseConnectionManager);
      long indexCreationEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Indexes created in " + (indexCreationEndDate - indexCreationStartDate)  + " ms.");
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
      throw new IllegalArgumentException("java -cp neo4art-wikipedia-importer-<version>.jar " + WikipediaBufferedBatchImporter.class.getName() + " /path/to/wikipedia-dump.xml [/path/to/storeDir]");
    }

    File wikipediaDump = new File(args[0]);

    if (!wikipediaDump.exists()) {
      throw new RuntimeException(new FileNotFoundException("File " + wikipediaDump.getAbsolutePath() + " not found."));
    }
    
    File storeDir = null;
    
    if (args.length == 2) {
    	storeDir = new File(args[1]);
    }
    
    try {
      new WikipediaInMemoryBatchImporter().importDump(wikipediaDump, storeDir);
    }
    catch (Exception e) {
      throw new RuntimeException("Import failed: " + ExceptionUtils.getStackTrace(e));
    }
  }
}