package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artwork;
import org.neo4art.importer.wikipedia.parser.WikipediaArtworkInfoboxParser;

public class WIkipediaArtworkIrisTest {
  
	private static String INFOBOX =
    "{{Infobox artwork\n"+
	  "| image_file         = Irises-Vincent van Gogh.jpg\n"+
	  "| painting_alignment = \n"+
	  "| image_size         = 300px\n"+
	  "| title              = Irises\n"+
	  "| alt                = \n"+
	  "| other_language_1   = Original title, in French\n"+
	  "| other_title_1      = Les Iris\n"+
	  "| other_language_2   =\n"+
	  "| other_title_2      = \n"+
	  "| artist             = Vincent van Gogh\n"+
	  "| year               = 1889\n"+
	  "| type               = Oil on canvas\n"+
	  "| height_metric      = 71\n"+
	  "| width_metric       = 93\n"+
	  "| height_imperial    = 28\n"+
	  "| width_imperial     = {{frac|36|5|8}}\n"+
	  "| metric_unit        = cm\n"+
	  "| imperial_unit      = in\n"+
	  "| city               = [[Los Angeles]], [[California]]\n"+
	  "| museum             = [[J. Paul Getty Museum]]\n"+
	  "}}";
	
	@Test
	public void shoudParseVanGoghIrisesArtwork() throws MalformedURLException{
	  
	  Calendar year1889 = Calendar.getInstance();
	  year1889.set(1889, Calendar.JANUARY, 1, 0, 0, 0);
	  year1889.set(Calendar.MILLISECOND, 0);
	  
		Artwork artwork = WikipediaArtworkInfoboxParser.parse(INFOBOX);
		
		Assert.assertEquals("Irises", artwork.getTitle());
		Assert.assertEquals("Vincent van Gogh", artwork.getArtist().getName());
		Assert.assertEquals("Los Angeles, California", artwork.getCity().getName());
		Assert.assertEquals(new URL("https://en.wikipedia.org/wiki/File:Irises-Vincent_van_Gogh.jpg"), artwork.getUrl());
		Assert.assertEquals("J. Paul Getty Museum", artwork.getMuseum().getName());
		Assert.assertEquals("Oil on canvas", artwork.getType());
		Assert.assertEquals(year1889.getTimeInMillis(), artwork.getYear().getTime());
	}
}
