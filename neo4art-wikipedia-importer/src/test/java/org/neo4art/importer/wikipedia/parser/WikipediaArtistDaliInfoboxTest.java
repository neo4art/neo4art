package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ArtMovement;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.importer.wikipedia.parser.WikipediaArtistInfoboxParser;

public class WikipediaArtistDaliInfoboxTest {

	private static String INFOBOX ="{{Infobox artist\n"+
		"| name        = Salvador Dalí\n"+
		"| image       = Salvador Dalí 1939.jpg\n"+
		"| caption     = Salvador Dalí photographed by [[Carl Van Vechten]] on November 29, 1939\n"+
		"| birth_name  = Salvador Domingo Felipe Jacinto Dalí i Domènech\n"+
		"| birth_date  = {{birth date|1904|5|11|mf=y}}\n"+
		"| birth_place = [[Figueres]], [[Catalonia]], [[Spain]]\n"+
		"| death_date  = {{death date and age|1989|01|23|1904|05|11}}\n"+
		"| death_place = Figueres, Catalonia, Spain\n"+
		"| resting_place = [[Crypt]] at [[Dalí Theatre and Museum]], Figueres\n"+
		"| nationality = Spanish\n"+
		"| religion    = Roman Catholic\n"+
		"| field       = [[Painting]], [[Drawing]], [[Photography]], [[Sculpture]], [[Writing]], [[Film]]\n"+
		"| training    = [[Real Academia de Bellas Artes de San Fernando|San Fernando School of Fine Arts]], [[Madrid]], Spain\n"+
		"| movement    = [[Cubism]], [[Dada]], [[Surrealism]]\n"+
		"| works       = ''[[The Persistence of Memory]]'' (1931)<br />''Face of Mae West Which May Be Used as an Apartment'', (1935)<br />''[[Soft Construction with Boiled Beans (Premonition of Civil War)]]'' (1936)<br />''[[Swans Reflecting Elephants]]'' (1937)<br />''Ballerina in a Death's Head'' (1939)<br />''[[Dream Caused by the Flight of a Bee Around a Pomegranate a Second Before Awakening]]'' (1944) <br />''[[The Temptation of St. Anthony by Salvador Dali|The Temptation of St. Anthony]]'' (1946)<br />''[[The Elephants]]'' (1948)<br />''[[Galatea of the Spheres]]'' (1952)<br />''[[Crucifixion (Corpus Hypercubus)]]'' (1954)\n"+
		"| spouse      = [[Gala Dalí]] (Elena Ivanovna Diakonova)\n"+
		"| patrons     =\n"+
		"| awards      =\n"+
		"}}";


	@Test
	public void shouldParseArtistInfobox() throws MalformedURLException {
		Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
	
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy M dd");	
		URL url = new URL("http://en.wikipedia.org/wiki/File:Salvador_Dalí_1939.jpg");
		
		String test = "";
		ArrayList<Artwork> work = artist.getNotableWorks();
		for(int i = 0; i < work.size(); i++){
			test = test + work.get(i).getTitle() +" ";
		}
		int leng = test.length();
		test = test.substring(0, leng-1);
		
		String movTest = "";
		ArrayList<ArtMovement> mov = artist.getMovement();
		for(int i = 0; i < mov.size(); i++){
			movTest = movTest + mov.get(i).getName() +",";
		}
		int lengt = movTest.length();
		movTest = movTest.substring(0, lengt-1);
		
		Assert.assertEquals("Salvador Dalí", artist.getName());
		Assert.assertEquals("Figueres", artist.getDeathPlace());
		Assert.assertEquals("1904 5 11", formatDate.format(artist.getBirthDate().getTime()));
		Assert.assertEquals("Figueres, Catalonia, Spain", artist.getBirthPlace());
		Assert.assertEquals("1989 1 23",formatDate.format(artist.getDeathDate().getTime()));
		Assert.assertEquals("Cubism, Dada, Surrealism", movTest);
		Assert.assertEquals("Real Academia de Bellas Artes de San Fernando|| San Fernando School of Fine Arts, Madrid, Spain", artist.getTrainer());
		Assert.assertEquals("The Persistence of Memory (1931) Face of Mae West Which May Be Used as an Apartment, (1935) Soft Construction with Boiled Beans (Premonition of Civil War) (1936) Swans Reflecting Elephants (1937) Ballerina in a Deaths Head (1939) Dream Caused by the Flight of a Bee Around a Pomegranate a Second Before Awakening (1944)  The Temptation of St. Anthony by Salvador Dali The Temptation of St. Anthony (1946) The Elephants (1948) Galatea of the Spheres (1952) Crucifixion (Corpus Hypercubus) (1954)", test);
		Assert.assertEquals(url, artist.getImage());
		Assert.assertEquals("Salvador Dalí photographed by Carl Van Vechten on November 29, 1939", artist.getCaption());
	
	}
	
}
