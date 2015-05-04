package org.neo4art.colour.manager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.domain.Artwork;

public class ArtworksColoursAnalyzerTest
{
  @Test
  public void shouldAnalyzeWaterMillAtGennepI()
  {
    try
    {
      Artwork artwork = new Artwork();
      artwork.setTitle("Water Mill at Gennep I");
      artwork.setImageFile("http://www.vggallery.com/painting/f_0046.jpg");
      
      List<Artwork> artworks = new ArrayList<Artwork>();
      artworks.add(artwork);
      
      ArtworksColoursAnalyzer artworksColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();
      
      List<ColourAnalysis> analyzeArtworksColours = artworksColoursAnalyzer.analyzeArtworksColours(artworks);
      
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
}
