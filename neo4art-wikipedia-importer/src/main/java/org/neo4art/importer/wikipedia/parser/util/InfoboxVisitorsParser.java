package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxVisitorsParser
{

  private static Log logger = LogFactory.getLog(InfoboxVisitorsParser.class);
  
  public InfoboxVisitorsParser()
  {
    
  }
  
  public static String infoboxVisitors(String visitors)
  {
    try
    {
      if (visitors.contains("<br/>"))
      {
        visitors = visitors.replace("<br/>", "");

        String[] da = StringUtils.split(visitors, "<");

        return da[0];
      }

      if (visitors.contains("<"))
      {
        String[] da = StringUtils.split(visitors, "<");

        return da[0];
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Visitors infobox: " + e.getMessage());
    }

    return null;
  }
  
}
