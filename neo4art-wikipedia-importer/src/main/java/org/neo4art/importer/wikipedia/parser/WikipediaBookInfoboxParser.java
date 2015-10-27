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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Book;
import org.neo4art.importer.wikipedia.parser.util.WikipediaInfoboxParserUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_book">Template :Infobox_book</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 11 Oct 2015
 */
public class WikipediaBookInfoboxParser {

  private static Log         logger   = LogFactory.getLog(WikipediaBookInfoboxParser.class);

  public static final String NAME     = "name";
  public static final String IMAGE    = "image";
  public static final String AUTHOR   = "author";
  public static final String LANGUAGE = "language";
  public static final String GENRE    = "genre";

  public WikipediaBookInfoboxParser() {
  }

  public static Book parse(String text) {

    Map<String, String> map = WikipediaInfoboxParserUtils.asMap(text);

    Book book = new Book();

    for (String key : map.keySet()) {

      try {

        switch (key) {
          case NAME:
            book.setName(map.get(key));
            break;
          case IMAGE:
            book.setImage(WikipediaInfoboxParserUtils.parseAsURL(map.get(key)));
            break;
          case AUTHOR:
            book.setAuthor(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case LANGUAGE:
            book.setLanguage(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
          case GENRE:
            book.setGenre(WikipediaInfoboxParserUtils.getTextFromLink(map.get(key)));
            break;
        }
      }
      catch (Exception e) {

        logger.error("     Error parsing infobox pair (" + key + " , " + map.get(key) + ") - " + e.getMessage() + "| " + e.getLocalizedMessage() + "| " + e.getCause().getMessage() + "| " + e.getCause().getLocalizedMessage());
      }
    }

    return book;
  }
}
