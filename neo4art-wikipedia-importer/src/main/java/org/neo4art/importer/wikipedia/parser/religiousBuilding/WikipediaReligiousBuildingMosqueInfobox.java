package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

public class WikipediaReligiousBuildingMosqueInfobox {

	public static final String NAME = "name";
	public static final String LATD = "latd";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
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

		ReligiousBuilding mosque = new ReligiousBuilding();
		Coordinate coordinate = new Coordinate();

		Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

		for (String key : map.keySet()) {
			switch (key) {
			case NAME:
				mosque.setBuildingName(infoboxName(map.get(key)));
				break;
			case IMAGE:
				mosque.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
				break;
			case STYLE:
				mosque.setType(map.get(key));
				break;
			case LATITUDE:
				coordinate.setLatD(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LONGITUDE:
				coordinate.setLongD(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LATD:
				coordinate.setLatD(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LATM:
				coordinate.setLatM(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LATS:
				coordinate.setLatS(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LATNS:
				coordinate.setLatNS(map.get(key));
				mosque.setCoordinates(coordinate);
				break;
			case LONGD:
				coordinate.setLongD(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LONGM:
				coordinate.setLongM(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LONGS:
				coordinate.setLongS(Double.parseDouble(map.get(key)));
				mosque.setCoordinates(coordinate);
				break;
			case LONGEW:
				coordinate.setLongEW(map.get(key));
				mosque.setCoordinates(coordinate);
				break;
			}
		}
		return mosque;
	}
	
	public static String infoboxName(String name){
		
		String[] n = StringUtils.split(name, "<");
		name = n[0];
		name = name.replace("'", "");
		
		return name;
	}
}
