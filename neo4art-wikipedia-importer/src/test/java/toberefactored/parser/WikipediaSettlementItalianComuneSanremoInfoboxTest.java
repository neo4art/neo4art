package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementItalianComuneSanremoInfoboxTest {

	private static String INFOBOX ="{{Infobox Italian comune\n"+
			"| name                = Sanremo\n"+
			"| latd  = 43 \n"+
			"|latm  = 49 \n"+
			"|lats  =  \n"+
			"|latNS  = N\n"+
			"| longd = 7 \n"+
			"|longm = 47 \n"+
			"|longs =   \n"+
			"|longEW = E \n"+
			"| website             = [http://www.comunedisanremo.it/ comunedisanremo.it]\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementItalianComuneInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Sanremo", settlement.getName());
		Assert.assertEquals("43.0",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("49.0", ""+settlement.getCoordinates().getLatM());
		Assert.assertEquals("N", settlement.getCoordinates().getLatNS());
		Assert.assertEquals("7.0", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals("47.0", ""+settlement.getCoordinates().getLongM());
		Assert.assertEquals("E", settlement.getCoordinates().getLongEW());
		Assert.assertEquals(new URL("http://www.comunedisanremo.it/"), settlement.getWebsite());
	}
}
