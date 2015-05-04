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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.graphdb.ColourLabel;
import org.neo4art.colour.graphdb.ColourRelationship;
import org.neo4art.core.graphdb.CoreRelationship;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Colour;
import org.neo4art.graphdb.Neo4ArtLabel;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.helpers.collection.MapUtil;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ColourGraphDatabaseServiceRepository implements ColourAnalysisRepository
{
  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#saveColourAnalysis(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public long saveColourAnalysis(ColourAnalysis colourAnalysis)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#connectColourAnalysisToArtwork(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void connectColourAnalysisToArtwork(ColourAnalysis colourAnalysis)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /**
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#connectColourAnalysisToClosestColours(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void connectColourAnalysisToClosestColours(ColourAnalysis colourAnalysis)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /* (non-Javadoc)
   * @see org.neo4art.colour.repository.ColourAnalysisRepository#getColourAnalisysByArtist(org.neo4art.domain.Artist)
   */
  @Override
  public List<ColourAnalysis> getColourAnalisysByArtist(Artist artist)
  {
    List<ColourAnalysis> colourAnalyses = null;
    
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
    
    String cql = "MATCH (artist:" + Neo4ArtLabel.Artist + ")-[:" + CoreRelationship.REALIZED + "]->(artwork:" + Neo4ArtLabel.Artwork + "), " +
                       "(artwork)-[:" + ColourRelationship.COLOUR_ANALYSIS + "]->(colourAnalysis:" + ColourLabel.ColourAnalysis + "), " +
                       "(colourAnalysis)-[:" + ColourRelationship.CLOSEST_AVG_COLOUR + "]->(averageClosestColour:" + Neo4ArtLabel.Colour + ") " +
                 "WHERE artist.name={name} " +
                 "RETURN artwork, colourAnalysis, averageClosestColour "+
                 "ORDER BY artwork.completionDate,artwork.year ASC ";
        
    Result result = graphDatabaseService.execute(cql, MapUtil.map("name", artist.getName()));
    
    while (result.hasNext())
    {
      Map<String, Object> next = result.next();
      
      if (colourAnalyses == null)
      {
        colourAnalyses = new ArrayList<ColourAnalysis>();
      }
      
      Node artworkNode = (Node) next.get("artwork");
      Node colourAnalysisNode = (Node) next.get("colourAnalysis");
      Node averageClosestColourNode = (Node) next.get("averageClosestColour");
      
      Artwork artwork = new Artwork();
      artwork.setArtist(artist);
      artwork.setTitle((String) artworkNode.getProperty("title"));
      artwork.setYear((String) artworkNode.getProperty("year"));
      artwork.setCompletionDate(new Date((Long) artworkNode.getProperty("completionDate")));
      
      Colour averageClosestColour = new Colour();
      averageClosestColour.setName((String) averageClosestColourNode.getProperty("name"));
      averageClosestColour.setRgb((int) averageClosestColourNode.getProperty("rgb"));
      
      Color minimumColour = new Color((int) colourAnalysisNode.getProperty("minimumColour"));
      Color averageColour = new Color((int) colourAnalysisNode.getProperty("averageColour"));
      Color maximumColour = new Color((int) colourAnalysisNode.getProperty("maximumColour"));
      
      ColourAnalysis colourAnalysis = new ColourAnalysis();      
      colourAnalysis.setArtwork(artwork);
      colourAnalysis.setAverageClosestColour(averageClosestColour);
      colourAnalysis.setMinimumColour(minimumColour);
      colourAnalysis.setAverageColour(averageColour);
      colourAnalysis.setMaximumColour(maximumColour);
      colourAnalysis.setSource((String) colourAnalysisNode.getProperty("source"));
      
      colourAnalyses.add(colourAnalysis);
    }
    
    return colourAnalyses;
  }
}
