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

import java.net.MalformedURLException;
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

  public static HistoricPlace parse(String text) throws MalformedURLException
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
          if (map.get(key).equals(""))
          {
            coordinate.setLatD(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGITUDE:
          if (map.get(key).equals(""))
          {
            coordinate.setLongD(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATD:
          if (map.get(key).equals(""))
          {
            coordinate.setLatD(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATM:
          if (map.get(key).equals(""))
          {
            coordinate.setLatM(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatM(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATS:
          if (map.get(key).equals(""))
          {
            coordinate.setLatS(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatS(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATDEG:
          if (map.get(key).equals(""))
          {
            coordinate.setLatD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATMIN:
          if (map.get(key).equals(""))
          {
            coordinate.setLatM(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatM(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATSEC:
          if (map.get(key).equals(""))
          {
            coordinate.setLatS(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatS(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LATNS:
          if (map.get(key).equals(""))
          {
            coordinate.setLatNS("");
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLatNS(map.get(key));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGD:
          if (map.get(key).equals(""))
          {
            coordinate.setLongD(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGM:
          if (map.get(key).equals(""))
          {
            coordinate.setLongM(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongM(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGS:
          if (map.get(key).equals(""))
          {
            coordinate.setLongS(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongS(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGDEG:
          if (map.get(key).equals(""))
          {
            coordinate.setLongD(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongD(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGMIN:
          if (map.get(key).equals(""))
          {
            coordinate.setLongM(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongM(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGSEC:
          if (map.get(key).equals(""))
          {
            coordinate.setLongS(0);
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongS(Double.parseDouble(map.get(key)));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case LONGEW:
          if (map.get(key).equals(""))
          {
            coordinate.setLongEW("");
            historicPlace.setCoordinate(coordinate);
          }
          else
          {
            coordinate.setLongEW(map.get(key));
            historicPlace.setCoordinate(coordinate);
          }
          break;
        case WEB:
          historicPlace.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
        case WEBSITE:
          historicPlace.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
      }

    }

    return historicPlace;
  }

}
