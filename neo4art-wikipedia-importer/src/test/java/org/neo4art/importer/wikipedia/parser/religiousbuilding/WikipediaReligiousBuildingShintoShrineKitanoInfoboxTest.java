package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.religiousBuilding.WikipediaReligiousBuildingShintoShrineInfobox;

public class WikipediaReligiousBuildingShintoShrineKitanoInfoboxTest {

	private static String INFOBOX = "{{Infobox Shinto shrine\n"+
			"| name             = Kitano Tenmangū<br>北野天満宮\n"+
			"| image            = Kitano-tenmangu Kyoto Japan41s3s4592.jpg\n"+
			"| latd = 35\n"+
			"| latm = 01\n"+
			"| lats = 52\n"+
			"| latNS =N\n"+
			"| longd=135 \n"+
			"| longm= 44\n"+
			"| longs= 07\n"+
			"| longEW=E\n"+
			"}}";
	
	@Test
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		ReligiousBuilding shintoShrine = WikipediaReligiousBuildingShintoShrineInfobox.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Kitano-tenmangu_Kyoto_Japan41s3s4592.jpg");
	
		Assert.assertEquals("Kitano Tenmangū", shintoShrine.getBuildingName());
		Assert.assertEquals(url , shintoShrine.getImage());
		Assert.assertEquals("35.0",""+shintoShrine.getCoordinates().getLatD());
		Assert.assertEquals("1.0", ""+shintoShrine.getCoordinates().getLatM());
		Assert.assertEquals("52.0", ""+shintoShrine.getCoordinates().getLatS());
		Assert.assertEquals("N", shintoShrine.getCoordinates().getLatNS());
		Assert.assertEquals("135.0", ""+shintoShrine.getCoordinates().getLongD());
		Assert.assertEquals("44.0", ""+shintoShrine.getCoordinates().getLongM());
		Assert.assertEquals("7.0", ""+shintoShrine.getCoordinates().getLongS());
		Assert.assertEquals("E", shintoShrine.getCoordinates().getLongEW());
		Assert.assertEquals("Shinto shrine" , shintoShrine.getType());
		
	}
}
