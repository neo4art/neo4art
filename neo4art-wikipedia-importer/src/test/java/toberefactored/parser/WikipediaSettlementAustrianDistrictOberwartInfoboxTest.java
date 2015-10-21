package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

import toberefactored.parser.WikipediaSettlementAustrianDistrictInfoboxParser;

public class WikipediaSettlementAustrianDistrictOberwartInfoboxTest {

	private static String INFOBOX ="{{Infobox Austrian district\n"+
			"| subject_name = Bezirk Oberwart\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementAustrianDistrictInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Bezirk Oberwart", settlement.getName());
	}
}
