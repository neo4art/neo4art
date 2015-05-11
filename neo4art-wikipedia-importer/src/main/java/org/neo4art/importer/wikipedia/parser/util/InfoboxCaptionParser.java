package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxCaptionParser
{
  private static Log logger = LogFactory.getLog(InfoboxCaptionParser.class);

  public static String infoboxCaption(String caption)
  {
    try
    {
      if(caption.contains("birth_name"))
        return null;
      if (caption.contains("<"))
      {
        String[] n = StringUtils.split(caption, "<");
        caption = n[0];
      }
      if (caption.contains("|"))
      {
        String[] n = StringUtils.split(caption, "[");
        String[] n1 = StringUtils.split(n[1], "|");
        caption = n1[0];
      }
      else
      {
        caption = caption.replace("[", "");
        caption = caption.replace("]", "");
        caption = caption.replace("'", "");
        caption = caption.replace("&nbsp;", " ");
        caption = caption.replace("\n", "");
      }
      return caption;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Caption infobox: " + e.getMessage());
    }

    return null;
  }
}
