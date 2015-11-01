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

import org.neo4art.domain.Artist;

/**
 * @author Enrico De Benetti
 * @since 26 Apr 2015
 *
 */
public class BuildMockAGArtists {

	public List<Artist> loadMockArtists() {

		List<Artist> artistList = new ArrayList<Artist>();
		
		Artist artist1 = new Artist();
		artist1.setName("Vincent Van gogh");
		
		Artist artist2 = new Artist();
		artist2.setName("Claude Monet");
		
		artistList.add(artist1);
		artistList.add(artist2);
		
		return artistList;
	}

}
