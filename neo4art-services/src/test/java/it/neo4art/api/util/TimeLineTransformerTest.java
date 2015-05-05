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
package it.neo4art.api.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.api.domain.TimelineEvent;
import org.neo4art.api.transformer.TimeLineTransformer;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.manager.ArtworksColoursAnalyzer;
import org.neo4art.colour.manager.ArtworksDefaultColoursAnalyzer;
import org.neo4art.domain.Artist;

/**
 * @author Enrico De Benetti
 * @since 02 Mag 2015
 *
 */
public class TimeLineTransformerTest {

  @Test
  public void testBuildTimeLineEvents(){
	  
	  ArtworksColoursAnalyzer artworksDefaultColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();
      Artist artist = new Artist();
	  artist.setName("Van Gogh");
	  List<ColourAnalysis> colourAnalysisByArtist = artworksDefaultColoursAnalyzer.getColourAnalysisByArtist(artist);	
	  
	  List<TimelineEvent> buildTimeLineEvents = TimeLineTransformer.buildTimeLineEvents(colourAnalysisByArtist);
	  Assert.assertNotNull(buildTimeLineEvents);
	  Assert.assertEquals(703, buildTimeLineEvents.size());
	  
	  
	  for (TimelineEvent timelineEvent : buildTimeLineEvents)
    {
      System.out.println("Start: "+timelineEvent.getStart()+" artwork: "+timelineEvent.getDescription());
    }
	  
  }
	
}
