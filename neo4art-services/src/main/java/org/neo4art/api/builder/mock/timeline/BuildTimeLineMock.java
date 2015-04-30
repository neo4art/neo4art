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
package org.neo4art.api.builder.mock.timeline;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.domain.TimelineEvent;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class BuildTimeLineMock {

	
	public List<TimelineEvent> getTimeLineList(){
		
        List<TimelineEvent> listaTimelineEvent = new ArrayList<TimelineEvent>();
		
		TimelineEvent event1= new TimelineEvent();
		event1.setAverageRgb("C5D7F2");
		event1.setDescription("Still Life with Cabbage and Clogs");
		event1.setEmotion("smile");
		event1.setStart("12-Dec-1881 00:00");
		event1.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
		
		TimelineEvent event2= new TimelineEvent();
		event2.setAverageRgb("0487D5");
		event2.setDescription("Farmhouses Among Trees");
		event2.setEmotion("meh");
		event2.setStart("15-sep-1883 00:00");
		event2.setThumbnail("http://www.vggallery.com/painting/f_0018.jpg");
		
		TimelineEvent event3= new TimelineEvent();
		event3.setAverageRgb("4DD312");
		event3.setDescription("Plaster Statuette of a Female Torso");
		event3.setEmotion("frown");
		event3.setStart("10-mar-1886 00:00");
		event3.setThumbnail("http://www.vggallery.com/painting/f_0216i.jpg");
		
		TimelineEvent event4= new TimelineEvent();
		event4.setAverageRgb("8D73F5");
		event4.setDescription("Le Moulin de Blute-Fin");
		event4.setEmotion("smile");
		event4.setStart("10-jun-1886 00:00");
		event4.setThumbnail("http://www.vggallery.com/painting/f_0273.jpg");
		
		TimelineEvent event5= new TimelineEvent();
		event5.setAverageRgb("9D5F66");
		event5.setDescription("Still Life: Potatoes in a Yellow Dish");
		event5.setEmotion("meh");
		event5.setStart("10-mar-1888 00:00");
		event5.setThumbnail("http://www.vggallery.com/painting/f_0386.jpg");
		
		TimelineEvent event6= new TimelineEvent();
		event6.setAverageRgb("23D56F");
		event6.setDescription("Langlois Bridge at Arles, The");
		event6.setEmotion("frown");
		event6.setStart("5-apr-1888 00:00");
		event6.setThumbnail("http://www.vggallery.com/painting/f_0571.jpg");
		
		TimelineEvent event7= new TimelineEvent();
		event7.setAverageRgb("15D7F4");
		event7.setDescription("Lane near Arles, A");
		event7.setEmotion("smile");
		event7.setStart("10-may-1888 00:00");
		event7.setThumbnail("http://www.vggallery.com/painting/f_0567.jpg");
		
		TimelineEvent event8= new TimelineEvent();
		event8.setAverageRgb("65F1A3");
		event8.setDescription("Seated Zouave, The");
		event8.setEmotion("meh");
		event8.setStart("10-jun-1888 00:00");
		event8.setThumbnail("http://www.vggallery.com/painting/f_0424.jpg");
		
		TimelineEvent event9= new TimelineEvent();
		event9.setAverageRgb("34F7E2");
		event9.setDescription("La Mousmé, Sitting");
		event9.setEmotion("frown");
		event9.setStart("10-jul-1888 00:00");
		event9.setThumbnail("http://www.vggallery.com/painting/f_0431.jpg");
		
		TimelineEvent event10= new TimelineEvent();
		event10.setAverageRgb("D543FF");
		event10.setDescription("Little Arlesienne, The");
		event10.setEmotion("smile");
		event10.setStart("10-jun-1890 00:00");
		event10.setThumbnail("http://www.vggallery.com/painting/f_0518.jpg");
		
		listaTimelineEvent.add(event1);
		listaTimelineEvent.add(event2);
		listaTimelineEvent.add(event3);
		listaTimelineEvent.add(event4);
		listaTimelineEvent.add(event5);
		listaTimelineEvent.add(event6);
		listaTimelineEvent.add(event7);
		listaTimelineEvent.add(event8);
		listaTimelineEvent.add(event9);
		listaTimelineEvent.add(event10);
		
	  return listaTimelineEvent;	
	}
	
}
