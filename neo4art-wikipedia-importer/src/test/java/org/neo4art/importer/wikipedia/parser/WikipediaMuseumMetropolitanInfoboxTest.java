package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.parser.WikipediaMuseumInfoboxParser;

public class WikipediaMuseumMetropolitanInfoboxTest {

	private static String INFOBOX ="{{Infobox Museum\n"+
		"|name= The Metropolitan Museum of Art\n"+
		"|image= Metropolitan Museum of Art entrance NYC.JPG\n"+
		"|alt= Facade of imposing building with Greek columns. Large colored banners hang from the building's top. A crowd of people is in front.\n"+
		"|imagesize= 250px\n"+
		"|map_type= United States Manhattan\n"+
		"|map_caption= Location in Manhattan\n"+
		"|latitude= 40.779447\n"+
		"|longitude= -73.96311\n"+
		"|established= April 13, 1870<ref name=\"Met:Today\">{{Cite web| title= Today in Met History: April 13 | publisher=The Metropolitan Museum of Art | url= http://www.metmuseum.org/about-the-museum/now-at-the-met/features/2010/today-in-met-history-april-13  | accessdate=  2015-01-16}}</ref><ref name=\"Met:About\">{{Cite journal | title=  The Metropolitan Museum of Art: About | publisher=Artinfo | year=2008 | url= http://wayback.archive.org/web/20090503030705/http://www.artinfo.com/galleryguide/19639/6185/the-metropolitan-museum-of-art-new-york/about/ | accessdate=  2013-02-18}}</ref><ref name=\"Met History\" />\n"+
		"|location= 1000 [[Fifth Avenue (Manhattan)|5th Avenue]], New York City, [[New York|NY]] 10028\n"+
		"|visitors= 5.2 million (2008)<ref name=\"Met:About\"/><br>4.9 million (2009)<ref name=AN>{{cite web|url=http://www.theartnewspaper.com/attfig/attfig09.pdf|title=Exhibition and museum attendance figures 2009|publisher=[[The Art Newspaper]]|location=London|date=April 2010|accessdate=20 May 2010}}</ref><br>5.24 million (2010)\n"+
		"* Ranked 1st nationally\n"+
		"* [[List of the most visited museums in the world|Ranked 3rd globally]]\n"+
		"|director = [[Thomas P. Campbell]]\n"+
		"|publictransit = '''Subway''': {{NYCS Lexington|time=bullets}} to [[86th Street (IRT Lexington Avenue Line)|86th Street]]<br>'''Bus''': {{NYC bus link|M1|M2|M3|M4|M79|M86}}\n"+
		"|website= {{url|www.metmuseum.org}}";

	@Test
	public void shouldParseMuseumInfobox() throws MalformedURLException {
		Museum museum = WikipediaMuseumInfoboxParser.parse(INFOBOX);
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Metropolitan_Museum_of_Art_entrance_NYC.JPG");
		
		Assert.assertEquals("The Metropolitan Museum of Art", museum.getName());
		Assert.assertEquals(url, museum.getImage());
		Assert.assertEquals("250px", museum.getImagesize());
		Assert.assertEquals("Facade of imposing building with Greek columns. Large colored banners hang from the building's top. A crowd of people is in front.", museum.getAlt());
		Assert.assertEquals("United States Manhattan", museum.getMapType());
		Assert.assertEquals("Location in Manhattan", museum.getMapCaption());
		Assert.assertEquals("40.779447",""+ museum.getCoordinate().getLatD());
		Assert.assertEquals("-73.96311",""+ museum.getCoordinate().getLongD());
		Assert.assertEquals("April 13, 1870", museum.getEstablished());
		Assert.assertEquals("1000 Fifth Avenue (Manhattan) 5th Avenue, New York City, New York NY 10028", museum.getLocation());
		Assert.assertEquals("5.2 million (2008)", museum.getVisitors());
		Assert.assertEquals("Thomas P. Campbell", museum.getDirector());
		Assert.assertEquals("www.metmuseum.org", museum.getWebsite());
		
	}
	
}
