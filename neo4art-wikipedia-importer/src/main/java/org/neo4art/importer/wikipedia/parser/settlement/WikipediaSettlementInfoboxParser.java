/**
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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
package org.neo4art.importer.wikipedia.parser.settlement;

import java.util.Map;

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_settlement"> Template:Infobox_settlement</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 19 Mar 2015
 */
public class WikipediaSettlementInfoboxParser
{

  public static final String NAME             = "name";
  public static final String OFFICIAL_NAME    = "official_name";
  public static final String NATIVE_NAME      = "native_name";
  public static final String NATIVE_NAME_LANG = "native_name_lang";
  public static final String OTHER_NAME       = "other_name";
  public static final String SETTLEMENT_TYPE  = "settlement_type";
  // public static final String TRANSLIT_LANG1 = "translit_lang1";
  // public static final String TRANSLIT_LANG1_TYPE = "translit_lang1_type";
  // public static final String TRANSLIT_LANG1_INFO = "translit_lang1_info";
  // public static final String TRANSLIT_LANG1_TYPE1 = "translit_lang1_type1";
  // public static final String TRANSLIT_LANG1_INFO1 = "translit_lang1_info1";
  // public static final String TRANSLIT_LANG1_TYPE2 = "translit_lang1_type2";
  // public static final String TRANSLIT_LANG1_INFO2 = "translit_lang1_info2";
  // public static final String TRANSLIT_LANG2 = "translit_lang2";
  // public static final String TRANSLIT_LANG2_TYPE = "translit_lang2_type";
  // public static final String TRANSLIT_LANG2_INFO = "translit_lang2_info";
  // public static final String TRANSLIT_LANG2_TYPE1 = "translit_lang2_type1";
  // public static final String TRANSLIT_LANG2_INFO1 = "translit_lang2_info1";
  // public static final String TRANSLIT_LANG2_TYPE2 = "translit_lang2_type2";
  // public static final String TRANSLIT_LANG2_INFO2 = "translit_lang2_info2";
  // public static final String IMAGE_SKYLINE = "image_skyline";
  // public static final String IMAGESIZE = "imagesize";
  // public static final String IMAGE_ALT = "image_alt";
  // public static final String IMAGE_CAPTION = "image_caption";
  // public static final String IMAGE = "image";
  // public static final String IMAGE_FLAG = "image_flag";
  // public static final String FLAG_SIZE = "flag_size";
  // public static final String FLAG_ALT = "flag_alt";
  // public static final String FLAG_BORDER = "flag_border";
  // public static final String FLAG_LINK = "flag_link";
  // public static final String IMAGE_SEAL = "image_seal";
  // public static final String SEAL_SIZE = "seal_size";
  // public static final String SEAL_ALT = "seal_alt";
  // public static final String SEAL_LINK = "seal_link";
  // public static final String SEAL_TYPE = "seal_type";
  // public static final String IMAGE_SHIELD = "image_shield";
  // public static final String SHIELD_SIZE = "shield_size";
  // public static final String SHIELD_ALT = "shield_alt";
  // public static final String SHIELD_LINK = "shield_link";
  // public static final String IMAGE_BLANK_EMBLEM = "image_blank_emblem";
  // public static final String BLANK_EMBLEM_TYPE = "blank_emblem_type";
  // public static final String BLANK_EMBLEM_SIZE = "blank_emblem_size";
  // public static final String BLANK_EMBLEM_ALT = "blank_emblem_alt";
  // public static final String BLANK_EMBLEM_LINK = "blank_emblem_link";
  // public static final String ETYMOLOGY = "etymology";
  // public static final String NICKNAME = "nickname";
  // public static final String MOTTO = "motto";
  // public static final String ANTHEM = "anthem";
  // public static final String IMAGE_MAP = "image_map";
  // public static final String MAPSIZE = "mapsize";
  // public static final String MAP_ALT = "map_alt";
  // public static final String MAP_CAPTION = "map_caption";
  // public static final String IMAGE_MAP1 = "image_map1";
  // public static final String MAPSIZE1 = "mapsize1";
  // public static final String MAP_ALT1 = "map_alt1";
  // public static final String MAP_CAPTION1 = "map_caption1";
  // public static final String PUSHPIN_MAP = "pushpin_map";
  // public static final String PUSHPIN_LABEL_POSITION =
  // "pushpin_label_position";
  // public static final String PUSHPIN_LABEL = "pushpin_label";
  // public static final String PUSHPIN_MAP_ALT = "pushpin_map_alt";
  // public static final String PUSHPIN_MAPSIZE = "pushpin_mapsize";
  // public static final String PUSHPIN_RELIEF = "pushpin_relief";
  // public static final String PUSHPIN_MAP_CAPTION = "pushpin_map_caption";
  // public static final String PUSHPIN_MAP1 = "pushpin_map1";
  // public static final String PUSHPIN_LABEL_POSITION1 =
  // "pushpin_label_position1";
  // public static final String PUSHPIN_LABEL1 = "pushpin_label1";
  // public static final String PUSHPIN_MAP_ALT1 = "pushpin_map_alt1";
  // public static final String PUSHPIN_MAPSIZE1 = "pushpin_mapsize1";
  // public static final String PUSHPIN_MAP_CAPTION1 = "pushpin_map_caption1";
  public static final String LATD             = "latd";
  public static final String LATM             = "latm";
  public static final String LATS             = "lats";
  public static final String LATNS            = "latNS";
  public static final String LONGD            = "longd";
  public static final String LONGM            = "longm";
  public static final String LONGS            = "longs";
  public static final String LONGEW           = "longEW";
  public static final String LATDEG           = "lat_deg";
  public static final String LATMIN           = "lat_min";
  public static final String LATSEC           = "lat_sec";
  public static final String LONGDEG          = "lon_deg";
  public static final String LONGMIN          = "lon_min";
  public static final String LONGSEC          = "lon_sec";
  public static final String LATITUDE         = "latitude";
  public static final String LONGITUDE        = "longitude";
  // public static final String COOR_PINPOINT = "coor_pinpoint";
  // public static final String COORDINATES_REGION = "coordinates_region";
  // public static final String COORDINATES_TYPE = "coordinates_type";
  // public static final String COORDINATES_DISPLAY = "coordinates_display";
  // public static final String COORDINATES_WIKIDATA = "coordinates_wikidata";
  // public static final String COORDINATES_FORMAT = "coordinates_format";
  // public static final String COORDINATES_FOOTNOTES =
  // "coordinates_footnotes";
  // public static final String GRID_NAME = "grid_name";
  // public static final String GRID_POSITION = "grid_position";
  // public static final String SUBDIVISION_TYPE = "subdivision_type";
  // public static final String SUBDIVISION_NAME = "subdivision_name";
  // public static final String SUBDIVISION_TYPE1 = "subdivision_type1";
  // public static final String SUBDIVISION_NAME1 = "subdivision_name1";
  // public static final String SUBDIVISION_TYPE2 = "subdivision_type2";
  // public static final String SUBDIVISION_NAME2 = "subdivision_name2";
  // public static final String ESTABLISHED_TITLE = "established_title";
  // public static final String ESTABLISHED_DATE = "established_date";
  // public static final String ESTABLISHED_TITLE1 = "established_title1";
  // public static final String ESTABLISHED_DATE1 = "established_date1";
  // public static final String ESTABLISHED_TITLE2 = "established_title2";
  // public static final String ESTABLISHED_DATE2 = "established_date2";
  // public static final String ESTABLISHED_TITLE3 = "established_title3";
  // public static final String ESTABLISHED_DATE3 = "established_date3";
  // public static final String ESTABLISHED_TITLE4 = "established_title4";
  // public static final String ESTABLISHED_DATE4 = "established_date4";
  // public static final String EXTINCT_TITLE = "extinct_title";
  // public static final String EXTINCT_DATE = "extinct_date";
  // public static final String FOUNDER = "founder";
  // public static final String NAMED_FOR = "named_for";
  // public static final String SEAT_TYPE = "seat_type";
  // public static final String SEAT = "seat";
  // public static final String SEAT1_TYPE = "seat1_type";
  // public static final String SEAT1 = "seat1";
  // public static final String PARTS_TYPE = "parts_type";
  // public static final String PARTS_STYLE = "parts_style";
  // public static final String PARTS = "parts";
  // public static final String P1 = "p1";
  // public static final String P2 = "p2";
  // public static final String GOVERNMENT_FOOTNOTES = "government_footnotes";
  // public static final String GOVERNMENT_TYPE = "government_type";
  // public static final String GOVERNING_BODY = "governing_body";
  // public static final String LEADER_PARTY = "leader_party";
  // public static final String LEADER_TITLE = "leader_title";
  // public static final String LEADER_NAME = "leader_name";
  // public static final String LEADER_TITLE1 = "leader_title1";
  // public static final String LEADER_NAME1 = "leader_name1";
  // public static final String TOTAL_TYPE = "total_type";
  // public static final String UNIT_PREF = "unit_pref";
  // public static final String AREA_FOOTNOTES = "area_footnotes";
  // public static final String AREA_MAGNITUDE = "area_magnitude";
  // public static final String DUNAM_LINK = "dunam_link";
  // public static final String AREA_TOTAL_KM2 = "area_total_km2";
  // public static final String AREA_TOTAL_SQ_MI = "area_total_sq_mi";
  // public static final String AREA_TOTAL_HA = "area_total_ha";
  // public static final String AREA_TOTAL_ACRE = "area_total_acre";
  // public static final String AREA_TOTAL_DUNAM = "area_total_dunam";
  // public static final String AREA_LAND_KM2 = "area_land_km2";
  // public static final String AREA_LAND_SQ_MI = "area_land_sq_mi";
  // public static final String AREA_LAND_HA = "area_land_ha";
  // public static final String AREA_LAND_ACRE = "area_land_acre";
  // public static final String AREA_LAND_DUNAM = "area_land_dunam";
  // public static final String AREA_WATER_KM2 = "area_water_km2";
  // public static final String AREA_WATER_SQ_MI = "area_water_sq_mi";
  // public static final String AREA_WATER_HA = "area_water_ha";
  // public static final String AREA_WATER_ACRE = "area_water_acre";
  // public static final String AREA_WATER_DUNAM = "area_water_dunam";
  // public static final String AREA_WATER_PERCENT = "area_water_percent";
  // public static final String AREA_URBAN_FOOTNOTES = "area_urban_footnotes";
  // public static final String AREA_URBAN_KM2 = "area_urban_km2";
  // public static final String AREA_URBAN_SQ_MI = "area_urban_sq_mi";
  // public static final String AREA_URBAN_HA = "area_urban_ha";
  // public static final String AREA_URBAN_ACRE = "area_urban_acre";
  // public static final String AREA_URBAN_DUNAM = "area_urban_dunam";
  // public static final String AREA_RURAL_FOOTNOTES = "area_rural_footnotes";
  // public static final String AREA_RURAL_KM2 = "area_rural_km2";
  // public static final String AREA_RURAL_SQ_MI = "area_rural_sq_mi";
  // public static final String AREA_RURAL_HA = "area_rural_ha";
  // public static final String AREA_RURAL_ACRE = "area_rural_acre";
  // public static final String AREA_RURAL_DUNAM = "area_rural_dunam";
  // public static final String AREA_METRO_FOOTNOTES = "area_metro_footnotes";
  // public static final String AREA_METRO_KM2 = "area_metro_km2";
  // public static final String AREA_METRO_SQ_MI = "area_metro_sq_mi";
  // public static final String AREA_METRO_HA = "area_metro_ha";
  // public static final String AREA_METRO_ACRE = "area_metro_acre";
  // public static final String AREA_METRO_DUNAM = "area_metro_dunam";
  // public static final String AREA_RANK = "area_rank";
  // public static final String AREA_BLANK1_TITLE = "area_blank1_title";
  // public static final String AREA_BLANK1_KM2 = "area_blank1_km2";
  // public static final String AREA_BLANK1_SQ_MI = "area_blank1_sq_mi";
  // public static final String AREA_BLANK1_HA = "area_blank1_ha";
  // public static final String AREA_BLANK1_ACRE = "area_blank1_acre";
  // public static final String AREA_BLANK1_DUNAM = "area_blank1_dunam";
  // public static final String AREA_BLANK2_TITLE = "area_blank2_title";
  // public static final String AREA_BLANK2_KM2 = "area_blank2_km2";
  // public static final String AREA_BLANK2_SQ_MI = "area_blank2_sq_mi";
  // public static final String AREA_BLANK2_HA = "area_blank2_ha";
  // public static final String AREA_BLANK2_ACRE = "area_blank2_acre";
  // public static final String AREA_BLANK2_DUNAM = "area_blank2_dunam";
  // public static final String AREA_NOTE = "area_note";
  // public static final String DIMENSIONS_FOOTNOTES = "dimensions_footnotes";
  // public static final String LENGTH_KM = "length_km";
  // public static final String LENGTH_MI = "length_mi";
  // public static final String WIDTH_KM = "width_km";
  // public static final String WIDTH_MI = "width_mi";
  // public static final String ELEVATION_FOOTNOTES = "elevation_footnotes";
  // public static final String ELEVATION_M = "elevation_m";
  // public static final String ELEVATION_FT = "elevation_ft";
  // public static final String ELEVATION_POINT = "elevation_point";
  // public static final String ELEVATION_MAX_FOOTNOTES =
  // "elevation_max_footnotes";
  // public static final String ELEVATION_MAX_M = "elevation_max_m";
  // public static final String ELEVATION_MAX_FT = "elevation_max_ft";
  // public static final String ELEVATION_MAX_POINT = "elevation_max_point";
  // public static final String ELEVATION_MAX_RANK = "elevation_max_rank";
  // public static final String ELEVATION_MIN_FOOTNOTES =
  // "elevation_min_footnotes";
  // public static final String ELEVATION_MIN_M = "elevation_min_m";
  // public static final String ELEVATION_MIN_FT = "elevation_min_ft";
  // public static final String ELEVATION_MIN_POINT = "elevation_min_point";
  // public static final String ELEVATION_MIN_RANK = "elevation_min_rank";
  // public static final String POPULATION_AS_OF = "population_as_of";
  // public static final String POPULATION_FOOTNOTES = "population_footnotes";
  // public static final String POPULATION_TOTAL = "population_total";
  // public static final String POP_EST_AS_OF = "pop_est_as_of";
  // public static final String POP_EST_FOOTNOTES = "pop_est_footnotes";
  // public static final String POPULATION_EST = "population_est";
  // public static final String POPULATION_RANK = "population_rank";
  // public static final String POPULATION_DENSITY_KM2 =
  // "population_density_km2";
  // public static final String POPULATION_DENSITY_SQ_MI =
  // "population_density_sq_mi";
  // public static final String POPULATION_URBAN_FOOTNOTES =
  // "population_urban_footnotes";
  // public static final String POPULATION_URBAN = "population_urban";
  // public static final String POPULATION_DENSITY_URBAN_KM2 =
  // "population_density_urban_km2";
  // public static final String POPULATION_DENSITY_URBAN_SQ_MI =
  // "population_density_urban_sq_mi";
  // public static final String POPULATION_RURAL_FOOTNOTES =
  // "population_rural_footnotes";
  // public static final String POPULATION_RURAL = "population_rural";
  // public static final String POPULATION_DENSITY_RURAL_KM2 =
  // "population_density_rural_km2";
  // public static final String POPULATION_DENSITY_RURAL_SQ_MI =
  // "population_density_rural_sq_mi";
  // public static final String POPULATION_METRO_FOOTNOTES =
  // "population_metro_footnotes";
  // public static final String POPULATION_METRO = "population_metro";
  // public static final String POPULATION_DENSITY_METRO_KM2 =
  // "population_density_metro_km2";
  // public static final String POPULATION_DENSITY_METRO_SQ_MI =
  // "population_density_metro_sq_mi";
  // public static final String POPULATION_DENSITY = "population_density";
  // public static final String POPULATION_DENSITY_RANK =
  // "population_density_rank";
  // public static final String POPULATION_BLANK1_TITLE =
  // "population_blank1_title";
  // public static final String POPULATION_BLANK1 = "population_blank1";
  // public static final String POPULATION_DENSITY_BLANK1_KM2 =
  // "population_density_blank1_km2";
  // public static final String POPULATION_DENSITY_BLANK1_SQ_MI =
  // "population_density_blank1_sq_mi";
  // public static final String POPULATION_BLANK2_TITLE =
  // "population_blank2_title";
  // public static final String POPULATION_BLANK2 = "population_blank2";
  // public static final String POPULATION_DENSITY_BLANK2_KM2 =
  // "population_density_blank2_km2";
  // public static final String POPULATION_DENSITY_BLANK2_SQ_MI =
  // "population_density_blank2_sq_mi";
  // public static final String POPULATION_DEMONYM = "population_demonym";
  // public static final String POPULATION_NOTE = "population_note";
  // public static final String DEMOGRAPHICS_TYPE1 = "demographics_type1";
  // public static final String DEMOGRAPHICS1_FOOTNOTES =
  // "demographics1_footnotes";
  // public static final String DEMOGRAPHICS1_TITLE1 = "demographics1_title1";
  // public static final String DEMOGRAPHICS1_INFO1 = "demographics1_info1";
  // public static final String DEMOGRAPHICS_TYPE2 = "demographics_type2";
  // public static final String DEMOGRAPHICS2_FOOTNOTES =
  // "demographics2_footnotes";
  // public static final String DEMOGRAPHICS2_TITLE1 = "demographics2_title1";
  // public static final String DEMOGRAPHICS2_INFO1 = "demographics2_info1";
  // public static final String TIMEZONE1 = "timezone1";
  // public static final String UTC_OFFSET1 = "utc_offset1";
  // public static final String TIMEZONE1_DST = "timezone1_DST";
  // public static final String UTC_OFFSET1_DST = "utc_offset1_DST";
  // public static final String TIMEZONE2 = "timezone2";
  // public static final String UTC_OFFSET2 = "utc_offset2";
  // public static final String TIMEZONE2_DST = "timezone2_DST";
  // public static final String UTC_OFFSET2_DST = "utc_offset2_DST";
  // public static final String POSTAL_CODE_TYPE = "postal_code_type";
  // public static final String POSTAL_CODE = "postal_code";
  // public static final String POSTAL2_CODE_TYPE = "postal2_code_type";
  // public static final String POSTAL2_CODE = "postal2_code";
  // public static final String AREA_CODE_TYPE = "area_code_type";
  // public static final String AREA_CODE = "area_code";
  // public static final String GEOCODE = "geocode";
  // public static final String ISO_CODE = "iso_code";
  // public static final String REGISTRATION_PLATE = "registration_plate";
  // public static final String BLANK_NAME_SEC1 = "blank_name_sec1";
  // public static final String BLANK_INFO_SEC1 = "blank_info_sec1";
  // public static final String BLANK1_NAME_SEC1 = "blank1_name_sec1";
  // public static final String BLANK1_INFO_SEC1 = "blank1_info_sec1";
  // public static final String BLANK2_NAME_SEC1 = "blank2_name_sec1";
  // public static final String BLANK2_INFO_SEC1 = "blank2_info_sec1";
  // public static final String BLANK_NAME_SEC2 = "blank_name_sec2";
  // public static final String BLANK_INFO_SEC2 = "blank_info_sec2";
  // public static final String BLANK1_NAME_SEC2 = "blank1_name_sec2";
  // public static final String BLANK1_INFO_SEC2 = "blank1_info_sec2";
  // public static final String BLANK2_NAME_SEC2 = "blank2_name_sec2";
  // public static final String BLANK2_INFO_SEC2 = "blank2_info_sec2";
  public static final String WEBSITE          = "website";
  public static final String WEB              = "web";
  public static final String STYLE            = "infobox";

