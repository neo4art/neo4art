package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artist;

public class WikipediaArtistPicassoInfoboxTest {
  
	private static String INFOBOX =
	  "{{Infobox artist\n" +
	  "| name         = Pablo Picasso\n" +
	  "| Born         = Málaga Spain\n" +
	  "| image        = Portrait de Picasso, 1908.jpg\n" +
	  "| image_size   = 230px\n" +
	  "| caption      = Picasso in 1908\n" +
	  "| birth_name   = Pablo Diego José Francisco de Paula Juan Nepomuceno María de los Remedios Cipriano de la Santísima Trinidad Ruiz y Picasso<ref name=\"CatRez\">[http://books.google.fr/books?ei=zvyDVN_EHJflauangugB&hl=fr&id=MlNGAQAAIAAJ&dq=Pablo+Diego+Jos%C3%A9+Francisco+picasso%2C+zervos&focus=searchwithinvolume&q=Pablo+Diego+Jos%C3%A9+Francisco Pierre Daix, Georges Boudaille, Joan Rosselet, ''Picasso, 1900-1906: catalogue raisonné de l'oeuvre peint'', Editions Ides et Calendes, 1988]</ref>\n" +
	  "| birth_date   = {{birth date|df=yes|1881|10|25}}\n" +
	  "| birth_place  = [[Málaga]], Spain\n" +
	  "| death_date   = {{death date and age|df=yes|1973|4|8|1881|10|25}}\n" +
	  "| death_place  = [[Mougins]], France\n" +
	  "| resting_place= [[Château of Vauvenargues]]\n" +
	  "| resting_place_coordinates = {{Coord|43.554142|5.604438|region:FR}}\n" +
	  "| nationality  = Spanish\n" +
	  "| field        = Painting, drawing, [[sculpture]] [[printmaking]], [[Ceramics (art)|ceramics]], [[Scenic design|stage design]], writing\n" +
	  "| training     = [[José Ruiz y Blasco]] (father), <br /> [[Real Academia de Bellas Artes de San Fernando]]\n" +
	  "| movement     = [[Cubism]], [[Surrealism]]\n" +
	  "| works        = ''[[Les Demoiselles d'Avignon]]'' (1907)<br />''[[Guernica (painting)|Guernica]]'' (1937)<br />''[[The Weeping Woman]]'' (1937)\n" +
	  "| patrons      =\n" +
	  "| awards       =\n" +
	  "| spouse       = [[Olga Khokhlova]] (1918–55)<br>[[Jacqueline Roque]] (1961–73)\n" +
	  "}}";

	
	@Test
	public void shouldParsePablePicassoArtistInfobox() throws MalformedURLException {
	  
    Calendar birthDateCalendar = Calendar.getInstance();
    birthDateCalendar.set(1881, Calendar.OCTOBER, 25, 0, 0, 0);
    birthDateCalendar.set(Calendar.MILLISECOND, 0);
    
    Calendar deathDateCalendar = Calendar.getInstance();
    deathDateCalendar.set(1973, Calendar.APRIL, 8, 0, 0, 0);
    deathDateCalendar.set(Calendar.MILLISECOND, 0);
    
    Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
    
    Assert.assertEquals("Pablo Picasso", artist.getName());
    Assert.assertEquals(new URL("https://en.wikipedia.org/wiki/File:Portrait_de_Picasso,_1908.jpg"), artist.getImage());
    Assert.assertEquals(birthDateCalendar.getTimeInMillis(), artist.getBirthDate().getTime());
    Assert.assertEquals("Málaga", artist.getBirthPlace().getName());
    Assert.assertEquals("Spain", artist.getBirthPlace().getCountry());
    Assert.assertEquals(deathDateCalendar.getTimeInMillis(), artist.getDeathDate().getTime());
    Assert.assertEquals("Mougins", artist.getDeathPlace().getName());
    Assert.assertEquals("France", artist.getDeathPlace().getCountry());
    Assert.assertEquals("Spanish", artist.getNationality());
    Assert.assertEquals("Cubism", artist.getArtMovements().get(0).getName());
    Assert.assertEquals("Surrealism", artist.getArtMovements().get(1).getName());
	}
}
