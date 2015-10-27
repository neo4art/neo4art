package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artist;

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
	
	@Test
	public void shouldParseArtistInfobox() throws MalformedURLException {
	  
    Calendar birthDateCalendar = Calendar.getInstance();
    birthDateCalendar.set(1853, Calendar.MARCH, 30, 0, 0, 0);
    birthDateCalendar.set(Calendar.MILLISECOND, 0);
    
    Calendar deathDateCalendar = Calendar.getInstance();
    deathDateCalendar.set(1890, Calendar.JULY, 29, 0, 0, 0);
    deathDateCalendar.set(Calendar.MILLISECOND, 0);

    Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
    
    Assert.assertEquals("Vincent van Gogh", artist.getName());
    Assert.assertEquals(new URL("https://en.wikipedia.org/wiki/File:Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_(454045).jpg"), artist.getImage());
    Assert.assertEquals(birthDateCalendar.getTimeInMillis(), artist.getBirthDate().getTime());
    Assert.assertEquals("Zundert", artist.getBirthPlace().getName());
    Assert.assertEquals("Netherlands", artist.getBirthPlace().getCountry());
    Assert.assertEquals(deathDateCalendar.getTimeInMillis(), artist.getDeathDate().getTime());
    Assert.assertEquals("Auvers-sur-Oise", artist.getDeathPlace().getName());
    Assert.assertEquals("French Third Republic", artist.getDeathPlace().getCountry());
    Assert.assertEquals("Post-Impressionism", artist.getArtMovements().get(0).getName());
	}
}
