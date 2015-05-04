package org.neo4art.colour.manager;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.bean.ArtworkURL;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.domain.Artwork;

public class ArtworksColoursAnalyzerTest
{
  @Test
  public void shouldAnalyzeWaterMillAtGennepI()
  {
    try
    {
      List<ArtworkURL> artworksURLsFromFile = new ArrayList<ArtworkURL>();
      artworksURLsFromFile.add(new ArtworkURL(new Artwork("Water Mill at Gennep I"), new URL("http://www.vggallery.com/painting/f_0046.jpg")));
      
      ArtworksColoursAnalyzer artworksColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();
      
      List<ColourAnalysis> analyzeArtworksColours = artworksColoursAnalyzer.analyzeArtworksColours(artworksURLsFromFile);
      
      Assert.assertEquals(1, analyzeArtworksColours.size());
      
      ColourAnalysis colourAnalysis = analyzeArtworksColours.get(0);
      
      System.out.println("Artwork: " + colourAnalysis.getArtwork().getTitle());
      System.out.println("------------------------------------------------\n");      
      System.out.println("MIN name:" + colourAnalysis.getMinimumClosestColour().getName());
      System.out.println("AVG name:" + colourAnalysis.getAverageClosestColour().getName());
      System.out.println("MAX name:" + colourAnalysis.getMaximumClosestColour().getName());
      System.out.println("------------------------------------------------\n");      
      
      Assert.assertEquals(new Color(  0,  0,  0), colourAnalysis.getMinimumColour());
      Assert.assertEquals(new Color( 88, 86, 54), colourAnalysis.getAverageColour());
      Assert.assertEquals(new Color(255,255,223), colourAnalysis.getMaximumColour());

    }
    catch (Exception e)
    {
      e.printStackTrace();

      Assert.fail(e.getMessage());
    }
  }
  
  @Test
  public void shouldAnalyzeAllVanGoghArtworks()
  {
    try
    {
      ArtworksColoursAnalyzer artworksColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();
      
      List<ArtworkURL> artworksURLsFromFile = artworksColoursAnalyzer.loadArtworksURLsFromFile("vangoghartworks/vangoghartworks.txt");
      
      List<ColourAnalysis> analyseVanGoghImage = artworksColoursAnalyzer.analyzeArtworksColours(artworksURLsFromFile);
      
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
