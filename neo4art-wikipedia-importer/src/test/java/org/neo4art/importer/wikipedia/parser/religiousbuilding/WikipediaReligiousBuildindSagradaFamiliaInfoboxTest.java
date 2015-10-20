package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.parser.WikipediaMuseumInfoboxParser;

public class WikipediaReligiousBuildindSagradaFamiliaInfoboxTest {

	private static String INFOBOX ="{{Infobox religious building\n"+
		"| building_name           = Sagrada Família <br /> {{small|{{lang|ca|''Basílica i Temple Expiatori de la Sagrada Família''}}}}<br /> {{nowrap|{{small|{{lang|en|Basilica and Expiatory Church of the Holy Family}}}}}}\n"+
		"| image                   = Sagrada Familia 01.jpg\n"+
		"| image_size              = 330px\n"+
		"| alt                     =\n"+
		"| caption                 = View of the Passion Façade (Western side) in September&nbsp;2009<br />(cranes digitally removed);\n"+
		"| location                = [[Barcelona]], [[Catalonia]], [[Spain]]\n"+
		"| coordinates             = {{Coord|41|24|13|N|2|10|28|E|type:landmark_region:ES-CT_scale:10000|display=inline,title}}\n"+
		"| website                 = {{URL|http://www.sagradafamilia.cat}}\n"+
		"}}";
		
		@Test
		@Ignore
		public void shouldParseMuseumInfobox() throws MalformedURLException {
			Museum museum = WikipediaMuseumInfoboxParser.parse(INFOBOX);
			
			URL url = new URL("http://en.wikipedia.org/wiki/File:Sagrada_Familia_01.jpg");
			
			Assert.assertEquals("Sagrada Família", museum.getBuildingName());
			Assert.assertEquals(url, museum.getImage());
			Assert.assertEquals("330px", museum.getImagesize());
			Assert.assertEquals("View of the Passion Façade (Western side) in September 2009", museum.getCaption());
			Assert.assertEquals("http://www.sagradafamilia.cat", museum.getWebsite());
			
		}
	
}
