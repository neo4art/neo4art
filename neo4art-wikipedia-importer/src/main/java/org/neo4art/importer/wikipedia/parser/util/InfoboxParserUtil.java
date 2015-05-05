package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxParserUtil
{

  private static Log logger = LogFactory.getLog(InfoboxParserUtil.class);

  public static String removeAllParenthesis(String string)
  {
    try
    {
      string = string.replace("\n", "");
      string = string.replace("[", "");
      string = string.replace("]", "");
      string = string.replace("{", "");
      string = string.replace("}", "");
      string = string.replace("|", "");
    }
    catch (Exception e)
    {
      logger.error("Error parsing RemoveAllParenthesis infobox: " + e.getMessage());
    }
    return string;
  }

  public static String removeLink(String string)
  {
    try
    {
      string = string.replace("\n", "");
      string = string.replace("[", "");
      string = string.replace("]", "");
      string = string.replace("{", "");
      string = string.replace("}", "");
      string = string.replace("'", "");
      if (string.contains("|"))
      {
        String[] link = StringUtils.split(string, "|");
        return link[0];
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing RemoveLink infobox: " + e.getMessage());
    }
    return string;
  }

}
