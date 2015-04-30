package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.settlement.WikipediaSettlementCountyRomaniaInfoboxParser;

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
		Assert.assertEquals("45.79",""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("26.97", ""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("http://www.cjvrancea.ro", settlement.getWebsite());
		Assert.assertEquals("County Romania", settlement.getType());
	}
}
