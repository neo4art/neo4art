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
package org.neo4art.importer.wikipedia.parser;

import java.util.Map;

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.HistoricPlace;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

public class WikipediaHistoricPlaceInfoboxParser
{
  public static final String NAME      = "name";
  public static final String IMAGE     = "image";
  public static final String LATD      = "lat_degrees";
  public static final String LATM      = "lat_minutes";
  public static final String LATS      = "lat_seconds";
  public static final String LATNS     = "lat_direction";
  public static final String LONGD     = "long_degrees";
  public static final String LONGM     = "long_minutes";
  public static final String LONGS     = "long_seconds";
  public static final String LONGEW    = "long_direction";
  public static final String LATDEG    = "lat_deg";
  public static final String LATMIN    = "lat_min";
  public static final String LATSEC    = "lat_sec";
  public static final String LONGDEG   = "lon_deg";
  public static final String LONGMIN   = "lon_min";
  public static final String LONGSEC   = "lon_sec";
  public static final String LATITUDE  = "latitude";
  public static final String LONGITUDE = "longitude";
  public static final String WEBSITE   = "website";
  public static final String WEB       = "web";
  public static final String STYLE     = "infobox";

  public WikipediaHistoricPlaceInfoboxParser()
  {
  }

  public static HistoricPlace parse(String text)
  {
    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    HistoricPlace historicPlace = new HistoricPlace();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case NAME:
          historicPlace.setName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE:
          historicPlace.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case STYLE:
          historicPlace.setType(WikipediaInfoboxUtils.getType(map.get(key)));
          break;
        case LATITUDE:
          coordinate.setLatD(map.get(key));
          break;
        case LONGITUDE:
          coordinate.setLongD(map.get(key));
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
        case LATDEG:
          coordinate.setLatD(map.get(key));
          break;
        case LATMIN:
          coordinate.setLatM(map.get(key));
          break;
        case LATSEC:
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
        case LONGDEG:
          coordinate.setLongD(map.get(key));
          break;
        case LONGMIN:
          coordinate.setLongM(map.get(key));
          break;
        case LONGSEC:
          coordinate.setLongS(map.get(key));
          break;
        case LONGEW:
          coordinate.setLongEW(map.get(key));
          break;
        case WEB:
          historicPlace.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
        case WEBSITE:
          historicPlace.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
      }
      
      historicPlace.setCoordinate(coordinate);
    }

    return historicPlace;
  }
}
