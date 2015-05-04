/**
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4art.colour.utility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.exception.ImageParserException;
import org.neo4art.colour.write.WriteFileService;

/**
 * 
 * @author Mattia Zaratin
 * @since 2 May 2015
 */
public class UtilityImage
{
  private BufferedImage    image;
  private int              x, y;
  private int              red, green, blue, count, increment;
  private Color            max = new Color(0, 0, 0);
  private Color            min = new Color(255, 255, 255);
  private Color            avg = new Color(0, 0, 0);
  private Color[][]        mappaPixel;
  private WriteFileService writeFile;

  public UtilityImage(BufferedImage im)
  {
    this.image = im;
    this.x = im.getWidth();
    this.y = im.getHeight();
  }

  public UtilityImage()
  {

  }

  public BufferedImage resizeImage(BufferedImage image) throws ImageParserException// Resize immagine
  {
    int x = image.getWidth() / 2;
    int y = image.getHeight() / 2;
    BufferedImage nuovaImage = image;
    try
    {
      Image resizedImage = image.getScaledInstance(x, y, Image.SCALE_DEFAULT);
      BufferedImage resizeNewImage = new BufferedImage(x, y, image.getType());
      Graphics2D ggraphics = resizeNewImage.createGraphics();
      ggraphics.drawImage(resizedImage, 0, 0, null);
      ggraphics.dispose();

      File folder = new File("./src/test/resources/resize/");

      String path = "" + folder + File.separator + "resizeImage.jpg";
      ImageIO.write(resizeNewImage, "jpg", new File(path));

      return nuovaImage;
    }
    catch (Exception e)
    {
      throw new ImageParserException("Path not found. Impossible to sava resize image!");
    }

  }

  public int increment()
  {
    int increment = 0;

    if (x <= 800 || y <= 800)
    {
      increment = 1;
    }
    else
    {
      if ((x <= 2000 || y <= 2000) && (x >= 800 || y >= 800))
      {
        increment = 10;
      }
      else
      {
        if ((x <= 5000 || y <= 5000) && (x >= 2000 || y >= 2000))
        {
          increment = 50;
        }
      }
    }
    return increment;
  }

  public void initializeMatrix()
  {
    this.mappaPixel = new Color[x][y];
  }

  public Color[][] pixelMatrix()
  {
    increment = increment();
    for (int i = 0; i < y; i = i + increment)
    {
      for (int j = 0; j < x; j = j + increment)
      {
        if (j < x && i < y)
          mappaPixel[j][i] = new Color(image.getRGB(j, i));
      }
    }
    return mappaPixel;

  }

  public void writePixel(Color[][] mappa)
  {
    int index = 0;
    Color[] vectorColorPixel = new Color[countPixelImage(mappaPixel)];
    for (int i = 0; i < y; i = i + increment)
    {
      for (int j = 0; j < x; j = j + increment)
      {
        Color color = mappa[j][i];
        vectorColorPixel[index] = color;
        index++;
      }
    }
    writeFile.savePixel(vectorColorPixel, true);
    writeFile.savePixel(vectorColorPixel, false);
  }

  public int countPixelImage(Color[][] mappa)
  {
    int count = 0;
    for (int i = 0; i < y; i = i + increment)
    {
      for (int j = 0; j < x; j = j + increment)
      {
        count++;
      }
    }
    return count;
  }

  public void writeReportCSV(ColourAnalysis image)
  {
    ColourAnalysis imageImport = image;
    writeFile.saveReport(imageImport, false);
    writeFile.saveReport(imageImport, true);
  }

  public void initializeFile()
  {
    File folder = new File("./src/main/resources/risultato");
    writeFile = new WriteFileService("" + folder);
    writeFile.inizializeFile();
  }

  public Color getAvg(Color[][] mappa)
  {
    for (int i = 0; i < y; i = i + increment)
    {
      for (int j = 0; j < x; j = j + increment)
      {
        Color color = mappa[j][i];
        red += color.getRed();
        green += color.getGreen();
        blue += color.getBlue();
        count++;
      }
    }
    red = red / count;
    green = green / count;
    blue = blue / count;
    this.avg = new Color(red, green, blue);
    return avg;
  }

  public Color getMax(Color[][] mappa)
  {

    for (int i = 0; i < y; i = i + increment)
    {
      for (int j = 0; j < x; j = j + increment)
      {
        Color color = mappa[j][i];
        if (max.getRGB() < color.getRGB())
        {
          max = color;
        }
      }
    }
    return max;
  }

  public Color getMin(Color[][] mappa)
  {

    for (int i = 0; i < y; i = i + increment)
    {
      for (int j = 0; j < x; j = j + increment)
      {
        Color color = mappa[j][i];
        if (min.getRGB() > color.getRGB())
        {
          min = color;
        }
      }
    }
    return min;
  }

  public void writePixelToFile()
  {
    this.initializeFile();
    this.writePixel(mappaPixel);
  }

  public void writeReport(Color avg, Color min, Color max, ColourAnalysis imageColor)
  {
    imageColor.setAverageColour(avg);
    imageColor.setMaximumColour(max);
    imageColor.setMinimumColour(min);
    this.writeReportCSV(imageColor);
  }

  public int getX()
  {
    return image.getWidth();
  }

  public int getY()
  {
    return image.getHeight();
  }

  public int getCount()
  {
    return count;
  }

  public int getIncrement()
  {
    return increment;
  }

  public Color[][] getMappa()
  {
    return mappaPixel;
  }

}
