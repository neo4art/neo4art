/**
 * Copyright 2015 the eoriginal author or authors.
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_museum">Template :Infobox_museum</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 20 Mar 2015
 */
public class WikipediaMuseumInfoboxParser
{
  private static Log         logger              = LogFactory.getLog(WikipediaMuseumInfoboxParser.class);

  public static final String NAME                = "name";
  public static final String BUILDING_NAME       = "building_name";
  public static final String NATIVE_NAME         = "native_name";
  public static final String NATIVE_NAME_LANG    = "native_name_lang";
  public static final String IMAGE               = "image";
  public static final String IMAGESIZE           = "imagesize";
  public static final String IMAGE_SIZE          = "image_size";
  public static final String CAPTION             = "caption";
  public static final String ALT                 = "alt";
  public static final String MAP_TYPE            = "map_type";
  public static final String MAP_RELIEF          = "map_relief";
  public static final String MAP_SIZE            = "map_size";
  public static final String MAP_CAPTION         = "map_caption";
  public static final String MAP_DOT_LABEL       = "map_dot_label";
  public static final String LATITUDE            = "latitude";
  public static final String LONGITUDE           = "longitude";
  public static final String LAT_DEG             = "lat_deg";
  public static final String LAT_MIN             = "lat_min";
  public static final String LAT_SEC             = "lat_sec";
  public static final String LAT_DIR             = "lat_dir";
  public static final String LON_DEG             = "lon_deg";
  public static final String LON_MIN             = "lon_min";
  public static final String LON_SEC             = "lon_sec";
  public static final String LON_DIR             = "lon_dir";
  public static final String COORDINATES_TYPE    = "coordinates_type";
  public static final String COORDINATES_REGION  = "coordinates_region";
  public static final String COORDINATES_FORMAT  = "coordinates_format";
  public static final String COORDINATES_DISPLAY = "coordinates_display";
  public static final String COORDINATES         = "coordinates";
  public static final String FORMER_NAME         = "former_name";
  public static final String ESTABLISHED         = "established";
  public static final String DISSOLVED           = "dissolved";
  public static final String LOCATION            = "location";
  public static final String TYPE                = "type";
  public static final String ACCREDITATION       = "accreditation";
  public static final String KEY_HOLDINGS        = "key_holdings";
  public static final String COLLECTIONS         = "collections";
  public static final String COLLECTION_SIZE     = "collection";
  public static final String VISITORS            = "visitors";
  public static final String FOUNDER             = "founder";
  public static final String DIRECTOR            = "director";
  public static final String PRESIDENT           = "president";
  public static final String CHAIRPERSON         = "chairperson";
  public static final String CURATOR             = "curator";
  public static final String HISTORIAN           = "historian";
  public static final String OWNER               = "owner";
  public static final String PUBLICTRANSIT       = "publictransit";
  public static final String CAR_PARK            = "car_park";
  public static final String PARKING             = "parking";
  public static final String NETWORK             = "network";
  public static final String WEBSITE             = "website";
  public static final String EMBEDDED            = "embedded";
  public static final String AREA                = "area";

