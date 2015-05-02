package org.neo4art.colour.manager;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.domain.ColourAnalysis;

public class DefaultVanGoghColoursAnalyzerTest
{
  @Test
  public void avgMinMaxVanGoghImage()
  {
    try
    {
      VanGoghArtworksColoursAnalyzer vanGoghArtworksColoursAnalyzer = new VanGoghArtworksDefaultColoursAnalyzer();
      
      List<ColourAnalysis> analyseVanGoghImage = vanGoghArtworksColoursAnalyzer.analyzeArtworksColours();

      Assert.assertNotNull(analyseVanGoghImage);
      Assert.assertEquals(827, analyseVanGoghImage.size());

      for (ColourAnalysis imageColor : analyseVanGoghImage)
      {

        System.out.println("Image Name: " + imageColor.getArtwork().getTitle());
        System.out.println("AVG name:" + imageColor.getAverageClosestColour().getName());
        System.out.println("MAX name:" + imageColor.getMaximumClosestColour().getName());
        System.out.println("MIN name:" + imageColor.getMinimumClosestColour().getName());
        System.out.println("AVG :" + imageColor.getAverageColour().toString());
        System.out.println("Max :" + imageColor.getMaximumColour().toString());
        System.out.println("Min :" + imageColor.getMinimumColour().toString());
        System.out.println("------------------------------------------------\n");

      }
    }
    catch (Exception e)
    {
      e.printStackTrace();

      Assert.fail(e.getMessage());
    }
  }
}
