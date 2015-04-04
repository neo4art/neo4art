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

import org.neo4art.domain.ArtMovement;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artist">Template:Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 19 Mar 2015
 */
public class WikipediaArtMovementInfoboxParser {

  public static final String NAME         = "name";
  public static final String IMAGE        = "image";
  public static final String IMAGE_SIZE   = "image_size";
  public static final String ALT          = "alt";
  public static final String CAPTION      = "caption";
  public static final String YEARSACTIVE  = "yearsactive";
  public static final String COUNTRY      = "country";
  public static final String MAJORFIGURES = "majorfigures";
  public static final String INFLUENCES   = "influences";
  public static final String INFLUENCED   = "influenced";

  public WikipediaArtMovementInfoboxParser() {
  }

  public static ArtMovement parse(String text) {

    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    ArtMovement artMovement = new ArtMovement();

    for (String key : map.keySet()) {
      
      switch (key) {
        case NAME:
          artMovement.setName(map.get(key));
          break;
        case IMAGE:
          artMovement.setImage(map.get(key));
          break;
        case IMAGE_SIZE:
          artMovement.setImageSize(map.get(key));
          break;
        case ALT:
          artMovement.setAlt(map.get(key));
          break;
        case CAPTION:
          artMovement.setCaption(map.get(key));
          break;
        case YEARSACTIVE:
          artMovement.setYearsActive(map.get(key));
          break;
        case COUNTRY:
          artMovement.setCountry(map.get(key));
          break;
        case MAJORFIGURES:
          artMovement.setMajorFigures(map.get(key));
          break;
        case INFLUENCES:
          artMovement.setInfluences(map.get(key));
          break;
        case INFLUENCED:
          artMovement.setInfluenced(map.get(key));
          break;
      }
    }

    return artMovement;
  }
}
