package org.neo4art.importer.wikipedia.parser;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artwork;
import org.neo4art.importer.wikipedia.parser.WikipediaArtworkInfoboxParser;

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
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Vincent_Willem_van_Gogh_127.jpg");
		
		Assert.assertEquals(url, artwork.getUrl());
		Assert.assertEquals("225px", artwork.getImageSize());
		Assert.assertEquals("Sunflowers", artwork.getTitle());
		Assert.assertEquals("Original title, in French", artwork.getOtherLanguage1());
		Assert.assertEquals("Tournesols", artwork.getOtherTitle1());
		Assert.assertEquals("Vincent van Gogh", artwork.getArtist().getName());
		Assert.assertEquals("1888", artwork.getYear());
		Assert.assertEquals("Oil on canvas", artwork.getType());
		Assert.assertEquals("92.1", artwork.getHeightMetric());
		Assert.assertEquals("73", artwork.getWidthMetric());
		Assert.assertEquals("cm", artwork.getMetricUnit());
		Assert.assertEquals("in", artwork.getImperialUnit());
		Assert.assertEquals("National Gallery (London)", artwork.getMuseum().getName());
		Assert.assertEquals("London", artwork.getCity().getCommonName());
	
	}
	
	@Test
	public void infoboxArtworkUrlTest() throws MalformedURLException{
		URL url = new URL("http://en.wikipedia.org/wiki/File:Vincent_Willem_van_Gogh_127.jpg");
		
		Assert.assertEquals(url, WikipediaArtworkInfoboxParser.infoboxImageUrl("Vincent Willem van Gogh 127.jpg\n"));
	}
}
