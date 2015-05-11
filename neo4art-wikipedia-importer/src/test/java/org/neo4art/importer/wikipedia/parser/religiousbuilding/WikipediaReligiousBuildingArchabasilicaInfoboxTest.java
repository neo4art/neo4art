package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.religiousBuilding.WikipediaReligiousBuildingInfoboxParser;

public class WikipediaReligiousBuildingArchabasilicaInfoboxTest {

	private static String INFOBOX = "{{Infobox religious building\n"+
		"|building_name=Papal Archbasilica of the Most Holy Savior and Saints John the Baptist and the Evangelist in the Lateran<br><small>{{lang|la|Archibasilica Sanctissimi Salvatoris et Sanctorum Iohannes Baptistae et Evangelistae in Laterano}} {{la icon}}<br>Omnium Urbis et Orbis Ecclesiarum Mater et Caput</small>\n"+
		"|infobox_width = \n"+
		"|image=Archbasilica of St. John Lateran HD.jpg\n"+
		"| image_size =300px\n"+
		"|caption=Façade of the Archbasilica of St. John Lateran\n"+
		"|location= [[Rome]], Italy<ref name=LateranTreaty />\n"+
		"|geo = {{coord|41|53|9.26|N|12|30|22.16|E|type:landmark|display=inline,title}}\n"+
		"|religious_affiliation=[[Roman Catholic]]\n"+
		"|rite=[[Latin Rite]]\n"+
		"|province=[[Rome]]\n"+
		"|district=\n"+
		"|consecration_year= AD 324\n"+
		"|status=[[Major basilica#Papal Basilicas|Papal]] [[major basilica]]\n"+
		"|leadership= Cardinal [[Agostino Vallini]]\n"+
		"|website= [http://www.vatican.va/various/basiliche/san_giovanni/index_it.htm Official Site]\n"+
		"|architect=[[Alessandro Galilei]]\n"+
		"|architecture_type=[[Cathedral]]\n"+
		"|architecture_style=[[Baroque]]\n"+
		"|specifications=yes\n"+
		"|facade_direction= [[ENE]]\n"+
		"| groundbreaking=AD 4th Century\n"+
		"|year_completed=\n"+
		"|construction_cost=\n"+
		"|capacity=\n"+
		"|length={{convert|140|m|ft}}\n"+
		"|width={{convert|140|m|ft}}\n"+
		"|width_nave={{convert|65|m|ft}}\n"+
		"|height_max=\n"+
		"|materials=[[Marble]], [[granite]], and [[cement]]\n"+
		"}}";
	
	@Test
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		ReligiousBuilding religiousBuilding = WikipediaReligiousBuildingInfoboxParser.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Archbasilica_of_St._John_Lateran_HD.jpg");
		
		Assert.assertEquals("Papal Archbasilica of the Most Holy Savior and Saints John the Baptist and the Evangelist in the Lateran", religiousBuilding.getBuildingName());
		Assert.assertEquals(url, religiousBuilding.getImage());
		Assert.assertEquals("300px", religiousBuilding.getImageSize());
		Assert.assertEquals("Façade of the Archbasilica of St. John Lateran", religiousBuilding.getCaption());
		Assert.assertEquals("Rome", religiousBuilding.getProvince());
		Assert.assertEquals("Roman Catholic", religiousBuilding.getReligiousAffiliation());
		Assert.assertEquals("Latin Rite" , religiousBuilding.getRite());
		Assert.assertEquals("Rome" , religiousBuilding.getProvince());
		Assert.assertEquals("AD 324" , religiousBuilding.getConsecrationYear());
		Assert.assertEquals("Agostino Vallini" , religiousBuilding.getLeadership());
		Assert.assertEquals("http://www.vatican.va/various/basiliche/san_giovanni/index_it.htm" , religiousBuilding.getWebsite());
		Assert.assertEquals("Alessandro Galilei" , religiousBuilding.getArchitect());
		Assert.assertEquals("Cathedral" , religiousBuilding.getArchitectureType());
		Assert.assertEquals("Baroque" , religiousBuilding.getArchitectureStyle());
		Assert.assertEquals("yes" , religiousBuilding.getSpecifications());
		Assert.assertEquals("ENE" , religiousBuilding.getFacadeDirection());
		Assert.assertEquals("AD 4th Century" , religiousBuilding.getGroundbreaking());
		Assert.assertEquals("140" , religiousBuilding.getLength());
		Assert.assertEquals("140" , religiousBuilding.getWidth());
		Assert.assertEquals("65" , religiousBuilding.getWidthNave());
		Assert.assertEquals("Marble, granite, and cement" , religiousBuilding.getMaterials());
		Assert.assertEquals("religious building" , religiousBuilding.getType());
	}
}
