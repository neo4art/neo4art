package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxPlaceParser
{

  private static Log logger = LogFactory.getLog(InfoboxPlaceParser.class);

  public InfoboxPlaceParser()
  {

  }

  public static String infoboxPlaceDeath(String place)
  {
    try
    {
      if (place != null)
      {
        place = place.replace("\n", "");
        place = place.replace("[", "");
        place = place.replace("]", "");
        place = place.replace("|", " ");

        if (place.indexOf(",") != -1)
        {
          String[] p = StringUtils.split(place, ",");

          return p[0];
        }
      }

      return place;
    }
    catch (Exception e)
    {
      logger.error("Error parsing PlaceDeadth infobox: " + e.getMessage());
    }

    return null;
  }

  public static String infoboxPlaceBirth(String place)
  {
    try
    {
      place = place.replace("\n", "");
      place = place.replace("[", "");
      place = place.replace("]", "");
      place = place.replace("|", " ");
      
      return place;
    }
    catch (Exception e)
    {
      logger.error("Error parsing PlaceBirth infobox: " + e.getMessage());
    }

    return null;
  }
  
  public static String infoboxLocation(String name)
  {
    try
    {
      if (name.contains("]"))
      {
        String[] n1 = StringUtils.split(name, "]");
        name = n1[0];
        name = InfoboxParserUtil.removeAllParenthesis(name);
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Location infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxProvince(String name)
  {
    try
    {
      String[] n1 = StringUtils.split(name, "|");
  
      name = n1[0].replace("[", "");
      name = name.replace("]", "");
  
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Province infobox: " + e.getMessage());      
    }
    
    return null;
  }

}
