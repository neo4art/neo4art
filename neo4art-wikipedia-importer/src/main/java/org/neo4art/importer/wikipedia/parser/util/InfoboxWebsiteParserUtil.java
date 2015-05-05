package org.neo4art.importer.wikipedia.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxWebsiteParserUtil
{
  private static Log logger = LogFactory.getLog(InfoboxWebsiteParserUtil.class);

  public static String getWebsite(String web)
  {
    try{
    if (web.contains("|"))
    {
      String[] w = StringUtils.split(web, "|");
      web = InfoboxParserUtil.removeAllParenthesis(w[1]);
      return web.trim();
    }
    if (web.contains("/"))
    {
      String[] w = StringUtils.split(web, "/");
      web = w[0] + "//" + w[1];
      for (int i = 2; i < w.length; i++)
      {
        web = web + "/" + w[i];
      }

      web = InfoboxParserUtil.removeAllParenthesis(web);
    }
    if (web.contains(" "))
    {
      String[] w = StringUtils.split(web, " ");
      web = w[0];
    }
    }
    catch(Exception e)
    {
      logger.error("Error parsing Website infobox: " + e.getMessage());
    }
    return web.trim();
  }
}
