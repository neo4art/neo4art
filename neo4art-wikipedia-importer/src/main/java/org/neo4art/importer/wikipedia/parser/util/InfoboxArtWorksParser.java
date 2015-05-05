package org.neo4art.importer.wikipedia.parser.util;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Artwork;

public class InfoboxArtWorksParser
{
  private static Log logger = LogFactory.getLog(InfoboxArtWorksParser.class);
  
  public InfoboxArtWorksParser()
  {
    
  }
  
  public static ArrayList<Artwork> infoboxWorks(String work)
  {
    try
    {
      work = work.replace("[", "");
      work = work.replace("]", "");
      work = work.replace("'", "");
      work = work.replace("\n", "");
      
      ArrayList<Artwork> worksArray = new ArrayList<Artwork>();
      
      if (work.contains("<br />"))
      {
        work = work.replace("br />", "");
        work = work.replace("||", "");
        
        String[] works = StringUtils.split(work, "<");
        
        for (int i = 0; i < works.length; i++)
        {
          Artwork art = new Artwork();
          art.setTitle(works[i]);
          worksArray.add(art);
        }
        
        return worksArray;
      }
      else
      {
        String[] works = StringUtils.split(work, ",");

        for (int i = 0; i < works.length; i++)
        {
          Artwork art = new Artwork();
          art.setTitle(works[i]);
          worksArray.add(art);
        }
        
        return worksArray;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing ArtWorks infobox: " + e.getMessage());
    }
    
    return null;
  }
}
