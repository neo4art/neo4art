package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.net.MalformedURLException;
import java.util.Map;

import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

public class WikipediaReligiousBuildingSpanishMissionsInTheAmericasInfobox {
	
	public static final String NAME = "name";
	public static final String LATD = "lat_degrees";
	public static final String LATM = "lat_minutes";
	public static final String LATS = "lat_seconds";
	public static final String LATNS = "lat_direction";
	public static final String LONGD = "long_degrees";
	public static final String LONGM = "long_minutes";
	public static final String LONGS = "long_seconds";
	public static final String LONGEW = "long_direction";
	public static final String IMAGE = "image";
	public static final String STYLE = "infobox";

	public static ReligiousBuilding parse(String text)
			throws MalformedURLException {

		ReligiousBuilding spanishMissionsInTheAmericans = new ReligiousBuilding();
		Coordinate coordinate = new Coordinate();

		Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

		for (String key : map.keySet()) {
			switch (key) {
			case NAME:
				spanishMissionsInTheAmericans.setBuildingName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
				break;
			case IMAGE:
				spanishMissionsInTheAmericans.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
				break;
			case STYLE:
				spanishMissionsInTheAmericans.setType(map.get(key));
				break;
			case LATD:
				coordinate.setLatD(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LATM:
				coordinate.setLatM(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LATS:
				coordinate.setLatS(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LATNS:
				coordinate.setLatNS(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LONGD:
				coordinate.setLongD(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LONGM:
				coordinate.setLongM(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LONGS:
				coordinate.setLongS(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			case LONGEW:
				coordinate.setLongEW(map.get(key));
				spanishMissionsInTheAmericans.setCoordinates(coordinate);
				break;
			}
		}
		return spanishMissionsInTheAmericans;
	}
}
