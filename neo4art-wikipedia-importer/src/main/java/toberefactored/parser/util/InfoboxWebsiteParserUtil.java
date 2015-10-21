package toberefactored.parser.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxWebsiteParserUtil
{
  private static Log logger = LogFactory.getLog(InfoboxWebsiteParserUtil.class);

  public static String getWebsite(String web)
  {
    try
    {
      web = InfoboxParserUtil.removeAllParenthesis(web);
      if (web.startsWith("URL")) {
        web = web.replace("URL", "");
      }
      if (web.contains("/"))
      {
        System.out.println("AAAAAA: " + web);

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
        if(w[0].contains("http://"))
        {
          return w[0].trim();
        }
        else
        {
          web = w[1];
          web = InfoboxParserUtil.removeAllParenthesis(web);
          if (!(web.contains("www.") || web.contains("http://")))
          {
            web = "http://www." + web;
          }
        }
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Website infobox: " + e.getMessage());
    }
    return web.trim();
  }
}
