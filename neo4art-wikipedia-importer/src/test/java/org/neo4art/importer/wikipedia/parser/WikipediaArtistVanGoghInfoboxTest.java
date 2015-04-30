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

public class WikipediaArtistVanGoghInfoboxTest {

	private static String INFOBOX =
		"{{Infobox artist\n" +
        "| bgcolour      = #FBEC5D\n" +
        "| name          = Vincent van Gogh\n" +
        "| image         = Vincent van Gogh - Self-Portrait - Google Art Project (454045).jpg|\n" +
        "| caption       = ''Self-Portrait'', Spring 1887, Oil on pasteboard, 42 x 33.7&nbsp;cm., [[Art Institute of Chicago]] (F 345)\n" +
        "| alt           = An intense man with close cropped hair and red beard gazes to the left.\n" +
        "| birth_date    = 30 March 1853\n" +
        "| birth_place   = [[Zundert]], [[Netherlands]]\n" +
        "| death_date    = {{Death date and age|df=yes|1890|7|29|1853|3|30}}\n" +
        "| death_place   = [[Auvers-sur-Oise]], [[French Third Republic|France]]\n" +
        "| field         = Painting, drawing\n" +
        "| training      = [[Anton Mauve]]\n" +
        "| movement      = [[Post-Impressionism]]\n" +
        "| works         =  ''[[The Starry Night|Starry Night]]'', ''[[Sunflowers (Van Gogh series)|Sunflowers]]'', ''[[Bedroom in Arles]]'', ''[[Portrait of Dr. Gachet]]'', ''[[Sien (Van Gogh series)|Sorrow]]''\n" +
        "| patron        = [[Theo van Gogh (art dealer)|Theo van Gogh]]}}";
	
	private static String works ="''[[The Starry Night|Starry Night]]'', ''[[Sunflowers (Van Gogh series)|Sunflowers]]'', ''[[Bedroom in Arles]]'', ''[[Portrait of Dr. Gachet]]'', ''[[Sien (Van Gogh series)|Sorrow]]''\n";
	
	@Test
	public void shouldParseArtistInfobox() throws MalformedURLException {
		Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy M dd");	
		URL url = new URL("http://en.wikipedia.org/wiki/File:Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_(454045).jpg");
		
		String test = "";
		ArrayList<Artwork> work = artist.getNotableWorks();
		for(int i = 0; i < work.size(); i++){
			test = test + work.get(i).getTitle() +",";
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
		
		Assert.assertEquals("Vincent van Gogh", artist.getName());
		Assert.assertEquals("Auvers-sur-Oise", artist.getDeathPlace());
		Assert.assertEquals("1853 3 30", formatDate.format(artist.getBirthDate().getTime()));
		Assert.assertEquals("Zundert, Netherlands", artist.getBirthPlace());
		Assert.assertEquals("1890 7 29",formatDate.format(artist.getDeathDate().getTime()));
		Assert.assertEquals("Post-Impressionism", movTest);
		Assert.assertEquals("The Starry Night|| Starry Night, Sunflowers (Van Gogh series)|| Sunflowers, Bedroom in Arles, Portrait of Dr. Gachet, Sien (Van Gogh series)|| Sorrow", test);
		Assert.assertEquals("Theo van Gogh (art dealer) Theo van Gogh", artist.getPatrons());
		Assert.assertEquals(url, artist.getImage());
		Assert.assertEquals("Self-Portrait, Spring 1887, Oil on pasteboard, 42 x 33.7 cm., Art Institute of Chicago (F 345)", artist.getCaption());
		Assert.assertEquals("An intense man with close cropped hair and red beard gazes to the left.", artist.getAlt());
		Assert.assertEquals("Painting, drawing", artist.getField());
		Assert.assertEquals("Anton Mauve", artist.getTrainer());
		
	}
	
	@Test
	public void infoboxMovementTest() {
		
		Assert.assertEquals("Post-Impressionism", WikipediaArtistInfoboxParser.infoboxPlaceDeath("[[Post-Impressionism]]\n"));
	}
	
	@Test
	public void infoboxWorksTest(){
		String test = "";
		ArrayList<Artwork> work = WikipediaArtistInfoboxParser.infoboxWorks(works);
		for(int i = 0; i < work.size(); i++){
			test = test + work.get(i).getTitle() +",";
		}
		int leng = test.length();
		test = test.substring(0, leng-1);
		
		Assert.assertEquals("The Starry Night|Starry Night, Sunflowers (Van Gogh series)|Sunflowers, Bedroom in Arles, Portrait of Dr. Gachet, Sien (Van Gogh series)|Sorrow", test);

	}
	
	@Test
	public void infoboxImageUrlTest() throws MalformedURLException{
		URL url = new URL("http://en.wikipedia.org/wiki/File:Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_(454045).jpg");
		
		Assert.assertEquals(url, WikipediaArtistInfoboxParser.infoboxImageUrl("Vincent van Gogh - Self-Portrait - Google Art Project (454045).jpg|\n"));

	}
	
	@Test
	public void infoboxCaptionTest() {	
		
		Assert.assertEquals("Self-Portrait, Spring 1887, Oil on pasteboard, 42 x 33.7 cm., Art Institute of Chicago (F 345)", WikipediaArtistInfoboxParser.infoboxCaption("''Self-Portrait'', Spring 1887, Oil on pasteboard, 42 x 33.7&nbsp;cm., [[Art Institute of Chicago]] (F 345)\n"));

	}
	
	@Test
	public void infoboxAltTest() {	
		
		Assert.assertEquals("An intense man with close cropped hair and red beard gazes to the left.", WikipediaArtistInfoboxParser.infoboxAlt("An intense man with close cropped hair and red beard gazes to the left.\n"));

	}
	
	@Test
	public void infoboxTrainingTest() {	
		
		Assert.assertEquals("Anton Mauve", WikipediaArtistInfoboxParser.infoboxTraining("[[Anton Mauve]]\n"));

	}

}
