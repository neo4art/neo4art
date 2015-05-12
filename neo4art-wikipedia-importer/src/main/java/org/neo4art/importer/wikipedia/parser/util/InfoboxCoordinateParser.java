package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Coordinate;

public class InfoboxCoordinateParser
{
  private static Log logger = LogFactory.getLog(InfoboxCoordinateParser.class);

  public InfoboxCoordinateParser()
  {

  }

  public static Coordinate infoboxRestingPlaceCoordinates(Coordinate coordinate, String coor)
  {
    try
    {
      if(coor.length() == 0)
      {
        return null;
      }
      else
      {
        String[] c = StringUtils.split(coor, "|");

        coordinate.setLatD(c[1]);
        coordinate.setLongD(c[2]);

        return coordinate;
      }
      
    }
    catch (Exception e)
    {
      logger.error("Error parsing Coordinate Place infobox: " + e.getMessage());
    }

    return null;
  }

  public static Coordinate infoboxCoordinate(Coordinate coordinate, String coo)
  {
    try
    {
      String[] c = StringUtils.split(coo, "|");
      coordinate.setLatD(c[1]);
      coordinate.setLatM(c[2]);
      coordinate.setLatS(c[3]);
      coordinate.setLatNS(c[4]);
      coordinate.setLongD(c[5]);
      coordinate.setLongM(c[6]);
      coordinate.setLongS(c[7]);
      coordinate.setLongEW(c[8]);

      return coordinate;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Coordinate infobox: " + e.getMessage());
    }

    return null;
  }

}
