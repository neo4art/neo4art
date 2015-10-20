package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artist;

public class WikipediaArtistAndyInfoboxTest {
  private static String INFOBOX =
    "{{Infobox artist\n"+
    "| name = Andy Warhol\n"+
    "| image = Andy Warhol by Jack Mitchell.jpg\n"+
    "| caption = Andy Warhol, with Archie, by [[Jack Mitchell (photographer)|Jack Mitchell]], 1973.\n"+
    "| birth_name = Andrew Warhola\n"+
    "| birth_date = {{Birth date|1928|8|6|mf=yes}}\n"+
    "| birth_place = [[Pittsburgh]], Pennsylvania, United States\n"+
    "| nationality = American\n"+
    "| field = [[Printmaking]], painting, cinema, photography\n"+
    "| training = [[Carnegie Institute of Technology]] ([[Carnegie Mellon University]])\n"+
    "| movement = [[Pop art]]\n"+
    "| works = ''[[Chelsea Girls]]'' (1966 film)<br>''[[Exploding Plastic Inevitable]]'' (1966 event)<br>''[[Campbell's Soup Cans]]'' (1962 painting)\n"+
    "| death_date = {{Death date and age|1987|2|22|1928|8|6|mf=yes}}\n"+
    "| death_place = New York City, New York, United States\n"+
    "}}";
  
  @Test
  public void shouldParseArtistInfobox() throws MalformedURLException {
    Artist artist = WikipediaArtistInfoboxParser.parse(INFOBOX);
    Assert.assertEquals("Andy Warhol", artist.getName());
  }
}
