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
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.importer.wikipedia.core.listener.WikipediaCVSFilesBatchImporterListener;
import org.neo4art.importer.wikipedia.core.listener.WikipediaImporterListener;
import org.xml.sax.SAXException;

import info.bliki.wiki.dump.WikiXMLParser;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaCVSFilesBatchImporter extends WikipediaAbstractBatchImporter implements WikipediaImporter {

  private static Log logger = LogFactory.getLog(WikipediaCVSFilesBatchImporter.class);
  
  @Override
  public long importDump(File dumpFile) throws IOException, SAXException, ParserConfigurationException {
  	return importDump(dumpFile, null);
  }
  
  @Override
  public long importDump(File dumpFile, File storeDir) throws IOException, SAXException, ParserConfigurationException {
    logger.info("Wikipedia dump file import started...");

    long dumpImportStartDate = Calendar.getInstance().getTimeInMillis();

    long newNodesAndRelationships = 0;

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.EMBEDDED_DATABASE, storeDir);

    logger.info("Configuration: ");
    logger.info("------------------------------------------------");
    logger.info("Batch size: NO BUFFER LIMIT");
    logger.info("Store directory is: " + graphDatabaseConnectionManager.getStoreDir());
    logger.info("CVS output directory is: " + dumpFile.getParentFile() + "");
    logger.info("");

    try {
      logger.info("Parsing of wikipedia dump " + dumpFile.getAbsolutePath() + " started...");
      long parserStartDate = Calendar.getInstance().getTimeInMillis();
      WikipediaImporterListener wikipediaNodesImporterListener = new WikipediaCVSFilesBatchImporterListener(dumpFile.getParentFile());
      wikipediaNodesImporterListener.setBatchSize(WikipediaImporterListener.NO_BUFFER_LIMITS_FOR_FULL_IN_MEMORY_MANAGEMENT);
      WikiXMLParser parserForNodes = new WikiXMLParser(dumpFile, wikipediaNodesImporterListener);
      parserForNodes.parse();
      long parserEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Wikipedia dump parsed in " + (parserEndDate - parserStartDate) + " ms.");
      logger.info("");

      logger.info("Creation of Wikipedia nodes and relationships started...");
      long flushOnGraphStartDate = Calendar.getInstance().getTimeInMillis();
      wikipediaNodesImporterListener.flush();
      long flushOnGraphEndDate = Calendar.getInstance().getTimeInMillis();
      newNodesAndRelationships = wikipediaNodesImporterListener.getGraphCount();
      logger.info("Done! " + newNodesAndRelationships + " nodes and relationships created in " + (flushOnGraphEndDate - flushOnGraphStartDate) + " ms.");
      logger.info("");

      logger.info("Creation of Wikipedia indexes started...");
      long indexCreationStartDate = Calendar.getInstance().getTimeInMillis();
      createIndexes(graphDatabaseConnectionManager);
      long indexCreationEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Indexes created in " + (indexCreationEndDate - indexCreationStartDate) + " ms.");
      logger.info("");
    }
    finally {
      logger.info("Neo4j files consolidation (before shutting down) started...");
      long shutdownStartDate = Calendar.getInstance().getTimeInMillis();
      graphDatabaseConnectionManager.close();
      long shutdownEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Shutdown completed in " + (shutdownEndDate - shutdownStartDate) + " ms.");
      logger.info("");
    }

    long dumpImportEndDate = Calendar.getInstance().getTimeInMillis();
    logger.info("Wikipedia dump file import completed in " + (dumpImportEndDate - dumpImportStartDate) + " ms.");

    return newNodesAndRelationships;
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("java -cp neo4art-wikipedia-importer-<version>.jar " + WikipediaCVSFilesBatchImporter.class.getName() + " /path/to/wikipedia-dump.xml [/path/to/storeDir]");
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
      new WikipediaCVSFilesBatchImporter().importDump(wikipediaDump, storeDir);
    }
    catch (Exception e) {
      throw new RuntimeException("Import failed: " + ExceptionUtils.getStackTrace(e));
    }
  }
}