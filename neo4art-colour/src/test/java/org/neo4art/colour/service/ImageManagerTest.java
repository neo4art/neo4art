package org.neo4art.colour.service;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.domain.ColourAnalysis;

public class ImageManagerTest
{

  @Test
  public void avgMinMaxBlackImageTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("BlackImage.PNG").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      Color avg = new Color(0, 0, 0);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Black");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Black");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxWhiteTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("white.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(255, 255, 255);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(255, 255, 255);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 255);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "White");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "White");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "White");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxRedTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("redImage.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(255, 0, 0);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(255, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 0, 0);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Red");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Red");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Red");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxGreenTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("greenImage.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(0, 255, 0);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 255, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(0, 255, 0);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Electric green");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Electric green");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Electric green");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxBlueTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("blueImage.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(0, 0, 255);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 255);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(0, 0, 255);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Blue");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Blue");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Blue");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxOnePixelWhiteTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("onePixelWhite.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(0, 0, 0);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 255);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Black");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "White");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxOnePixelBlackTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("onePixelBlack.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(254, 254, 254);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 255);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "White");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "White");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxVincentVanGoghBranchesOfAnAlmondTreeInRedTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("vincent-van-gogh-branches-of-an-almond-tree-in-red.jpg").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(137, 30, 26);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 248, 234);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Falu red");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Cosmic latte");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxThe_Sower_painting_by_Van_GoghTest()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("The_Sower_-_painting_by_Van_Gogh.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(104, 98, 74);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(12, 26, 9);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(212, 192, 131);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Wenge");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Burlywood");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Smoky black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxvangoghbridge()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("van-gogh-bridge.png").getFile());

      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(file));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(102, 123, 119);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 250, 242);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "AuroMetalSaurus");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Floral white");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxTheYellowHouseUrl()
  {
    try
    {
      URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Vincent_van_Gogh_-_The_yellow_house_%28%27The_street%27%29.jpg/175px-Vincent_van_Gogh_-_The_yellow_house_%28%27The_street%27%29.jpg");
      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(108, 116, 96);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 61, 94);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(247, 212, 156);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Dim gray");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Deep champagne");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Dark imperial blue");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxSediaUrl()
  {
    try
    {
      URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Vincent_Willem_van_Gogh_138.jpg/640px-Vincent_Willem_van_Gogh_138.jpg");
      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(123, 128, 85);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(9, 10, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 244);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Gold Fusion");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Ivory");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Smoky black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxVincentWillemUrl()
  {
    try
    {
      URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Vincent_Willem_van_Gogh_128.jpg/640px-Vincent_Willem_van_Gogh_128.jpg");
      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(185, 162, 97);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 3, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 240);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Camel");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Ivory");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxVincentWillem1Url()
  {
    try
    {
      URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Vincent_Willem_van_Gogh_015.jpg/640px-Vincent_Willem_van_Gogh_015.jpg");
      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(122, 115, 86);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 14, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 246);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "Gold Fusion");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Baby powder");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Rich black (FOGRA39)");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void avgMinMaxJosepRoulin1Url()
  {
    try
    {
      URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Vincent_van_Gogh_-_Portret_van_de_postbode_Joseph_Roulin.jpg/640px-Vincent_van_Gogh_-_Portret_van_de_postbode_Joseph_Roulin.jpg");
      ImageDefaultManager imageManager = new ImageDefaultManager(ImageIO.read(url));
      ColourAnalysis analyzeImage = imageManager.analyzeImage();

      System.out.println("AVG name:" + analyzeImage.getAverageColourName());
      System.out.println("MAX name:" + analyzeImage.getMaximumColourName());
      System.out.println("MIN name:" + analyzeImage.getMinimumColourName());
      System.out.println("AVG :" + analyzeImage.getAverageRGBColour().toString());
      System.out.println("Max :" + analyzeImage.getMaximumRGBColour().toString());
      System.out.println("Min :" + analyzeImage.getMinimumRGBColour().toString());

      Color avg = new Color(97, 122, 128);
      assertEquals(analyzeImage.getAverageRGBColour(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getMinimumRGBColour(), min);
      Color max = new Color(255, 255, 248);
      assertEquals(analyzeImage.getMaximumRGBColour(), max);

      Assert.assertEquals(analyzeImage.getAverageColourName(), "AuroMetalSaurus");
      Assert.assertEquals(analyzeImage.getMaximumColourName(), "Baby powder");
      Assert.assertEquals(analyzeImage.getMinimumColourName(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }
}
