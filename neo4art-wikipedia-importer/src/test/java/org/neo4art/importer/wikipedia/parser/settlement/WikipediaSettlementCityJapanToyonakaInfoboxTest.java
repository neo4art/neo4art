package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.settlement.WikipediaSettlementCityJapanInfoboxParser;

public class WikipediaSettlementCityJapanToyonakaInfoboxTest {

	private static String INFOBOX ="{{Infobox City Japan\n"+
			"|Name= Toyonaka\n"+
			"|LatitudeDegrees= 34\n"+
			"|LatitudeMinutes= 47\n"+
			"|LatitudeSeconds=\n"+
			"|LongtitudeDegrees= 135\n"+
			"|LongtitudeMinutes= 28\n"+
			"|LongtitudeSeconds=\n"+
			"|CityHallLink= [http://www.city.toyonaka.osaka.jp/ Toyonaka City]\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementCityJapanInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Toyonaka", settlement.getName());
		Assert.assertEquals("34.0",""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("47.0", ""+settlement.getCoordinate().getLatM());
		Assert.assertEquals("135.0", ""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("28.0", ""+settlement.getCoordinate().getLongM());
		Assert.assertEquals("http://www.city.toyonaka.osaka.jp/", settlement.getWebsite());
		Assert.assertEquals("City Japan", settlement.getType());
	}
	
}
