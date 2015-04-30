package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.net.MalformedURLException;
import java.util.Map;

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

public class WikipediaReligiousBuildingLDSTempleInfobox {
	
	public static final String NAME = "name";
	public static final String LATD = "latd";
	public static final String LATM = "latm";
	public static final String LATS = "lats";
	public static final String LATNS = "latNS";
	public static final String LONGD = "longd";
	public static final String LONGM = "longm";
	public static final String LONGS = "longs";
	public static final String LONGEW = "longEW";
	public static final String IMAGE = "image";
	public static final String STYLE = "infobox";

	public static ReligiousBuilding parse(String text)
			throws MalformedURLException {

		ReligiousBuilding lDSTemple = new ReligiousBuilding();
		Coordinate coordinate = new Coordinate();

		Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

		for (String key : map.keySet()) {
			switch (key) {
			case NAME:
				lDSTemple.setBuildingName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
				break;
			 case STYLE:
					lDSTemple.setType(map.get(key));
					break;
			case IMAGE:
				lDSTemple.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
				break;
			case LATD:
				coordinate.setLatD(Double.parseDouble(map.get(key)));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LATM:
				coordinate.setLatM(Double.parseDouble(map.get(key)));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LATS:
				coordinate.setLatS(Double.parseDouble(map.get(key)));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LATNS:
				coordinate.setLatNS(map.get(key));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LONGD:
				coordinate.setLongD(Double.parseDouble(map.get(key)));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LONGM:
				coordinate.setLongM(Double.parseDouble(map.get(key)));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LONGS:
				coordinate.setLongS(Double.parseDouble(map.get(key)));
				lDSTemple.setCoordinates(coordinate);
				break;
			case LONGEW:
				coordinate.setLongEW(map.get(key));
				lDSTemple.setCoordinates(coordinate);
				break;
			}
		}
		return lDSTemple;
	}
}
