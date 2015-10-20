package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

import toberefactored.parser.WikipediaSettlementCapeVerdeInfoboxParser;

public class WikipediaSettlementCapeVerdeSettlementInfoboxTest {

	private static String INFOBOX ="{{Infobox Cape Verde settlement\n"+
			"| name       =  \n"+
			"| latd       = 14.987\n"+
			"| longd      = -24.305\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementCapeVerdeInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("14.987", ""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("-24.305", ""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("Cape Verde settlement", settlement.getType());
	}
	
}
