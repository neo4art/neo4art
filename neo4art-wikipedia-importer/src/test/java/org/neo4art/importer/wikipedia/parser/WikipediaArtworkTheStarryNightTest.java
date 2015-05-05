package org.neo4art.importer.wikipedia.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artwork;

public class WikipediaArtworkTheStarryNightTest {

	private static String INFOBOX ="{{Infobox Artwork\n"+
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
		
		URL url = new URL("http://en.wikipedia.org/wiki/File:Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg");
		
		Assert.assertEquals(url, artwork.getUrl());
		Assert.assertEquals("The Starry Night", artwork.getTitle());
		Assert.assertEquals("A painting of a scene at night with 11 swirly stars and a bright yellow crescent moon. In the background there are hills, in the middle ground there is a moonlit town with a church that has an elongated steeple, and in the foreground there is the dark green silhouette of a cypress tree.", artwork.getAlt());
		Assert.assertEquals("Vincent van Gogh", artwork.getArtist().getName());
		Assert.assertEquals("1889", artwork.getYear());
		Assert.assertEquals("Oil painting Oil on canvas", artwork.getType());
		Assert.assertEquals("Jacob Baart de la Faille F612; Jan Hulsker JH1731", artwork.getCatalogue());
		Assert.assertEquals("73.7", artwork.getHeightMetric());
		Assert.assertEquals("92.1", artwork.getWidthMetric());
		Assert.assertEquals("29", artwork.getHeightImperial());
		Assert.assertEquals("361/4", artwork.getWidthImperial());
		Assert.assertEquals("cm", artwork.getMetricUnit());
		Assert.assertEquals("in", artwork.getImperialUnit());
		Assert.assertEquals("Museum of Modern Art", artwork.getMuseum().getName());
		Assert.assertEquals("New York City", artwork.getCity().getCommonName());
	
	}
}
