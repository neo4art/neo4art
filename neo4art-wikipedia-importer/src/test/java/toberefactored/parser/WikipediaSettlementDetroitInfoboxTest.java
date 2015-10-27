package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.WikipediaSettlementInfoboxParser;

public class WikipediaSettlementDetroitInfoboxTest {

	private static String INFOBOX ="{{Infobox settlement\n"+
		"| name                    = Detroit\n"+
		"| settlement_type         = [[City (Michigan)|City]]\n"+
		"| official_name           = City of Detroit\n"+
		"| latd  =  42 \n"+
		"| latm  = 19 \n"+
		"| lats  = 53 \n"+
		"| latNS  = N\n"+
		"| longd = 083 \n"+
		"| longm = 02 \n"+
		"| longs = 45 \n"+
		"| longEW = W\n"+
		"| website = [http://www.detroitmi.gov/ DetroitMI.gov]\n"+
		"}}";
		
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Detroit", settlement.getName());
		Assert.assertEquals("City of Detroit", settlement.getOfficialName());
		Assert.assertEquals("42.0",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("19.0", ""+settlement.getCoordinates().getLatM());
		Assert.assertEquals("53.0", ""+settlement.getCoordinates().getLatS());
		Assert.assertEquals("N", settlement.getCoordinates().getLatNS());
		Assert.assertEquals("83.0", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals("2.0", ""+settlement.getCoordinates().getLongM());
		Assert.assertEquals("45.0", ""+settlement.getCoordinates().getLongS());
		Assert.assertEquals("W", settlement.getCoordinates().getLongEW());
		Assert.assertEquals(new URL("http://www.detroitmi.gov/"), settlement.getWebsite());
	}
}
