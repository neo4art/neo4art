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
package toberefactored.parser;

import java.util.Map;

import org.neo4art.domain.Monument;

import toberefactored.parser.util.InfoboxMap;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_monument">Template :Infobox_monument</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaMonumentInfoboxParser {

  public static final String MONUMENT_NAME = "monument_name";

  public WikipediaMonumentInfoboxParser() {
  }

  public static Monument parse(String text) {
    Map<String, String> map = InfoboxMap.asMap(text);

    Monument monument = new Monument();

    for (String key : map.keySet()) {
      switch (key) {
        case MONUMENT_NAME:
          monument.setName(map.get(key));
          break;
      }
    }

    return monument;
  }

}
