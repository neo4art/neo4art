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

import java.util.List;

import org.neo4art.api.domain.SearchDomain;

/**
 * @author Enrico De Benetti
 * @since 26 Apr 2015
 *
 */
public class BuildMockVanGoghArtworks {

	public void loadMockArtworks(List<SearchDomain> listaSearchDomain) {

		listaSearchDomain.add(new SearchDomain("Still Life with Cabbage and Clogs"));
		listaSearchDomain.add(new SearchDomain("Crouching Boy with Sickle"));
		listaSearchDomain.add(new SearchDomain("Old Man at the Fireside"));
		listaSearchDomain.add(new SearchDomain("Woman Sewing"));
		listaSearchDomain.add(new SearchDomain("Woman with White Shawl"));
		listaSearchDomain.add(new SearchDomain("Beach at Scheveningen in Calm Weather"));
		listaSearchDomain.add(new SearchDomain("Dunes"));
		listaSearchDomain.add(new SearchDomain("Dunes with Figures"));
		listaSearchDomain.add(new SearchDomain("Beach at Scheveningen in Stormy Weather"));
		listaSearchDomain.add(new SearchDomain("Fisherman on the Beach"));
		listaSearchDomain.add(new SearchDomain("Starry Night Over the Rhone"));
		listaSearchDomain.add(new SearchDomain("Blossoming Acacia Branches"));
	}
}
