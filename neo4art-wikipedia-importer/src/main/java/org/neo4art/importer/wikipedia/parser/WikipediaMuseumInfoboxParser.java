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

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.parser.util.InfoboxAreaParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxCaptionParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxCollectionsParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxCoordinateParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxDateParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxMap;
import org.neo4art.importer.wikipedia.parser.util.InfoboxNameParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxParserUtil;
import org.neo4art.importer.wikipedia.parser.util.InfoboxUrlParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxVisitorsParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxWebsiteParserUtil;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_museum">Template :Infobox_museum</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 20 Mar 2015
 */
public class WikipediaMuseumInfoboxParser
{

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
    Map<String, String> map = InfoboxMap.asMap(text);

    Museum museum = new Museum();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case NAME:
          museum.setName(map.get(key));
          break;
        case BUILDING_NAME:
          museum.setBuildingName(InfoboxNameParser.infoboxBuildingName(map.get(key)));
          break;
        case AREA:
          museum.setArea(InfoboxAreaParser.infoboxArea(map.get(key)));
          break;
        case NATIVE_NAME:
          museum.setNativeName(map.get(key));
          break;
        case NATIVE_NAME_LANG:
          museum.setNativeNameLang(map.get(key));
          break;
        case IMAGE:
          museum.setImage(InfoboxUrlParser.infoboxUrl(map.get(key)));
          break;
        case IMAGESIZE:
          museum.setImagesize(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE_SIZE:
          museum.setImagesize(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case CAPTION:
          museum.setCaption(InfoboxCaptionParser.infoboxCaption(map.get(key)));
          break;
        case ALT:
          museum.setAlt(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case MAP_TYPE:
          museum.setMapType(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case MAP_RELIEF:
          museum.setMapRelief(map.get(key));
          break;
        case MAP_SIZE:
          museum.setMapSize(map.get(key));
          break;
        case MAP_CAPTION:
          museum.setMapCaption(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case MAP_DOT_LABEL:
          museum.setMapDotLabel(map.get(key));
          break;
        case LATITUDE:
          coordinate.setLatD(map.get(key));
          break;
        case LONGITUDE:
          coordinate.setLongD(map.get(key));
          break;
        case LAT_DEG:
          coordinate.setLatD(map.get(key));
          break;
        case LAT_MIN:
          coordinate.setLatM(map.get(key));
          break;
        case LAT_SEC:
          coordinate.setLatS(map.get(key));
          break;
        case LAT_DIR:
          coordinate.setLatNS(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case LON_DEG:
          coordinate.setLongD(map.get(key));
          break;
        case LON_MIN:
          coordinate.setLongM(map.get(key));
          break;
        case LON_SEC:
          coordinate.setLongS(map.get(key));
          break;
        case LON_DIR:
          coordinate.setLongEW(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
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
          InfoboxCoordinateParser.infoboxCoordinate(coordinate, map.get(key));
          break;
        case FORMER_NAME:
          museum.setFormerName(map.get(key));
          break;
        case ESTABLISHED:
          museum.setEstablished(InfoboxDateParser.infoboxEstablished(map.get(key).trim()));
          break;
        case DISSOLVED:
          museum.setDissolved(map.get(key));
          break;
        case LOCATION:
          museum.setLocation(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case TYPE:
          museum.setType(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case ACCREDITATION:
          museum.setAccreditation(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case KEY_HOLDINGS:
          museum.setKeyHoldings(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case COLLECTIONS:
          museum.setCollections(map.get(key));
          break;
        case COLLECTION_SIZE:
          museum.setCollectionSize(InfoboxCollectionsParser.infoboxCollectionCount(map.get(key)));
          break;
        case VISITORS:
          museum.setVisitors(InfoboxVisitorsParser.infoboxVisitors(map.get(key)));
          break;
        case FOUNDER:
          museum.setFounder(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case DIRECTOR:
          museum.setDirector(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case PRESIDENT:
          museum.setPresident(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case CHAIRPERSON:
          museum.setChairperson(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case CURATOR:
          museum.setCurator(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case HISTORIAN:
          museum.setHistorian(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case OWNER:
          museum.setOwner(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case PUBLICTRANSIT:
          museum.setPublictransit(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
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
          museum.setWebsite(InfoboxWebsiteParserUtil.getWebsite(map.get(key)));
          break;
        case EMBEDDED:
          museum.setEmbedded(map.get(key));
          break;
      }
      
      museum.setCoordinates(coordinate);
    }

    return museum;
  }
}
