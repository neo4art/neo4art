package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

public class WikipediaReligiousBuildingShintoShrineInfobox {
	
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

		ReligiousBuilding shintoShrine = new ReligiousBuilding();
		Coordinate coordinate = new Coordinate();

		Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

		for (String key : map.keySet()) {
			switch (key) {
			case NAME:
				shintoShrine.setBuildingName(infoboxName(map.get(key)));
				break;
			case STYLE:
				shintoShrine.setType(map.get(key));
				break;
			case IMAGE:
				shintoShrine.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
				break;
			case LATD:
				coordinate.setLatD(Double.parseDouble(map.get(key)));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LATM:
				coordinate.setLatM(Double.parseDouble(map.get(key)));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LATS:
				coordinate.setLatS(Double.parseDouble(map.get(key)));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LATNS:
				coordinate.setLatNS(map.get(key));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LONGD:
				coordinate.setLongD(Double.parseDouble(map.get(key)));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LONGM:
				coordinate.setLongM(Double.parseDouble(map.get(key)));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LONGS:
				coordinate.setLongS(Double.parseDouble(map.get(key)));
				shintoShrine.setCoordinates(coordinate);
				break;
			case LONGEW:
				coordinate.setLongEW(map.get(key));
				shintoShrine.setCoordinates(coordinate);
				break;
			}
		}
		return shintoShrine;
	}
	
	public static String infoboxName(String name){
		
		String[] n = StringUtils.split(name, "<");
		name = n[0];
		name = name.replace("'", "");
		
		return name;
	}
}
