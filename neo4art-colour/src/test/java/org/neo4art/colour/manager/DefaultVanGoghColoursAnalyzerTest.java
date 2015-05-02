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
        System.out.println("AVG name:" + imageColor.getNameAverageColor());
        System.out.println("MAX name:" + imageColor.getNameMaximumColor());
        System.out.println("MIN name:" + imageColor.getNameMinimumColor());
        System.out.println("AVG :" + imageColor.getRgbAverageColor().toString());
        System.out.println("Max :" + imageColor.getRgbMaximumColor().toString());
        System.out.println("Min :" + imageColor.getRgbMinimumColor().toString());
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
