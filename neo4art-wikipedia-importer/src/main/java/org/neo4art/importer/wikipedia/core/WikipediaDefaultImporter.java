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
import org.neo4art.importer.wikipedia.service.WikipediaDefaultGraphService;
import org.neo4art.importer.wikipedia.service.WikipediaGraphService;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Service
public class WikipediaDefaultImporter implements WikipediaImporter {

  private static Log logger = LogFactory.getLog(WikipediaDefaultImporter.class);
  
  @Override
  public long importOrUpdateDump(File dumpFile) throws IOException, SAXException, ParserConfigurationException {
    
    logger.info("Wikipedia dump file import started...");
    
    long newNodes = 0;
    long newRelationships = 0;
    
    {
      WikipediaImporterListener wikipediaNodesImporterListener = new WikipediaNodesImporterListener();
      wikipediaNodesImporterListener.setBatchSize(5000);
      long parserForNodesStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForNodes = new WikiXMLParser(dumpFile.getAbsolutePath(), wikipediaNodesImporterListener);
      parserForNodes.parse();
      wikipediaNodesImporterListener.flush();
      long parserForNodesEndDate = Calendar.getInstance().getTimeInMillis();
      newNodes = wikipediaNodesImporterListener.getGraphCount();
      logger.info("Done! Created " + newNodes + " nodes in " + (parserForNodesEndDate - parserForNodesStartDate) + " ms.");
    }
    
    {
      WikipediaGraphService wikipediaGraphService = new WikipediaDefaultGraphService();
      long indexCreationStartDate = Calendar.getInstance().getTimeInMillis();
      wikipediaGraphService.removeDuplicates();
      wikipediaGraphService.createConstraints();
      long indexCreationEndDate = Calendar.getInstance().getTimeInMillis();
      logger.info("Done! Contraints created in " + (indexCreationEndDate - indexCreationStartDate) + " ms.");
    }
    
    {
      WikipediaImporterListener wikipediaRelationshipsImporterListener = new WikipediaRelationshipsImporterListener();
      wikipediaRelationshipsImporterListener.setBatchSize(5000);
      long parserForRelationshipsStartDate = Calendar.getInstance().getTimeInMillis();
      WikiXMLParser parserForRelationships = new WikiXMLParser(dumpFile.getAbsolutePath(), wikipediaRelationshipsImporterListener);
      parserForRelationships.parse();
      wikipediaRelationshipsImporterListener.flush();
      long parserForRelationshipsEndDate = Calendar.getInstance().getTimeInMillis();
      newRelationships = wikipediaRelationshipsImporterListener.getGraphCount();
      logger.info("Done! Created " + newRelationships + " relationships in " + (parserForRelationshipsEndDate - parserForRelationshipsStartDate) + " ms.");
    }
    
    logger.info("Wikipedia dump file import completed.");
    
    return newNodes + newRelationships;
  }
  
  public static void main(String[] args) {
    
    if (args.length != 1) {
      throw new IllegalArgumentException("java -cp neo4art-wikipedia-importer-<version>.jar /path/to/wikipedia-dump.xml.bz2");
    }
    
    File wikipediaDump = new File(args[0]);
    
    if (!wikipediaDump.exists()) {
      throw new RuntimeException(new FileNotFoundException("File " + wikipediaDump.getAbsolutePath() + " not found."));
    }
    
    try {
      new WikipediaDefaultImporter().importOrUpdateDump(wikipediaDump);
    } catch (Exception e) {
      throw new RuntimeException("Import failed: " + e.getMessage() + ".", e);
    }
  }
}