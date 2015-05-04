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
package org.neo4art.api.builder.mock;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.domain.Colour;

/**
 * @author Enrico De Benetti
 * @since 26 Apr 2015
 *
 */
public class BuildMockColours {

	public List<Colour> loadMockColours() {

		List<Colour> colourList = new ArrayList<Colour>();
		
		Colour color1 = new Colour();
		color1.setName("Acid Green");
		Colour color2 = new Colour();
		color2.setName("Red");
		Colour color3 = new Colour();
		color3.setName("White");
		Colour color4 = new Colour();
		color4.setName("Brown");
		Colour color5 = new Colour();
		color5.setName("Yellow");
		
		colourList.add(color1);
		colourList.add(color2);
		colourList.add(color3);
		colourList.add(color4);
		colourList.add(color5);
		
		return colourList;
	}

}
