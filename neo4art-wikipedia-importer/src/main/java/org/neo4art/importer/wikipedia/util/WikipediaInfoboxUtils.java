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

}
