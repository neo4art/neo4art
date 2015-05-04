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
import org.neo4art.domain.Artist;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Country;
import org.neo4art.importer.wikipedia.parser.util.InfoboxAltParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxArtWorksParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxCaptionParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxCoordinateParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxDateParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxMap;
import org.neo4art.importer.wikipedia.parser.util.InfoboxMovementParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxNameParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxParserUtil;
import org.neo4art.importer.wikipedia.parser.util.InfoboxPatronParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxPlaceParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxTrainerParser;
import org.neo4art.importer.wikipedia.parser.util.InfoboxUrlParser;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artist">Template :Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtistInfoboxParser
{
  
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
  public static final String NOTABLE_WORKS             = "works";
  public static final String STYLE                     = "style";
  public static final String MOVEMENT                  = "movement";
  public static final String SPOUSE                    = "spouse";
  public static final String AWARDS                    = "awards";
  public static final String ELECTED                   = "elected";
  public static final String PATRONS                   = "patron";
  public static final String MEMORIALS                 = "memorials";
  public static final String WEBSITE                   = "website";
  public static final String FIELD                     = "field";
  public static final String TRAINING                  = "training";

  public WikipediaArtistInfoboxParser()
  {
  }

  public static Artist parse(String text)
  {
    Map<String, String> map = InfoboxMap.asMap(text);

    Artist artist = new Artist();
    ArtMovement artMovement = null;
    Country country = new Country();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case HONORIFIC_PREFIX:
          artist.setHonorificPrefix(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case NAME:
          artist.setName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case HONORIFIC_SUFFIX:
          artist.setHonorificSuffix(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case FIELD:
          artist.setField(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case TRAINING:
          artist.setTrainer(InfoboxTrainerParser.infoboxTraining(map.get(key)));
          break;
        case IMAGE:
          artist.setImage(InfoboxUrlParser.infoboxUrl(map.get(key)));
          break;
        case IMAGE_SIZE:
          artist.setImageSize(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case ALT:
          artist.setAlt(InfoboxAltParser.infoboxAlt(map.get(key)));
          break;
        case CAPTION:
          artist.setCaption(InfoboxCaptionParser.infoboxCaption(map.get(key)));
          break;
        case NATIVE_NAME:
          artist.setNativeName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case NATIVE_NAME_LANG:
          artist.setNativeNameLang(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case BIRTH_NAME:
          artist.setBirthName(InfoboxNameParser.infoboxBirthName(map.get(key)));
          break;
        case BIRTH_DATE:
          artist.setBirthDate(InfoboxDateParser.parseInfoboxDateBirth(map.get(key)));
          break;
        case BIRTH_PLACE:
          artist.setBirthPlace(InfoboxPlaceParser.infoboxPlaceBirth(map.get(key)));
          break;
        case DEATH_DATE:
          artist.setDeathDate(InfoboxDateParser.parseInfoboxDateDeath(map.get(key)));
          break;
        case DEATH_PLACE:
          artist.setDeathPlace(InfoboxPlaceParser.infoboxPlaceDeath(map.get(key)));
          break;
        case RESTING_PLACE:
          coordinate.setMap(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          artist.setRestingPlace(coordinate);
          break;
        case RESTING_PLACE_COORDINATES:
            artist.setRestingPlaceCoordinates(InfoboxCoordinateParser.infoboxRestingPlaceCoordinates(coordinate, map.get(key)));
          break;
        case NATIONALITY:
          country.setCommonName(map.get(key));
          artist.setNationality(country);
          break;
        case EDUCATION:
          artist.setEducation(map.get(key));
          break;
        case ALMA_MATER:
          artist.setAlmaMater(map.get(key));
          break;
        case KNOWN_FOR:
          artist.setKnownFor(map.get(key));
          break;
        case NOTABLE_WORKS:
          artist.setNotableWorks(InfoboxArtWorksParser.infoboxWorks(map.get(key)));
          break;
        case STYLE:
          artist.setStyle(map.get(key));
          break;
        case MOVEMENT:
          artist.setMovement(InfoboxMovementParser.infoboxMovement(artMovement, map.get(key)));
          break;
        case SPOUSE:
          artist.setSpouse(map.get(key));
          break;
        case AWARDS:
          artist.setAwards(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case ELECTED:
          artist.setElected(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case PATRONS:
          artist.setPatrons(InfoboxPatronParser.infoboxPatron(map.get(key)));
          break;
        case MEMORIALS:
          artist.setMemorials(map.get(key));
          break;
        case WEBSITE:
          artist.setWebsite(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
      }
    }

    return artist;
  }
}
