package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

import toberefactored.parser.WikipediaSettlementTownAtInfoboxParser;

public class WikipediaSettlementKlagenfurtEisenstadtInfoboxTest {

	
	private static String INFOBOX ="{{Infobox Town AT\n"+
			"| name=Klagenfurt\n"+
			"| lat_deg=46\n"+
			"| lat_min=37\n"+
			"| lat_hem=N\n"+
			"| lon_deg=14\n"+
			"| lon_min=18\n"+
			"| lon_hem=E\n"+
			"}}";
		
		
	@Test
	public void shoudParseArtwork() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementTownAtInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Klagenfurt",settlement.getName());
		Assert.assertEquals("46.0",""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("37.0",""+settlement.getCoordinate().getLatM());
		Assert.assertEquals("N",""+settlement.getCoordinate().getLatNS());
		Assert.assertEquals("14.0",""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("18.0",""+settlement.getCoordinate().getLongM());
		Assert.assertEquals("E",""+settlement.getCoordinate().getLongEW());

	}
}
