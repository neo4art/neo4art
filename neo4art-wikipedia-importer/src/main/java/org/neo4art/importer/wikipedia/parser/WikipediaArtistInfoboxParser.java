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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.ArtMovement;
import org.neo4art.domain.Artist;
import org.neo4art.importer.wikipedia.parser.util.WikipediaDateTimeInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artist">Template:Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtistInfoboxParser {

  private static Log         logger      = LogFactory.getLog(WikipediaArtistInfoboxParser.class);

  public static final String NAME        = "name";
  public static final String IMAGE       = "image";

  public static final String BIRTH_DATE  = "birth_date";
  public static final String BIRTH_PLACE = "birth_place";
  public static final String DEATH_DATE  = "death_date";
  public static final String DEATH_PLACE = "death_place";

  public static final String NATIONALITY = "nationality";
  
  public static final String MOVEMENT    = "movement";

  public WikipediaArtistInfoboxParser() {
  }

  public static Artist parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    Artist artist = new Artist();

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case NAME:
            artist.setName(map.get(key));
            break;
          case IMAGE:
            artist.setImage(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
          case BIRTH_DATE:
            artist.setBirthDate(WikipediaDateTimeInfoboxParserUtils.parseAsDate(map.get(key)));
            break;
          case BIRTH_PLACE:
            artist.setBirthPlace(WikipediaInfoboxParserUtils.parseAsSettlement(map.get(key)));
            break;
          case DEATH_DATE:
            artist.setDeathDate(WikipediaDateTimeInfoboxParserUtils.parseAsDate(map.get(key)));
            break;
          case DEATH_PLACE:
            artist.setDeathPlace(WikipediaInfoboxParserUtils.parseAsSettlement(map.get(key)));
            break;
          case NATIONALITY:
            artist.setNationality(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case MOVEMENT:
            artist.setArtMovements(toArtMovements(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.error("     Error parsing infobox pair (" + key + " , " + map.get(key) + ") - " + e.getMessage() + " | " + e.getLocalizedMessage() + " | " + e.getCause().getMessage() + " | " + e.getCause().getLocalizedMessage());
      }
    }

    return artist;
  }

  /**
   * It turns a String into a list of {@link ArtMovement}
   * 
   * @param input
   * @return
   */
  private static List<ArtMovement> toArtMovements(String input) {
    
    List<ArtMovement> artMovements = null;
    
    List<String> artMovementNames = WikipediaInfoboxParserUtils.asListOfStrings(input);
    
    if (artMovementNames != null) {

      artMovements = new ArrayList<ArtMovement>();
      
      for (String artMovementName : artMovementNames) {
        
        artMovements.add(new ArtMovement(artMovementName));
      }
    }
    
    return artMovements;
  }
  
  
}
