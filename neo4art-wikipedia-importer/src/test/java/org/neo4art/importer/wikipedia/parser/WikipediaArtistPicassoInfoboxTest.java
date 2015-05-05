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

public class WikipediaArtistPicassoInfoboxTest {

	private static String INFOBOX ="{{Infobox artist\n"+
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
	
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy MM dd");	
		URL url = new URL("http://en.wikipedia.org/wiki/File:Portrait_of_Pablo_Picasso,_1908-1909,_anonymous_photographer,_Musée_Picasso,_Paris...jpg");
		
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
		
		Assert.assertEquals("Pablo Picasso", artist.getName());
		Assert.assertEquals("Pablo, Diego, José, Francisco de Paula, Juan Nepomuceno, Maria de los Remedios, Cipriano de la Santisima Trinidad, Ruiz Picasso", artist.getBirthName());
		Assert.assertEquals("Málaga, Spain", artist.getBirthPlace());
		Assert.assertEquals("Mougins", artist.getDeathPlace());
		Assert.assertEquals("1881 10 25", formatDate.format(artist.getBirthDate().getTime()));
		Assert.assertEquals("1973 04 08",formatDate.format(artist.getDeathDate().getTime()));
		Assert.assertEquals("Château of Vauvenargues",artist.getRestingPlace().getMap());
		Assert.assertEquals("Cubism, Surrealism", movTest);
		Assert.assertEquals("Spanish", artist.getNationality().getCommonName());
		Assert.assertEquals(new Double(43.554142), new Double(artist.getRestingPlaceCoordinates().getLatD()));
		Assert.assertEquals(new Double(5.604438), new Double(artist.getRestingPlaceCoordinates().getLongD()));
		Assert.assertEquals("Painting, drawing, sculpture printmaking, Ceramics (art) ceramics, Scenic design stage design, writing", artist.getField());
		Assert.assertEquals("José Ruiz y Blasco (father), Real Academia de Bellas Artes de San Fernando", artist.getTrainer());
		Assert.assertEquals("Les Demoiselles dAvignon (1907) Guernica (painting) Guernica (1937) The Weeping Woman (1937)", test);
		Assert.assertEquals(url, artist.getImage());
		Assert.assertEquals("Pablo Picasso, 1908–1909", artist.getCaption());
	
	}
}
