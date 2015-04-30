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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.ArtMovement;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.Country;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artist">Template :Infobox_artist</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaArtistInfoboxParser
{
  private static Log logger = LogFactory.getLog(WikipediaArtistInfoboxParser.class);
  
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
    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    ArrayList<ArtMovement> artworks = new ArrayList<ArtMovement>();

    Artist artist = new Artist();
    ArtMovement artMovement = null;
    Country country = new Country();
    Coordinate coordinate = new Coordinate();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case HONORIFIC_PREFIX:
          artist.setHonorificPrefix(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case NAME:
          artist.setName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case HONORIFIC_SUFFIX:
          artist.setHonorificSuffix(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case FIELD:
          artist.setField(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case TRAINING:
          artist.setTrainer(infoboxTraining(map.get(key)));
          break;
        case IMAGE:
          artist.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case IMAGE_SIZE:
          artist.setImageSize(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ALT:
          artist.setAlt(infoboxAlt(map.get(key)));
          break;
        case CAPTION:
          artist.setCaption(infoboxCaption(map.get(key)));
          break;
        case NATIVE_NAME:
          artist.setNativeName(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case NATIVE_NAME_LANG:
          artist.setNativeNameLang(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case BIRTH_NAME:
          artist.setBirthName(infoboxBirthName(map.get(key)));
          break;
        case BIRTH_DATE:
          artist.setBirthDate(parseInfoboxDateBirth(map.get(key)));
          break;
        case BIRTH_PLACE:
          artist.setBirthPlace(infoboxPlaceBirth(map.get(key)));
          break;
        case DEATH_DATE:
          Calendar date = parseInfoboxDateDeath(map.get(key));
          artist.setDeathDate(date);
          break;
        case DEATH_PLACE:
          artist.setDeathPlace(infoboxPlaceDeath(map.get(key)));
          break;
        case RESTING_PLACE:
          coordinate.setMap(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          artist.setRestingPlace(coordinate);
          break;
        case RESTING_PLACE_COORDINATES:
          String[] c = infoboxRestingPlaceCoordinates(map.get(key));
          coordinate.setLatD(c[1]);
          coordinate.setLongD(c[2]);
          artist.setRestingPlaceCoordinates(coordinate);
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
          artist.setNotableWorks(infoboxWorks(map.get(key)));
          break;
        case STYLE:
          artist.setStyle(map.get(key));
          break;
        case MOVEMENT:
          String[] work = infoboxMovement(map.get(key));
          for (int i = 0; i < work.length; i++)
          {
            artMovement = new ArtMovement();
            artMovement.setName(work[i]);
            artworks.add(artMovement);
          }
          artist.setMovement(artworks);
          break;
        case SPOUSE:
          artist.setSpouse(map.get(key));
          break;
        case AWARDS:
          artist.setAwards(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ELECTED:
          artist.setElected(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case PATRONS:
          artist.setPatrons(infoboxPatron(map.get(key)));
          break;
        case MEMORIALS:
          artist.setMemorials(map.get(key));
          break;
        case WEBSITE:
          artist.setWebsite(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
      }
    }

    return artist;
  }

  public static Calendar parseInfoboxDateDeath(String date)
  {
    try
    {
      date = date.replace("\n", "");
      date = date.replace("{", "");
      date = date.replace("}", "");
      
      if (date.contains("|| df=yes"))
      {
        date = date.replace("|| df=yes", "");
      }
      
      date = date.replace("death date||", "");
      date = date.replace("death date ||", "");
      date = date.replace("Death date||", "");
      date = date.replace("Death date ||", "");
      date = date.replace("death date and age||", "");
      date = date.replace("death date and age ||", "");
      date = date.replace("Death date and age||", "");
      date = date.replace("Death date and age ||", "");
  
      String[] dateSplit = StringUtils.split(date, "|| ");
      
      if (dateSplit.length == 0)
        return null;
      
      int year   = Integer.parseInt(dateSplit[0]);
      int month = (dateSplit.length > 1) ? Integer.parseInt(dateSplit[1]) : 0;
      int day   = (dateSplit.length > 2) ? Integer.parseInt(dateSplit[2]) : 0;
      
      Calendar dateClean = new GregorianCalendar(year, month - 1, day);
      
      return dateClean;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static Calendar parseInfoboxDateBirth(String date)
  {
    try
    {
      date = date.replace("March", "3");
      date = date.replace("\n", "");
      date = date.replace("{", "");
      date = date.replace("}", "");
      
      if (date.contains("|| df=yes") || date.contains("|| mf=yes"))
      {
        date = date.replace("|| df=yes", "");
        date = date.replace("|| mf=yes", "");
      }
      
      date = date.replace("birth date||", "");
      date = date.replace("birth date ||", "");
      date = date.replace("Birth date||", "");
      date = date.replace("Birth date ||", "");
      date = date.replace("birth date and age||", "");
      date = date.replace("birth date and age ||", "");
      date = date.replace("Birth date and age||", "");
      date = date.replace("Birth date and age ||", "");
  
      if (date.contains("|| "))
      {
        String[] dateSplit = StringUtils.split(date, "|| ");
        
        int year  = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day   = Integer.parseInt(dateSplit[2]);
        
        Calendar dateClean = new GregorianCalendar(year, month - 1, day);
        
        return dateClean;
      }
      else
      {
        String[] dateSplit = StringUtils.split(date, " ");
        
        int year  = (dateSplit.length == 1) ? Integer.parseInt(dateSplit[0]) : Integer.parseInt(dateSplit[2]);
        int month = 0;
        
        if (dateSplit.length > 1)
        {          
          try
          {
            month = Integer.parseInt(dateSplit[1]);
          }
          catch (Exception e)
          {
            try
            {
              Calendar x = Calendar.getInstance();
              x.setTime(new SimpleDateFormat("MMM", Locale.ENGLISH).parse(dateSplit[1]));
              month = x.get(Calendar.MONTH);
            }
            catch (Exception e2)
            {
              return null;
            }
          }
        }
        
        int day = (dateSplit.length > 2) ? Integer.parseInt(dateSplit[0]) : 0;
        
        Calendar dateClean = new GregorianCalendar(year, month - 1, day);
        
        return dateClean;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxPlaceDeath(String place)
  {
    try
    {
      if (place != null)
      {
        place = place.replace("\n", "");
        place = place.replace("[", "");
        place = place.replace("]", "");
        place = place.replace("|", " ");
  
        if (place.indexOf(",") != -1)
        {
          String[] p = StringUtils.split(place, ",");
          
          return p[0];
        }
      }
  
      return place;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String[] infoboxRestingPlaceCoordinates(String coor)
  {
    try
    {
      String[] c = StringUtils.split(coor, "|");

      return c;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxPlaceBirth(String place)
  {
    try
    {
      place = place.replace("\n", "");
      place = place.replace("[", "");
      place = place.replace("]", "");
      place = place.replace("|", " ");
      
      return place;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String[] infoboxMovement(String movement)
  {
    try
    {
      movement = movement.replace("[", "");
      movement = movement.replace("]", "");
      movement = movement.replace("\n", "");
  
      String[] mov = StringUtils.split(movement, ",");
  
      return mov;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static ArrayList<Artwork> infoboxWorks(String work)
  {
    try
    {
      work = work.replace("[", "");
      work = work.replace("]", "");
      work = work.replace("'", "");
      work = work.replace("\n", "");
      
      ArrayList<Artwork> worksArray = new ArrayList<Artwork>();
      
      if (work.contains("<br />"))
      {
        work = work.replace("br />", "");
        work = work.replace("||", "");
        
        String[] works = StringUtils.split(work, "<");
        
        for (int i = 0; i < works.length; i++)
        {
          Artwork art = new Artwork();
          art.setTitle(works[i]);
          worksArray.add(art);
        }
        
        return worksArray;
      }
      else
      {
        String[] works = StringUtils.split(work, ",");

        for (int i = 0; i < works.length; i++)
        {
          Artwork art = new Artwork();
          art.setTitle(works[i]);
          worksArray.add(art);
        }
        
        return worksArray;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxPatron(String patron)
  {
    try
    {
      patron = patron.replace("[", "");
      patron = patron.replace("]", "");
      patron = patron.replace("'", "");
      patron = patron.replace("}", "");
      patron = patron.replace("|", "");
      patron = patron.replace("\n", "");
  
      return patron;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxCaption(String caption)
  {
    try
    {
      caption = caption.replace("[", "");
      caption = caption.replace("]", "");
      caption = caption.replace("'", "");
      caption = caption.replace("&nbsp;", " ");
      caption = caption.replace("\n", "");
  
      return caption;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxAlt(String alt)
  {
    try
    {
       alt = alt.replace("\n", "");
       
       return alt;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxTraining(String trainer)
  {
    try
    {
      trainer = trainer.replace("[", "");
      trainer = trainer.replace("]", "");
      trainer = trainer.replace("\n", "");
      trainer = trainer.replace("<br /> ", "");
  
      return trainer;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxBirthName(String name)
  {
    try
    {
      System.out.println(name);
  
      if (name != null && name.indexOf("<") != -1)
      {
        String[] field = StringUtils.split(name, "<");
        return field[0];
      }
  
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }
}
