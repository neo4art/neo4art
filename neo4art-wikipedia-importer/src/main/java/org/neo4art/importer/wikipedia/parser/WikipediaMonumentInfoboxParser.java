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

import org.apache.commons.lang3.StringUtils;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Monument;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_monument">Template :Infobox_monument</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaMonumentInfoboxParser
{
  public static final String MONUMENT_NAME = "monument_name";
  public static final String NATIVE_NAME   = "native_name";
  public static final String IMAGE         = "image";
  public static final String CAPTION       = "caption";
  public static final String COORDINATES   = "coordinates";
  public static final String LOCATION      = "location";
  public static final String DESIGNER      = "designer";
  public static final String TYPE          = "type";
  public static final String MATERIAL      = "material";
  public static final String LENGTH        = "length";
  public static final String WIDTH         = "width";
  public static final String HEIGHT        = "height";
  public static final String BEGIN         = "begin";
  public static final String COMPLETE      = "complete";
  public static final String OPEN          = "open";
  public static final String DEDICATED_TO  = "dedicated_to";
  public static final String MAP_IMAGE     = "map_image";
  public static final String MAP_TEXT      = "map_text";
  public static final String MAP_WIDTH     = "map_width";
  public static final String RELIEF        = "relief";
  public static final String LATD          = "latD";
  public static final String LATM          = "latM";
  public static final String LATS          = "latS";
  public static final String LATNS         = "latNS";
  public static final String LONGD         = "longD";
  public static final String LONGM         = "longM";
  public static final String LONGS         = "longS";
  public static final String LONGEW        = "longEW";
  public static final String LAT           = "lat";
  public static final String LONG          = "long";
  public static final String EXTRA         = "extra";

  public WikipediaMonumentInfoboxParser()
  {
  }

  public static Monument parse(String text)
  {
    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    Monument monument = new Monument();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {

      switch (key)
      {

        case MONUMENT_NAME:
          monument.setMonumentName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case NATIVE_NAME:
          monument.setNativeName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE:
          monument.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case CAPTION:
          monument.setCaption(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case COORDINATES:
          String[] c = infoboxRestingPlaceCoordinates(map.get(key));
//          coordinate.setLatD(Double.parseDouble(c[1]));
//          coordinate.setLatM(Double.parseDouble(c[2]));
//          coordinate.setLatS(Double.parseDouble(c[3]));
//          coordinate.setLatNS(c[4]);
//          coordinate.setLongD(Double.parseDouble(c[5]));
//          coordinate.setLongM(Double.parseDouble(c[6]));
//          coordinate.setLongS(Double.parseDouble(c[7]));
          coordinate.setLongEW(c[8]);
          monument.setCoordinates(coordinate);
          break;
        case LOCATION:
          monument.setLocation(map.get(key));
          break;
        case DESIGNER:
          monument.setDesigner(map.get(key));
          break;
        case TYPE:
          monument.setType(map.get(key));
          break;
        case MATERIAL:
          monument.setMaterial(map.get(key));
          break;
        case LENGTH:
          monument.setLength(map.get(key));
          break;
        case WIDTH:
          monument.setWidth(map.get(key));
          break;
        case HEIGHT:
          monument.setHeight(map.get(key));
          break;
        case BEGIN:
          monument.setBegin(map.get(key));
          break;
        case COMPLETE:
          monument.setComplete(map.get(key));
          break;
        case OPEN:
          monument.setOpen(map.get(key));
          break;
        case DEDICATED_TO:
          monument.setDedicatedTo(map.get(key));
          break;
        case MAP_IMAGE:
          monument.setMapImage(map.get(key));
          break;
        case MAP_TEXT:
          monument.setMapText(map.get(key));
          break;
        case MAP_WIDTH:
          monument.setMapWidth(map.get(key));
          break;
        case RELIEF:
          monument.setRelief(map.get(key));
          break;
        case LATD:

          coordinate.setLatD(map.get(key));
          monument.setCoordinate(coordinate);
          break;
        case LATM:

          coordinate.setLatM(map.get(key));
          monument.setCoordinate(coordinate);
          break;
        case LATS:

          coordinate.setLatS(map.get(key));
          monument.setCoordinate(coordinate);
          break;
        case LATNS:

          coordinate.setLatNS(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          monument.setCoordinate(coordinate);
          break;
        case LONGD:

          coordinate.setLongD(map.get(key));
          monument.setCoordinate(coordinate);
          break;
        case LONGM:

          coordinate.setLongM(map.get(key));
          monument.setCoordinate(coordinate);
          break;
        case LONGS:

          coordinate.setLongS(map.get(key));
          monument.setCoordinate(coordinate);
          break;
        case LONGEW:

          coordinate.setLongEW(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          monument.setCoordinate(coordinate);
          break;
        case LAT:
          monument.setLat(map.get(key));
          break;
        case LONG:
          monument.setLon(map.get(key));
          break;
        case EXTRA:
          monument.setExtra(map.get(key));
          break;
      }
    }

    return monument;
  }

  public static String[] infoboxRestingPlaceCoordinates(String coor)
  {

    String[] c = StringUtils.split(coor, "|");

    return c;
  }

  public static String[] infoboxLocation(String coor)
  {

    String[] c = StringUtils.split(coor, ",");

    return c;
  }

}
