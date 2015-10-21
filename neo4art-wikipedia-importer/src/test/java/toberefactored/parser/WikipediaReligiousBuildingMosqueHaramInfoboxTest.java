package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;

import toberefactored.parser.WikipediaReligiousBuildingMosqueInfobox;

public class WikipediaReligiousBuildingMosqueHaramInfoboxTest {

	private static String INFOBOX = "{{Infobox mosque\n"+
			"| name               =''Al-Masjid al-Haram''<br/>The Sacred Masjid\n"+
			"| image              = A packed house - Flickr - Al Jazeera English.jpg\n"+
			"| latitude           = 21.422\n"+
			"| longitude          = 39.826\n"+
			"}}";
	
	@Test
	public void shouldParseReligiousBuildingMosqueHaramInfobox() throws MalformedURLException {
		ReligiousBuilding mosque = WikipediaReligiousBuildingMosqueInfobox.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:A_packed_house_-_Flickr_-_Al_Jazeera_English.jpg");
	
		Assert.assertEquals("Al-Masjid al-Haram", mosque.getBuildingName());
		Assert.assertEquals(url , mosque.getImage());
		Assert.assertEquals("21.422",""+mosque.getCoordinates().getLatD());
		Assert.assertEquals("39.826", ""+mosque.getCoordinates().getLongD());
	}
}
