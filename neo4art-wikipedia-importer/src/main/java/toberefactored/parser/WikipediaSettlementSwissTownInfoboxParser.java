package toberefactored.parser;

import java.util.Map;

import org.neo4art.domain.Coordinates;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

import toberefactored.parser.util.InfoboxMap;
import toberefactored.parser.util.InfoboxParserUtil;
import toberefactored.parser.util.InfoboxTypeParserUtil;

public class WikipediaSettlementSwissTownInfoboxParser {

  public static final String NAME             = "name";
  public static final String SUBJECT_NAME     = "subject_name";
  public static final String OFFICIAL_NAME    = "official_name";
  public static final String NATIVE_NAME      = "native_name";
  public static final String NATIVE_NAME_LANG = "native_name_lang";
  public static final String OTHER_NAME       = "other_name";
  public static final String SETTLEMENT_TYPE  = "settlement_type";
  public static final String LATD             = "latd";
  public static final String LATM             = "latm";
  public static final String LATS             = "lats";
  public static final String LATNS            = "latNS";
  public static final String LONGD            = "longd";
  public static final String LONGM            = "longm";
  public static final String LONGS            = "longs";
  public static final String LAT_D            = "lat_d";
  public static final String LAT_M            = "lat_m";
  public static final String LAT_S            = "lat_s";
  public static final String LAT_NS           = "lat_NS";
  public static final String LONG_D           = "long_d";
  public static final String LONG_M           = "long_m";
  public static final String LONG_S           = "long_s";
  public static final String LONG_EW          = "long_EW";
  public static final String LONGEW           = "longEW";
  public static final String LATDEG           = "lat_deg";
  public static final String LATMIN           = "lat_min";
  public static final String LATSEC           = "lat_sec";
  public static final String LONGDEG          = "lon_deg";
  public static final String LONGMIN          = "lon_min";
  public static final String LONGSEC          = "lon_sec";
  public static final String LATITUDE         = "latitude";
  public static final String LONGITUDE        = "longitude";
  public static final String WEBSITE          = "website";
  public static final String WEB              = "web";
  public static final String STYLE            = "infobox";

  public WikipediaSettlementSwissTownInfoboxParser() {
  }

  public static Settlement parse(String text) {

    Map<String, String> map = InfoboxMap.asMap(text);

    Settlement settlement = new Settlement();
    Coordinates coordinates = new Coordinates();

    for (String key : map.keySet()) {

      try {
        switch (key) {

          case NAME:
            settlement.setName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
            break;
          case SUBJECT_NAME:
            settlement.setName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
            break;
          case STYLE:
            settlement.setType(InfoboxTypeParserUtil.getType(map.get(key)));
            break;
          case OFFICIAL_NAME:
            settlement.setOfficialName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
            break;
          case NATIVE_NAME:
            settlement.setNativeName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
            break;
          case OTHER_NAME:
            settlement.setOtherName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
            break;
          case LATITUDE:
            coordinates.setLatD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);

            break;
          case LONGITUDE:
            coordinates.setLongD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LATD:
            coordinates.setLatD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);

            break;
          case LATM:
            coordinates.setLatM(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LATS:

            coordinates.setLatS(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LATNS:
            coordinates.setLatNS(map.get(key));
            settlement.setCoordinates(coordinates);
            break;
          case LAT_D:
            coordinates.setLatD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LAT_M:
            coordinates.setLatM(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LAT_S:

            coordinates.setLatS(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LAT_NS:
            coordinates.setLatNS(map.get(key));
            settlement.setCoordinates(coordinates);
            break;
          case LATDEG:
            coordinates.setLatD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);

            break;
          case LATMIN:
            coordinates.setLatM(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LATSEC:

            coordinates.setLatS(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;

          case LONGD:
            coordinates.setLongD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONGM:
            coordinates.setLongM(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONGS:
            coordinates.setLongS(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONGEW:
            coordinates.setLongEW(map.get(key));
            settlement.setCoordinates(coordinates);
            break;
          case LONG_D:
            coordinates.setLongD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONG_M:
            coordinates.setLongM(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONG_S:
            coordinates.setLongS(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONG_EW:
            coordinates.setLongEW(map.get(key));
            settlement.setCoordinates(coordinates);
            break;
          case LONGDEG:
            coordinates.setLongD(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONGMIN:
            coordinates.setLongM(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;
          case LONGSEC:
            coordinates.setLongS(Double.parseDouble(map.get(key)));
            settlement.setCoordinates(coordinates);
            break;

          case WEB:
            settlement.setWebsite(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
          case WEBSITE:
            settlement.setWebsite(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
        }
      }
      catch (Exception e) {
      }
    }

    return settlement;
  }
}
