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

package org.neo4art.importer.wikipedia.parser.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Parser for
 * 
 * <a href="https://en.wikipedia.org/wiki/Template:Birth_date">Template:Birth_date</a>,
 * <a href="https://en.wikipedia.org/wiki/Template:Birth_date_and_age">Template:Birth_date_and_age</a>,
 * <a href="https://en.wikipedia.org/wiki/Template:Birth_year_and_age">Template:Birth_year_and_age</a>,
 * 
 * <a href="https://en.wikipedia.org/wiki/Template:Death_date_and_age">Template:Death_date_and_age</a>,
 * 
 * @author Lorenzo Speranzoni
 * @since 20 Oct 2015
 */
public class WikipediaDateTimeInfoboxParserUtils {

  private static Log logger = LogFactory.getLog(WikipediaDateTimeInfoboxParserUtils.class);

  /**
   * @param dateAsString
   * @return
   */
  public static Date parseAsDate(String dateAsString) {

    if (StringUtils.isBlank(dateAsString))
      return null;

    else if (StringUtils.contains(dateAsString, "Birth date"))
      return parseBirthDateAsDate(dateAsString);

    else if (StringUtils.contains(dateAsString, "Birth date and age"))
      return parseBirthDateAndAgeAsDate(dateAsString);

    else if (StringUtils.contains(dateAsString, "Birth year and age"))
      return parseBirthYearAndAgeAsDate(dateAsString);

    else if (StringUtils.contains(dateAsString, "Death date and age"))
      return parseDeathDateAndAgeAsDate(dateAsString);
    
    return null;
  }

  /**
   * {{Birth date|1993|2|4|df=yes}}
   * {{Birth date|1993|2|4|mf=yes}}
   * {{Birth date|1993|2|4}}
   * {{Birth date |1901|7|31|df=y}}
   * {{Birth date|df=yes|1756|03|04}}
   * 
   * @param dateAsString
   * @return
   */
  public static Date parseBirthDateAsDate(String dateAsString) {

    final String PATTERNS[] = { "[\\{]{2}[Bb]irth date[\\s]*\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)\\|[\\S]+[\\}]{2}" ,
                                "[\\{]{2}[Bb]irth date[\\s]*\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)[\\}]{2}",
                                "[\\{]{2}[Bb]irth date[\\s]*\\|[\\D]+\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)[\\}]{2}"};

    for (String patternRegex : PATTERNS) {
      
      System.out.println("AAAAAAA: " + dateAsString + "   -> " + patternRegex);
      Pattern patter = Pattern.compile(patternRegex);
      Matcher matcher = patter.matcher(dateAsString);
      
      if (matcher.matches()) {
      
        return toDate(matcher.group(1), matcher.group(2), matcher.group(3));
      }
    }

    logger.error("Unable to parse " + dateAsString);
    
    return null;
  }

  /**
   * {{Birth date and age|1993|2|4|df=yes}}
   * {{Birth date and age|1993|2|4|mf=yes}}
   * {{Birth date and age|1993|2|4}}
   * 
   * @param dateAsString
   * @return
   */
  public static Date parseBirthDateAndAgeAsDate(String dateAsString) {

    final String PATTERNS[] = { "[\\{]{2}[Bb]irth date and age[\\s]*\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)\\|[\\S]*",
                                "[\\{]{2}[Bb]irth date and age[\\s]*\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)[\\}]{2}"};

    for (String patternRegex : PATTERNS) {
      
      Pattern patter = Pattern.compile(patternRegex);
      Matcher matcher = patter.matcher(null);
      
      if (matcher.matches()) {
        
        return toDate(matcher.group(1), matcher.group(2), matcher.group(3));
      }      
    }

    logger.error("Unable to parse " + dateAsString);
      
    return null;
  }

  /**
   * @param dateAsString
   * @return
   */
  public static Date parseBirthYearAndAgeAsDate(String dateAsString) {
    return null;
  }

  /**
   * {{Death date and age|1993|2|24|1941|4|12|df=yes}}
   * {{Death date and age|1993|2|24|1941|4|df=yes}}
   * {{Death date and age|1993|2|24|1941|mf=yes}}
   * {{Death date and age|df=yes|1890|7|29|1853|3|30}}
   * 
   * @param dateAsString
   * @return
   */
  public static Date parseDeathDateAndAgeAsDate(String dateAsString) {

    final String PATTERNS[] = { "[\\{]{2}[Dd]eath date and age[\\s]*\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)\\|[\\S]*" ,
                                "[\\{]{2}[Dd]eath date and age[\\s]*\\|[\\D]+\\|([\\d]+)\\|([\\d]+)\\|([\\d]+)\\|[\\S]*" };

    for (String patternRegex : PATTERNS) {
      
      Pattern patter = Pattern.compile(patternRegex);
      Matcher matcher = patter.matcher(dateAsString);
      
      if (matcher.matches()) {
        
        return toDate(matcher.group(1), matcher.group(2), matcher.group(3));
      }      
    }

    logger.error("Unable to parse " + dateAsString);
      
    return null;
  }

  /**
   * 
   * @param year
   * @param month
   * @param day
   * @return
   */
  public static Date toDate(String year, String month, String day) {
    
    try {
      
      Calendar calendar = Calendar.getInstance();
      
      calendar.set(Integer.parseInt(StringUtils.trim(year)), Integer.parseInt(StringUtils.trim(month)) - 1, Integer.parseInt(StringUtils.trim(day)), 0, 0, 0);
      calendar.set(Calendar.MILLISECOND, 0);
    
      return calendar.getTime();
    }
    catch (Exception e) {
      
      logger.error("Unable to generate date for year = " + year + ", month = " + month + ", day = " + day);
      
      return null;
    }
  }
}
