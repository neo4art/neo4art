package toberefactored.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Coordinates;

public class InfoboxCoordinateParser
{
  private static Log logger = LogFactory.getLog(InfoboxCoordinateParser.class);

  public InfoboxCoordinateParser()
  {

  }

  public static Coordinates infoboxRestingPlaceCoordinates(Coordinates coordinates, String coor)
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

        coordinates.setLatD(Double.parseDouble(c[1]));
        coordinates.setLongD(Double.parseDouble(c[2]));

        return coordinates;
      }
      
    }
    catch (Exception e)
    {
      logger.error("Error parsing Coordinates Place infobox: " + e.getMessage());
    }

    return null;
  }

  public static Coordinates infoboxCoordinate(Coordinates coordinates, String coo)
  {
    try
    {
      String[] c = StringUtils.split(coo, "|");
      coordinates.setLatD(Double.parseDouble(c[1]));
      coordinates.setLatM(Double.parseDouble(c[2]));
      coordinates.setLatS(Double.parseDouble(c[3]));
      coordinates.setLatNS(c[4]);
      coordinates.setLongD(Double.parseDouble(c[5]));
      coordinates.setLongM(Double.parseDouble(c[6]));
      coordinates.setLongS(Double.parseDouble(c[7]));
      coordinates.setLongEW(c[8]);

      return coordinates;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Coordinates infobox: " + e.getMessage());
    }

    return null;
  }

}
