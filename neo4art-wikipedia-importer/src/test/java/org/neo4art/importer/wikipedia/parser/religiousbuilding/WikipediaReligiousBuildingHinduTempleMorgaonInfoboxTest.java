package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;

import toberefactored.parser.WikipediaReligiousBuildingHinduTempleInfobox;

public class WikipediaReligiousBuildingHinduTempleMorgaonInfoboxTest {
	
	private static String INFOBOX = "{{Infobox Hindu temple\n"+
			"|name                 = Shri Mayureshwar Mandir, Morgaon\n"+
			"|image                = Morgaon.jpg\n"+
			"|image_size           = 250\n"+
			"|image_alt            =\n"+
			"|caption              = The shikara of the temple\n"+
			"|pushpin_map          = Maharashtra \n"+
			"|latd = 18\n"+
			"|latm =  16\n"+
			"|lats =  33.8\n"+
			"|latNS = N\n"+
			"|longd= 74\n"+
			"|longm= 19\n"+
			"|longs = 17\n"+
			"|longEW = E\n"+
			"|map_caption          = Location within Maharashtra\n"+
			"|map_size             = 250\n"+
			"|other_names          =\n"+
			"|marathi              = श्री मयूरेश्वर मंदीर\n"+
			"|sanskrit_translit    = Śri Mayūreśvara Mandira\n"+
			"|country              = India\n"+
			"|state/province       = [[Maharashtra]]\n"+
			"|district             = Pune District\n"+
			"|locale               = Morgaon\n"+
			"|primary_deity        = [[Ganesha]] as Mayureshwar or Moreshwar\n"+
			"|important_festivals  = [[Ganesh Chaturthi]], [[Ganesh Jayanti]]\n"+
			"|architecture         = [[Mandir]] architecture\n"+
			"|number_of_temples    =\n"+
			"|number_of_monuments  =\n"+
			"|inscriptions         =\n"+
			"|date_built           = Before 17th century AD\n"+
			"|creator              = Unknown\n"+
			"}}";
		
		@Test
		public void shouldParseMuseumInfobox() throws MalformedURLException {
			ReligiousBuilding hinduTemple = WikipediaReligiousBuildingHinduTempleInfobox.parse(INFOBOX);
			
			URL url = new URL("http://en.wikipedia.org/wiki/File:Morgaon.jpg");
		
			Assert.assertEquals("Shri Mayureshwar Mandir, Morgaon", hinduTemple.getBuildingName());
			Assert.assertEquals(url , hinduTemple.getImage());
			Assert.assertEquals("18.0",""+hinduTemple.getCoordinates().getLatD());
			Assert.assertEquals("16.0", ""+hinduTemple.getCoordinates().getLatM());
			Assert.assertEquals("33.8", ""+hinduTemple.getCoordinates().getLatS());
			Assert.assertEquals("N", hinduTemple.getCoordinates().getLatNS());
			Assert.assertEquals("74.0", ""+hinduTemple.getCoordinates().getLongD());
			Assert.assertEquals("19.0", ""+hinduTemple.getCoordinates().getLongM());
			Assert.assertEquals("17.0", ""+hinduTemple.getCoordinates().getLongS());
			Assert.assertEquals("E", hinduTemple.getCoordinates().getLongEW());
			Assert.assertEquals("Hindu temple" , hinduTemple.getType());
			
		}
}
