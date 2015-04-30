package org.neo4art.importer.wikipedia.parser;


import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artist;
import org.neo4art.importer.wikipedia.parser.WikipediaArtistInfoboxParser;

public class WikipediaArtistInfoboxTest {

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
	public void shouldParseArtistInfobox() {
		Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Vincent van Gogh", artist.getName());
		Assert.assertEquals("30 March 1853", artist.getBirthDate());
		Assert.assertEquals("[[Zundert]], [[Netherlands]]", artist.getBirthPlace());
		Assert.assertEquals("{{Death date and age|df=yes|1890|7|29|1853|3|30}}", artist.getDeathDate());
		Assert.assertEquals("[[Auvers-sur-Oise]], [[French Third Republic|France]]", artist.getDeathPlace());
	}
}
