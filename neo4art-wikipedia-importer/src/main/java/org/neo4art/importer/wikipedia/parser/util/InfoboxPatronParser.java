package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxPatronParser
{

  private static Log logger = LogFactory.getLog(InfoboxPatronParser.class);
  
  public static String infoboxPatron(String patron)
  {
    try
    {
      patron = patron.replace("[", "");
      patron = patron.replace("]", "");
      patron = patron.replace("'", "");
      patron = patron.replace("}", "");
      patron = patron.replace("|", "");
      patron = patron.replace("\n", "");
  
      return patron;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Patron infobox: " + e.getMessage());
    }
    
    return null;
  }

}
