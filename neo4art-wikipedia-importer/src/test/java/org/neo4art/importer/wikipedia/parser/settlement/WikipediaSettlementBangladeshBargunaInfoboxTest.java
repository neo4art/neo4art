package org.neo4art.importer.wikipedia.parser.settlement;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.settlement.WikipediaSettlementBangladeshDistrictInfoboxParser;

public class WikipediaSettlementBangladeshBargunaInfoboxTest {

	
	private static String INFOBOX ="{{Infobox Bangladesh district\n"+
			 "|name                    = Barguna\n"+
			 "| latd  = 22.1508\n"+
			 "|latm  = \n"+
			 "|lats  = \n"+
			 "|latNS  = \n"+
			 "| longd = 90.1264\n"+
			 "|longm = \n"+
			 "|longs = \n"+
			 "|longEW =\n"+ 
			 "| website                 = [http://www.dcbarguna.gov.bd www.dcbarguna.gov.bd]\n"+ 
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementBangladeshDistrictInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Barguna", settlement.getName());
		Assert.assertEquals("22.1508",""+settlement.getCoordinate().getLatD());
		Assert.assertEquals("90.1264", ""+settlement.getCoordinate().getLongD());
		Assert.assertEquals("http://www.dcbarguna.gov.bd", settlement.getWebsite());
		Assert.assertEquals("Bangladesh district", settlement.getType());
	}
}
