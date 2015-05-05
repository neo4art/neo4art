package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxTrainerParser
{
  
  private static Log logger = LogFactory.getLog(InfoboxTrainerParser.class);

  public static String infoboxTraining(String trainer)
  {
    try
    {
      trainer = trainer.replace("[", "");
      trainer = trainer.replace("]", "");
      trainer = trainer.replace("\n", "");
      trainer = trainer.replace("<br /> ", "");
  
      return trainer;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Trainer infobox: " + e.getMessage());
    }
    
    return null;
  }
  
}
