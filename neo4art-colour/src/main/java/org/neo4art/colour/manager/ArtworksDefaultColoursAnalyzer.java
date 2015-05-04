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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.repository.ColourAnalysisRepository;
import org.neo4art.colour.repository.ColourGraphDatabaseServiceRepository;
import org.neo4art.colour.service.ImageDefaultManager;
import org.neo4art.colour.service.ImageManager;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ArtworksDefaultColoursAnalyzer implements ArtworksColoursAnalyzer
{
  private static Log logger = LogFactory.getLog(ArtworksDefaultColoursAnalyzer.class);
  
  public ArtworksDefaultColoursAnalyzer()
  {
  }
  
  /**
   * @see org.neo4art.colour.manager.ArtworksColoursAnalyzer#analyzeArtworksColours(java.util.List)
   */
  @Override
  public List<ColourAnalysis> analyzeArtworksColours(List<Artwork> artworks)
  {
    List<ColourAnalysis> colourAnalyses = null;
    
    if (CollectionUtils.isNotEmpty(artworks))
    {
      colourAnalyses = new ArrayList<ColourAnalysis>();
      
      ImageManager imageManager = new ImageDefaultManager();
  
      for (Artwork artwork : artworks)
      {
        try
        {
          logger.info("Starting colour analysis for " + artwork.getTitle() + " from " + artwork.getImageFile());
  
          ColourAnalysis colourAnalysis = imageManager.analyzeImageByUrl(new URL(artwork.getImageFile()), artwork.getTitle());
          
          colourAnalysis.setArtwork(artwork);
          
          logger.info("Done!");
          
          colourAnalyses.add(colourAnalysis);
        }
        catch (Exception e)
        {
          logger.error("Colour analysis failed for " + artwork.getTitle() + " from " + artwork.getImageFile() + ". Cause: " + e.getMessage());
        }
      }    
    }

    return colourAnalyses;
  }

  /* (non-Javadoc)
   * @see org.neo4art.colour.manager.ArtworksColoursAnalyzer#getColourAnalysisByArtist(org.neo4art.domain.Artist)
   */
  @Override
  public List<ColourAnalysis> getColourAnalysisByArtist(Artist artist)
  {
    List<ColourAnalysis> colourAnalyses = null;
    
    try
    {
      Neo4ArtGraphDatabaseServiceSingleton.beginTransaction();
      
      ColourAnalysisRepository colourAnalysisRepository = new ColourGraphDatabaseServiceRepository();
      
      colourAnalyses = colourAnalysisRepository.getColourAnalisysByArtist(artist);
      
      Neo4ArtGraphDatabaseServiceSingleton.commitTransaction();
    }
    catch (Exception e)
    {
      Neo4ArtGraphDatabaseServiceSingleton.rollbackTransaction();
    }
    
    return colourAnalyses;
  }

}
