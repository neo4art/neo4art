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
package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.util.Map;

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * 
 * @author Mattia Zaratin
 * @since 30 Apr 2015
 */
public class WikipediaReligiousBuildingSpanishMissionsInTheAmericasInfobox
{
  public static final String NAME   = "name";
  public static final String LATD   = "lat_degrees";
  public static final String LATM   = "lat_minutes";
  public static final String LATS   = "lat_seconds";
  public static final String LATNS  = "lat_direction";
  public static final String LONGD  = "long_degrees";
  public static final String LONGM  = "long_minutes";
  public static final String LONGS  = "long_seconds";
  public static final String LONGEW = "long_direction";
  public static final String IMAGE  = "image";
  public static final String STYLE  = "infobox";

  /**
   * 
   * @param text
   * @return
   */
  public static ReligiousBuilding parse(String text)
  {
    ReligiousBuilding spanishMissionsInTheAmericans = new ReligiousBuilding();
    Coordinate coordinate = new Coordinate();

    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    for (String key : map.keySet())
    {
      switch (key)
      {
        case NAME:
          spanishMissionsInTheAmericans.setBuildingName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE:
          spanishMissionsInTheAmericans.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case STYLE:
          spanishMissionsInTheAmericans.setType(map.get(key));
          break;
        case LATD:
          coordinate.setLatD(map.get(key));
          break;
        case LATM:
          coordinate.setLatM(map.get(key));
          break;
        case LATS:
          coordinate.setLatS(map.get(key));
          break;
        case LATNS:
          coordinate.setLatNS(map.get(key));
          break;
        case LONGD:
          coordinate.setLongD(map.get(key));
          break;
        case LONGM:
          coordinate.setLongM(map.get(key));
          break;
        case LONGS:
          coordinate.setLongS(map.get(key));
          break;
        case LONGEW:
          coordinate.setLongEW(map.get(key));
          break;
      }

      spanishMissionsInTheAmericans.setCoordinates(coordinate);
    }
    
    return spanishMissionsInTheAmericans;
  }
}
