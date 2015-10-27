/**
 * Copyright 2015 the eoriginal author or authors.
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Museum;
import org.neo4art.importer.wikipedia.parser.util.WikipediaCoordinatesInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_museum">Template :Infobox_museum</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 20 Mar 2015
 */
public class WikipediaMuseumInfoboxParser {

  private static Log         logger   = LogFactory.getLog(WikipediaMuseumInfoboxParser.class);

  public static final String NAME     = "name";
  public static final String IMAGE    = "image";
  public static final String LOCATION = "location";
  public static final String WEBSITE  = "website";

  public static Museum parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    Museum museum = new Museum();

    museum.setCoordinates(WikipediaCoordinatesInfoboxParserUtils.buildCoordinates(map));

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case NAME:
            museum.setName(map.get(key));
            break;
          case IMAGE:
            museum.setImage(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
          case LOCATION:
            museum.setLocation(map.get(key));
            break;
          case WEBSITE:
            museum.setWebsite(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.error("     Error parsing infobox pair (" + key + " , " + map.get(key) + ") : " + e.getMessage());
      }
    }

    return museum;
  }
}