  public static Museum parse(String text)
  {
    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    Museum museum = new Museum();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case NAME:
          museum.setName(infoboxEstablished(map.get(key)).trim());
          break;
        case BUILDING_NAME:
          museum.setName(infoboxEstablished(map.get(key)).trim());
          break;
        case AREA:
          museum.setArea(infoboxArea(map.get(key)));
          break;
        case NATIVE_NAME:
          museum.setNativeName(map.get(key));
          break;
        case NATIVE_NAME_LANG:
          museum.setNativeNameLang(map.get(key));
          break;
        case IMAGE:
          museum.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case IMAGESIZE:
          museum.setImagesize(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE_SIZE:
          museum.setImagesize(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case CAPTION:
          museum.setCaption(infoboxCaption(map.get(key)));
          break;
        case ALT:
          museum.setAlt(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case MAP_TYPE:
          museum.setMapType(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case MAP_RELIEF:
          museum.setMapRelief(map.get(key));
          break;
        case MAP_SIZE:
          museum.setMapSize(map.get(key));
          break;
        case MAP_CAPTION:
          museum.setMapCaption(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case MAP_DOT_LABEL:
          museum.setMapDotLabel(map.get(key));
          break;
        case LATITUDE:
          coordinate.setLatD(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LONGITUDE:
          coordinate.setLongD(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LAT_DEG:
          coordinate.setLatD(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LAT_MIN:
          coordinate.setLatM(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LAT_SEC:
          coordinate.setLatS(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LAT_DIR:
          coordinate.setLatNS(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          museum.setCoordinate(coordinate);
          break;
        case LON_DEG:
          coordinate.setLongD(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LON_MIN:
          coordinate.setLongM(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LON_SEC:
          coordinate.setLongS(map.get(key));
          museum.setCoordinate(coordinate);
          break;
        case LON_DIR:
          coordinate.setLongEW(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          museum.setCoordinate(coordinate);
          break;
        case COORDINATES_TYPE:
          museum.setCoordinatesType(map.get(key));
          break;
        case COORDINATES_REGION:
          museum.setCoordinatesRegion(map.get(key));
          break;
        case COORDINATES_FORMAT:
          museum.setCoordinatesFormat(map.get(key));
          break;
        case COORDINATES_DISPLAY:
          museum.setCoordinatesDisplay(map.get(key));
          break;
        case COORDINATES:
          infoboxRestingPlaceCoordinates(map.get(key));
          museum.setCoordinates(coordinate);
          break;
        case FORMER_NAME:
          museum.setFormerName(map.get(key));
          break;
        case ESTABLISHED:
          museum.setEstablished(infoboxEstablished(map.get(key).trim()));
          break;
        case DISSOLVED:
          museum.setDissolved(map.get(key));
          break;
        case LOCATION:
          museum.setLocation(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case TYPE:
          museum.setType(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ACCREDITATION:
          museum.setAccreditation(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case KEY_HOLDINGS:
          museum.setKeyHoldings(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case COLLECTIONS:
          museum.setCollections(map.get(key));
          break;
        case COLLECTION_SIZE:
          museum.setCollectionSize(infoboxCollectionCount(map.get(key)));
          break;
        case VISITORS:
          museum.setVisitors(infoboxVisitors(map.get(key)));
          break;
        case FOUNDER:
          museum.setFounder(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case DIRECTOR:
          museum.setDirector(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case PRESIDENT:
          museum.setPresident(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case CHAIRPERSON:
          museum.setChairperson(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case CURATOR:
          museum.setCurator(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case HISTORIAN:
          museum.setHistorian(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case OWNER:
          museum.setOwner(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case PUBLICTRANSIT:
          museum.setPublictransit(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case CAR_PARK:
          museum.setCarPark(map.get(key));
          break;
        case PARKING:
          museum.setParking(map.get(key));
          break;
        case NETWORK:
          museum.setNetwork(map.get(key));
          break;
        case WEBSITE:
          museum.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
        case EMBEDDED:
          museum.setEmbedded(map.get(key));
          break;
      }
    }

    return museum;
  }

  public static String infoboxEstablished(String dateString)
  {
    try
    {
      if (dateString.contains("<"))
      {
        String[] da1 = StringUtils.split(dateString, "<");

        return da1[0];
      }

      if (dateString.contains("|"))
      {
        String[] da = StringUtils.split(dateString, "|");

        return da[1];
      }

      return dateString;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Museum infobox: " + e.getMessage());
    }

    return null;
  }

  public static String infoboxCaption(String coll)
  {
    try
    {
      String[] da = StringUtils.split(coll, "<");

      coll = da[0].replace("&nbsp;", " ");

      return coll;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Museum infobox: " + e.getMessage());
    }

    return null;
  }

  public static String infoboxCollectionCount(String coll)
  {
    try
    {
      String[] da = StringUtils.split(coll, "<");

      return da[0];
    }
    catch (Exception e)
    {
      logger.error("Error parsing Museum infobox: " + e.getMessage());
    }

    return null;
  }

  public static int infoboxArea(String area)
  {
    try
    {
      int count;

      String[] da = StringUtils.split(area, "|| ");

      count = Integer.parseInt(da[1]);

      return count;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Museum infobox: " + e.getMessage());
    }

    return 0;
  }

  public static String infoboxVisitors(String visitors)
  {
    try
    {
      if (visitors.contains("<br/>"))
      {
        visitors = visitors.replace("<br/>", "");

        String[] da = StringUtils.split(visitors, "<");

        return da[0];
      }

      if (visitors.contains("<"))
      {
        String[] da = StringUtils.split(visitors, "<");

        return da[0];
      }

      return visitors;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Museum infobox: " + e.getMessage());
    }

    return null;
  }

  public static String[] infoboxRestingPlaceCoordinates(String coor)
  {
    try
    {
      String[] c = StringUtils.split(coor, "|");

      return c;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Museum infobox: " + e.getMessage());
    }

    return null;
  }
}
