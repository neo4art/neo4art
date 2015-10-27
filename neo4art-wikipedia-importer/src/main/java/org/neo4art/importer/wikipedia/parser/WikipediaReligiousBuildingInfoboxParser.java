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
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.util.WikipediaCoordinatesInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

import toberefactored.parser.util.InfoboxMap;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_religious_building">Template:Infobox_religious_building</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaReligiousBuildingInfoboxParser {

  private static Log         logger        = LogFactory.getLog(WikipediaReligiousBuildingInfoboxParser.class);

  public static final String BUILDING_NAME = "building_name";
  public static final String NATIVE_NAME   = "native_name";
  public static final String IMAGE         = "image";
  public static final String WEBSITE       = "website";

  public WikipediaReligiousBuildingInfoboxParser() {
  }

  public static ReligiousBuilding parse(String text) {

    Map<String, String> map = InfoboxMap.asMap(text);

    ReligiousBuilding religiousBuilding = new ReligiousBuilding();

    religiousBuilding.setCoordinates(WikipediaCoordinatesInfoboxParserUtils.buildCoordinates(map));
    
    for (String key : map.keySet()) {

      try {

        switch (key) {
          case BUILDING_NAME:
            religiousBuilding.setName(map.get(key));
            break;
          case NATIVE_NAME:
            religiousBuilding.setNativeName(map.get(key));
            break;
          case IMAGE:
            religiousBuilding.setImage(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
          case WEBSITE:
            religiousBuilding.setWebsite(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.warn("Error parsing infobox pair [ " + key + " | " + map.get(key) + " ]");
      }
    }

    return religiousBuilding;
  }
}
