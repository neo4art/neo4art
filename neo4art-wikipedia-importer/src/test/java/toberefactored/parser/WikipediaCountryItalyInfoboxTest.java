package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Country;

public class WikipediaCountryItalyInfoboxTest {

	private static String INFOBOX ="{{Infobox country\n"+
			"|conventional_long_name = Italian Republic\n"+
			"|native_name = {{native name|it|Repubblica itaiana<!--italiana is without\n"+ 
			"|latd=41 \n"+
			"|latm=54 \n"+
			"|latNS=N \n"+
			"|longd=12 \n"+
			"|longm=29 \n"+
			"|longEW=E\n"+
			"}}";
		
		
		@Test
		@Ignore
		public void shoudParseArtwork() throws MalformedURLException{
			Country country = WikipediaCountryInfoboxParser.parse(INFOBOX);
			
			Assert.assertEquals("Italian Republic",country.getConventionalLongName());
			Assert.assertEquals("Repubblica itaiana",country.getNativeName());
			Assert.assertEquals("41.0",""+country.getCoordinate().getLatD());
			Assert.assertEquals("54.0",""+country.getCoordinate().getLatM());
			Assert.assertEquals("N",country.getCoordinate().getLatNS());
			Assert.assertEquals("12.0",""+country.getCoordinate().getLongD());
			Assert.assertEquals("29.0",""+country.getCoordinate().getLongM());
			Assert.assertEquals("E",country.getCoordinate().getLongEW());	

		}
	
}
