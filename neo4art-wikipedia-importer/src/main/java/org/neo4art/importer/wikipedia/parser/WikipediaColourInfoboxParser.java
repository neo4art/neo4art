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

import java.awt.Color;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Colour;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_color">Template :Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 11 Oct 2015
 */
public class WikipediaColourInfoboxParser {

  private static Log         logger = LogFactory.getLog(WikipediaColourInfoboxParser.class);

  public static final String TITLE  = "title";
  public static final String HEX    = "hex";
  public static final String R      = "r";
  public static final String G      = "g";
  public static final String B      = "b";

  public WikipediaColourInfoboxParser() {
  }

  public static Colour parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    Colour colour = new Colour();

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case TITLE:
            colour.setName(map.get(key));
            break;
          case HEX:
            colour.setRgb(new Color(Integer.valueOf( map.get(key).substring(0, 2), 16),
                                    Integer.valueOf( map.get(key).substring(2, 4), 16),
                                    Integer.valueOf( map.get(key).substring(4, 6), 16)));
            break;
          case R:
            colour.setRed(Integer.parseInt(map.get(key)));
            break;
          case G:
            colour.setGreen(Integer.parseInt(map.get(key)));
            break;
          case B:
            colour.setBlue(Integer.parseInt(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.error("     Error parsing infobox pair (" + key + " , " + map.get(key) + ") - " + e.getMessage() + "| " + e.getLocalizedMessage() + "| " + e.getCause().getMessage() + "| " + e.getCause().getLocalizedMessage());
      }
    }

    return colour;
  }
}
