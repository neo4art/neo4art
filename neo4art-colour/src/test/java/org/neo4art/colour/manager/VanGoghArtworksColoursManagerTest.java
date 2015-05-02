package org.neo4art.colour.manager;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.service.ColourDefaultService;
import org.neo4art.colour.service.ColourService;
import org.neo4art.domain.Colour;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabase;

public class VanGoghArtworksColoursManagerTest
{
  @Test
  public void shouldComputeAndSaveColourAnalyses()
  {
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));
     
      System.out.println("STEP 1. Saving Van Gogh artworks sample into Graph...");
      
      VanGoghArtworksColoursManager vanGoghArtworksColoursManager = new VanGoghArtworksColoursDefaultManager();
      vanGoghArtworksColoursManager.saveArtworksSample();
      
      System.out.println("STEP 2. Saving Colours into Graph...");
      
      ColourService colourService = new ColourDefaultService();
      
      List<Colour> colours = colourService.getColours();
      
      colourService.createIndexes();
      colourService.saveColours(colours);
      
      new VanGoghArtworksColoursDefaultManager().computeAndSaveColourAnalyses();
      
    }
    catch (Exception e)
    {
      e.printStackTrace();

      Assert.fail(e.getMessage());
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
    }
  }
}
