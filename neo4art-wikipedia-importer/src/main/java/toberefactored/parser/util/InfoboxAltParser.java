package toberefactored.parser.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxAltParser
{

  private static Log logger = LogFactory.getLog(InfoboxAltParser.class);
  
  public static String infoboxAlt(String alt)
  {
    try
    {
       alt = alt.replace("\n", "");
       
       return alt;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Alt infobox: " + e.getMessage());
    }
    
    return null;
  }
}
