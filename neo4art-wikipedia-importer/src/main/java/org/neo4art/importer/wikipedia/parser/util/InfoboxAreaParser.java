package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxAreaParser
{
  private static Log logger = LogFactory.getLog(InfoboxAreaParser.class);
  
  public InfoboxAreaParser()
  {
    
  }
  
  public static int infoboxArea(String area)
  {
    try
    {
      int count;

      String[] da = StringUtils.split(area, "|| ");

      count = Integer.parseInt(da[1]);

      return count;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Area infobox: " + e.getMessage());
    }

    return 0;
  }
}
