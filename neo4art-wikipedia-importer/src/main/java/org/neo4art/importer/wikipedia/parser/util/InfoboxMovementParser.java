package org.neo4art.importer.wikipedia.parser.util;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.ArtMovement;

public class InfoboxMovementParser
{

  private static Log logger = LogFactory.getLog(InfoboxMovementParser.class);
  
  public InfoboxMovementParser()
  {
    
  }
  
  public static ArrayList<ArtMovement> infoboxMovement(ArtMovement artMovement, String movement)
  {
    ArrayList<ArtMovement> artMovementArrayList = new ArrayList<ArtMovement>();
    try
    {
      movement = movement.replace("[", "");
      movement = movement.replace("]", "");
      movement = movement.replace("\n", "");
  
      String[] work = StringUtils.split(movement, ",");
  
      if (work.length > 0)
      {
        for (int i = 0; i < work.length; i++)
        {
          artMovement = new ArtMovement();
          artMovement.setName(work[i]);
          artMovementArrayList.add(artMovement);
        }
      }
      
      return artMovementArrayList;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Movement infobox: " + e.getMessage());
    }
    
    return null;
  }
  
}
