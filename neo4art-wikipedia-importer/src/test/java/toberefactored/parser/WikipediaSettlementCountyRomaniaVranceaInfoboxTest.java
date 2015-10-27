package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementCountyRomaniaVranceaInfoboxTest {

	private static String INFOBOX ="{{Infobox County Romania\n"+
			"|name= Vrancea\n"+
			"|latd = 45.79\n"+
			"|longd = 26.97\n"+
			"|webc=http://www.cjvrancea.ro/\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementCountyRomaniaInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Vrancea", settlement.getName());
		Assert.assertEquals("45.79",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("26.97", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals(new URL("http://www.cjvrancea.ro/"), settlement.getWebsite());
	}
}
