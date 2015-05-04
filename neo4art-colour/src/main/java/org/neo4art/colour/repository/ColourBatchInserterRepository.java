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

package org.neo4art.colour.repository;

import java.util.List;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.graphdb.ColourLegacyIndex;
import org.neo4art.colour.graphdb.ColourRelationship;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Colour;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4j.graphdb.index.IndexHits;

/**
 * @author Lorenzo Speranzoni
 * @since 22 Apr 2015
 */
public class ColourBatchInserterRepository implements ColourAnalysisRepository
{
  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#saveColourAnalysis(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public long saveColourAnalysis(ColourAnalysis colourAnalysis)
  {
    long colourAnalysisNodeId = Neo4ArtBatchInserterSingleton.createNode(colourAnalysis);

    colourAnalysis.setNodeId(colourAnalysisNodeId);

    return colourAnalysisNodeId;
  }

  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#connectColourAnalysisToArtwork(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void connectColourAnalysisToArtwork(ColourAnalysis colourAnalysis)
  {
    Long colourAnalysisNodeId = colourAnalysis.getNodeId();
    Long artworkNodeId = colourAnalysis.getArtwork().getNodeId();

    if (colourAnalysisNodeId != null && artworkNodeId != null)
    {
      Neo4ArtBatchInserterSingleton.createRelationship(artworkNodeId, colourAnalysisNodeId, ColourRelationship.COLOUR_ANALYSIS, null);
    }
  }

  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#connectColourAnalysisToClosestColours(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void connectColourAnalysisToClosestColours(ColourAnalysis colourAnalysis)
  {
    Long colourAnalysisNodeId = colourAnalysis.getNodeId();

    connectColourAnalysisToClosestColour(colourAnalysisNodeId, colourAnalysis.getMinimumClosestColour(), ColourRelationship.CLOSEST_MIN_COLOUR);
    connectColourAnalysisToClosestColour(colourAnalysisNodeId, colourAnalysis.getAverageClosestColour(), ColourRelationship.CLOSEST_AVG_COLOUR);
    connectColourAnalysisToClosestColour(colourAnalysisNodeId, colourAnalysis.getMaximumClosestColour(), ColourRelationship.CLOSEST_MAX_COLOUR);
  }

  /**
   * 
   * @param colourAnalysisNodeId
   * @param closestColour
   * @param colourRelationship
   */
  private void connectColourAnalysisToClosestColour(Long colourAnalysisNodeId, Colour closestColour, ColourRelationship colourRelationship)
  {
    if (closestColour != null)
    {
      IndexHits<Long> closestColourIndexHits = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(ColourLegacyIndex.COLOUR_LEGACY_INDEX.name(), Colour.RGB_PROPERTY_NAME, closestColour.getColor().getRGB());
      
      if (closestColourIndexHits != null)
      {
        if (closestColourIndexHits.hasNext())
        {
          long closestColourIndexHit = closestColourIndexHits.next();
          
          Neo4ArtBatchInserterSingleton.createRelationship(colourAnalysisNodeId, closestColourIndexHit, colourRelationship, null);
        }
      }
      
      closestColourIndexHits.close();
    }
  }

  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#getColourAnalisysByArtist(org.neo4art.domain.Artist)
   */
  @Override
  public List<ColourAnalysis> getColourAnalisysByArtist(Artist artist)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }
}
