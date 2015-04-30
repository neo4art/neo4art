package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.settlement.WikipediaSettlementUKPlaceInfoboxParser;

public class WikipediaSettlementUKPlaceTemplecombeInfoboxTest {

	
	private static String INFOBOX ="{{infobox UK place\n"+
			"|country= England\n"+
			"|latitude= 50.999982\n"+
			"|longitude= -2.415075\n"+
			"|official_name= Templecombe\n"+
			"}}";
		
		
	@Test
	public void shoudParseArtwork() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementUKPlaceInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Templecombe",settlement.getOfficialName());
		Assert.assertEquals("50.999982",""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("-2.415075",""+settlement.getCoordinate().getLongD());

	}
}
