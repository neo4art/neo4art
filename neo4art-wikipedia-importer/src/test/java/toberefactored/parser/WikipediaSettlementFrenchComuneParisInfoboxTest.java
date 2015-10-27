package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementFrenchComuneParisInfoboxTest {

	private static String INFOBOX ="{{Infobox French communet\n"+
			"|name = Paris \n"+
			"|latitude = 48.856667\n"+
			"|longitude = 2.350833\n"+
			"|website = [http://www.paris.fr paris.fr]\n"+
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement frenchComune = WikipediaSettlementFinnishFormerInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Paris", frenchComune.getName());
		Assert.assertEquals("48.856667",""+frenchComune.getCoordinates().getLatD());
		Assert.assertEquals("2.350833", ""+frenchComune.getCoordinates().getLongD());
		Assert.assertEquals(new URL("http://www.paris.fr"), frenchComune.getWebsite());
	}
	
}
