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

package org.neo4art.colour.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.colour.manager.VanGoghArtworksColourAnalysisDefaultManager;
import org.neo4art.colour.manager.VanGoghArtworksColourAnalysisManager;
import org.neo4art.core.repository.ArtistBatchInserterRepository;
import org.neo4art.core.repository.ArtistRepository;
import org.neo4art.core.service.ArtworkDefaultService;
import org.neo4art.core.service.ArtworkService;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;

import deprecated.Neo4ArtBatchInserterSingleton;

/**
 * @author Lorenzo Speranzoni
 * @since 3 May 2015
 */
public class VanGoghColourAnalysisBatchLoader
{
  private static Log logger = LogFactory.getLog(VanGoghColourAnalysisBatchLoader.class);

  public static void main(String[] args)
  {
    String fileName = "vangoghartworks/van-gogh-artworks.csv";
    
    try
    {
      logger.info("Step 1. Saving Van Gogh artist...");
      
      Artist artist = new Artist();
      artist.setName("Vincent van Gogh");
      
      ArtistRepository artistRepository = new ArtistBatchInserterRepository();
      artistRepository.saveArtist(artist);
      
      logger.info("Step 2. Loading artworks from file: " + fileName + "...");
      
      VanGoghArtworksColourAnalysisManager vanGoghArtworksColourAnalysisDefaultManager = new VanGoghArtworksColourAnalysisDefaultManager();
      List<Artwork> artworks = vanGoghArtworksColourAnalysisDefaultManager.loadArtworksFromFile(fileName);
      
      for (Artwork artwork : artworks)
      {
        artwork.setArtist(artist);
      }
     
      logger.info("Step 3. Saving artworks...");
      
      ArtworkService artworkService = new ArtworkDefaultService();
      artworkService.saveArtworks(artworks);
      
      logger.info("Step 4. Computing and saving colour analyses...");
      
      vanGoghArtworksColourAnalysisDefaultManager.computeAndSaveColourAnalyses(artworks);
    }
    catch (Exception e)
    {
      e.printStackTrace();

      logger.error("Error computing and saving Van Gogh colours analyses into neo4j: " + e.getMessage());
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
    }
    
    logger.info("Done!");
  }
}
