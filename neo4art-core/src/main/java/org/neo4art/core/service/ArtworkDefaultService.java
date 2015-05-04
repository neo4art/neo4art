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

package org.neo4art.core.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.core.repository.ArtworkBatchInserterRepository;
import org.neo4art.core.repository.ArtworkRepository;
import org.neo4art.domain.Artwork;

/**
 * @author Lorenzo Speranzoni
 * @since 4 May 2015
 */
public class ArtworkDefaultService implements ArtworkService
{
  private static Log logger = LogFactory.getLog(ArtworkDefaultService.class);
  
  /**
   * @see org.neo4art.core.service.ArtworkService#saveArtworks(java.util.List)
   */
  @Override
  public void saveArtworks(List<Artwork> artworks)
  {
    logger.info("Saving artworks into neo4j...");
  
    if (CollectionUtils.isNotEmpty(artworks))
    {
      logger.info(artworks.size() + " artworks to be saved.");
      
      for (Artwork artwork : artworks)
      {
        this.saveArtwork(artwork);
      }
  
      logger.info("Saved!");
    }
  }

  /**
   * @see org.neo4art.core.service.ArtworkService#saveArtwork(org.neo4art.domain.Artwork)
   */
  @Override
  public long saveArtwork(Artwork artwork)
  {
    ArtworkRepository artworkRepository = new ArtworkBatchInserterRepository();
    
    long nodeId = artworkRepository.saveArtwork(artwork);
    
    artworkRepository.connectArtworkToArtist(artwork);
    
    return nodeId;
  }
}
