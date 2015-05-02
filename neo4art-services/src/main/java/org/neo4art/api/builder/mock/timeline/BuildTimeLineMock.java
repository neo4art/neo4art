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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.domain.Artwork;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class BuildTimeLineMock {

	
	public List<ColourAnalysis> getColourAnalysis(){
		
		List<ColourAnalysis> colourAnalysisList = new ArrayList<ColourAnalysis>();
        
		ColourAnalysis colourAnalysis1 = new ColourAnalysis();
		colourAnalysis1.setAverageColour(new Color(197,215,242));
		colourAnalysis1.setSource("http://www.vggallery.com/painting/f_0001.jpg");
		Artwork artwork1 = new Artwork();
		artwork1.setTitle("Still Life with Cabbage and Clogs");
		artwork1.setYear("1881");
		colourAnalysis1.setArtwork(artwork1);
		
		ColourAnalysis colourAnalysis2 = new ColourAnalysis();
		colourAnalysis2.setAverageColour(new Color(4,135,213));
		colourAnalysis2.setSource("http://www.vggallery.com/painting/f_0018.jpg");
		Artwork artwork2 = new Artwork();
		artwork2.setTitle("Farmhouses Among Trees");
		
		Calendar calendar2 = Calendar.getInstance();  
		calendar2.set(Calendar.YEAR, 1883);  
		calendar2.set(Calendar.MONTH, 8);  
		calendar2.set(Calendar.DAY_OF_MONTH, 14);  
		calendar2.set(Calendar.HOUR,12);
		calendar2.set(Calendar.MINUTE,0);
		
		artwork2.setCompletionDate(calendar2.getTime());
		colourAnalysis2.setArtwork(artwork2);
		
		ColourAnalysis colourAnalysis3 = new ColourAnalysis();
		colourAnalysis3.setAverageColour(new Color(77,211,18));
		colourAnalysis3.setSource("http://www.vggallery.com/painting/f_0216i.jpg");
		Artwork artwork3 = new Artwork();
		artwork3.setTitle("Plaster Statuette of a Female Torso");
		
		Calendar calendar3 = Calendar.getInstance();  
		calendar3.set(Calendar.YEAR, 1886);  
		calendar3.set(Calendar.MONTH, 2);  
		calendar3.set(Calendar.DAY_OF_MONTH, 9);  
		calendar3.set(Calendar.HOUR,12);
		calendar3.set(Calendar.MINUTE,0);
		
		artwork3.setCompletionDate(calendar3.getTime());
		colourAnalysis3.setArtwork(artwork3);
		
		ColourAnalysis colourAnalysis4 = new ColourAnalysis();
		colourAnalysis4.setAverageColour(new Color(141,115,245));
		colourAnalysis4.setSource("http://www.vggallery.com/painting/f_0273.jpg");
		Artwork artwork4 = new Artwork();
		artwork4.setTitle("Le Moulin de Blute-Fin");
		
		Calendar calendar4 = Calendar.getInstance();  
		calendar4.set(Calendar.YEAR, 1886);  
		calendar4.set(Calendar.MONTH, 5);  
		calendar4.set(Calendar.DAY_OF_MONTH, 9);  
		calendar4.set(Calendar.HOUR,12);
		calendar4.set(Calendar.MINUTE,0);
		
		artwork4.setCompletionDate(calendar4.getTime());
		colourAnalysis4.setArtwork(artwork4);
		
		colourAnalysisList.add(colourAnalysis1);
		colourAnalysisList.add(colourAnalysis2);
		colourAnalysisList.add(colourAnalysis3);
		colourAnalysisList.add(colourAnalysis4);
		
	  return colourAnalysisList;	
	}
	
}
