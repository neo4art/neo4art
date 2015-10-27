/**
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.parser.util.WikipediaCoordinatesInfoboxParserUtils;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_settlement"> Template:Infobox_settlement</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 19 Mar 2015
 */
public class WikipediaSettlementInfoboxParser {

  private static Log         logger           = LogFactory.getLog(WikipediaSettlementInfoboxParser.class);

  public static final String NAME             = "name";
  public static final String OFFICIAL_NAME    = "official_name";
  public static final String NATIVE_NAME      = "native_name";
  public static final String OTHER_NAME       = "other_name";
  
  public static final String WEBSITE          = "website";

  public WikipediaSettlementInfoboxParser() {
  }

  public static Settlement parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    Settlement settlement = new Settlement();

    settlement.setCoordinates(WikipediaCoordinatesInfoboxParserUtils.buildCoordinates(map));

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case NAME:
            settlement.setName(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case OFFICIAL_NAME:
            settlement.setOfficialName(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case NATIVE_NAME:
            settlement.setNativeName(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case OTHER_NAME:
            settlement.setOtherName(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case WEBSITE:
            settlement.setWebsite(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.error("# Error parsing infobox pair (" + key + " , " + map.get(key) + ") : " + e.getMessage());
      }
    }

    return settlement;
  }
}
