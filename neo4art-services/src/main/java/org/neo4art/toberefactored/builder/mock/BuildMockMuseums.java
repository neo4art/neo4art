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
package org.neo4art.toberefactored.builder.mock;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.domain.Museum;

/**
 * @author Enrico De Benetti
 * @since 26 Apr 2015
 *
 */
public class BuildMockMuseums {

	public List<Museum> loadMockMuseums() {

	 List<Museum> museumList = new ArrayList<Museum>();
		
	 Museum museum1 = new Museum();
	 museum1.setName("Museum of Modern Art of Algiers");
	 	
	 Museum museum2 = new Museum();
	 museum2.setName("Museum of Popular Arts and Traditions");
		
	 Museum museum3 = new Museum();
	 museum3.setName("National Museum of Fine Arts of Algiers");
		
	 museumList.add(museum1);
	 museumList.add(museum2);
	 museumList.add(museum3);
				
	 return museumList;	
	}
}
