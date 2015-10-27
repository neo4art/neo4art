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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Settlement;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 20 Oct 2015
 */
public class WikipediaInfoboxParserUtils {

  private static Log  logger                = LogFactory.getLog(WikipediaInfoboxParserUtils.class);

  private static final String ONE_OR_MORE_NEW_LINES = "[\\n\\r]+";
  private static final String ZERO_OR_MORE_SPACES   = "[\\s]*";
  private static final String JUST_ONE_PIPE         = "[\\|]{1}";
  private static final String JUST_ONE_EQUAL        = "[=]{1}";
  private static final String INFO_KEY              = "([\\S]+)";
  private static final String INFO_VALUE            = "([\\S\\s&&[^\\n^\\r]]*)";

  /**
   * @param text
   * @return
   */
  public static Map<String, String> asMap(String text) {
  
    Map<String, String> map = new HashMap<String, String>();
  
    String patternRegex =
        ONE_OR_MORE_NEW_LINES +
        ZERO_OR_MORE_SPACES + 
        JUST_ONE_PIPE + 
        ZERO_OR_MORE_SPACES + 
        INFO_KEY + 
        ZERO_OR_MORE_SPACES + 
        JUST_ONE_EQUAL + 
        INFO_VALUE;
  
    Pattern pattern = Pattern.compile(patternRegex);
    Matcher matcher = pattern.matcher(text);
  
    while (matcher.find()) {
  
      if (logger.isTraceEnabled()) {
        for (int i = 1; i <= matcher.groupCount(); i++) {
          logger.trace(i + " [" + matcher.start(i) + "," + matcher.end() + "]:" + matcher.group(i));
        }
      }
  
      map.put(StringUtils.trim(matcher.group(1)), StringUtils.trim(matcher.group(2)));
    }
  
    return map;
  }

  /**
   * @param input
   * @return
   */
  public static String getTextFromLink(String input) {

    String result = null;

    if (StringUtils.isNotBlank(input)) {

      result = input;
      result = StringUtils.remove(result, "[[");
      result = StringUtils.remove(result, "]]");

      if (StringUtils.indexOf(result, "|") != -1) {

        result = StringUtils.substring(result, 0, StringUtils.indexOf(result, "|"));
      }

      result = StringUtils.trim(result);
    }

    return result;
  }

  /**
   * @param input
   * @return
   */
  public static Settlement parseAsSettlement(String input) {

    Settlement result = null;

    if (StringUtils.isNotBlank(input)) {

      String[] tokens = StringUtils.split(input, ",");

      result = new Settlement();

      if (tokens.length == 0) {

        result.setName(getTextFromLink(input));
      }
      else if (tokens.length == 1) {

        result.setName(getTextFromLink(tokens[0]));
      }
      else if (tokens.length == 2) {

        result.setName(getTextFromLink(tokens[0]));
        result.setCountry(getTextFromLink(tokens[1]));
      }
      else if (tokens.length == 3) {

        result.setName(getTextFromLink(tokens[0]));
        result.setState(getTextFromLink(tokens[1]));
        result.setCountry(getTextFromLink(tokens[2]));
      }
    }

    return result;
  }

  /**
   * 
   * @param input
   * @return
   * @throws MalformedURLException
   */
  public static URL parseAsURL(String input) throws MalformedURLException {

    URL result = null;

    if (!StringUtils.isBlank(input)) {

      String temp = input;

      temp = StringUtils.remove(temp, "{");
      temp = StringUtils.remove(temp, "}");

      if (StringUtils.indexOf(temp, "[") != -1) {

        temp = StringUtils.remove(temp, "[");
        temp = StringUtils.remove(temp, "]");

        if (StringUtils.indexOf(temp, " ") != -1) {
          temp = StringUtils.substring(temp, 0, StringUtils.indexOf(temp, " "));
        }
      }

      temp = StringUtils.remove(temp, "URL|");
      temp = StringUtils.replace(temp, " ", "_");

      if (!StringUtils.startsWith(temp, "http")) {

        if (StringUtils.startsWith(temp, "www")) {

          temp = "http://" + temp;
        }
        else {

          temp = "https://en.wikipedia.org/wiki/File:" + temp;
        }
      }

      temp = StringUtils.removeEnd(temp, "|");

      result = new URL(temp);
    }

    return result;
  }

  /**
   * @param input
   * @return
   */
  public static List<String> asListOfStrings(String input) {

    List<String> result = null;

    if (StringUtils.isNotBlank(input)) {

      result = new ArrayList<String>();

      String[] movements = StringUtils.split(input, ",");

      for (String movement : movements) {

        movement = StringUtils.trim(movement);
        movement = StringUtils.remove(movement, "[[");
        movement = StringUtils.remove(movement, "]]");

        result.add(StringUtils.trim(movement));
      }
    }

    return result;
  }
}
