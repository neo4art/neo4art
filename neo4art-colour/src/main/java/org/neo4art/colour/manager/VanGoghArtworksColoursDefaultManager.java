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

package org.neo4art.colour.manager;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.neo4art.colour.bean.ArtworkURL;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.service.ColourDefaultService;
import org.neo4art.colour.service.ColourService;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4j.graphdb.DynamicRelationshipType;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class VanGoghArtworksColoursDefaultManager implements VanGoghArtworksColoursManager
{
  private static Log logger = LogFactory.getLog(VanGoghArtworksColoursDefaultManager.class);
  
  public VanGoghArtworksColoursDefaultManager()
  {
  }
  
  /**
   * @see org.neo4art.colour.manager.VanGoghArtworksColoursManager#saveArtworksSample()
   */
  @Override
  public void saveArtworksSample()
  {
    try
    {
      String fileName = "vangoghartworks/van-gogh-artworks.csv";
      
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource(fileName).getFile());
      
      CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL.withDelimiter(',').withQuote('\'').withEscape('\\').withIgnoreSurroundingSpaces(true));
      List<CSVRecord> cvsRecords = csvParser.getRecords();

      Artist vanGogh = new Artist();
      vanGogh.setName("Van Gogh");
      
      Neo4ArtBatchInserterSingleton.createNode(vanGogh);
      
      for (int i = 1; i < cvsRecords.size(); i++)
      {
        CSVRecord csvRecord = cvsRecords.get(i);
        
        Artwork vanGoghArtwork = new Artwork();
        
        vanGoghArtwork.setTitle(csvRecord.get(0));
        vanGoghArtwork.setType(csvRecord.get(1));
        vanGoghArtwork.setYear(csvRecord.get(2));
        
        Calendar completionDate = Calendar.getInstance();
        
        completionDate.set(Calendar.YEAR, Integer.parseInt(csvRecord.get(2)));
        completionDate.set(Calendar.MONTH, Integer.parseInt(csvRecord.get(3)));
        completionDate.set(Calendar.DAY_OF_MONTH, 1);
        completionDate.set(Calendar.HOUR, 0);
        completionDate.set(Calendar.MINUTE, 0);
        completionDate.set(Calendar.SECOND, 0);
        completionDate.set(Calendar.MILLISECOND, 0);
        
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(completionDate.getTime()));
        
        vanGoghArtwork.setCompletionDate(completionDate.getTime());
        vanGoghArtwork.setImageFile(csvRecord.get(4));
        vanGoghArtwork.setCatalogue("F: " + csvRecord.get(5) + " - JH:" + csvRecord.get(6));
        
        vanGoghArtwork.setArtist(vanGogh);
        
        Neo4ArtBatchInserterSingleton.createNode(vanGoghArtwork);
        Neo4ArtBatchInserterSingleton.createRelationship(vanGogh.getNodeId(), vanGoghArtwork.getNodeId(), DynamicRelationshipType.withName("REALIZED"), null);
      }

      csvParser.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }
  }

  /**
   * @see org.neo4art.colour.manager.VanGoghArtworksColoursManager#computeAndSaveColourAnalyses()
   */
  @Override
  public void computeAndSaveColourAnalyses()
  {
    logger.info("Starting Van Gogh's artworks colours analysis...");
    
    try
    {
      String fileName = "vangoghartworks/vangoghartworks.txt";
      
      logger.info("Loading artworks URLs from file: " + fileName);
      
      ArtworksColoursAnalyzer artworksColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();
      
      List<ArtworkURL> artworksURLsFromFile = artworksColoursAnalyzer.loadArtworksURLsFromFile(fileName);

      if (CollectionUtils.isNotEmpty(artworksURLsFromFile))
      {
        logger.info(artworksURLsFromFile.size() + " artworks to be analyzed.");      
        
        List<ColourAnalysis> coloursAnalyses = artworksColoursAnalyzer.analyzeArtworksColours(artworksURLsFromFile);
        
        logger.info("Analysis done!");
        
        if (CollectionUtils.isNotEmpty(coloursAnalyses))
        {
          logger.info("Saving " + coloursAnalyses.size() + " analyses to graph...");
          
          ColourService colourService = new ColourDefaultService();
          
          for (ColourAnalysis colourAnalysis : coloursAnalyses)
          {
            colourService.saveColourAnalysis(colourAnalysis);
          }
          
          logger.info("Saved!");
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      logger.error("Error executing Van Gogh artworks colour analyses: " + e.getMessage());
    }
  }
}
