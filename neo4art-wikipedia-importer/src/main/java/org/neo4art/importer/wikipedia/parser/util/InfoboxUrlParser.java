package org.neo4art.importer.wikipedia.parser.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxUrlParser
{
  
  private static Log logger = LogFactory.getLog(InfoboxUrlParser.class);
  
  public InfoboxUrlParser()
  {
    
  }

  public static URL infoboxUrl(String nameImage)
  {
    nameImage = nameImage.replaceAll(" ", "_");
    nameImage = nameImage.replace("|", "");
    nameImage = nameImage.replace("\n", "");
    if (nameImage.contains("File:"))
    {
      nameImage = "http://en.wikipedia.org/wiki/" + nameImage;
    }
    else
    {
      nameImage = "http://en.wikipedia.org/wiki/File:" + nameImage;
    }

    URL url = null;
    
    try
    {
      url = new URL(nameImage);
    }
    catch (MalformedURLException e)
    {
      logger.error("Error parsing Url infobox: " + e.getMessage());
    }

    return url;
  }
  
}
