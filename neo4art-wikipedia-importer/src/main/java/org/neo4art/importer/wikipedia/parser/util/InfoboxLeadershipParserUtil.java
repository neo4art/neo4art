package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxLeadershipParserUtil
{
  private static Log logger = LogFactory.getLog(InfoboxLeadershipParserUtil.class);

  public static String infoboxLeadership(String name)
  {
    try
    {
      if (name.contains("["))
      {
        String[] n1 = StringUtils.split(name, "[");
        name = InfoboxParserUtil.removeAllParenthesis(n1[1]);
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing LeaderShip infobox: " + e.getMessage());
    }
    
    return null;
  }


}
