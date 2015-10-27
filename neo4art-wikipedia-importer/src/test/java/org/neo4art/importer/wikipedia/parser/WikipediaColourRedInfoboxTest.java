package org.neo4art.importer.wikipedia.parser;

import java.awt.Color;
import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Colour;

public class WikipediaColourRedInfoboxTest {
  
  private static String INFOBOX =
      
    "{{Infobox color\n" +
    "|title=Red\n" +
    "|image= File:Color icon red.svg\n" +
    "|hex=FF0000\n" +
    "|textcolor=white\n" +
    "|wavelength=~620–740<ref>Thomas J. Bruno, Paris D. N. Svoronos. ''CRC Handbook of Fundamental Spectroscopic Correlation Charts.'' CRC Press, 2005.<br>[http://hyperphysics.phy-astr.gsu.edu/hbase/vision/specol.html#c1 Color]</ref><ref name=Bohren2006 />| frequency=~480–400\n" +
    "|r=255|g=0|b=0|rgbspace=[[sRGB color space|sRGB]]\n" +
    "|source=[[X11 color names|X11]]}}";
  
  @Test
  public void shouldParseRedColourInfobox() throws MalformedURLException {
    
    Colour colour = WikipediaColourInfoboxParser.parse(INFOBOX);
    
    Assert.assertEquals("Red", colour.getName());
    Assert.assertEquals(Color.RED, colour.getColor());
  }
}
