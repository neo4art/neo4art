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
package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * 
 * @author Mattia Zaratin
 * @since 30 Apr 2015
 */
public class WikipediaReligiousBuildingShintoShrineInfobox
{
  private static Log logger = LogFactory.getLog(WikipediaReligiousBuildingShintoShrineInfobox.class);
  
  public static final String NAME   = "name";
  public static final String LATD   = "latd";
  public static final String LATM   = "latm";
  public static final String LATS   = "lats";
  public static final String LATNS  = "latNS";
  public static final String LONGD  = "longd";
  public static final String LONGM  = "longm";
  public static final String LONGS  = "longs";
  public static final String LONGEW = "longEW";
  public static final String IMAGE  = "image";
  public static final String STYLE  = "infobox";

  /**
   * 
   * @param text
   * @return
   */
  public static ReligiousBuilding parse(String text)
  {
    ReligiousBuilding shintoShrine = new ReligiousBuilding();
    Coordinate coordinate = new Coordinate();

    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    for (String key : map.keySet())
    {
      switch (key)
      {
        case NAME:
          shintoShrine.setBuildingName(infoboxName(map.get(key)));
          break;
        case STYLE:
          shintoShrine.setType(map.get(key));
          break;
        case IMAGE:
          shintoShrine.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case LATD:
          coordinate.setLatD(map.get(key));
          break;
        case LATM:
          coordinate.setLatM(map.get(key));
          break;
        case LATS:
          coordinate.setLatS(map.get(key));
          break;
        case LATNS:
          coordinate.setLatNS(map.get(key));
          break;
        case LONGD:
          coordinate.setLongD(map.get(key));
          break;
        case LONGM:
          coordinate.setLongM(map.get(key));
          break;
        case LONGS:
          coordinate.setLongS(map.get(key));
          break;
        case LONGEW:
          coordinate.setLongEW(map.get(key));
          break;
      }
      
      shintoShrine.setCoordinates(coordinate);
    }
    
    return shintoShrine;
  }

  public static String infoboxName(String name)
  {
    try
    {
      String[] n = StringUtils.split(name, "<");
      name = n[0];
      name = name.replace("'", "");
  
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ShintoShrine infobox: " + e.getMessage());      
    }
    
    return null;
  }
}
