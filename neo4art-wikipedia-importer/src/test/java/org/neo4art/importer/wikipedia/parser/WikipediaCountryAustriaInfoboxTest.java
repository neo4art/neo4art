package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Country;

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
		@Ignore
		public void shoudParseArtwork() throws MalformedURLException{
			Country country = WikipediaCountryInfoboxParser.parse(INFOBOX);
			
			Assert.assertEquals("Republik Österreich",country.getNativeName());
			Assert.assertEquals("Republic of Austria",country.getConventionalLongName());
			Assert.assertEquals("48.2",""+country.getCoordinate().getLatitude());
			Assert.assertEquals("16.35",""+country.getCoordinate().getLongitude());

		}
	
}
