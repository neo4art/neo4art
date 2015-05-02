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
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Black");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Black");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(255, 255, 255);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(255, 255, 255);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 255);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "White");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "White");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "White");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(255, 0, 0);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(255, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 0, 0);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Red");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Red");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Red");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(0, 255, 0);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 255, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(0, 255, 0);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Electric green");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Electric green");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Electric green");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(0, 0, 255);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 255);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(0, 0, 255);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Blue");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Blue");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Blue");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 255);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Black");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "White");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(254, 254, 254);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 255);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "White");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "White");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(137, 30, 26);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 248, 234);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Falu red");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Cosmic latte");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(104, 98, 74);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(12, 26, 9);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(212, 192, 131);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Wenge");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Burlywood");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Smoky black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(102, 123, 119);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 250, 242);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "AuroMetalSaurus");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Floral white");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(108, 116, 96);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 61, 94);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(247, 212, 156);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Dim gray");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Deep champagne");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Dark imperial blue");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(123, 128, 85);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(9, 10, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 244);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Gold Fusion");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Ivory");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Smoky black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(185, 162, 97);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 3, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 240);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Camel");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Ivory");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(122, 115, 86);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 14, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 246);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "Gold Fusion");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Baby powder");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Rich black (FOGRA39)");
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

      System.out.println("AVG name:" + analyzeImage.getNameAverageColor());
      System.out.println("MAX name:" + analyzeImage.getNameMaximumColor());
      System.out.println("MIN name:" + analyzeImage.getNameMinimumColor());
      System.out.println("AVG :" + analyzeImage.getRgbAverageColor().toString());
      System.out.println("Max :" + analyzeImage.getRgbMaximumColor().toString());
      System.out.println("Min :" + analyzeImage.getRgbMinimumColor().toString());

      Color avg = new Color(97, 122, 128);
      assertEquals(analyzeImage.getRgbAverageColor(), avg);
      Color min = new Color(0, 0, 0);
      assertEquals(analyzeImage.getRgbMinimumColor(), min);
      Color max = new Color(255, 255, 248);
      assertEquals(analyzeImage.getRgbMaximumColor(), max);

      Assert.assertEquals(analyzeImage.getNameAverageColor(), "AuroMetalSaurus");
      Assert.assertEquals(analyzeImage.getNameMaximumColor(), "Baby powder");
      Assert.assertEquals(analyzeImage.getNameMinimumColor(), "Black");
    }
    catch (Exception e)
    {
      Assert.fail(e.getMessage());
    }
  }
}
