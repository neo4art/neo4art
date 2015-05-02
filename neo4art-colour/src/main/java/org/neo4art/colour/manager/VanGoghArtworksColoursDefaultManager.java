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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.colour.bean.ArtworkURL;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.service.ColourDefaultService;
import org.neo4art.colour.service.ColourService;

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
