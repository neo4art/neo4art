package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.religiousBuilding.WikipediaReligiousBuildingSpanishMissionsInTheAmericasInfobox;

public class WikipediaReligiousBuildingSpanishAmericansCapistranoInfoboxTest {

	private static String INFOBOX = "{{Infobox Missions\n"+
			"| image=Mission San Juan Capistrano.jpg\n"+
			"| name=Mission San Juan Capistrano\n"+
			"| lat_degrees = 33\n"+
			"| lat_minutes = 30\n"+
			"| lat_seconds = 10\n"+
			"| lat_direction = N\n"+
			"| long_degrees = 117\n"+
			"| long_minutes = 39\n"+
			"| long_seconds = 46\n"+
			"| long_direction = W\n"+
			"}}";
	
	@Test
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		ReligiousBuilding spanishMissionsInTheAmericans = WikipediaReligiousBuildingSpanishMissionsInTheAmericasInfobox.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Mission_San_Juan_Capistrano.jpg");
	
		Assert.assertEquals("Mission San Juan Capistrano", spanishMissionsInTheAmericans.getBuildingName());
		Assert.assertEquals(url , spanishMissionsInTheAmericans.getImage());
		Assert.assertEquals("33.0",""+spanishMissionsInTheAmericans.getCoordinates().getLatD());
		Assert.assertEquals("30.0", ""+spanishMissionsInTheAmericans.getCoordinates().getLatM());
		Assert.assertEquals("10.0", ""+spanishMissionsInTheAmericans.getCoordinates().getLatS());
		Assert.assertEquals("N", spanishMissionsInTheAmericans.getCoordinates().getLatNS());
		Assert.assertEquals("117.0", ""+spanishMissionsInTheAmericans.getCoordinates().getLongD());
		Assert.assertEquals("39.0", ""+spanishMissionsInTheAmericans.getCoordinates().getLongM());
		Assert.assertEquals("46.0", ""+spanishMissionsInTheAmericans.getCoordinates().getLongS());
		Assert.assertEquals("W", spanishMissionsInTheAmericans.getCoordinates().getLongEW());
		Assert.assertEquals("Missions" , spanishMissionsInTheAmericans.getType());
		
	}
}
