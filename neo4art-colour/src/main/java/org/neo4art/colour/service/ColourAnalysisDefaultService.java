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

package org.neo4art.colour.service;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.repository.ColourAnalysisRepository;
import org.neo4art.colour.repository.ColourBatchInserterRepository;
import org.neo4art.core.repository.ArtworkBatchInserterRepository;
import org.neo4art.core.repository.ArtworkRepository;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ColourAnalysisDefaultService implements ColourAnalysisService
{
  /**
   * @see org.neo4art.colour.service.ColourAnalysisService#saveColourAnalysis(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void saveColourAnalysis(ColourAnalysis colourAnalysis)
  {
    ColourAnalysisRepository colourAnalysisRepository = new ColourBatchInserterRepository();
    
    colourAnalysisRepository.saveColourAnalysis(colourAnalysis);
    
    colourAnalysisRepository.connectColourAnalysisToClosestColours(colourAnalysis);

    ArtworkRepository artworkRepository = new ArtworkBatchInserterRepository();
    
    Long artworkNodeId = colourAnalysis.getArtwork().getNodeId();
    
    System.out.println("COLOUR ANALYSIS - ARTWORK: " + colourAnalysis.getArtwork().getTitle());
    
    if (artworkNodeId == null)
    {
      artworkNodeId = artworkRepository.getArtworkByTitle(colourAnalysis.getArtwork().getTitle());
    }
    
    System.out.println("ARTWORK NODE ID: " + artworkNodeId);
    
    if (artworkNodeId != null)
    {
      colourAnalysis.getArtwork().setNodeId(artworkNodeId);
      
      colourAnalysisRepository.connectColourAnalysisToArtwork(colourAnalysis);
    }
  }
}
