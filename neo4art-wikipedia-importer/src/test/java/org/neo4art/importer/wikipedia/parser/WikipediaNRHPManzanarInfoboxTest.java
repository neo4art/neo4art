package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.HistoricPlace;

public class WikipediaNRHPManzanarInfoboxTest {

	private static String INFOBOX ="{{Infobox HistoricPlace \n"+
		  "| name =Manzanar War Relocation Center\n"+
		  "| image = Manzanar Flag.jpg\n"+
		  "| lat_degrees = 36\n"+
		  "| lat_minutes = 43\n"+
		  "| lat_seconds = 42\n"+
		  "| lat_direction = N\n"+
		  "| long_degrees = 118\n"+
		  "| long_minutes = 9\n"+
		  "| long_seconds = 16\n"+
		  "| long_direction = W\n"+
		  "}}";
	
	@Test
	public void shoudParseNRHP() throws MalformedURLException{
		HistoricPlace settlement = WikipediaHistoricPlaceInfoboxParser.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Manzanar_Flag.jpg");
		
		Assert.assertEquals("Manzanar War Relocation Center", settlement.getName());
		Assert.assertEquals(url, settlement.getImage());
		Assert.assertEquals("36.0", ""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("43.0", ""+settlement.getCoordinate().getLatM());
		Assert.assertEquals("42.0", ""+settlement.getCoordinate().getLatS());
		Assert.assertEquals("N", settlement.getCoordinate().getLatNS());
		Assert.assertEquals("118.0", ""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("9.0", ""+settlement.getCoordinate().getLongM());
		Assert.assertEquals("16.0", ""+settlement.getCoordinate().getLongS());
		Assert.assertEquals("W", settlement.getCoordinate().getLongEW());
		Assert.assertEquals("HistoricPlace", ""+settlement.getType());
	}
	
}
