package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxTypeParserUtil
{

  private static Log logger = LogFactory.getLog(InfoboxTypeParserUtil.class);
  
  public static String getType(String type)
  {
    try
    {
      if(type.contains("|"))
      {
        String[] t = StringUtils.split(type, "|");
        
        t[0] = InfoboxParserUtil.removeAllParenthesis(t[0]);
        type = t[0].trim();
        return type;
      }
    }
    catch(Exception e)
    {
      logger.error("Error parsing Type infobox: " + e.getMessage());
    }
    return type;
  }
}
