package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artist;

public class WikipediaArtistPicassoInfoboxTest {
	private static String INFOBOX =
    "{{Infobox artist\n"+
	  "| name         = Pablo Picasso\n"+
	  "| Born         = Málaga Spain\n"+
	  "| image        = File:Portrait of Pablo Picasso, 1908-1909, anonymous photographer, Musée Picasso, Paris...jpg\n"+
	  "| caption      = Pablo Picasso, 1908–1909\n"+
	  "| birth_name   = Pablo, Diego, José, Francisco de Paula, Juan Nepomuceno, Maria de los Remedios, Cipriano de la Santisima Trinidad, Ruiz Picasso<ref name=\"CatRez\">[http://books.google.fr/books?ei=zvyDVN_EHJflauangugB&hl=fr&id=MlNGAQAAIAAJ&dq=Pablo+Diego+Jos%C3%A9+Francisco+picasso%2C+zervos&focus=searchwithinvolume&q=Pablo+Diego+Jos%C3%A9+Francisco Pierre Daix, Georges Boudaille, Joan Rosselet, ''Picasso, 1900-1906: catalogue raisonné de l'oeuvre peint'', Editions Ides et Calendes, 1988]</ref>\n"+
	  "| birth_date   = {{birth date|df=yes|1881|10|25}}\n"+
	  "| birth_place  = [[Málaga]], Spain\n"+
	  "| death_date   = {{death date and age|df=yes|1973|4|8|1881|10|25}}\n"+
	  "| death_place  = [[Mougins]], France\n"+
	  "| resting_place= [[Château of Vauvenargues]]\n"+
	  "| resting_place_coordinates = {{Coord|43.554142|5.604438|region:FR}}\n"+
	  "| nationality  = Spanish\n"+
	  "| field        = Painting, drawing, [[sculpture]] [[printmaking]], [[Ceramics (art)|ceramics]], [[Scenic design|stage design]], writing\n"+
	  "| training     = [[José Ruiz y Blasco]] (father), <br /> [[Real Academia de Bellas Artes de San Fernando]]\n"+
	  "| movement     = [[Cubism]], [[Surrealism]]\n"+
	  "| works        = ''[[Les Demoiselles d'Avignon]]'' (1907)<br />''[[Guernica (painting)|Guernica]]'' (1937)<br />''[[The Weeping Woman]]'' (1937)\n"+
	  "| patrons      =\n"+
	  "| awards       =\n"+
	  "| spouse      = [[Olga Khokhlova]] (1918–55)<br>[[Jacqueline Roque]] (1961–73)\n"+
	  "}}";

	
	@Test
	public void shouldParseArtistInfobox() throws MalformedURLException {
		Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
		Assert.assertEquals("Pablo Picasso", artist.getName());
	}
}
