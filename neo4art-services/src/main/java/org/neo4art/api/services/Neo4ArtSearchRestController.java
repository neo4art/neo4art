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
package org.neo4art.api.services;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.builder.SearchDomainBuilder;
import org.neo4art.api.builder.SearchResultBuilder;
import org.neo4art.api.domain.SearchDomain;
import org.neo4art.api.domain.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Enrico De Benetti
 * @since 25 Apr 2015
 *
 */
@Controller
@RequestMapping("/api/services/search")
public class Neo4ArtSearchRestController {

	
	@RequestMapping(value = "/domains.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SearchDomain> getDomains(Model model) {

		List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
		listaSearchDomain.addAll(SearchDomainBuilder.buildDomainArtists());
		listaSearchDomain.addAll(SearchDomainBuilder.buildDomainArtworks());
		listaSearchDomain.addAll(SearchDomainBuilder.buildDomainMuseums());
		listaSearchDomain.addAll(SearchDomainBuilder.buildDomainColors());
		listaSearchDomain.addAll(SearchDomainBuilder.buildDomainSentiments());
		
		return listaSearchDomain;
	}
	
	@RequestMapping(value = "/search-results.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SearchResult getResults(Model model,
			                                     @RequestParam(value="searchInput", required=true) String searchInput) {

		System.out.println("Input search: "+searchInput);
		
		return SearchResultBuilder.buildSearchResult();
	}
	
	
	@RequestMapping(value = "/detail-node-search.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SearchResult getDetailNodeSearch(Model model,
			                                     @RequestParam(value="nodeId", required=true) Long nodeId) {

		System.out.println("Input nodeId: "+nodeId);
		
		return SearchResultBuilder.buildDetailNodeSearch();
	}
	
	
	
	
	
}
