package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementChileanRegionSantiagoInfoboxTest {
	
	private static String INFOBOX ="{{Infobox Chilean region\n"+
			"| name                    = Santiago Metropolitan Region\n"+
			"| latd = 33 \n"+
			"| latm = 26 \n"+
			"| lats = 16 \n"+
			"| latNS = S\n"+
			"| longd = 70 \n"+
			"| longm = 39 \n"+
			"| longs = 01 \n"+
			"| longEW = W\n"+
			"| website                 = [http://www.gobiernosantiago.cl/ Gobierno Regional Metropolitano de Santiago] {{es}}\n"+
			"}}";
	
	@Test
	public void shoudParseSettlementChileanRegionInfobox() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementChileanRegionInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Santiago Metropolitan Region", settlement.getName());
		Assert.assertEquals("33.0",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("26.0", ""+settlement.getCoordinates().getLatM());
		Assert.assertEquals("16.0", ""+settlement.getCoordinates().getLatS());
		Assert.assertEquals("S", settlement.getCoordinates().getLatNS());
		Assert.assertEquals("70.0", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals("39.0", ""+settlement.getCoordinates().getLongM());
		Assert.assertEquals("1.0", ""+settlement.getCoordinates().getLongS());
		Assert.assertEquals("W", settlement.getCoordinates().getLongEW());
		Assert.assertEquals(new URL("http://www.gobiernosantiago.cl/"), settlement.getWebsite());
	}
}
