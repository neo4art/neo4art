package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.WikipediaSettlementInfoboxParser;

public class WikipediaSettlementLondonInfoboxTest {

	
	private static String INFOBOX ="{{Infobox settlement\n"+
			"| name                    = London\n"+
			"| latd  =  51 \n"+
			"| latm  = 30 \n"+
			"| lats  = 26 \n"+
			"| latNS  = N\n"+
			"| longd = 0 \n"+
			"| longm = 7 \n"+
			"| longs = 39 \n"+
			"| longEW = W\n"+
			"}}";
			
		@Test
		public void shoudParseSettlement() throws MalformedURLException{
		  
			Settlement settlement = WikipediaSettlementInfoboxParser.parse(INFOBOX);
			
			Assert.assertEquals("London", settlement.getName());
			Assert.assertEquals("51.0",""+settlement.getCoordinates().getLatD());
			Assert.assertEquals("30.0", ""+settlement.getCoordinates().getLatM());
			Assert.assertEquals("26.0", ""+settlement.getCoordinates().getLatS());
			Assert.assertEquals("N", settlement.getCoordinates().getLatNS());
			Assert.assertEquals("0.0", ""+settlement.getCoordinates().getLongD());
			Assert.assertEquals("7.0", ""+settlement.getCoordinates().getLongM());
			Assert.assertEquals("39.0", ""+settlement.getCoordinates().getLongS());
			Assert.assertEquals("W", settlement.getCoordinates().getLongEW());
		}
}
