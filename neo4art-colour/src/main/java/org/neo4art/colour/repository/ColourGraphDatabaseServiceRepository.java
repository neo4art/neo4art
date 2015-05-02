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
import java.util.Map;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.graphdb.ColourLabel;
import org.neo4art.colour.graphdb.ColourRelationship;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Colour;
import org.neo4art.graphdb.Neo4ArtLabel;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ColourGraphDatabaseServiceRepository implements ColourRepository
{
  /**
   * @see org.neo4art.colour.repository.ColourRepository#createIndexes()
   */
  @Override
  public void createIndexes()
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /**
   * @see org.neo4art.colour.repository.ColourRepository#saveColour(org.neo4art.domain.Colour)
   */
  @Override
  public long saveColour(Colour colour)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /**
   * @see org.neo4art.colour.repository.ColourRepository#saveColourAnalysis(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public long saveColourAnalysis(ColourAnalysis colourAnalysis)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /**
   * @see org.neo4art.colour.repository.ColourRepository#connectColourAnalysisToArtwork(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void connectColourAnalysisToArtwork(ColourAnalysis colourAnalysis)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /**
   * @see org.neo4art.colour.repository.ColourRepository#connectColourAnalysisToClosestColours(org.neo4art.colour.domain.ColourAnalysis)
   */
  @Override
  public void connectColourAnalysisToClosestColours(ColourAnalysis colourAnalysis)
  {
    throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
  }

  /* (non-Javadoc)
   * @see org.neo4art.colour.repository.ColourRepository#getColourAnalisysByArtist(org.neo4art.domain.Artist)
   */
  @Override
  public List<ColourAnalysis> getColourAnalisysByArtist(Artist artist)
  {
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
    
    String cql = "MATCH (artist:" + Neo4ArtLabel.Artist + ")-[:REALIZED]->(artwork:" + Neo4ArtLabel.Artwork + ")" +
                       "(artwork)-[:" + ColourRelationship.COLOUR_ANALYSIS + "]->(colourAnalysis:" + ColourLabel.ColourAnalysis + ")," +
                       "(colourAnalysis)-[:" + ColourRelationship.CLOSEST_AVG_COLOUR + "]->(averageClosestColour:" + Neo4ArtLabel.Colour + ")" +
                 "WHERE artist.name=" + artist.getName() + " " +
                 "RETURN artist, artwork, colourAnalysis, averageClosestColour";
        
    Result result = graphDatabaseService.execute(cql);
    
    while (result.hasNext())
    {
      Map<String, Object> next = result.next();
      
      System.out.println("Artist: " + next.get("artist").getClass().getName());
      System.out.println("Artwork: " + next.get("artwork").getClass().getName());
      System.out.println("Colour Analysis: " + next.get("colourAnalysis").getClass().getName());
      System.out.println("Average Closest Colour: " + next.get("averageClosestColour").getClass().getName());
    }
    
    return null;
  }
}
