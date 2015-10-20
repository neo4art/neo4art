package toberefactored.parser.util;

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
      if(name.contains("|"))
      {
        String[] n1 = StringUtils.split(name, "|");
        
        name = n1[0].replace("[", "");
        name = name.replace("]", "");
      }
      else
      {
        name = InfoboxParserUtil.removeAllParenthesis(name);
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Architectural infobox: " + e.getMessage());      
    }
    
    return name;
  }
}
