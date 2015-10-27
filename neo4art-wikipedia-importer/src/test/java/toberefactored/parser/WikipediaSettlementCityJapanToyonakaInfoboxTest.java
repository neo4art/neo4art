package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

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
		Assert.assertEquals("34.0",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("47.0", ""+settlement.getCoordinates().getLatM());
		Assert.assertEquals("135.0", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals("28.0", ""+settlement.getCoordinates().getLongM());
		Assert.assertEquals(new URL("http://www.city.toyonaka.osaka.jp/"), settlement.getWebsite());
	}
	
}
