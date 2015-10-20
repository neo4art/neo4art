package toberefactored.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxCollectionsParser
{

  private static Log logger = LogFactory.getLog(InfoboxCollectionsParser.class);
  
  public InfoboxCollectionsParser()
  {
    
  }
  
  public static String infoboxCollectionCount(String coll)
  {
    try
    {
      if(coll.contains("<"))
      {
        String[] da = StringUtils.split(coll, "<");

        return da[0];
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Collections infobox: " + e.getMessage());
    }

    return null;
  }

}
