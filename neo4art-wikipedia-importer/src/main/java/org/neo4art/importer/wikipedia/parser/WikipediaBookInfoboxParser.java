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

import org.neo4art.domain.Book;

import toberefactored.parser.util.InfoboxMap;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_book">Template :Infobox_book</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 11 Oct 2015
 */
public class WikipediaBookInfoboxParser {
  public static final String NAME = "name";

  public WikipediaBookInfoboxParser() {
  }

  public static Book parse(String text) {
    Map<String, String> map = InfoboxMap.asMap(text);

    Book book = new Book();

    for (String key : map.keySet()) {
      switch (key) {
        case NAME:
          book.setName(map.get(key));
          break;
      }
    }

    return book;
  }
}
