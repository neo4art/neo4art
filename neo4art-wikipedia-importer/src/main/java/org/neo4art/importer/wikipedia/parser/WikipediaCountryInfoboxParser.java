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
import org.neo4art.domain.Country;
import org.neo4art.importer.wikipedia.parser.util.InfoboxMap;
import org.neo4art.importer.wikipedia.parser.util.InfoboxNameParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxParserUtil;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artist">Template:Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaCountryInfoboxParser
{

  // public static final String MICRONATION = "micronation";
  public static final String CONVENTIONAL_LONG_NAME = "conventional_long_name";
  public static final String NATIVE_NAME            = "native_name";
  public static final String COMMON_NAME            = "common_name";
  // public static final String STATUS = "status";
  // public static final String IMAGE_FLAG = "image_flag";
  // public static final String ALT_FLAG = "alt_flag";
  // public static final String FLAG_BORDER = "flag_border";
  // public static final String IMAGE_FLAG2 = "image_flag2";
  // public static final String ALT_FLAG2 = "alt_flag2";
  // public static final String FLAG2_BORDER = "flag2_border";
  // public static final String IMAGE_COAT = "image_coat";
  // public static final String ALT_COAT = "alt_coat";
  // public static final String SYMBOL_TYPE = "symbol_type";
  // public static final String NATIONAL_MOTTO = "national_motto";
  // public static final String ENGLISHMOTTO = "englishmotto";
  // public static final String NATIONAL_ANTHEM = "national_anthem";
  // public static final String ROYAL_ANTHEM = "royal_anthem";
  // public static final String OTHER_SYMBOL_TYPE = "other_symbol_type";
  // public static final String OTHER_SYMBOL = "other_symbol";
  // public static final String IMAGE_MAP = "image_map";
  // public static final String LOCTEXT = "loctext";
  // public static final String ALT_MAP = "alt_map";
  // public static final String MAP_CAPTION = "map_caption";
  // public static final String IMAGE_MAP2 = "image_map2";
  // public static final String ALT_MAP2 = "alt_map2";
  // public static final String MAP_CAPTION2 = "map_caption2";
  // public static final String CAPITAL = "capital";
  // public static final String CAPITAL2 = "capital2";
  public static final String LATD                   = "latd";
  public static final String LATM                   = "latm";
  public static final String LATS                   = "lats";
  public static final String LATNS                  = "latNS";
  public static final String LONGD                  = "longd";
  public static final String LONGM                  = "longm";
  public static final String LONGS                  = "longs";
  public static final String LONGEW                 = "longEW";

  // public static final String LARGEST_CITY = "largest_city";
  // public static final String LARGEST_SETTLEMENT_TYPE = "largest_settlement_type";
  // public static final String LARGEST_SETTLEMENT = "largest_settlement";
  // public static final String OFFICIAL_LANGUAGES = "official_languages";
  // public static final String NATIONAL_LANGUAGES = "national_languages";
  // public static final String REGIONAL_LANGUAGES = "regional_languages";
  // public static final String LANGUAGES_TYPE = "languages_type";
  // public static final String LANGUAGES = "languages";
  // public static final String LANGUAGES_SUB = "languages_sub";
  // public static final String LANGUAGES2_TYPE = "languages2_type";
  // public static final String LANGUAGES2 = "languages2";
  // public static final String LANGUAGES2_SUB = "languages2_sub";
  // public static final String ETHNIC_GROUPS = "ethnic_groups";
  // public static final String ETHNIC_GROUPS_YEAR = "ethnic_groups_year";
  // public static final String NATIONALITIES = "nationalities";
  // public static final String RELIGION = "religion";
  // public static final String DEMONYM = "demonym";
  // public static final String GOVERNMENT_TYPE = "government_type";
  // public static final String LEADER_TITLE1 = "leader_title1";
  // public static final String LEADER_NAME1 = "leader_name1";
  // public static final String LEADER_TITLE2 = "leader_title2";
  // public static final String LEADER_NAME2 = "leader_name2";
  // public static final String LEADER_TITLE3 = "leader_title3";
  // public static final String LEADER_NAME3 = "leader_name3";
  // public static final String LEADER_TITLE4 = "leader_title4";
  // public static final String LEADER_NAME4 = "leader_name4";
  // public static final String LEADER_TITLE5 = "leader_title5";
  // public static final String LEADER_NAME5 = "leader_name5";
  // public static final String LEADER_TITLE6 = "leader_title6";
  // public static final String LEADER_NAME6 = "leader_name6";
  // public static final String LEGISLATURE = "legislature";
  // public static final String UPPER_HOUSE = "upper_house";
  // public static final String LOWER_HOUSE = "lower_house";
  // public static final String SOVEREIGNTY_TYPE = "sovereignty_type";
  // public static final String SOVEREIGNTY_NOTE = "sovereignty_note";
  // public static final String ESTABLISHED_EVENT1 = "established_event1";
  // public static final String ESTABLISHED_DATE1 = "established_date1";
  // public static final String ESTABLISHED_EVENT2 = "established_event2";
  // public static final String ESTABLISHED_DATE2 = "established_date2";
  // public static final String ESTABLISHED_EVENT3 = "established_event3";
  // public static final String ESTABLISHED_DATE3 = "established_date3";
  // public static final String ESTABLISHED_EVENT4 = "established_event4";
  // public static final String ESTABLISHED_DATE4 = "established_date4";
  // public static final String ESTABLISHED_EVENT5 = "established_event5";
  // public static final String ESTABLISHED_DATE5 = "established_date5";
  // public static final String ESTABLISHED_EVENT6 = "established_event6";
  // public static final String ESTABLISHED_DATE6 = "established_date6";
  // public static final String ESTABLISHED_EVENT7 = "established_event7";
  // public static final String ESTABLISHED_DATE7 = "established_date7";
  // public static final String ESTABLISHED_EVENT8 = "established_event8";
  // public static final String ESTABLISHED_DATE8 = "established_date8";
  // public static final String ESTABLISHED_EVENT9 = "established_event9";
  // public static final String ESTABLISHED_DATE9 = "established_date9";
  // public static final String AREA_RANK = "area_rank";
  // public static final String AREA_MAGNITUDE = "area_magnitude";
  // public static final String AREA = "area";
  // public static final String AREA_KM2 = "area_km2";
  // public static final String AREA_SQ_MI = "area_sq_mi";
  // public static final String AREA_FOOTNOTE = "area_footnote";
  // public static final String PERCENT_WATER = "percent_water";
  // public static final String AREA_LABEL = "area_label";
  // public static final String AREA_LABEL2 = "area_label2";
  // public static final String AREA_DATA2 = "area_data2";
  // public static final String POPULATION_ESTIMATE = "population_estimate";
  // public static final String POPULATION_ESTIMATE_RANK = "population_estimate_rank";
  // public static final String POPULATION_ESTIMATE_YEAR = "population_estimate_year";
  // public static final String POPULATION_CENSUS = "population_census";
  // public static final String POPULATION_CENSUS_YEAR = "population_census_year";
  // public static final String POPULATION_DENSITY_KM2 = "population_density_km2";
  // public static final String POPULATION_DENSITY_SQ_MI = "population_density_sq_mi";
  // public static final String POPULATION_DENSITY_RANK = "population_density_rank";
  // public static final String NUMMEMBERS = "nummembers";
  // public static final String GDP_PPP = "GDP_PPP";
  // public static final String GDP_PPP_RANK = "GDP_PPP_rank";
  // public static final String GDP_PPP_YEAR = "GDP_PPP_year";
  // public static final String GDP_PPP_PER_CAPITA = "GDP_PPP_per_capita";
  // public static final String GDP_PPP_PER_CAPITA_RANK = "GDP_PPP_per_capita_rank";
  // public static final String GDP_NOMINAL = "GDP_nominal";
  // public static final String GDP_NOMINAL_RANK = "GDP_nominal_rank";
  // public static final String GDP_NOMINAL_YEAR = "GDP_nominal_year";
  // public static final String GDP_NOMINAL_PER_CAPITA = "GDP_nominal_per_capita";
  // public static final String GDP_NOMINAL_PER_CAPITA_RANK = "GDP_nominal_per_capita_rank";
  // public static final String GINI = "Gini";
  // public static final String GINI_REF = "Gini_ref";
  // public static final String GINI_RANK = "Gini_rank";
  // public static final String GINI_YEAR = "Gini_year";
  // public static final String HDI_YEAR = "HDI_year";
  // public static final String HDI = "HDI";
  // public static final String HDI_CHANGE = "HDI_change";
  // public static final String HDI_RANK = "HDI_rank";
  // public static final String HDI_REF = "HDI_ref";
  // public static final String CURRENCY = "currency";
  // public static final String CURRENCY_CODE = "currency_cod";
  // public static final String TIME_ZONE = "time_zone";
  // public static final String UTC_OFFSET = "utc_offset";
  // public static final String TIME_ZONE_DST = "time_zone_DS";
  // public static final String UTC_OFFSET_DST = "utc_offset_DST";
  // public static final String DST_NOTE = "DST_note";
  // public static final String ANTIPODES = "antipodes";
  // public static final String DATE_FORMAT = "date_format";
  // public static final String DRIVES_ON = "drives_on";
  // public static final String CCTLD = "cctld";
  // public static final String ISO3166CODE = "iso3166code";
  // public static final String CALLING_CODE = "calling_code";
  // public static final String IMAGE_MAP3 = "image_map3";
  // public static final String ALT_MAP3 = "alt_map3";
  // public static final String FOOTNOTE_A = "footnote_a";
  // public static final String FOOTNOTE_B = "footnote_b";
  // public static final String FOOTNOTE_C = "footnote_c";
  // public static final String FOOTNOTE_D = "footnote_d";
  // public static final String FOOTNOTE_E = "footnote_e";
  // public static final String FOOTNOTE_F = "footnote_f";
  // public static final String FOOTNOTE_G = "footnote_g";
  // public static final String FOOTNOTE_H = "footnote_h";
  // public static final String FOOTNOTES = "footnotes";

  public WikipediaCountryInfoboxParser()
  {
  }

  public static Country parse(String text)
  {
    Map<String, String> map = InfoboxMap.asMap(text);

    Country country = new Country();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case CONVENTIONAL_LONG_NAME:
          country.setConventionalLongName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case NATIVE_NAME:
          country.setNativeName(InfoboxNameParser.infoboxNativeName(map.get(key)));
          break;
        case COMMON_NAME:
          country.setCommonName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case LATD:
          coordinate.setLatD(map.get(key));
          country.setCoordinate(coordinate);
          break;
        case LATM:
          coordinate.setLatM(map.get(key));
          country.setCoordinate(coordinate);
          break;
        case LATS:
          coordinate.setLatS(map.get(key));
          country.setCoordinate(coordinate);
          break;
        case LATNS:
          coordinate.setLatNS(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          country.setCoordinate(coordinate);
          break;
        case LONGD:
          coordinate.setLongD(map.get(key));
          country.setCoordinate(coordinate);
          break;
        case LONGM:
          coordinate.setLongM(map.get(key));
          country.setCoordinate(coordinate);
          break;
        case LONGS:
          coordinate.setLongS(map.get(key));
          country.setCoordinate(coordinate);
          break;
        case LONGEW:
          coordinate.setLongEW(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          country.setCoordinate(coordinate);
          break;
      }
    }

    return country;
  }

 
}
