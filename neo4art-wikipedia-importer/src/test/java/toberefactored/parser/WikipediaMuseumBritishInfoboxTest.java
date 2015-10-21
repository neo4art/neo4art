package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Museum;

public class WikipediaMuseumBritishInfoboxTest {

	private static String INFOBOX ="{{Infobox Museum\n"+
									 "| name        = British Museum\n"+
									 "| image       = British Museum from NE 2.JPG\n"+
									 "| imagesize   = 250\n"+
									 "| established = {{Start date and age|1753|df=yes}}\n"+
									 "| collection  = approx. 8 million objects<ref>{{cite web|url= http://www.britishmuseum.org/about_us/management/about_us.aspx|title= Collection size|work=British Museum}}</ref>\n"+
									 "| area        = {{convert|807000|sqft|m2|-2|abbr=on}} in<br> 94 Galleries\n"+
									 "| location    = [[Great Russell Street]], London, United Kingdom\n"+
									 "| map_type         = United Kingdom Central London\n"+
									 "| map_caption      = Location within central London\n"+
									 "| latitude         = 51.519459\n"+
									 "| longitude        = -0.126931\n"+
									 "| visitors    = 6,701,043 (2014)<ref name=Guardian>{{cite web|author=Mark Brown, arts correspondent |url=http://www.theguardian.com/culture/2014/jan/14/british-museum-record-visitor-numbers |title=The British Museum celebrates 255 years with record visitor numbers |publisher=The Guardian |date= |accessdate=15 January 2014}}</ref><br>\n"+
									"* [[Most visited museums in the United Kingdom|Ranked 1st nationally]]\n"+
									"* [[Museum#Most_visited_museums|Ranked 4th globally]]\n"+
									 "| publictransit = London Underground: [[Tottenham Court Road tube station|Tottenham Court Road]], [[Holborn tube station|Holborn]], [[Russell Square tube station|Russell Square]], and [[Goodge Street tube station|Goodge Street]] stations\n"+
									 "| website     = [http://www.britishmuseum.org/ britishmuseum.org]\n"+
									 "}}";
	
	@Test
	@Ignore
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		Museum museum = WikipediaMuseumInfoboxParser.parse(INFOBOX);
		URL url = new URL("http://en.wikipedia.org/wiki/File:British_Museum_from_NE_2.JPG");
		
		Assert.assertEquals("British Museum", museum.getName());
		Assert.assertEquals(url, museum.getImage());
		Assert.assertEquals("250", museum.getImagesize());
		Assert.assertEquals(" 1753", museum.getEstablished());
		Assert.assertEquals("approx. 8 million objects", museum.getCollectionSize());
		Assert.assertEquals(807000, museum.getArea());
		Assert.assertEquals("United Kingdom Central London", museum.getMapType());
		Assert.assertEquals("Location within central London", museum.getMapCaption());
		Assert.assertEquals("51.519459",""+ museum.getCoordinates().getLatD());
		Assert.assertEquals("-0.126931",""+ museum.getCoordinates().getLongD());
		Assert.assertEquals("6,701,043 (2014)", museum.getVisitors());
		Assert.assertEquals("http://www.britishmuseum.org/", museum.getWebsite());
		
	}
}
