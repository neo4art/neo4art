package org.neo4art.importer.wikipedia.parser.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoboxDateParser
{

  private static Log logger = LogFactory.getLog(InfoboxDateParser.class);
  
  public InfoboxDateParser()
  {
    
  }
  
  public static Calendar parseInfoboxDateDeath(String date)
  {
    try
    {
      date = date.replace("\n", "");
      date = date.replace("{", "");
      date = date.replace("}", "");
      date = date.replace(",", " ");
      
      if (date.contains("|| df=yes"))
      {
        date = date.replace("|| df=yes", "");
      }
      date = date.replace("January", "1");
      date = date.replace("February", "2");
      date = date.replace("March", "3");
      date = date.replace("April", "4");
      date = date.replace("May", "5");
      date = date.replace("June", "6");
      date = date.replace("July", "7");
      date = date.replace("August", "8");
      date = date.replace("September", "9");
      date = date.replace("October", "10");
      date = date.replace("November", "11");
      date = date.replace("December", "12");
      
      date = date.replace("death date||", "");
      date = date.replace("death date ||", "");
      date = date.replace("death ||", "");
      date = date.replace("death||", "");
      date = date.replace("death-date ||", "");
      date = date.replace("death-date||", "");
      date = date.replace("Death date||", "");
      date = date.replace("Death date ||", "");
      date = date.replace("death date and age||", "");
      date = date.replace("death date and age ||", "");
      date = date.replace("Death date and age||", "");
      date = date.replace("Death date and age ||", "");
      date = date.replace("death year and age||", "");
      date = date.replace("death year and age ||", "");
      
  
      String[] dateSplit = StringUtils.split(date, "|| ");
      
      if (dateSplit.length == 0)
        return null;
      
      int year   = Integer.parseInt(dateSplit[0]);
      int month = (dateSplit.length > 1) ? Integer.parseInt(dateSplit[1]) : 0;
      int day   = (dateSplit.length > 2) ? Integer.parseInt(dateSplit[2]) : 0;
      
      Calendar dateClean = new GregorianCalendar(year, month - 1, day);
      
      return dateClean;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Date Death infobox: " + e.getMessage());
    }
    
    return null;
  }
  
  public static Calendar parseInfoboxDateBirth(String date)
  {
    try
    {
      date = date.replace("January", "1");
      date = date.replace("February", "2");
      date = date.replace("March", "3");
      date = date.replace("April", "4");
      date = date.replace("May", "5");
      date = date.replace("June", "6");
      date = date.replace("July", "7");
      date = date.replace("August", "8");
      date = date.replace("September", "9");
      date = date.replace("October", "10");
      date = date.replace("November", "11");
      date = date.replace("December", "12");
      date = date.replace("\n", "");
      date = date.replace("{", "");
      date = date.replace("}", "");
      
      if (date.contains("|| df=yes") || date.contains("|| mf=yes"))
      {
        date = date.replace("|| df=yes", "");
        date = date.replace("|| mf=yes", "");
      }
      
      if(date.contains("c."))
      {
        date = date.replace("c. ", "");
        Calendar dateClean = new GregorianCalendar(Integer.parseInt(date), 1, 1);
        return dateClean;
      }
      
      date = date.replace("birth date||", "");
      date = date.replace("birth date ||", "");
      date = date.replace("Birth date||", "");
      date = date.replace("Birth date ||", "");
      date = date.replace("birth-date ||", "");
      date = date.replace("birth-date||", "");
      date = date.replace("circa||", "");
      date = date.replace("circa ||", "");
      date = date.replace("birth date and age||", "");
      date = date.replace("birth date and age ||", "");
      date = date.replace("Birth date and age||", "");
      date = date.replace("Birth date and age ||", "");
  
      if (date.contains("|| "))
      {
        String[] dateSplit = StringUtils.split(date, "|| ");
        
        int year  = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day   = Integer.parseInt(dateSplit[2]);
        
        Calendar dateClean = new GregorianCalendar(year, month - 1, day);
        
        return dateClean;
      }
      else
      {
        String[] dateSplit = StringUtils.split(date, " ");
        
        int year  = (dateSplit.length == 1) ? Integer.parseInt(dateSplit[0]) : Integer.parseInt(dateSplit[2]);
        int month = 0;
        
        if (dateSplit.length > 1)
        {          
          try
          {
            month = Integer.parseInt(dateSplit[1]);
          }
          catch (Exception e)
          {
            try
            {
              Calendar x = Calendar.getInstance();
              x.setTime(new SimpleDateFormat("MMM", Locale.ENGLISH).parse(dateSplit[1]));
              month = x.get(Calendar.MONTH);
            }
            catch (Exception e2)
            {
              return null;
            }
          }
        }
        
        int day = (dateSplit.length > 2) ? Integer.parseInt(dateSplit[0]) : 0;
        Calendar dateClean = new GregorianCalendar(year, month - 1, day);
        
        return dateClean;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing DateBirth infobox: " + e.getMessage());
    }
    
    return null;
  }
  

  public static String infoboxYear(String year)
  {
    try
    {
      if (year.contains("|"))
      {
        year = InfoboxParserUtil.removeAllParenthesis(year);
        
        String[] y = StringUtils.split(year, "|");
        
        return y[1];
      }
      else
      {
        return year;
      }
    }
    catch (Exception e)
    {
      logger.error("Error parsing Year infobox: " + e.getMessage());
    }
    
    return null;
  }
  

  public static String infoboxEstablished(String dateString)
  {
    try
    {
      if (dateString.contains("<"))
      {
        String[] da1 = StringUtils.split(dateString, "<");

        return da1[0];
      }

      if (dateString.contains("|"))
      {
        String[] da = StringUtils.split(dateString, "|");

        return da[1];
      }

      return dateString;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Established infobox: " + e.getMessage());
    }

    return null;
  }
  
}
