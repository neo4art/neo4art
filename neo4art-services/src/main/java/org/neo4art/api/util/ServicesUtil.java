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
package org.neo4art.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.neo4art.domain.Artwork;
import org.neo4art.literature.domain.Document;

/**
 * @author Enrico De Benetti
 * @since 02 Mag 2015
 *
 */
public class ServicesUtil
{

  private static ServicesUtil instance                      = null;

  private SimpleDateFormat    format;
  private SimpleDateFormat    documentFormat;
  Map<String, Integer>        mappaComletitionDay;
  private static final String DATE_FORMAT_COMPLETITION_DATE = "dd-MMM-yyyy HH:mm";
  private static final String DATE_FORMAT_DOCUMENT_DATE     = "E, dd MMM yyyy";

  private ServicesUtil()
  {
    documentFormat = new SimpleDateFormat(DATE_FORMAT_DOCUMENT_DATE, Locale.ENGLISH);
    format = new SimpleDateFormat(DATE_FORMAT_COMPLETITION_DATE, Locale.ENGLISH);
    mappaComletitionDay = new HashMap<String, Integer>();
  }

  public static ServicesUtil getInstance()
  {

    if (instance == null)
    {

      instance = new ServicesUtil();
    }

    return instance;
  }

  public String verifyArtworkDate(Artwork artwork)
  {

    String result = "";

    try
    {

      if (artwork != null && artwork.getCompletionDate() != null)
      {
        result = this.splitDaySameMonthAndYear(artwork);
      }
      else if (artwork != null)
      {
        result = "10-Jan-" + artwork.getYear() + " 00:00";
      }
    }
    catch (Exception e)
    {
    }

    return result;
  }

  /**
   * @param artwork
   * @return
   */
  private String splitDaySameMonthAndYear(Artwork artwork)
  {
    String result = "";

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(artwork.getCompletionDate());
    int mm = calendar.get(Calendar.MONTH);
    int yy = calendar.get(Calendar.YEAR);
    int gg = calendar.get(Calendar.DATE);

    if (this.mappaComletitionDay.get(yy + "_" + mm) == null)
    {
      mappaComletitionDay.put(yy + "_" + mm, gg);
      result = format.format(artwork.getCompletionDate());
    }
    else
    {
      int newgg = this.mappaComletitionDay.get(yy + "_" + mm) + 1;
      mappaComletitionDay.put(yy + "_" + mm, newgg);

      Calendar calendarNewDay = Calendar.getInstance();
      calendarNewDay.set(Calendar.YEAR, yy);
      calendarNewDay.set(Calendar.MONTH, mm);
      calendarNewDay.set(Calendar.DAY_OF_MONTH, newgg);
      calendarNewDay.set(Calendar.HOUR, 0);
      calendarNewDay.set(Calendar.MINUTE, 0);
      calendarNewDay.set(Calendar.SECOND, 0);
      calendarNewDay.set(Calendar.MILLISECOND, 0);

      mappaComletitionDay.put(calendarNewDay.get(Calendar.YEAR) + "_" + calendarNewDay.get(Calendar.MONTH), newgg);
      result = format.format(calendarNewDay.getTime());
    }

    return result;
  }

  public String verifyDocumentDate(Document document)
  {

    String result = "";

    try
    {

      if (document != null && document.getDate() != null)
      {
        Date documentDate = documentFormat.parse(document.getDate());
        result = format.format(documentDate);
      }
    }
    catch (Exception e)
    {
    }

    return result;
  }

  public void cleanMemory()
  {
    this.mappaComletitionDay.clear();
  }

}
