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

package org.neo4art.importer.wikipedia.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * We use <a href="http://en.wikipedia.org/wiki/Template:Infobox">infobox</a> to classify art contents.
 * 
 * Here you can see <a href="http://en.wikipedia.org/wiki/Wikipedia:List_of_infoboxes">list of infoboxes</a>.
 * 
 * @author Lorenzo Speranzoni
 * @since 21 Mar 2015
 */
public class WikipediaInfoboxUtils
{

  /**
   * @param text
   * @return
   */
  public static boolean isArtist(String text)
  {
    return StringUtils.contains(text, "{{Infobox artist");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isArtwork(String text)
  {
    return StringUtils.contains(text, "{{Infobox artwork");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isArtMovement(String text)
  {
    return StringUtils.contains(text, "{{Infobox art movement");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isMuseum(String text)
  {
    return StringUtils.contains(text, "{{Infobox museum");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isMonument(String text)
  {
    return StringUtils.contains(text, "{{Infobox monument");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isReligiousBuilding(String text)
  {
    return StringUtils.contains(text, "{{Infobox religious building");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isSettlement(String text)
  {
    return StringUtils.contains(text, "{{Infobox settlement");
  }

  /**
   * @param text
   * @return
   */
  public static boolean isCountry(String text)
  {
    return StringUtils.contains(text, "{{Infobox country");
  }

  /**
   * @param text
   * @return
   */
  public static Map<String, String> asMap(String text)
  {

    Map<String, String> map = new HashMap<String, String>();

    text = text.replace("|", "|| ");

    String[] infos = StringUtils.split(text, "\n");

    String type = infos[0];

    type = type.replace("\n ", "");
    type = type.replace("{{Infobox ", "");

    for (int i = 0; i < infos.length; i++)
    {
      if (infos[i].startsWith("|| ") && infos[i].contains("="))
      {
        int separatorIndex = infos[i].indexOf("=");

        if (separatorIndex != -1)
        {
          String key = infos[i].substring(2, separatorIndex).trim();
          String value = infos[i].substring(separatorIndex + 1).trim();
          map.put(key, value);
        }
      }
    }
    map.put("infobox", type);
    return map;
  }

  public static URL infoboxImageUrl(String nameImage)
  {
    nameImage = nameImage.replaceAll(" ", "_");
    nameImage = nameImage.replace("|", "");
    nameImage = nameImage.replace("\n", "");
    if (nameImage.contains("File:"))
    {
      nameImage = "http://en.wikipedia.org/wiki/" + nameImage;
    }
    else
    {
      nameImage = "http://en.wikipedia.org/wiki/File:" + nameImage;
    }

    URL url = null;
    
    try
    {
      url = new URL(nameImage);
    }
    catch (MalformedURLException e)
    {
    }

    return url;
  }

  public static String removeAllParenthesis(String string)
  {

    string = string.replace("\n", "");
    string = string.replace("[", "");
    string = string.replace("]", "");
    string = string.replace("{", "");
    string = string.replace("}", "");
    string = string.replace("|", "");

    return string;
  }

  public static String removeLink(String string)
  {

    string = string.replace("\n", "");
    string = string.replace("[", "");
    string = string.replace("]", "");
    string = string.replace("{", "");
    string = string.replace("}", "");
    string = string.replace("'", "");
    if (string.contains("|"))
    {
      String[] link = StringUtils.split(string, "|");
      return link[0];
    }

    return string;
  }

  public static String getType(String type)
  {

    String[] t = StringUtils.split(type, "|");

    t[0] = WikipediaInfoboxUtils.removeAllParenthesis(t[0]);

    return type = t[0].trim();
  }

  public static String getWebsite(String web)
  {

    if (web.contains("|"))
    {
      String[] w = StringUtils.split(web, "|");
      web = WikipediaInfoboxUtils.removeAllParenthesis(w[1]);
      return web.trim();
    }
    if (web.contains("/"))
    {
      String[] w = StringUtils.split(web, "/");
      web = w[0] + "//" + w[1];
      for (int i = 2; i < w.length; i++)
      {
        web = web + "/" + w[i];
      }

      web = WikipediaInfoboxUtils.removeAllParenthesis(web);
    }
    if (web.contains(" "))
    {
      String[] w = StringUtils.split(web, " ");
      web = w[0];
    }
    return web.trim();
  }
}
