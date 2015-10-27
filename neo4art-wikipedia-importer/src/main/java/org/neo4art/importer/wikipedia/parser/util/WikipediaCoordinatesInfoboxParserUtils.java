/**
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.neo4art.importer.wikipedia.parser.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Coordinates;

/**
 * @author Lorenzo Speranzoni
 * @since 22 Oct 2015
 */
public class WikipediaCoordinatesInfoboxParserUtils {

  private static Log logger = LogFactory.getLog(WikipediaCoordinatesInfoboxParserUtils.class);
  
  public static final String COORDINATES = "coordinates";

  public static final String LATITUDE    = "latitude";
  public static final String LONGITUDE   = "longitude";

  public static final String LAT_D       = "latd";
  public static final String LAT_M       = "latm";
  public static final String LAT_S       = "lats";
  public static final String LAT_NS      = "latNS";

  public static final String LONG_D      = "longd";
  public static final String LONG_M      = "longm";
  public static final String LONG_S      = "longs";
  public static final String LONG_EW     = "longEW";

  /**
   * @param map
   * @return
   */
  public static Coordinates buildCoordinates(Map<String, String> map) {

    Coordinates coordinates = new Coordinates();

    for (String key : map.keySet()) {

      try {
        
        switch (key) {
          case COORDINATES:
            coordinates.setName(map.get(key));
            break;
            
          case LATITUDE:
            coordinates.setLatitude(Double.parseDouble(map.get(key)));
            break;
          case LONGITUDE:
            coordinates.setLongitude(Double.parseDouble(map.get(key)));
            break;

          case LAT_D:
            coordinates.setLatD(Double.parseDouble(map.get(key)));
            break;
          case LAT_M:
            coordinates.setLatM(Double.parseDouble(map.get(key)));
            break;
          case LAT_S:
            coordinates.setLatS(Double.parseDouble(map.get(key)));
            break;
          case LAT_NS:
            coordinates.setLatNS(map.get(key));
            break;

          case LONG_D:
            coordinates.setLongD(Double.parseDouble(map.get(key)));
            break;
          case LONG_M:
            coordinates.setLongM(Double.parseDouble(map.get(key)));
            break;
          case LONG_S:
            coordinates.setLongS(Double.parseDouble(map.get(key)));
            break;
          case LONG_EW:
            coordinates.setLongEW(map.get(key));
            break;
        }
      }
      catch (NumberFormatException nfe) {
        logger.trace(nfe.getMessage());
      }
    }

    return (coordinates.getStatus() == Coordinates.COMPUTED) ? coordinates : null;
  }

}
