package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artwork;

public class WikipediaArtworkSunFlowerInfoboxTest {
	private static String INFOBOX =
	 	"{{Infobox Artwork\n"+
	  "| image_file       = Vincent Willem van Gogh 127.jpg\n"+
	  "| image_size       = 225px\n"+
	  "| title            = Sunflowers\n"+
	  "| other_language_1 = Original title, in French\n"+
	  "| other_title_1    = Tournesols\n"+
	  "| artist           = Vincent van Gogh\n"+
	  "| year             = 1888\n"+
	  "| type             = Oil on canvas\n"+
	  "| height_metric    = 92.1\n"+
	  "| width_metric     = 73\n"+
	  "| height_imperial  = 36.2\n"+
	  "| width_imperial   = 28.7\n"+
	  "| metric_unit      = cm\n"+
	  "| imperial_unit    = in\n"+
	  "| museum           = [[National Gallery (London)|National Gallery]]\n"+
	  "| city             = London}}";
	
	@Test
	public void shoudParseArtwork() throws MalformedURLException{
		Artwork artwork = WikipediaArtworkInfoboxParser.parse(INFOBOX);
		Assert.assertEquals("Sunflowers", artwork.getTitle());
	}
}
