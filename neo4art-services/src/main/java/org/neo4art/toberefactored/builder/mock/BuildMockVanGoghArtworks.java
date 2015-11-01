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

import org.neo4art.domain.Artwork;

/**
 * @author Enrico De Benetti
 * @since 26 Apr 2015
 *
 */
public class BuildMockVanGoghArtworks {

	public List<Artwork> loadMockArtworks() {

		List<Artwork> artworkList = new ArrayList<Artwork>();
		
		Artwork artwork2 = new Artwork();
		artwork2.setTitle("Still Life with Cabbage and Clogs");
		Artwork artwork3 = new Artwork();
		artwork3.setTitle("Crouching Boy with Sickle");
		Artwork artwork4 = new Artwork();
		artwork4.setTitle("Old Man at the Fireside");
		Artwork artwork5 = new Artwork();
		artwork5.setTitle("Woman Sewing");
		Artwork artwork6 = new Artwork();
		artwork6.setTitle("Woman with White Shawl");
		Artwork artwork7 = new Artwork();
		artwork7.setTitle("Beach at Scheveningen in Calm Weather");
		Artwork artwork8 = new Artwork();
		artwork8.setTitle("Dunes");
		Artwork artwork9 = new Artwork();
		artwork9.setTitle("Dunes with Figures");
		Artwork artwork10 = new Artwork();
		artwork10.setTitle("Beach at Scheveningen in Stormy Weather");
		Artwork artwork11 = new Artwork();
		artwork11.setTitle("Fisherman on the Beach");
		Artwork artwork12 = new Artwork();
		artwork12.setTitle("Starry Night Over the Rhone");
		Artwork artwork13 = new Artwork();
		artwork13.setTitle("Blossoming Acacia Branches");
		
		artworkList.add(artwork2);
		artworkList.add(artwork3);
		artworkList.add(artwork4);
		artworkList.add(artwork5);
		artworkList.add(artwork6);
		artworkList.add(artwork7);
		artworkList.add(artwork8);
		artworkList.add(artwork9);
		artworkList.add(artwork10);
		artworkList.add(artwork11);
		artworkList.add(artwork12);
		artworkList.add(artwork13);
		
	 return artworkList;	
	}
}
