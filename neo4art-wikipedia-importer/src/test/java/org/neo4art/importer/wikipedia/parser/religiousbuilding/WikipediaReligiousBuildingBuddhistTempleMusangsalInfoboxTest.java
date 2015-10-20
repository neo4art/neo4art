package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;

import toberefactored.parser.WikipediaReligiousBuildingBuddhistTempleInfobox;

public class WikipediaReligiousBuildingBuddhistTempleMusangsalInfoboxTest {
	
	private static String INFOBOX = "{{Infobox Buddhist temple\n"+
			"| name             = Musangsa\n"+
			"| img              = Musansga.jpg\n"+ 
			"| img_size         = \n"+
			"| img_capt         = The Buddha Hall.\n"+
			"| mountain         =\n"+
			"| denomination     = [[Zen|Seon]]\n"+
			"| venerated        = \n"+
			"| founded          = \n"+
			"| closed           = \n"+
			"| founder          = \n"+
			"| founderpriest    =\n"+
			"| teacher          = Dae Bong\n"+
			"| director         = \n"+
			"| roshi            = \n"+
			"| abbot            = Mu Shim\n"+
			"| priest           = \n"+
			"| rinpoche         = \n"+
			"| reverend         = \n"+
			"| address          = 452-13, Hyanghan-ri, Eomsa-myeon, Gyeryong-si, Chungnam Prov., 320-917,\n"+
			"| country          = [[Taejon, South Korea]]\n"+
			"| coordinates      =\n"+ 
			"| website          = [http://www.musangsa.org www.musangsa.org]\n"+
			"}}";
		
		@Test
		public void shouldParseMuseumInfobox() throws MalformedURLException {
			ReligiousBuilding buddhistTemple = WikipediaReligiousBuildingBuddhistTempleInfobox.parse(INFOBOX);
			
			URL url = new URL("http://en.wikipedia.org/wiki/File:Musansga.jpg");
		
			Assert.assertEquals("Musangsa", buddhistTemple.getBuildingName());
			Assert.assertEquals(url , buddhistTemple.getImage());
			Assert.assertEquals("Buddhist temple" , buddhistTemple.getType());
			
		}
		
}
