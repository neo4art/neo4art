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

package org.neo4art.sentiment.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lorenzo Speranzoni
 * @since 25 Apr 2015
 */
public class DictionaryUtils
{
  public static final String[] LUCENE_SPECIAL_CHARACTERS        = { "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^", "\"", "~", "*", "?", ":", "\\", "AND", "OR", "NOT" };
  public static final String[] LUCENE_SPECIAL_CHARACTERS_ESCAPE = { "\\+", "\\-", "\\&\\&", "\\|\\|", "\\!", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]", "\\^", "\\\"", "\\~", "\\*", "\\?", "\\:", "\\\\", "\\AND", "\\OR", "\\NOT" };
  
  public static final List<String> PUNCTUATION_SYMBOLS = new ArrayList<String>();
  
  static
  {
    PUNCTUATION_SYMBOLS.add(",");
    PUNCTUATION_SYMBOLS.add(".");
    PUNCTUATION_SYMBOLS.add("!");
    PUNCTUATION_SYMBOLS.add("?");
    PUNCTUATION_SYMBOLS.add("\"");
    PUNCTUATION_SYMBOLS.add(":");
    PUNCTUATION_SYMBOLS.add(";");
    PUNCTUATION_SYMBOLS.add("'");
    PUNCTUATION_SYMBOLS.add("-");
  };
  
  public static String escapeWordForLuceneSearch(String word)
  {
    return StringUtils.replaceEach(word, LUCENE_SPECIAL_CHARACTERS, LUCENE_SPECIAL_CHARACTERS_ESCAPE);
  }

  /**
   * 
   * @param text
   * @return
   */
  public static String forNLP(String text)
  {
    String result = null;
    
    result = StringUtils.replaceChars(text  , "‘", "'");
    result = StringUtils.replaceChars(result, "’", "'");
    result = StringUtils.replaceChars(result, "“", "\"");
    result = StringUtils.replaceChars(result, "”", "\"");
        
    return result;
  }

  /**
   * @param string
   * @return
   */
  public static boolean isPunctuation(String word)
  {
    return PUNCTUATION_SYMBOLS.contains(word);
  }
}
