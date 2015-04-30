package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Country;
import org.neo4art.importer.wikipedia.parser.WikipediaCountryInfoboxParser;

public class WikipediaCountryAustriaInfoboxTest {

	private static String INFOBOX ="{{Infobox country\n"+
			"| conventional_long_name = Republic of Austria\n"+
			"| native_name = {{native name|de|Republik Österreich}}\n"+
			"| latd=48 \n"+
			"|latm=12 \n"+
			"|latNS=N \n"+
			"|longd=16 \n"+
			"|longm=21 \n"+
			"|longEW=E\n"+
			"}}";
		
		
		@Test
		public void shoudParseArtwork() throws MalformedURLException{
			Country country = WikipediaCountryInfoboxParser.parse(INFOBOX);
			
			Assert.assertEquals("Republik Österreich",country.getNativeName());
			Assert.assertEquals("Republic of Austria",country.getConventionalLongName());
			Assert.assertEquals("48.0",""+country.getCoordinate().getLatD());
			Assert.assertEquals("12.0",""+country.getCoordinate().getLatM());
			Assert.assertEquals("N",country.getCoordinate().getLatNS());
			Assert.assertEquals("16.0",""+country.getCoordinate().getLongD());
			Assert.assertEquals("21.0",""+country.getCoordinate().getLongM());
			Assert.assertEquals("E",country.getCoordinate().getLongEW());	

		}
	
}
