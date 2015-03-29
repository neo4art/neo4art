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

import info.bliki.wiki.dump.WikiArticle;

import org.neo4art.importer.wikipedia.domain.WikipediaType;


/**
 * @author Lorenzo Speranzoni
 * @since 20 Mar 2015
 */
public class WikipediaElementUtils {

  /**
   * @param text
   * @return
   */
  public static boolean isArtist(String text) {
    return text.indexOf("{{Infobox artist") != -1;
  }

  /**
   * @param text
   * @return
   */
  public static boolean isArtwork(String text) {
    return text.indexOf("{{Infobox artwork") != -1;
  }
  
  /**
   * @param text
   * @return
   */
  public static boolean isMuseum(String text) {
    return text.indexOf("{{Infobox museum") != -1;
  }
  
  /**
   * 
   * @param article
   * @return
   */
  public static WikipediaType getWikipediaElementType(WikiArticle article) {

    if (article.isMain()) {
      if (WikipediaElementUtils.isArtist(article.getText())) {
        return WikipediaType.ARTIST_PAGE;
      } else if (WikipediaElementUtils.isArtwork(article.getText())) {
        return WikipediaType.ARTWORK_PAGE;
      } else if (WikipediaElementUtils.isMuseum(article.getText())) {
        return WikipediaType.MUSEUM_PAGE;
      } else {
        return WikipediaType.PAGE;
      }
    } else if (article.isCategory()) {
      return WikipediaType.CATEGORY;
    } else if (article.isTemplate()) {
      return WikipediaType.TEMPLATE;
    } else if (article.isFile()) {
      return WikipediaType.FILE;
    } else if (article.isProject()) {
      return WikipediaType.PROJECT;
    } else {
      return WikipediaType.GENERIC;
    }
  }
}
