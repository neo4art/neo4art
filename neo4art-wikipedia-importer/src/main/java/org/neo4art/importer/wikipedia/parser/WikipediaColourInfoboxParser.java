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

import org.neo4art.domain.Colour;

import toberefactored.parser.util.InfoboxMap;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_color">Template :Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 11 Oct 2015
 */
public class WikipediaColourInfoboxParser {

  public static final String TITLE = "title";

  public WikipediaColourInfoboxParser() {
  }

  public static Colour parse(String text) {
    Map<String, String> map = InfoboxMap.asMap(text);

    Colour colour = new Colour();

    for (String key : map.keySet()) {
      switch (key) {
        case TITLE:
          colour.setName(map.get(key));
          break;
      }
    }

    return colour;
  }
}
