package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxUnitsMeasurementParser
{

  private static Log logger = LogFactory.getLog(InfoboxUnitsMeasurementParser.class);
  
  public InfoboxUnitsMeasurementParser()
  {
    
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
      logger.error("Error parsing ImperialWidth infobox: " + e.getMessage());
    }
    
    return null;
  }
  
  public static String infoboxMisure(String name)
  {
    try
    {
      name = name.replace("\n", "");
  
      if (name.contains("|"))
      {
        String[] n1 = StringUtils.split(name, "|");
        name = n1[1].replace(" ", "");
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Misure infobox: " + e.getMessage());      
    }
    
    return null;
  }
}
