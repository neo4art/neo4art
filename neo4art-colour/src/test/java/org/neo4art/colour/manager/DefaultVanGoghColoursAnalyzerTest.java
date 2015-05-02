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
      VanGoghColoursAnalyzer vanGoghColoursAnalyzer = new VanGoghDefaultColoursAnalyzer();
      
      List<ColourAnalysis> analyseVanGoghImage = vanGoghColoursAnalyzer.analyzeArtworksColors();

      Assert.assertNotNull(analyseVanGoghImage);
      Assert.assertEquals(827, analyseVanGoghImage.size());

      for (ColourAnalysis imageColor : analyseVanGoghImage)
      {

        System.out.println("Image Name: " + imageColor.getImageName());
        System.out.println("AVG name:" + imageColor.getAverageColourName());
        System.out.println("MAX name:" + imageColor.getMaximumColourName());
        System.out.println("MIN name:" + imageColor.getMinimumColourName());
        System.out.println("AVG :" + imageColor.getAverageRGBColour().toString());
        System.out.println("Max :" + imageColor.getMaximumRGBColour().toString());
        System.out.println("Min :" + imageColor.getMinimumRGBColour().toString());
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
