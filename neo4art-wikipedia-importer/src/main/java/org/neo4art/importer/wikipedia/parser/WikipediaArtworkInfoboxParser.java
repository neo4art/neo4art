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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Museum;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.util.WikipediaCoordinatesInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaDateTimeInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artwork">Template :Infobox_artwork</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtworkInfoboxParser {

  private static Log         logger          = LogFactory.getLog(WikipediaArtworkInfoboxParser.class);

  public static final String TITLE           = "title";

  public static final String IMAGE           = "image";
  public static final String IMAGE_FILE      = "image_file";
  public static final String YEAR            = "year";
  public static final String COMPLETION_DATE = "completion_date";

  public static final String TYPE            = "type";
  public static final String SUBJECT         = "subject";
  public static final String CONDITION       = "condition";
  public static final String CATALOGUE       = "catalogue";

  public static final String ARTIST          = "artist";
  public static final String CITY            = "city";
  public static final String MUSEUM          = "museum";

  public WikipediaArtworkInfoboxParser() {
  }

  public static Artwork parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    Artwork artwork = new Artwork();

    artwork.setCoordinates(WikipediaCoordinatesInfoboxParserUtils.buildCoordinates(map));

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case TITLE:
            artwork.setTitle(map.get(key));
            break;
          case IMAGE:
          case IMAGE_FILE:
            artwork.setUrl(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
          case YEAR:
            artwork.setYear(WikipediaDateTimeInfoboxParserUtils.parseAsDate(map.get(key)));
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
          case ARTIST:
            artwork.setArtist(toArtist(map.get(key)));
            break;
          case CITY:
            artwork.setCity(toCity(map.get(key)));
            break;
          case MUSEUM:
            artwork.setMuseum(toMuseum(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.warn("Error parsing infobox pair [ " + key + " | " + map.get(key) + " ]");
      }
    }

    return artwork;
  }

  /**
   * It turns a String into a {@link Museum}
   * 
   * @param input
   * @return
   */
  private static Museum toMuseum(String input) {

    if (StringUtils.isNotBlank(input)) {

      return new Museum(WikipediaInfoboxParserUtils.getTextFromLink(input));
    }

    return null;
  }

  /**
   * It turns a String into a {@link Settlement}
   * 
   * @param input
   * @return
   */
  private static Settlement toCity(String input) {

    if (StringUtils.isNotBlank(input)) {

      return new Settlement(WikipediaInfoboxParserUtils.getTextFromLink(input));
    }

    return null;
  }

  /**
   * It turns a String into an {@link Artist}
   * 
   * @param input
   * @return
   */
  private static Artist toArtist(String input) {

    if (StringUtils.isNotBlank(input)) {

      return new Artist(WikipediaInfoboxParserUtils.getTextFromLink(input));
    }

    return null;
  }
}
