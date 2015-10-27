package org.neo4art.importer.wikipedia.parser;

import java.awt.Color;
import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Colour;

public class WikipediaColourWhiteInfoboxTest {
  
  private static String INFOBOX =
      
    "{{infobox color\n" +
    "|title=White\n" +
    "|hex=FFFFFF\n" +
    "|image=\n" +
    "{{photomontage\n" +
    "|photo1a=Polar bear with young - ANWR.jpg\n" +
    "|photo1b=Andalusian.jpg\n" +
    "|photo1c=Delphinapterus leucas 2.jpg\n" +
    "|photo2a=White sand on Berneray - geograph.org.uk - 684958.jpg\n" +
    "|photo2b=Pope Francis in March 2013 b.jpg\n" +
    "|photo2c=Alps.jpg\n" +
    "|photo3a=Wedding Kimono.jpg\n" +
    "|photo3b=Taj Mahal 2002.JPG\n" +
    "|photo3c=Milk glass.jpg\n" +
    "|size=243\n" +
    "|color_border=#AAAAAA\n" +
    "|color=#F9F9F9\n" +
    "|foot_montage=}}\n" +
    "|r=255|g=255|b=255 |\n" +
    "|c=0|m=0|y=0|k=0|\n" +
    "|h=-|s=0|v=100\n" +
    "|source=By definition\n" +
    "}}";
  
  @Test
  public void shouldParseRedColourInfobox() throws MalformedURLException {
    
    Colour colour = WikipediaColourInfoboxParser.parse(INFOBOX);
    
    Assert.assertEquals("White", colour.getName());
    Assert.assertEquals(Color.WHITE, colour.getColor());
  }
}
