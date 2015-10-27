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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.ArtMovement;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_art_movement">Template:Infobox_art_movement</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtMovementInfoboxParser {

  private static Log         logger = LogFactory.getLog(WikipediaArtMovementInfoboxParser.class);

  public static final String NAME   = "name";
  public static final String IMAGE  = "image";

  public WikipediaArtMovementInfoboxParser() {
  }

  public static ArtMovement parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    ArtMovement artMovement = new ArtMovement();

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case NAME:
            artMovement.setName(map.get(key));
            break;
          case IMAGE:
            artMovement.setImage(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.error("     Error parsing infobox pair (" + key + " , " + map.get(key) + ") - " + e.getMessage() + "| " + e.getLocalizedMessage() + "| " + e.getCause().getMessage() + "| " + e.getCause().getLocalizedMessage());
      }
    }

    return artMovement;
  }
}
