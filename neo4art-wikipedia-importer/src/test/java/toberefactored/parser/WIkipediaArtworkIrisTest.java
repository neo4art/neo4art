package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artwork;

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
	public void shoudParseArtwork() throws MalformedURLException{
		Artwork artwork = WikipediaArtworkInfoboxParser.parse(INFOBOX);
		Assert.assertEquals("Irises", artwork.getTitle());
	}
}
