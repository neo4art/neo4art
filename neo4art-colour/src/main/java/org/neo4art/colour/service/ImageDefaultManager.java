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
package org.neo4art.colour.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.exception.ImageParserException;
import org.neo4art.colour.utility.UtilityImage;
import org.neo4art.domain.Colour;

/**
 * 
 * @author Mattia Zaratin, Enrico De Benetti
 * @since 30 Apr 2015
 */
public class ImageDefaultManager implements ImageManager
{
  private BufferedImage     image, nuovaImage;
  private UtilityImage      utility;
  private Color[][]         mappaPixel;
  private Color             avg, min, max;
  private ArrayList<Colour> listColor = null;

  public ImageDefaultManager() throws IOException
  {
    this.image = null;
    this.listColor = getColorList();
  }

  public ImageDefaultManager(BufferedImage image) throws IOException
  {
    this.image = image;
    this.listColor = getColorList();
  }

  /**
   * @see org.neo4art.colour.service.ImageManager#analyzeImageByUrl(java.net.URL, java.lang.String)
   */
  @Override
  public ColourAnalysis analyzeImageByUrl(URL url, String imageName) throws ImageParserException
  {
    ColourAnalysis imageColor = null;

    try
    {
      this.image = ImageIO.read(url);
      imageColor = analyzeImage();
      imageColor.setImageName(imageName);
      imageColor.setSource(url.toString());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new ImageParserException(e.getMessage());
    }

    return imageColor;
  }

  /**
   * @see org.neo4art.colour.service.ImageManager#getColorList()
   */
  @Override
  public ArrayList<Colour> getColorList()
  {
    int red, green, blue;
    ArrayList<Colour> color = new ArrayList<Colour>();
    try
    {
  
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("color.txt").getFile());
  
      BufferedReader input = new BufferedReader(new FileReader(file));
  
      int index = 0;
      String line;
      while ((line = input.readLine()) != null)
      {
  
        String[] hex = line.split("#");
        red = Integer.parseInt(hex[1].substring(0, 2), 16);
        green = Integer.parseInt(hex[1].substring(2, 4), 16);
        blue = Integer.parseInt(hex[1].substring(4, 6), 16);
  
        Colour colour = new Colour(line.replaceAll("#+[0-9A-z]*", "").replace("\t", ""), red, green, blue);
  
        color.add(index, colour);
        index++;
      }
      input.close();
  
    }
    catch (Exception e)
    {
    }
    return color;
  }

  /**
   * 
   * @return
   * @throws ImageParserException
   */
  public ColourAnalysis analyzeImage() throws ImageParserException
  {
    ColourAnalysis imageColor = new ColourAnalysis();

    Color[] colorReport = new Color[3];
    operationImage();
    colorReport = getReportRgb(utility.pixelMatrix());

    imageColor.setNameAverageColor(getNameColor(colorReport[0]));
    imageColor.setNameMaximumColor(getNameColor(colorReport[2]));
    imageColor.setNameMinimumColor(getNameColor(colorReport[1]));
    imageColor.setRgbAverageColor(colorReport[0]);
    imageColor.setRgbMaximumColor(colorReport[2]);
    imageColor.setRgbMinimumColor(colorReport[1]);

    return imageColor;
  }

  /**
   * 
   * @throws ImageParserException
   */
  private void operationImage() throws ImageParserException
  {
    utility = new UtilityImage(image);
    nuovaImage = utility.resizeImage(image);
    utility = new UtilityImage(nuovaImage);
    utility.initializeMatrix();
  }

  /**
   * 
   * @return
   */
  public Color[][] getMapPixel()
  {
    return utility.pixelMatrix();
  }

  /**
   * 
   * @param mappaPixel
   * @return
   */
  public Color[] getReportRgb(Color[][] mappaPixel)
  {
    Color[] colorReport = new Color[3];
    colorReport[0] = utility.getAvg(mappaPixel);
    colorReport[1] = utility.getMin(mappaPixel);
    colorReport[2] = utility.getMax(mappaPixel);
    return colorReport;
  }

  /**
   * 
   * @param color
   * @return
   */
  public String getNameColor(Color color)
  {
    String nome = "";
    nome = getColorName(color.getRed(), color.getGreen(), color.getBlue());
    return nome;
  }

  /**
   * 
   * @param red
   * @param green
   * @param blue
   * @return
   */
  public String getColorName(int red, int green, int blue)
  {
    int maxPixel = Integer.MAX_VALUE;
    int valuePixel;
    Colour trovato = null;
    for (Colour colour : listColor)
    {
      valuePixel = ((red - colour.getRed()) * (red - colour.getRed()) + (green - colour.getGreen()) * (green - colour.getGreen()) + (blue - colour.getBlue()) * (blue - colour.getBlue())) / 3;

      if (valuePixel < maxPixel)
      {
        maxPixel = valuePixel;
        trovato = colour;
      }
    }
    if (trovato != null)
    {
      return trovato.getName();
    }
    return null;
  }

  /**
   * 
   * @return
   */
  public Color[][] getMappaPixel()
  {
    return mappaPixel;
  }

  /**
   * 
   * @return
   */
  public Color getAvg()
  {
    return avg;
  }

  /**
   * 
   * @return
   */
  public Color getMin()
  {
    return min;
  }

  /**
   * 
   * @return
   */
  public Color getMax()
  {
    return max;
  }
}