  // public static final String FOOTNOTES = "footnotes";

  public WikipediaSettlementInfoboxParser()
  {
  }

  public static Settlement parse(String text)
  {

    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    Settlement settlement = new Settlement();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {

      switch (key)
      {

        case NAME:
          settlement.setName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case STYLE:
          settlement.setType(WikipediaInfoboxUtils.getType(map.get(key)));
          break;
        case OFFICIAL_NAME:
          settlement.setOfficialName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case NATIVE_NAME:
          settlement.setNativeName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case NATIVE_NAME_LANG:
          settlement.setNativeNameLang(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case OTHER_NAME:
          settlement.setOtherName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case SETTLEMENT_TYPE:
          settlement.setSettlementType(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case LATITUDE:
          break;
        case LONGITUDE:
          coordinate.setLongD(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LATD:
          break;
        case LATM:
          coordinate.setLatM(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LATS:
          coordinate.setLatS(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LATDEG:

          break;
        case LATMIN:

          coordinate.setLatM(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LATSEC:

          coordinate.setLatS(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LATNS:

          coordinate.setLatNS(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGD:

          coordinate.setLongD(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGM:

          coordinate.setLongM(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGS:

          coordinate.setLongS(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGDEG:

          coordinate.setLongD(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGMIN:

          coordinate.setLongM(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGSEC:

          coordinate.setLongS(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case LONGEW:

          coordinate.setLongEW(map.get(key));
          settlement.setCoordinate(coordinate);
          break;
        case WEB:
          settlement.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
        case WEBSITE:
          settlement.setWebsite(WikipediaInfoboxUtils.getWebsite(map.get(key)));
          break;
      }

    }

    return settlement;
  }
}
