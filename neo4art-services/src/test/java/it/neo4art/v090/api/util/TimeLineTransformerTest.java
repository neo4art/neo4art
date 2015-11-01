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
package it.neo4art.v090.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.manager.ArtworksColoursAnalyzer;
import org.neo4art.colour.manager.ArtworksDefaultColoursAnalyzer;
import org.neo4art.domain.Artist;
import org.neo4art.toberefactored.domain.TimelineEvent;
import org.neo4art.toberefactored.transformer.TimeLineTransformer;

/**
 * @author Enrico De Benetti
 * @since 02 Mag 2015
 *
 */
public class TimeLineTransformerTest
{

  @Test
  @Ignore
  public void testBuildTimeLineEvents() throws ParseException
  {

    ArtworksColoursAnalyzer artworksDefaultColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();
    Artist artist = new Artist();
    artist.setName("Vincent van Gogh");
    List<ColourAnalysis> colourAnalysisByArtist = artworksDefaultColoursAnalyzer.getColourAnalysisByArtist(artist);

    List<TimelineEvent> buildTimeLineEvents = TimeLineTransformer.buildTimeLineEvents(colourAnalysisByArtist);
    Assert.assertNotNull(buildTimeLineEvents);
    Assert.assertEquals(827, buildTimeLineEvents.size());
    System.out.println("Total: "+buildTimeLineEvents.size());
    
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
    Date firstDate = formatDate.parse(buildTimeLineEvents.get(0).getStart());

    List<String> error=new ArrayList<String>();
    
    
    for (TimelineEvent timelineEvent : buildTimeLineEvents)
    {

      Date parse = formatDate.parse(timelineEvent.getStart());
      if (parse.after(firstDate))
      {

        firstDate = parse;
      }
      else
      {
        error.add( "Le date: " + firstDate.toString() + " e " + parse.toString() + " non sono ordinate. Painting: "+timelineEvent.getDescription());
      }
      
      System.out.println("Start: "+timelineEvent.getStart()+" artwork: "+timelineEvent.getDescription());
    }
    
    if(error.size() > 1)
    {
      for (String errore : error)
      {
        System.out.println("ERROR: "+errore);
      }  
    }
    

    Assert.assertEquals(1, error.size());
    
  }
  
  @Test
  public void dateNotOrdinated() throws ParseException{
    
    String date1="07-Jan-1887 12:00";
    String date2="08-Jan-1887 12:00";
    String date3="09-Jan-1887 12:00";
    String date4="02-Jan-1887 12:00";
   
    List<String> listaDate = new ArrayList<String>();
    listaDate.add(date1);
    listaDate.add(date2);
    listaDate.add(date3);
    listaDate.add(date4);
    
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
    Date firstDate = formatDate.parse(listaDate.get(0));

    String error=null;
    
    for (String timelineEvent : listaDate)
    {

      Date parse = formatDate.parse(timelineEvent);
      if (parse.after(firstDate) || parse.equals(firstDate))
      {

        firstDate = parse;
      }
      else
      {
        error= "Le date: " + firstDate.toString() + " e " + parse.toString() + " non sono ordinate.";
      }
      
      System.out.println("Start: "+timelineEvent);
    }
    
    Assert.assertNotNull(error, error);
  }

}
