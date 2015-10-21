package toberefactored.parser;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artwork;

public class WikipediaArtworkTheStarryNightTest {
	private static String INFOBOX =
    "{{Infobox Artwork\n"+
    "| image_file=Van Gogh - Starry Night - Google Art Project.jpg\n"+
    "| title=The Starry Night\n"+
    "| alt= A painting of a scene at night with 11 swirly stars and a bright yellow crescent moon. In the background there are hills, in the middle ground there is a moonlit town with a church that has an elongated steeple, and in the foreground there is the dark green silhouette of a cypress tree.\n"+
    "| artist=[[Vincent van Gogh]]\n"+
    "| year=1889\n"+
    "| type=[[Oil painting|Oil on canvas]]\n"+
    "| catalogue=[[Jacob Baart de la Faille|F612]]; [[Jan Hulsker|JH1731]]\n"+
    "| height_metric=73.7\n"+
    "| width_metric=92.1\n"+
    "| height_imperial=29\n"+
    "| width_imperial={{frac|36|1|4}}\n"+
    "| metric_unit=cm\n"+
    "| imperial_unit=in\n"+
    "| city= New York City\n"+
    "| museum=[[Museum of Modern Art]]\n"+
    "}}";

	@Test
	public void shoudParseArtwork() throws MalformedURLException{
		Artwork artwork = WikipediaArtworkInfoboxParser.parse(INFOBOX);
		Assert.assertEquals("The Starry Night", artwork.getTitle());
	}
}
