package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.settlement.WikipediaSettlementDistrictPTInfoboxParser;

public class WikipediaSettlementDistrictPTAveiroInfoboxTest {

	private static String INFOBOX ="{{Infobox District pt|\n"+
			"|official_name   = District of Aveiro\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementDistrictPTInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("District of Aveiro", settlement.getOfficialName());
		Assert.assertEquals("District pt", settlement.getType());
	}
}
