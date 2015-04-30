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

public class WikipediaArtistAndyInfoboxTest
{
  private static String INFOBOX ="{{Infobox artist\n"+
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
  
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy M d"); 
    URL url = new URL("http://en.wikipedia.org/wiki/File:Andy_Warhol_by_Jack_Mitchell.jpg");
    
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
    
    Assert.assertEquals("Andy Warhol", artist.getName());
    Assert.assertEquals("Pittsburgh, Pennsylvania, United States", artist.getBirthPlace());
    Assert.assertEquals("New York City", artist.getDeathPlace());
    Assert.assertEquals("1928 8 6", formatDate.format(artist.getBirthDate().getTime()));
    Assert.assertEquals("1987 2 22",formatDate.format(artist.getDeathDate().getTime()));
  
  }
  
  
}
