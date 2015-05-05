package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.HistoricSite;

public class WikipediaHistoricSiteWestminsterAbbeyInfoboxParserTest {

	private static String INFOBOX ="{{Infobox Historic Site\n"+
		"| name = Westminster Abbey\n"+
		"| image = Westminster-Abbey.JPG\n"+
		"| caption = Western façade\n"+
		"| lat_degrees = 51\n"+
		"| lat_minutes = 29\n"+
		"| lat_seconds = 58\n"+
		"| lat_direction = N\n"+
		"| long_degrees = 00\n"+
		"| long_minutes = 07\n"+
		"| long_seconds = 39\n"+
		"| long_direction = W\n"+
		"}}";
	
		@Test
		public void shoudParseArtwork() throws MalformedURLException{
			HistoricSite historicSite = WikipediaHistoricSiteInfoboxParser.parse(INFOBOX);
		
			URL url = new URL("http://en.wikipedia.org/wiki/File:Westminster-Abbey.JPG");
			
			Assert.assertEquals(url, historicSite.getImage());
			Assert.assertEquals("Westminster Abbey", historicSite.getName());
			Assert.assertEquals("Western façade", historicSite.getCaption());
			Assert.assertEquals("51.0", ""+historicSite.getCoordinate().getLatD());
			Assert.assertEquals("29.0", ""+historicSite.getCoordinate().getLatM());
			Assert.assertEquals("58.0", ""+historicSite.getCoordinate().getLatS());
			Assert.assertEquals("N", ""+historicSite.getCoordinate().getLatNS());
			Assert.assertEquals("0.0", ""+historicSite.getCoordinate().getLongD());
			Assert.assertEquals("7.0", ""+historicSite.getCoordinate().getLongM());
			Assert.assertEquals("39.0", ""+historicSite.getCoordinate().getLongS());
			Assert.assertEquals("W", ""+historicSite.getCoordinate().getLongEW());
			Assert.assertEquals("Historic Site", historicSite.getType());
			
		}
	
}
