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
    String ciao = name;
    try
    {
      if(!name.contains("}}"))
      {
        return null;
      }
      if(name.contains("unbulleted"))
      {
        name = name.replace("}}}}", "}}");
        String[] n = StringUtils.split(name, "{{");
        if(n.length == 1)
        {
          String[] h = StringUtils.split(n[0], "||");
          name = h[2];
          name = name.replace("'", "");
          name = InfoboxParserUtil.removeAllParenthesis(name);
          return name.trim();
        }
        if(n.length == 2)
        {
          String[] h = StringUtils.split(n[1], "||");
          name = h[2];
          name = name.replace("'", "");
          name = InfoboxParserUtil.removeAllParenthesis(name);
          return name.trim();
        }
        if(n.length == 3)
        {
          String[] h = StringUtils.split(n[2], "||");
          name = h[2];
          name = name.replace("'", "");
          name = InfoboxParserUtil.removeAllParenthesis(name);
          return name.trim();
        }
        if(n.length == 5)
        {
          String[] h = StringUtils.split(n[4], "||");
          int k = h.length;
          name = h[k];
          name = name.replace("'", "");
          name = InfoboxParserUtil.removeAllParenthesis(name);
          return name.trim();
        }
      }
      if (name.contains("|"))
      {
        if (name.contains("''"))
        {
          String[] n1 = StringUtils.split(name, "''");
          if(n1 != null)
          {
            name = n1[0].trim();
            return name.trim();
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
        }
      }
      
      name = InfoboxParserUtil.removeLink(name);

      
      return name.trim();
    }
    catch (Exception e)
    {
      logger.error("Error parsing NativeName infobox: " + ciao);
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
