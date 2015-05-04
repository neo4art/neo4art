package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxArchitecturalParser
{

  private static Log logger = LogFactory.getLog(InfoboxArchitecturalParser.class);

  public InfoboxArchitecturalParser()
  {
    
  }
  
  public static String infoboxArchitecturalStyle(String name)
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
      logger.error("Error parsing Architectural infobox: " + e.getMessage());      
    }
    
    return null;
  }
}
