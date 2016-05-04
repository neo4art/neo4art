package org.neo4art.colour.service;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.domain.ColourAnalysis;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ImageManagerTest {

	@Test public void avgMinMaxBlackImageTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("BlackImage.PNG").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			Color avg = new Color(0, 0, 0);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(0, 0, 0);
			assertEquals(max, analyzeImage.getMaximumColour());

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Assert.assertEquals("Black", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxWhiteTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("white.png").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(255, 255, 255);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(255, 255, 255);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 255);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("White", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("White", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("White", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxRedTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("redImage.png").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(255, 0, 0);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(255, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 0, 0);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Red", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Red", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Red", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxGreenTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("greenImage.png").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(0, 255, 0);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 255, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(0, 255, 0);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Electric green", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Electric green", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Electric green", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxBlueTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("blueImage.png").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(0, 0, 255);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 255);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(0, 0, 255);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Blue", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Blue", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Blue", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxOnePixelWhiteTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("onePixelWhite.png").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(0, 0, 0);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 255);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Black", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("White", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxOnePixelBlackTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("onePixelBlack.png").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(254, 254, 254);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 255);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("White", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("White", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxVincentVanGoghBranchesOfAnAlmondTreeInRedTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("vincent-van-gogh-branches-of-an-almond-tree-in-red.jpg").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(137, 30, 26);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 248, 234);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Falu red", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Cosmic latte", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxThe_Sower_painting_by_Van_GoghTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("The_Sower_-_painting_by_Van_Gogh.jpg").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(104, 98, 74);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(12, 26, 9);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(212, 192, 131);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Wenge", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Burlywood", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Smoky black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxvangoghbridge() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("van-gogh-bridge.jpg").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(102, 123, 119);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 250, 242);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("AuroMetalSaurus", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Floral white", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxTheYellowHouse() {
		try {
			//URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Vincent_van_Gogh_-_The_yellow_house_%28%27The_street%27%29.jpg/175px-Vincent_van_Gogh_-_The_yellow_house_%28%27The_street%27%29.jpg");
			//ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("the-yellow-house.jpg").getFile());

			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));

			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(108, 116, 96);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 61, 94);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(247, 212, 156);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Dim gray", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Deep champagne", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals(/*"Rich black (FOGRA29)"*/"Dark imperial blue", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxSediaUrl() {
		try {
			URL url = new URL(
					"https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Vincent_Willem_van_Gogh_138.jpg/640px-Vincent_Willem_van_Gogh_138.jpg");
			BufferedImage bufferedImage = ImageIO.read(url);
			if (bufferedImage == null)
				throw new IllegalStateException("Can't read image from " + url);
			ImageDefaultManager imageManager = new ImageDefaultManager(bufferedImage);
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(123, 128, 85);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(9, 10, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 244);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Gold Fusion", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Ivory", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Smoky black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxVincentWillemUrl() {
		try {
			URL url = new URL(
					"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Vincent_Willem_van_Gogh_128.jpg/640px-Vincent_Willem_van_Gogh_128.jpg");
			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(185, 162, 97);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 3, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 240);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Camel", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Ivory", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxVincentWillem1Url() {
		try {
			URL url = new URL(
					"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Vincent_Willem_van_Gogh_-_Cafe_Terrace_at_Night_%28Yorck%29.jpg/640px-Vincent_Willem_van_Gogh_-_Cafe_Terrace_at_Night_%28Yorck%29.jpg");
			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(122, 115, 86);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 2, 3);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 248);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("Gold Fusion", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Baby powder", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Rich black (FOGRA39)", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test public void avgMinMaxJosepRoulin1Url() {
		try {
			URL url = new URL(
					"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Vincent_van_Gogh_-_Portret_van_de_postbode_Joseph_Roulin.jpg/640px-Vincent_van_Gogh_-_Portret_van_de_postbode_Joseph_Roulin.jpg");
			ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
			ColourAnalysis analyzeImage = imageManager.analyzeImage();

			System.out.println("AVG name:" + analyzeImage.getAverageClosestColour().getName());
			System.out.println("MAX name:" + analyzeImage.getMaximumClosestColour().getName());
			System.out.println("MIN name:" + analyzeImage.getMinimumClosestColour().getName());
			System.out.println("AVG :" + analyzeImage.getAverageColour().toString());
			System.out.println("Max :" + analyzeImage.getMaximumColour().toString());
			System.out.println("Min :" + analyzeImage.getMinimumColour().toString());

			Color avg = new Color(97, 122, 128);
			assertEquals(avg, analyzeImage.getAverageColour());
			Color min = new Color(0, 0, 0);
			assertEquals(min, analyzeImage.getMinimumColour());
			Color max = new Color(255, 255, 248);
			assertEquals(max, analyzeImage.getMaximumColour());

			Assert.assertEquals("AuroMetalSaurus", analyzeImage.getAverageClosestColour().getName());
			Assert.assertEquals("Baby powder", analyzeImage.getMaximumClosestColour().getName());
			Assert.assertEquals("Black", analyzeImage.getMinimumClosestColour().getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
