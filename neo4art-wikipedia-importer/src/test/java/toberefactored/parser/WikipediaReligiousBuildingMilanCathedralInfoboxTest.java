package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.WikipediaReligiousBuildingInfoboxParser;

public class WikipediaReligiousBuildingMilanCathedralInfoboxTest {

	private static String INFOBOX = "{{Infobox religious building\n"+
		"| building_name         = Metropolitan Cathedral-Basilica of St&nbsp;Mary of the Nativity<br /><small>{{lang|it|Basilica cattedrale metropolitana di Santa Maria Nascente}} {{it icon}}</small>\n"+
		"| infobox_width         =\n"+
		"| image                 = 876MilanoDuomo.JPG\n"+
		"| image_size            =\n"+
		"| caption               = Milan Cathedral from [[Piazza del Duomo, Milan|the Square]].\n"+
		"| map_type              =\n"+
		"| map_size              =\n"+
		"| map_caption           =\n"+
		"| location              = [[Milan]], Italy\n"+
		"| geo                   = {{coord|45|27|51|N|9|11|29|E|region:IT_type:landmark|display=inline, title}}\n"+
		"| religious_affiliation = [[Catholic]]\n"+
		"| rite                  = [[Latin Rite]]\n"+
		"| province              = [[Roman Catholic Archdiocese of Milan|Archdiocese of Milan]]\n"+
		"| district              =\n"+
		"| consecration_year     = \n"+
		"| status                =\n"+
		"| functional_status     =\n"+
		"| heritage_designation  =\n"+
		"| leadership            =\n"+
		"| website               =\n"+
		"| architecture          = yes\n"+
		"| architect             =\n"+
		"| architecture_type     = \n"+
		"| architecture_style    = [[Italian Gothic architecture|Italian Gothic]]\n"+
		"| facade_direction      = West\n"+
		"| groundbreaking        = 1386\n"+
		"| year_completed        = 1965\n"+
		"| construction_cost     =\n"+
		"| specifications        = yes\n"+
		"| capacity              = 40,000\n"+
		"| length                = {{convert|158.5|m|ft|0}}\n"+
		"| width                 = {{convert|92|m|ft|0}}\n"+
		"| width_nave            = {{convert|16.75|m|ft|0}}\n"+
		"| height_max            = {{convert|108|m|ft|0}}\n"+
		"| dome_quantity         =\n"+
		"| dome_height_outer     = {{convert|65.5|m|ft|0}} <!-- is this outer or inner height? -->\n"+
		"| dome_height_inner     =\n"+
		"| dome_dia_outer        =\n"+
		"| dome_dia_inner        =\n"+
		"| spire_quantity        = 135\n"+
		"| spire_height          = {{convert|108.5|m|ft|0}}\n"+
		"| materials             = Brick with [[Candoglia]] marble\n"+
		"}}";
	
	@Test
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		ReligiousBuilding religiousBuilding = WikipediaReligiousBuildingInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Metropolitan Cathedral-Basilica of St&nbsp;Mary of the Nativity<br /><small>{{lang|it|Basilica cattedrale metropolitana di Santa Maria Nascente}} {{it icon}}</small>", religiousBuilding.getName());
		Assert.assertEquals(new URL("https://en.wikipedia.org/wiki/File:876MilanoDuomo.JPG") , religiousBuilding.getImage());
		//Assert.assertEquals("Piazza del Duomo, Milan" , religiousBuilding.getCaption());
		//Assert.assertEquals("Milan" , religiousBuilding.getLocation().getName());
    //Assert.assertEquals("45.0 27.0 51.0 N 9.0 11.0 29.0 E" , religiousBuilding.getCoordinates().getCoordinateComplete());
		//Assert.assertEquals("Catholic" , religiousBuilding.getReligiousAffiliation());
		//Assert.assertEquals("Latin Rite" , religiousBuilding.getRite());
		//Assert.assertEquals("Roman Catholic Archdiocese of Milan" , religiousBuilding.getProvince());
		//Assert.assertEquals("yes" , religiousBuilding.getArchitecture());
		//Assert.assertEquals("Italian Gothic architecture" , religiousBuilding.getArchitectureStyle());
		//Assert.assertEquals("West" , religiousBuilding.getFacadeDirection());
		//Assert.assertEquals("1386" , religiousBuilding.getGroundbreaking());
		//Assert.assertEquals("1965" , religiousBuilding.getYearCompleted());
		//Assert.assertEquals("40,000" , religiousBuilding.getCapacity());
		//Assert.assertEquals("158.5" , religiousBuilding.getLength());
		//Assert.assertEquals("92" , religiousBuilding.getWidth());
		//Assert.assertEquals("16.75" , religiousBuilding.getWidthNave());
		//Assert.assertEquals("108" , religiousBuilding.getHeightMax());
		//Assert.assertEquals("65.5" , religiousBuilding.getDomeHeightOuter());
		//Assert.assertEquals("135" , religiousBuilding.getSpireQuantity());
		//Assert.assertEquals("108.5" , religiousBuilding.getSpireHeight());
		//Assert.assertEquals("Brick with Candoglia marble" , religiousBuilding.getMaterials());
	}
}
