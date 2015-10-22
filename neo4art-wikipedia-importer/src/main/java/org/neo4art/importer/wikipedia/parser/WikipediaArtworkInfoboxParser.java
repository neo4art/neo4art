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

import org.neo4art.domain.Artwork;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.parser.util.WikipediaCoordinatesInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaDateTimeInfoboxParserUtils;

import toberefactored.parser.WikipediaSettlementInfoboxParser;
import toberefactored.parser.util.InfoboxMap;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artwork">Template :Infobox_artwork</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtworkInfoboxParser {

  public static final String TITLE           = "title";

  public static final String YEAR            = "year";
  public static final String COMPLETION_DATE = "completion_date";

  public static final String TYPE            = "type";
  public static final String SUBJECT         = "subject";
  public static final String CONDITION       = "condition";
  public static final String CATALOGUE       = "catalogue";

  public static final String CITY            = "city";
  public static final String MUSEUM          = "museum";

  public WikipediaArtworkInfoboxParser() {
  }

  public static Artwork parse(String text) {
    
    Map<String, String> map = InfoboxMap.asMap(text);

    Artwork artwork = new Artwork();

    artwork.setCoordinates(WikipediaCoordinatesInfoboxParserUtils.buildCoordinates(map));
    
    for (String key : map.keySet()) {

      switch (key) {
        case TITLE:
          artwork.setTitle(map.get(key));
          break;
        case YEAR:
          artwork.setYear(map.get(key));
          break;
        case COMPLETION_DATE:
          artwork.setCompletionDate(WikipediaDateTimeInfoboxParserUtils.parseAsDate(map.get(key)));
          break;
        case TYPE:
          artwork.setType(map.get(key));
          break;
        case CATALOGUE:
          artwork.setCatalogue(map.get(key));
          break;
        case SUBJECT:
          artwork.setSubject(map.get(key));
          break;
        case CITY:
          artwork.setCity(WikipediaSettlementInfoboxParser.parse(map.get(key)));
          break;
        case MUSEUM:
          artwork.setMuseum(new Museum(map.get(key)));
          break;
      }
    }

    return artwork;
  }
}
