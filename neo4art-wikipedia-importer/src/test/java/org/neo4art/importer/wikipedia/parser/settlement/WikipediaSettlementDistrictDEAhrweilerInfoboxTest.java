package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

import toberefactored.parser.WikipediaSettlementDistrictDEInfoboxParser;

public class WikipediaSettlementDistrictDEAhrweilerInfoboxTest {

	private static String INFOBOX ="{{Infobox German district\n"+
			"|name           = Ahrweiler\n"+
			"|url            = [http://www.kreis.aw-online.de kreis.aw-online.de]\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementDistrictDEInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Ahrweiler", settlement.getName());
		Assert.assertEquals("http://www.kreis.aw-online.de", settlement.getWebsite());
		Assert.assertEquals("German district", settlement.getType());
	}
	
}
