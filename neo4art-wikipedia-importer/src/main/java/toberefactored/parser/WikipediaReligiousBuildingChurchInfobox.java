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
package toberefactored.parser;

import java.util.Map;

import org.neo4art.domain.Coordinates;
import org.neo4art.domain.ReligiousBuilding;

import toberefactored.parser.util.InfoboxMap;
import toberefactored.parser.util.InfoboxParserUtil;
import toberefactored.parser.util.InfoboxUrlParser;

/**
 * 
 * @author Mattia Zaratin
 * @since 30 Apr 2015
 */
public class WikipediaReligiousBuildingChurchInfobox
{
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

  public static ReligiousBuilding parse(String text)
  {
    ReligiousBuilding church = new ReligiousBuilding();
    Coordinates coordinates = new Coordinates();

    Map<String, String> map = InfoboxMap.asMap(text);

    for (String key : map.keySet())
    {
      switch (key)
      {
        case NAME:
          church.setName(InfoboxParserUtil.removeAllParenthesis(map.get(key)));
          break;
        case IMAGE:
          church.setImage(InfoboxUrlParser.infoboxUrl(map.get(key)));
          break;
        case LATD:
          coordinates.setLatD(Double.parseDouble(map.get(key)));
          break;
        case LATM:
          coordinates.setLatM(Double.parseDouble(map.get(key)));
          break;
        case LATS:
          coordinates.setLatS(Double.parseDouble(map.get(key)));
          break;
        case LATNS:
          coordinates.setLatNS(map.get(key));
          break;
        case LONGD:
          coordinates.setLongD(Double.parseDouble(map.get(key)));
          break;
        case LONGM:
          coordinates.setLongM(Double.parseDouble(map.get(key)));
          break;
        case LONGS:
          coordinates.setLongS(Double.parseDouble(map.get(key)));
          break;
        case LONGEW:
          coordinates.setLongEW(map.get(key));
          break;
      }
      
      church.setCoordinates(coordinates);
    }
    
    return church;
  }
}
