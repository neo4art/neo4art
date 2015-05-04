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
package org.neo4art.importer.wikipedia.parser;

import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Country;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artwork">Template :Infobox_artwork</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtworkInfoboxParser
{
  private static Log         logger             = LogFactory.getLog(WikipediaArtworkInfoboxParser.class);

  public static final String TITLE              = "title";
  public static final String IMAGE_SIZE         = "image_size";
  public static final String ALT                = "alt";
  public static final String CAPTION            = "caption";
  public static final String PAINTING_ALIGNMENT = "painting_alignment";
  public static final String OTHER_LANGUAGE_1   = "other_language_1";
  public static final String OTHER_TITLE_1      = "other_title_1";
  public static final String OTHER_LANGUAGE_2   = "other_language_2";
  public static final String OTHER_TITLE_2      = "other_title_2";
  public static final String ARTIST             = "artist";
  public static final String YEAR               = "year";
  public static final String COMPLETION_DATE    = "completion_date";
  public static final String CATALOGUE          = "catalogue";
  public static final String TYPE               = "type";
  public static final String MATERIAL           = "material";
  public static final String SUBJECT            = "subject";
  public static final String HEIGHT_METRIC      = "height_metric";
  public static final String WIDTH_METRIC       = "width_metric";
  public static final String LENGTH_METRIC      = "length_metric";
  public static final String HEIGHT_IMPERIAL    = "height_imperial";
  public static final String HEIGHT_INCH        = "height_inch";
  public static final String WIDTH_IMPERIAL     = "width_imperial";
  public static final String WIDTH_INCH         = "width_inch";
  public static final String LENGTH_IMPERIAL    = "length_imperial";
  public static final String LENGTH_INCH        = "length_inch";
  public static final String DIAMETER_METRIC    = "diameter_metric";
  public static final String DIAMETER_IMPERIAL  = "diameter_imperial";
  public static final String METRIC_UNIT        = "metric_unit";
  public static final String IMPERIAL_UNIT      = "imperial_unit";
  public static final String DIMENSIONS         = "dimensions";
  public static final String DIMENSIONS_REF     = "dimensions_ref";
  public static final String WEIGHT             = "weight";
  public static final String CONDITION          = "condition";
  public static final String OWNER              = "owner";
  public static final String ACCESSION          = "accession";
  public static final String MUSEUM             = "museum";
  public static final String CITY               = "city";
  public static final String COORDINATES        = "coordinates";
  public static final String URL                = "image_file";

  public WikipediaArtworkInfoboxParser()
  {
  }

  public static Artwork parse(String text)
  {
    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    Artwork artwork = new Artwork();
    Artist artist = new Artist();
    Museum museum = new Museum();
    Coordinate coordinate = new Coordinate();
    Country country = new Country();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case TITLE:
          artwork.setTitle(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE_SIZE:
          artwork.setImageSize(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ALT:
          artwork.setAlt(map.get(key));
          break;
        case CAPTION:
          artwork.setCaption(map.get(key));
          break;
        case PAINTING_ALIGNMENT:
          artwork.setPaintingAlignment(map.get(key));
          break;
        case OTHER_LANGUAGE_1:
          artwork.setOtherLanguage1(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case OTHER_TITLE_1:
          artwork.setOtherTitle1(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case OTHER_LANGUAGE_2:
          artwork.setOtherLanguage2(map.get(key));
          break;
        case OTHER_TITLE_2:
          artwork.setOtherTitle2(map.get(key));
          break;
        case ARTIST:
          artist.setName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          artwork.setArtist(artist);
          break;
        case YEAR:
          artwork.setYear(infoboxYear(map.get(key)));
          break;
        case COMPLETION_DATE:
          artwork.setCompletionDate(toDate(map.get(key)));
          break;
        case CATALOGUE:
          artwork.setCatalogue(infoboxCatalogue(map.get(key)));
          break;
        case TYPE:
          artwork.setType(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case MATERIAL:
          artwork.setMaterial(map.get(key));
          break;
        case SUBJECT:
          artwork.setSubject(map.get(key));
          break;
        case HEIGHT_METRIC:
          artwork.setHeightMetric(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case WIDTH_METRIC:
          artwork.setWidthMetric(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case LENGTH_METRIC:
          artwork.setLengthMetric(map.get(key));
          break;
        case HEIGHT_IMPERIAL:
          artwork.setHeightImperial(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case HEIGHT_INCH:
          artwork.setHeightInch(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case WIDTH_IMPERIAL:
          artwork.setWidthImperial(infoboxImperialWidth(map.get(key)));
          break;
        case WIDTH_INCH:
          artwork.setWidthInch(map.get(key));
          break;
        case LENGTH_IMPERIAL:
          artwork.setLengthImperial(map.get(key));
          break;
        case LENGTH_INCH:
          artwork.setLengthInch(map.get(key));
          break;
        case DIAMETER_METRIC:
          artwork.setDiameterMetric(map.get(key));
          break;
        case DIAMETER_IMPERIAL:
          artwork.setDiameterImperial(map.get(key));
          break;
        case METRIC_UNIT:
          artwork.setMetricUnit(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case IMPERIAL_UNIT:
          artwork.setImperialUnit(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case DIMENSIONS:
          artwork.setDimensions(map.get(key));
          break;
        case DIMENSIONS_REF:
          artwork.setDimensionsRef(map.get(key));
          break;
        case WEIGHT:
          artwork.setWeight(map.get(key));
          break;
        case CONDITION:
          artwork.setCondition(map.get(key));
          break;
        case OWNER:
          artwork.setOwner(map.get(key));
          break;
        case ACCESSION:
          artwork.setAccession(map.get(key));
          break;
        case MUSEUM:
          museum.setName(WikipediaInfoboxUtils.removeLink(map.get(key)));
          artwork.setMuseum(museum);
          break;
        case CITY:
          country.setCommonName(infoboxCountry(map.get(key)));
          artwork.setCity(country);
          break;
        case COORDINATES:
          infoboxCoordinate(map.get(key));
          // coordinate.setLatD(Double.parseDouble(c[1]));
          // coordinate.setLatM(Double.parseDouble(c[2]));
          // coordinate.setLatS(Double.parseDouble(c[3]));
          // coordinate.setLatNS(c[4]);
          // coordinate.setLongD(Double.parseDouble(c[5]));
          // coordinate.setLongM(Double.parseDouble(c[6]));
          // coordinate.setLongS(Double.parseDouble(c[7]));
          // coordinate.setLongEW(c[8]);
          artwork.setCoordinates(coordinate);
          break;
        case URL:
          artwork.setUrl(infoboxImageUrl(map.get(key)));
          break;
      }
    }

    return artwork;
  }

  /**
   * @param string
   * @return
   */
  private static Date toDate(String string)
  {
    //TODO not yet implemented
    return null;
  }

  public static String[] infoboxCoordinate(String coo)
  {
    try
    {
      String[] c = StringUtils.split(coo, "|");
  
      return c;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artwork infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static URL infoboxImageUrl(String nameImage)
  {
    try
    {
      nameImage = nameImage.replaceAll(" ", "_");
      nameImage = nameImage.replace("|", "");
      nameImage = nameImage.replace("\n", "");
      nameImage = "http://en.wikipedia.org/wiki/File:" + nameImage;
      URL url = new URL(nameImage);
      return url;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artwork infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxCountry(String city)
  {
    try
    {
      city = WikipediaInfoboxUtils.removeAllParenthesis(city);
  
      return city;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artwork infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxCatalogue(String catalogue)
  {
    try
    {
      return WikipediaInfoboxUtils.removeAllParenthesis(catalogue);
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artwork infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxImperialWidth(String width)
  {
    try
    {
      if (width.contains("|"))
      {
        width = width.replace("{", "");
        width = width.replace("}", "");
        
        String[] im = StringUtils.split(width, "|");
        
        im[0] = im[1] + im[2] + "/" + im[3];
        im[0] = im[0].replace(" ", "");
        
        return im[0];
      }
      else
      {
        return width;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artwork infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxYear(String year)
  {
    try
    {
      if (year.contains("|"))
      {
        year = WikipediaInfoboxUtils.removeAllParenthesis(year);
        
        String[] y = StringUtils.split(year, "|");
        
        return y[1];
      }
      else
      {
        return year;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artwork infobox: " + e.getMessage());
    }
    
    return null;
  }

}
