package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;

import toberefactored.parser.WikipediaReligiousBuildingTibetanBuddhistMonasteryInfobox;

public class WikipediaReligiousBuildingTibetanBuddhistMonasteryJokhangInfoboxTest {

	private static String INFOBOX =
	  "{{Infobox Tibetan Buddhist monastery\n"+
		"|name = Jokhang\n"+
		"|image = Gilt roof of the Jokhang.JPG\n"+
		"|latd = 29 \n"+
		"| latm = 39 \n"+
		"| lats = 11 \n"+
		"| latNS = N\n"+
		"|longd= 91 \n"+
		"| longm=07 \n"+
		"| longs =53\n"+
		"| longEW = E\n"+
		"}}";
	
	@Test
	public void shouldParseReligiousBuildingTibetanBuddhistMonasteryInfobox() throws MalformedURLException {
	  
		ReligiousBuilding tibetanBuddhistMonastery = WikipediaReligiousBuildingTibetanBuddhistMonasteryInfobox.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Gilt_roof_of_the_Jokhang.JPG");
	
		Assert.assertEquals("Jokhang", tibetanBuddhistMonastery.getName());
		Assert.assertEquals(url , tibetanBuddhistMonastery.getImage());
		Assert.assertEquals("29.0",""+tibetanBuddhistMonastery.getCoordinates().getLatD());
		Assert.assertEquals("39.0", ""+tibetanBuddhistMonastery.getCoordinates().getLatM());
		Assert.assertEquals("11.0", ""+tibetanBuddhistMonastery.getCoordinates().getLatS());
		Assert.assertEquals("N", tibetanBuddhistMonastery.getCoordinates().getLatNS());
		Assert.assertEquals("91.0", ""+tibetanBuddhistMonastery.getCoordinates().getLongD());
		Assert.assertEquals("7.0", ""+tibetanBuddhistMonastery.getCoordinates().getLongM());
		Assert.assertEquals("53.0", ""+tibetanBuddhistMonastery.getCoordinates().getLongS());
		Assert.assertEquals("E", tibetanBuddhistMonastery.getCoordinates().getLongEW());		
	}
	
}
