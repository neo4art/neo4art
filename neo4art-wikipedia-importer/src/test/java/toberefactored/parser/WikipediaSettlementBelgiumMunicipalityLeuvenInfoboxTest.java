package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementBelgiumMunicipalityLeuvenInfoboxTest {
	
	private static String INFOBOX ="{{Infobox Belgium Municipality\n"+
			 "|name=Leuven\n"+
			 "|lat_deg=50\n"+
			 "|lat_min=53\n"+
			 "|lon_deg=04\n"+
			 "|lon_min=42\n"+		 
			 "|web=[http://www.leuven.be/ www.leuven.be]\n"+ 
			"}}";
	
	@Test
	public void shoudParseSettlement() throws MalformedURLException{
		Settlement settlement = WikipediaSettlementBelgiumSettlementInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Leuven", settlement.getName());
		Assert.assertEquals("50.0",""+settlement.getCoordinates().getLatD());
		Assert.assertEquals("53.0",""+settlement.getCoordinates().getLatM());
		Assert.assertEquals("4.0", ""+settlement.getCoordinates().getLongD());
		Assert.assertEquals("42.0", ""+settlement.getCoordinates().getLongM());
		Assert.assertEquals(new URL("http://www.leuven.be/"), settlement.getWebsite());
	}
}
