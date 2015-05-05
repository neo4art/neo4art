package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxNameParser
{
  private static Log logger = LogFactory.getLog(InfoboxNameParser.class);
  
  public static String infoboxBirthName(String name)
  {
    try
    {
      if (name != null && name.indexOf("<") != -1)
      {
        String[] field = StringUtils.split(name, "<");
        return field[0];
      }
  
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing BirthName infobox: " + e.getMessage());
    }
    
    return null;
  }
  
  public static String infoboxNativeName(String name)
  {
    try
    {
      if (name.contains("|"))
      {
        if (name.contains("<br/>"))
        {
          String[] n1 = StringUtils.split(name, ">");
          if(n1 != null)
          {
            name = n1[1].trim();
            name = name.replace("'", "");
            return name;
          }
          else
          {
            name = null;
          }
        }
        
        String[] n = StringUtils.split(name, "|");
        name = n[2];
        name = InfoboxParserUtil.removeAllParenthesis(name);
        
        if (name.contains("<"))
        {
          String[] n1 = StringUtils.split(name, "<");
          if(n1 != null)
          {
            name = n1[0].trim();
          }
          else
          {
            name = null;
          }
        }
      }
      
      name = InfoboxParserUtil.removeLink(name);

      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing NativeName infobox: " + e.getMessage());
    }
    
    return null;
  }


  public static String infoboxBuildingName(String name)
  {
    try
    {
      String[] n = StringUtils.split(name, "<");
      name = n[0];
      name = name.replace("&nbsp;", " ");
      name = name.replace("'", "");
      return name.trim();
    }
    catch (Exception e)
    {
      logger.error("Error parsing BuildingName infobox: " + e.getMessage());
    }
    
    return null;
  }

  
}
