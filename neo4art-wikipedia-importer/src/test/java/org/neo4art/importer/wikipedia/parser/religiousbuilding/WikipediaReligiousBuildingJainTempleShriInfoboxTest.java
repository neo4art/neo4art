package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;

import toberefactored.parser.WikipediaReligiousBuildingJainTempleInfobox;

public class WikipediaReligiousBuildingJainTempleShriInfoboxTest {
	
	private static String INFOBOX = "{{Infobox Jain temple\n"+
			"|name                 = Shri Mahavirji Temple\n"+
			"|image                = Mahavirji Temple.JPG\n"+
			"|image_size           = 270px\n"+
			"|alt                  = \n"+
			"|caption              = Shri Mahavirji Temple, Rajasthan\n"+
			"|pushpin_map          = Rajasthan\n"+
			"|latd = 26\n"+
			"| latm =  41\n"+
			"| lats =  43.7\n"+
			"| latNS = N\n"+
			"|longd= 76\n"+
			"| longm= 55\n"+
			"| longs = 48.78\n"+
			"| longEW = E       \n"+ 
			"}}";
		
		@Test
		public void shouldParseMuseumInfobox() throws MalformedURLException {
			ReligiousBuilding jainTemple = WikipediaReligiousBuildingJainTempleInfobox.parse(INFOBOX);
			
			URL url = new URL("http://en.wikipedia.org/wiki/File:Mahavirji_Temple.JPG");
		
			Assert.assertEquals("Shri Mahavirji Temple", jainTemple.getBuildingName());
			Assert.assertEquals(url , jainTemple.getImage());
			Assert.assertEquals("26.0",""+jainTemple.getCoordinates().getLatD());
			Assert.assertEquals("41.0", ""+jainTemple.getCoordinates().getLatM());
			Assert.assertEquals("43.7", ""+jainTemple.getCoordinates().getLatS());
			Assert.assertEquals("N", jainTemple.getCoordinates().getLatNS());
			Assert.assertEquals("76.0", ""+jainTemple.getCoordinates().getLongD());
			Assert.assertEquals("55.0", ""+jainTemple.getCoordinates().getLongM());
			Assert.assertEquals("48.78", ""+jainTemple.getCoordinates().getLongS());
			Assert.assertEquals("E", jainTemple.getCoordinates().getLongEW());
			Assert.assertEquals("Jain temple" , jainTemple.getType());
			
		}
}
