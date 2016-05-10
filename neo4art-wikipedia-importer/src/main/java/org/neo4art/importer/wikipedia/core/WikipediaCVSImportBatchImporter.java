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
import org.neo4art.importer.wikipedia.core.listener.WikipediaCVSImportBatchImporterListener;
import org.neo4art.importer.wikipedia.core.listener.WikipediaImporterListener;
import org.xml.sax.SAXException;

import info.bliki.wiki.dump.WikiXMLParser;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaCVSImportBatchImporter extends WikipediaAbstractBatchImporter implements WikipediaImporter {

  private static Log logger = LogFactory.getLog(WikipediaCVSImportBatchImporter.class);
  
  @Override
  public long importDump(File dumpFile) throws IOException, SAXException, ParserConfigurationException {
  	return importDump(dumpFile, null);
  }
  
  @Override
  public long importDump(File dumpFile, File storeDir) throws IOException, SAXException, ParserConfigurationException {
    logger.info("Wikipedia dump file import started...");

    long dumpImportStartDate = Calendar.getInstance().getTimeInMillis();

    long newNodesAndRelationships = 0;

    logger.info("Configuration: ");
    logger.info("------------------------------------------------");
    logger.info("Batch size: NO BUFFER LIMIT");
    logger.info("CVS output directory is: " + dumpFile.getParentFile() + "");
    logger.info("");

    try {
    	WikipediaImporterListener wikipediaNodesImporterListener = new WikipediaCVSImportBatchImporterListener(dumpFile.getParentFile());
    	wikipediaNodesImporterListener.setBatchSize(WikipediaImporterListener.NO_BUFFER_LIMITS_FOR_FULL_IN_MEMORY_MANAGEMENT);
    	
      logger.info("wikipedia-nodes.cvs creation started!");
      logger.info("Parsing wikipedia dump " + dumpFile.getAbsolutePath() + " ...");
      long parserStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForNodes = new WikiXMLParser(dumpFile, wikipediaNodesImporterListener);
      parserForNodes.parse();
      long parserEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Wikipedia dump parsed in " + (parserEndDate - parserStartDate) + " ms.");
      wikipediaNodesImporterListener.flush();
      logger.info("wikipedia-nodes.cvs created!");
      logger.info("");

      logger.info("wikipedia-rels.cvs creation started!");
      logger.info("Parsing of wikipedia dump " + dumpFile.getAbsolutePath() + " started...");
      parserStartDate = Calendar.getInstance().getTimeInMillis();
      parserForNodes = new WikiXMLParser(dumpFile, wikipediaNodesImporterListener);
      parserForNodes.parse();
      parserEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Wikipedia dump parsed in " + (parserEndDate - parserStartDate) + " ms.");
      wikipediaNodesImporterListener.flush();
      logger.info("wikipedia-rels.cvs creation created!");
      logger.info("");      
    }
    finally {
    }

    long dumpImportEndDate = Calendar.getInstance().getTimeInMillis();
    logger.info("Wikipedia dump file import completed in " + (dumpImportEndDate - dumpImportStartDate) + " ms.");

    return newNodesAndRelationships;
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("java -cp neo4art-wikipedia-importer-<version>.jar " + WikipediaCVSImportBatchImporter.class.getName() + " /path/to/wikipedia-dump.xml [/path/to/storeDir]");
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
      new WikipediaCVSImportBatchImporter().importDump(wikipediaDump, storeDir);
    }
    catch (Exception e) {
      throw new RuntimeException("Import failed: " + ExceptionUtils.getStackTrace(e));
    }
  }
}