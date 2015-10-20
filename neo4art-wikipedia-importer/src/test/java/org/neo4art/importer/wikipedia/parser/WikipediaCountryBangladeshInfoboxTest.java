package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Country;
import org.neo4art.importer.wikipedia.parser.WikipediaCountryInfoboxParser;

public class WikipediaCountryBangladeshInfoboxTest
{
  private static String INFOBOX ="{{Infobox country\n"+
      "| native_name = {{unbulleted list |{{lower|0.2em|{{nobold|{{lang|ko|조선민주주의인민공화국}}}} |{{small|''Chosŏn Minjujuŭi Inmin Konghwaguk''}} }}\n"+
      "| latd=23 \n"+
      "|latm=42 \n"+
      "|latNS=N \n"+
      "|longd=90 \n"+
      "|longm=21 \n"+
      "|longEW=E\n"+
      "}}";
 
 @Test
 @Ignore
 public void shoudParseSettlement() throws MalformedURLException{
   Country country = WikipediaCountryInfoboxParser.parse(INFOBOX);
   
   Assert.assertEquals("Gônôprôjatôntri Bangladesh", country.getNativeName());
   Assert.assertEquals("23.0",""+country.getCoordinate().getLatD());
   Assert.assertEquals("42.0",""+country.getCoordinate().getLatM());
   Assert.assertEquals("N", ""+country.getCoordinate().getLatNS());
   Assert.assertEquals("90.0", ""+country.getCoordinate().getLongD());
   Assert.assertEquals("21.0", ""+country.getCoordinate().getLongM());
   Assert.assertEquals("E", ""+country.getCoordinate().getLongEW());
 }
}
