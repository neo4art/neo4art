package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementTownATGmundenInfoboxTest
{
  private static String INFOBOX ="{{Infobox Town AT|\n"+
      "name=Gmunden|\n"+
      "lat_deg=47|\n"+
      "lat_min=55|\n"+
      "lat_sec=05|\n"+
      "lat_hem=N|\n"+
      "lon_deg=13|\n"+
      "lon_min=47|\n"+
      "lon_sec=58|\n"+
      "lon_hem=E|\n"+
      "}}";
  
  
@Test
@Ignore
public void shoudParseArtwork() throws MalformedURLException{
  Settlement settlement = WikipediaSettlementTownAtInfoboxParser.parse(INFOBOX);
  
  Assert.assertEquals("Gmunden",settlement.getName());
  Assert.assertEquals("47.0",""+settlement.getCoordinates().getLatD());
  Assert.assertEquals("55.0",""+settlement.getCoordinates().getLatM());
  Assert.assertEquals("5.0",""+settlement.getCoordinates().getLatS());
  Assert.assertEquals("N",""+settlement.getCoordinates().getLatNS());
  Assert.assertEquals("13.0",""+settlement.getCoordinates().getLongD());
  Assert.assertEquals("47.0",""+settlement.getCoordinates().getLongM());
  Assert.assertEquals("58.0",""+settlement.getCoordinates().getLongS());
  Assert.assertEquals("E",""+settlement.getCoordinates().getLongEW());

}
}
