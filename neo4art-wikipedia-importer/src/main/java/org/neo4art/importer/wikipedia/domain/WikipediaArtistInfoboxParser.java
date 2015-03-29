package org.neo4art.importer.wikipedia.domain;

import org.apache.commons.lang3.StringUtils;

public class WikipediaArtistInfoboxParser implements WikipediaInfoboxParser {

	public static final String NAME = "name";
	public static final String IMAGE = "image";
	public static final String CAPTION = "caption";
	public static final String ALT = "alt";
	public static final String BIRTH_DATE = "birth_date";
	public static final String BIRTH_PLACE = "birth_place";
	public static final String DEATH_DATE = "death_date";
	public static final String DEATH_PLACE = "death_place";
	public static final String FIELD = "field";
	public static final String TRAINING = "training";
	public static final String MOVEMENT = "movement";
	public static final String WORKS = "works";
	public static final String PATRON = "patron";

	public WikipediaArtistInfoboxParser() {
	}
	
	@Override
	public WikipediaInfobox parseInfobox(String infobox) {
		
		WikipediaArtistInfobox artistInfobox = new WikipediaArtistInfobox();
		
		String[] infos = StringUtils.split(infobox, "\n");
		
		for (int i = 0; i < infos.length; i++) {
			if (infos[i].startsWith("| ") && infos[i].contains("=")) {
				int separatorIndex = infos[i].indexOf("=");
				
				String key = "";
				String value = "";
				
				if (separatorIndex != -1) {
					key = infos[i].substring(2, separatorIndex).trim();
					value = infos[i].substring(separatorIndex+ 1 ).trim();
				}
				
				switch (key) {
				    case NAME:
				    	artistInfobox.setName(value);
				    case IMAGE:
				    	artistInfobox.setImage(value);
				    case CAPTION:
				    	artistInfobox.setCaption(value);
				    case ALT:
				    	artistInfobox.setAlt(value);
				    case BIRTH_DATE:
				    	artistInfobox.setBirthDate(value);
				    case BIRTH_PLACE:
				    	artistInfobox.setBirthPlace(value);
				    case DEATH_DATE:
				    	artistInfobox.setDeathDate(value);
				    case DEATH_PLACE:
				    	artistInfobox.setDeathPlace(value);
				    case FIELD:
				    	artistInfobox.setField(value);
				    case TRAINING:
				    	artistInfobox.setTraining(value);
				    case MOVEMENT:
				    	artistInfobox.setMovement(value);
				    case WORKS:
				    	artistInfobox.setWorks(value);
				    case PATRON:
				    	artistInfobox.setPatron(value);
				}
			}
		}
		
		return artistInfobox;
	}
}
