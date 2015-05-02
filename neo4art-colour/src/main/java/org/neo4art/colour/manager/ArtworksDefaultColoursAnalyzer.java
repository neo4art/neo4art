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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.colour.bean.ArtworkURL;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.exception.ImageParserException;
import org.neo4art.colour.repository.ColourGraphDatabaseServiceRepository;
import org.neo4art.colour.repository.ColourAnalysisRepository;
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
   * @see org.neo4art.colour.manager.ArtworksColoursAnalyzer#loadArtworksURLsFromFile(java.lang.String)
   */
  @Override
  public List<ArtworkURL> loadArtworksURLsFromFile(String fileName) throws IOException
  {
    List<ArtworkURL> result = new ArrayList<ArtworkURL>();
    
    ClassLoader classLoader = getClass().getClassLoader();
    
    File file = new File(classLoader.getResource(fileName).getFile());

    BufferedReader input = new BufferedReader(new FileReader(file));
    
    String line = null;
    
    while ((line = input.readLine()) != null)
    {
      String[] splitLine = line.split("-");
      
      result.add(new ArtworkURL(new Artwork(splitLine[0]), new URL(splitLine[1])));
    }
    
    input.close();
    
    return result;
  }

  /**
   * @see org.neo4art.colour.manager.ArtworksColoursAnalyzer#analyzeArtworksColours(java.util.List)
   */
  @Override
  public List<ColourAnalysis> analyzeArtworksColours(List<ArtworkURL> artworksURLsFromFile) throws ImageParserException
  {
    List<ColourAnalysis> artworksColours = null;
    
    if (CollectionUtils.isNotEmpty(artworksURLsFromFile))
    {
      artworksColours = new ArrayList<ColourAnalysis>();
      
      ImageManager imageManager = new ImageDefaultManager();
  
      for (ArtworkURL artworkURL : artworksURLsFromFile)
      {
        ColourAnalysis colourAnalysis = imageManager.analyzeImageByUrl(artworkURL.getUrl(), artworkURL.getArtwork().getTitle());
        
        logger.info("Colour analysis for " + artworkURL.getArtwork().getTitle() + " from " + artworkURL.getUrl() + " done.");
        
        artworksColours.add(colourAnalysis);
      }    
    }

    return artworksColours;
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
