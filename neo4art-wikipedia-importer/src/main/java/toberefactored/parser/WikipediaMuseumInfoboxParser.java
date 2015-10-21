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

package toberefactored.parser;

import java.util.Map;

import org.neo4art.domain.Museum;

import toberefactored.parser.util.InfoboxMap;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_museum">Template :Infobox_museum</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 20 Mar 2015
 */
public class WikipediaMuseumInfoboxParser {
  public static final String NAME = "name";

  public static Museum parse(String text) {
    Map<String, String> map = InfoboxMap.asMap(text);

    Museum museum = new Museum();

    for (String key : map.keySet()) {
      switch (key) {
        case NAME:
          museum.setName(map.get(key));
          break;
      }
    }

    return museum;
  }
}
