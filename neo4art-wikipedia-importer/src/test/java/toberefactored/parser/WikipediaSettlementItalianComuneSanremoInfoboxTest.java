package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

import toberefactored.parser.WikipediaSettlementItalianComuneInfoboxParser;

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
		Assert.assertEquals("43.0",""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("49.0", ""+settlement.getCoordinate().getLatM());
		Assert.assertEquals("N", settlement.getCoordinate().getLatNS());
		Assert.assertEquals("7.0", ""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("47.0", ""+settlement.getCoordinate().getLongM());
		Assert.assertEquals("E", settlement.getCoordinate().getLongEW());
		Assert.assertEquals("http://www.comunedisanremo.it/", settlement.getWebsite());
	}
}
