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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lorenzo Speranzoni
 * @since 21 Mar 2015
 */
public class WikipediaInfoboxUtils {

  public static Map<String, String> asMap(String text) {

    Map<String, String> map = new HashMap<String, String>();
    
    String[] infos = StringUtils.split(text, "\n");

    for (int i = 0; i < infos.length; i++) {
      if (infos[i].startsWith("| ") && infos[i].contains("=")) {
        int separatorIndex = infos[i].indexOf("=");

        if (separatorIndex != -1) {
          String key   = infos[i].substring(2,  separatorIndex).trim();
          String value = infos[i].substring(separatorIndex + 1).trim();
          
          map.put(key, value);
        }
      }
    }

    return map;
  }
}
