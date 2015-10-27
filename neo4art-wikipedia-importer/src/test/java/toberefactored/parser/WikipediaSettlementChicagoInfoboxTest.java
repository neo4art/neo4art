package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.WikipediaSettlementInfoboxParser;

public class WikipediaSettlementChicagoInfoboxTest {
	
	private static String INFOBOX ="{{Infobox settlement\n"+
			"| name                    = Chicago\n"+
			"| settlement_type         = [[City (Illinois)|City]]\n"+
			"| latd  =  41 \n"+
			"| latm  = 50 \n"+
			"| lats  = 15 \n"+
			"| latNS  = N\n"+
			"| longd = 087 \n"+
			"| longm = 40 \n"+
			"| longs = 55 \n"+
			"| longEW = W\n"+
			"| website = {{URL|www.cityofchicago.org}}\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Chicago", settlement.getName());
		Assert.assertEquals("41.0",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("50.0", ""+settlement.getCoordinates().getLatM());
		Assert.assertEquals("15.0", ""+settlement.getCoordinates().getLatS());
		Assert.assertEquals("N", settlement.getCoordinates().getLatNS());
		Assert.assertEquals("87.0", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals("40.0", ""+settlement.getCoordinates().getLongM());
		Assert.assertEquals("55.0", ""+settlement.getCoordinates().getLongS());
		Assert.assertEquals("W", settlement.getCoordinates().getLongEW());
		Assert.assertEquals(new URL("http://www.cityofchicago.org"), settlement.getWebsite());
	}
}
