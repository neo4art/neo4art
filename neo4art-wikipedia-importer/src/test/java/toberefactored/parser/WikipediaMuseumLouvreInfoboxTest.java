package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.domain.Museum;

public class WikipediaMuseumLouvreInfoboxTest {

	private static String INFOBOX ="{{Infobox Museum\n"+
		"|name = The Louvre Museum\n"+
		"|image = Le Louvre - Aile Richelieu.jpg\n"+
		"|caption = the Richelieu wing (2005)\n"+
		"|imagesize = 225\n"+
		"|pushpin_map = Paris\n"+
		"|latitude = 48.860339\n"+
		"|longitude = 2.337599\n"+
		"|established = 1792\n"+
		"|dissolved =\n"+
		"|location = Musée du Louvre, 75001 Paris, France\n"+
		"|type = [[Art museum]], Design/Textile Museum, [[Historic site]]\n"+
		"|visitors = <br/>9.7 million (2012)<ref name=\"aecom.com\">http://www.aecom.com/deployedfiles/Internet/Capabilities/Economics/_documents/2012%20Theme%20Index%20Combined_1-1_online.pdf</ref><br />\n"+
		"*Ranked 1st nationally\n"+
		"*[[List of the most visited museums in the world|Ranked 1st globally]]\n"+
		"|director = Jean-Luc Martinez\n"+
		"|curator = Marie-Laure de Rochebrune\n"+
		"|publictransit = *[[Palais Royal - Musée du Louvre (Paris Métro)|Palais Royal – Musée du Louvre]] {{rint|paris|m|size=15}} {{rint|paris|m|1|size=15}} {{rint|paris|m|7|size=15}}\n"+
		"*[[Louvre – Rivoli (Paris Métro)|Louvre-Rivoli]] {{rint|paris|m|size=15}} {{rint|paris|m|1|size=15}}\n"+
		"|website = [http://www.louvre.fr/en/homepage www.louvre.fr]\n"+
		"}}";

	@Test
	@Ignore
	public void shoudParseMuseum() throws MalformedURLException{
		Museum museum = WikipediaMuseumInfoboxParser.parse(INFOBOX);
	
		URL url = new URL("http://en.wikipedia.org/wiki/File:Le_Louvre_-_Aile_Richelieu.jpg");
		
		Assert.assertEquals("The Louvre Museum", museum.getName());
		Assert.assertEquals(url, museum.getImage());
		Assert.assertEquals("225", museum.getImagesize());
		Assert.assertEquals("the Richelieu wing (2005)", museum.getCaption());
		Assert.assertEquals("Art museum, Design/Textile Museum, Historic site", museum.getType());
		Assert.assertEquals("1792", museum.getEstablished());
		Assert.assertEquals("Jean-Luc Martinez", museum.getDirector());
		Assert.assertEquals("Musée du Louvre, 75001 Paris, France", museum.getLocation());
		Assert.assertEquals("48.860339",""+ museum.getCoordinates().getLatD());
		Assert.assertEquals("2.337599", ""+museum.getCoordinates().getLongD());
		Assert.assertEquals("9.7 million (2012)", museum.getVisitors());
		Assert.assertEquals("Marie-Laure de Rochebrune", museum.getCurator());
		Assert.assertEquals("http://www.louvre.fr/en/homepage", museum.getWebsite());
		
	}
}
