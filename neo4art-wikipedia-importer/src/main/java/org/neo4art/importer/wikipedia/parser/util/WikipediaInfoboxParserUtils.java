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

import org.apache.commons.lang3.StringUtils;

/**
 * Parser for <a href="https://en.wikipedia.org/wiki/Template:Birth_date_and_age">Template:Birth_date_and_age</a>,
 *            <a href="https://en.wikipedia.org/wiki/Template:Birth_year_and_age">Template:Birth_year_and_age</a>,
 * 
 * @author Lorenzo Speranzoni
 * @since 20 Oct 2015
 */
public class WikipediaInfoboxParserUtils {
  
  /**
   * @param input
   * @return
   */
  public static String removeDoubleSquareBracketsForLinks(String input) {
    
    String result = null;
    
    if (!StringUtils.isBlank(input)) {
    
      result = StringUtils.remove(input, "[[");
      result = StringUtils.remove(result, "]]");
    }
      
    return result;
  }
}
