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
package org.neo4art.api.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.api.domain.TimelineEvent;
import org.neo4art.api.util.ServicesUtil;
import org.neo4art.colour.domain.ColourAnalysis;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class TimeLineTransformer {

	/**
	 * 
	 * @return
	 */
	public static List<TimelineEvent> buildTimeLineEvents(List<ColourAnalysis> colourAnalysisByArtist){
		
	 List<TimelineEvent> timelineEventsList = new ArrayList<TimelineEvent>();
	 ServicesUtil servicesUtil = ServicesUtil.getInstance();
	 
	 if(!CollectionUtils.isEmpty(colourAnalysisByArtist)){

      for (ColourAnalysis colourAnalysis : colourAnalysisByArtist) {
    	  
       TimelineEvent timelineEvent = new TimelineEvent(); 
       timelineEvent.setAverageRgb(colourAnalysis.getHexaDecimalAverageColor());
       timelineEvent.setClosestAverageColorName(colourAnalysis.getAverageClosestColour() != null ? colourAnalysis.getAverageClosestColour().getName() : "");
       timelineEvent.setDescription(colourAnalysis.getArtwork() != null ? colourAnalysis.getArtwork().getTitle() : "");
       timelineEvent.setStart(servicesUtil.verifyArtworkDate(colourAnalysis.getArtwork()));
       timelineEvent.setThumbnail(colourAnalysis.getSource());
       //TODO DA ELIMINARE..
       timelineEvent.setEmotion("smile");
       
       timelineEventsList.add(timelineEvent);
	  }  
	 }
      
	 return timelineEventsList;
	}
	
	
}
