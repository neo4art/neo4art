package org.neo4art.importer.wikipedia.parser.settlement;

import java.util.Map;

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

public class WikipediaSettlementBangladeshDistrictInfoboxParser {
	public static final String NAME = "name";
	public static final String OFFICIAL_NAME = "official_name";
	public static final String NATIVE_NAME = "native_name";
	public static final String NATIVE_NAME_LANG = "native_name_lang";
	public static final String OTHER_NAME = "other_name";
	public static final String SETTLEMENT_TYPE = "settlement_type";
	public static final String LATD = "latd";
	public static final String LATM = "latm";
	public static final String LATS = "lats";
	public static final String LATNS = "latNS";
	public static final String LONGD = "longd";
	public static final String LONGM = "longm";
	public static final String LONGS = "longs";
	public static final String LONGEW = "longEW";
	public static final String LATDEG = "lat_deg";
	public static final String LATMIN = "lat_min";
	public static final String LATSEC = "lat_sec";
	public static final String LONGDEG = "lon_deg";
	public static final String LONGMIN = "lon_min";
	public static final String LONGSEC = "lon_sec";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String WEBSITE = "website";
	public static final String WEB = "web";
	public static final String STYLE = "infobox";
	
	public WikipediaSettlementBangladeshDistrictInfoboxParser() {
	}

	public static Settlement parse(String text) {

		Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

		Settlement settlement = new Settlement();
		Coordinate coordinate = new Coordinate();

		for (String key : map.keySet()) {

			switch (key) {

			case NAME:
				settlement
						.setName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
				break;
			case STYLE:
				settlement
						.setType(WikipediaInfoboxUtils.getType(map.get(key)));
				break;
			case OFFICIAL_NAME:
				settlement.setOfficialName(WikipediaInfoboxUtils.removeAllParenthesis(map
						.get(key)));
				break;
			case NATIVE_NAME:
				settlement.setNativeName(WikipediaInfoboxUtils.removeAllParenthesis(map
						.get(key)));
				break;
			case NATIVE_NAME_LANG:
				settlement.setNativeNameLang(WikipediaInfoboxUtils
						.removeAllParenthesis(map.get(key)));
				break;
			case OTHER_NAME:
				settlement.setOtherName(WikipediaInfoboxUtils.removeAllParenthesis(map
						.get(key)));
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
