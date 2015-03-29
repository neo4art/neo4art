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

import org.neo4art.domain.Artist;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artist">Template:Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 19 Mar 2015
 */
public class WikipediaArtistInfoboxParser {

  public static final String HONORIFIC_PREFIX          = "honorific_prefix";
  public static final String NAME                      = "name";
  public static final String HONORIFIC_SUFFIX          = "honorific_suffix";
  public static final String IMAGE                     = "image";
  public static final String IMAGE_SIZE                = "image_size";
  public static final String ALT                       = "alt";
  public static final String CAPTION                   = "caption";
  public static final String NATIVE_NAME               = "native_name";
  public static final String NATIVE_NAME_LANG          = "native_name_lang";
  public static final String BIRTH_NAME                = "birth_name";
  public static final String BIRTH_DATE                = "birth_date";
  public static final String BIRTH_PLACE               = "birth_place";
  public static final String DEATH_DATE                = "death_date";
  public static final String DEATH_PLACE               = "death_place";
  public static final String RESTING_PLACE             = "resting_place";
  public static final String RESTING_PLACE_COORDINATES = "resting_place_coordinates";
  public static final String NATIONALITY               = "nationality";
  public static final String EDUCATION                 = "education";
  public static final String ALMA_MATER                = "alma_mater";
  public static final String KNOWN_FOR                 = "known_for";
  public static final String NOTABLE_WORKS             = "notable_works";
  public static final String STYLE                     = "style";
  public static final String MOVEMENT                  = "movement";
  public static final String SPOUSE                    = "spouse";
  public static final String AWARDS                    = "awards";
  public static final String ELECTED                   = "elected";
  public static final String PATRONS                   = "patrons";
  public static final String MEMORIALS                 = "memorials";
  public static final String WEBSITE                   = "website";

  public WikipediaArtistInfoboxParser() {
  }

  public static Artist parse(String text) {

    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    Artist artist = new Artist();

    for (String key : map.keySet()) {
      
      switch (key) {
        case HONORIFIC_PREFIX          :
          artist.setHonorificPrefix(map.get(key));
          break;
        case NAME                      :
          artist.setName(map.get(key));
          break;
        case HONORIFIC_SUFFIX          :
          artist.setHonorificSuffix(map.get(key));
          break;
        case IMAGE                     :
          artist.setImage(map.get(key));
          break;
        case IMAGE_SIZE                :
          artist.setImageSize(map.get(key));
          break;
        case ALT                       :
          artist.setAlt(map.get(key));
          break;
        case CAPTION                   :
          artist.setCaption(map.get(key));
          break;
        case NATIVE_NAME               :
          artist.setNativeName(map.get(key));
          break;
        case NATIVE_NAME_LANG          :
          artist.setNativeNameLang(map.get(key));
          break;
        case BIRTH_NAME                :
          artist.setBirthName(map.get(key));
          break;
        case BIRTH_DATE                :
          artist.setBirthDate(map.get(key));
          break;
        case BIRTH_PLACE               :
          artist.setBirthPlace(map.get(key));
          break;
        case DEATH_DATE                :
          artist.setDeathDate(map.get(key));
          break;
        case DEATH_PLACE               :
          artist.setDeathPlace(map.get(key));
          break;
        case RESTING_PLACE             :
          artist.setRestingPlace(map.get(key));
          break;
        case RESTING_PLACE_COORDINATES :
          artist.setRestingPlaceCoordinates(map.get(key));
          break;
        case NATIONALITY               :
          artist.setNationality(map.get(key));
          break;
        case EDUCATION                 :
          artist.setEducation(map.get(key));
          break;
        case ALMA_MATER                :
          artist.setAlmaMater(map.get(key));
          break;
        case KNOWN_FOR                 :
          artist.setKnownFor(map.get(key));
          break;
        case NOTABLE_WORKS             :
          artist.setNotableWorks(map.get(key));
          break;
        case STYLE                     :
          artist.setStyle(map.get(key));
          break;
        case MOVEMENT                  :
          artist.setMovement(map.get(key));
          break;
        case SPOUSE                    :
          artist.setSpouse(map.get(key));
          break;
        case AWARDS                    :
          artist.setAwards(map.get(key));
          break;
        case ELECTED                   :
          artist.setElected(map.get(key));
          break;
        case PATRONS                   :
          artist.setPatrons(map.get(key));
          break;
        case MEMORIALS                 :
          artist.setMemorials(map.get(key));
          break;
        case WEBSITE                   :
          artist.setWebsite(map.get(key));
          break;
      }
    }

    return artist;
  }
}
