package org.neo4art.importer.wikipedia.parser.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class InfoboxMap
{

  public static Map<String, String> asMap(String text)
  {

    Map<String, String> map = new HashMap<String, String>();

    text = text.replace("|", "|| ");
    String[] infos = StringUtils.split(text, "\n");

    String type = infos[0];

    type = type.replace("\n ", "");
    type = type.replace("{{Infobox ", "");
    type = type.replace("|", "");

    for (int i = 0; i < infos.length; i++)
    {
      if (infos[i].contains("="))
      {
        int separatorIndex = infos[i].indexOf("=");

        if (separatorIndex != -1)
        {
          String key = infos[i].substring(0, separatorIndex).trim();
          String value = infos[i].substring(separatorIndex + 1).trim();
          if (key.contains("|"))
            key = key.replace("|", "");
          if (value.endsWith("||"))
          {
            value = value.replace("|", "");
          }
            
          map.put(key.trim(), value.trim());
        }
      }
    }
    map.put("infobox", type);
    return map;
  }
}
