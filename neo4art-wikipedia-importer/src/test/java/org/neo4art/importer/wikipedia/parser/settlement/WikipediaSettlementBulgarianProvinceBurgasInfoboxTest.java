package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.settlement.WikipediaSettlementBulgarianProvinceInfoboxParser;

public class WikipediaSettlementBulgarianProvinceBurgasInfoboxTest {

	private static String INFOBOX ="{{Infobox Bulgarian province\n"+
			"| name             = Burgas Provincen\n"+
			"| website          = [http://www.bsregion.org/ www.bsregion.org]\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementBulgarianProvinceInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Burgas Provincen", settlement.getName());
		Assert.assertEquals("http://www.bsregion.org/", settlement.getWebsite());
		Assert.assertEquals("Bulgarian province", settlement.getType());
	}
	
}
