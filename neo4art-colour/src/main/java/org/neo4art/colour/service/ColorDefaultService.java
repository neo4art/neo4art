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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.repository.ColourBatchInserterRepository;
import org.neo4art.colour.repository.ColourRepository;
import org.neo4art.domain.Colour;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ColorDefaultService implements ColourService
{
  /**
   * @see org.neo4art.colour.service.ColourService#saveColours(java.util.List)
   */
  @Override
  public void saveColours(List<Colour> colours)
  {
    if (CollectionUtils.isNotEmpty(colours))
    {
      for (Colour colour : colours)
      {
        saveColour(colour);
      }
    }
  }

  /**
   * @see org.neo4art.colour.service.ColourService#saveColour(org.neo4art.domain.Colour)
   */
  @Override
  public void saveColour(Colour colour)
  {
    ColourRepository colourRepository = new ColourBatchInserterRepository();
    
    colourRepository.saveColour(colour);
  }

  /**
   * @see org.neo4art.colour.service.ColourService#saveColourAnalysis(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void saveColourAnalysis(ColourAnalysis colourAnalysis)
  {
    ColourRepository colourRepository = new ColourBatchInserterRepository();
    
    colourRepository.saveColourAnalysis(colourAnalysis);
    
    colourRepository.connectColourAnalysisToArtwork(colourAnalysis);
    colourRepository.connectColourAnalysisToClosestColours(colourAnalysis);
  }
}
