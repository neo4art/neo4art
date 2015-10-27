package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementBulgarianProvinceBurgasInfoboxTest {

	private static String INFOBOX =
	  "{{Infobox Bulgarian province\n"+
		"| name             = Burgas Provincen\n"+
		"| website          = [http://www.bsregion.org/ www.bsregion.org]\n"+
		"}}";
	
	@Test
	public void shoudParseSettlementBulgarianProvinceInfobox() throws MalformedURLException{
	  
		Settlement settlement = WikipediaSettlementBulgarianProvinceInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Burgas Provincen", settlement.getName());
		Assert.assertEquals(new URL("http://www.bsregion.org/"), settlement.getWebsite());
	}
}
