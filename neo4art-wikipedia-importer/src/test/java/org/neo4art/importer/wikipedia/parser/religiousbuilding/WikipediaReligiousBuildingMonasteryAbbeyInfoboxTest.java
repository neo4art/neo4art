package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.religiousBuilding.WikipediaReligiousBuildingMonasteryInfobox;

public class WikipediaReligiousBuildingMonasteryAbbeyInfoboxTest {
	
	private static String INFOBOX = "{{Infobox monastery\n"+
			"| name           = Example Abbey\n"+
			"| image          = Mount Grace Priory.jpg\n"+
			"| latd  = 51 \n"+
			"| latm  = 11 \n"+
			"| lats  = 33 \n"+
			"| latNS  = N\n"+
			"| longd = 0  \n"+
			"| longm = 16 \n"+
			"| longs = 19 \n"+
			"| longEW = E\n"+
			"}}";
	
	@Test
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		ReligiousBuilding monastery = WikipediaReligiousBuildingMonasteryInfobox.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Mount_Grace_Priory.jpg");
	
		Assert.assertEquals("Example Abbey", monastery.getBuildingName());
		Assert.assertEquals(url , monastery.getImage());
		Assert.assertEquals("51.0",""+monastery.getCoordinates().getLatD());
		Assert.assertEquals("11.0", ""+monastery.getCoordinates().getLatM());
		Assert.assertEquals("33.0", ""+monastery.getCoordinates().getLatS());
		Assert.assertEquals("N", monastery.getCoordinates().getLatNS());
		Assert.assertEquals("0.0", ""+monastery.getCoordinates().getLongD());
		Assert.assertEquals("16.0", ""+monastery.getCoordinates().getLongM());
		Assert.assertEquals("19.0", ""+monastery.getCoordinates().getLongS());
		Assert.assertEquals("E", monastery.getCoordinates().getLongEW());
		Assert.assertEquals("monastery" , monastery.getType());
		
	}
}
