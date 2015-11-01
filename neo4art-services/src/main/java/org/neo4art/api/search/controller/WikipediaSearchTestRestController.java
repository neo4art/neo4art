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
package org.neo4art.api.search.controller;

import org.neo4art.api.search.bean.WikipediaSearchResult;
import org.neo4art.api.search.bean.WikipediaSearchResultNode;
import org.neo4art.api.search.repository.WikipediaSearchGraphDatabaseServiceRepository;
import org.neo4art.api.search.repository.WikipediaSearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lorenzo Speranzoni
 * @since 25 Apr 2015
 *
 */
@Controller
@RequestMapping("/api/search/test/wikipedia")
public class WikipediaSearchTestRestController {

  @RequestMapping(value = "/{title}", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody WikipediaSearchResult searchOnWikipediaByTitle(@PathVariable(value = "title") String title) {

    WikipediaSearchResultNode node = new WikipediaSearchResultNode();
    node.setId(20814);
    node.setName("Vincent van Gogh");
    node.setType("Wikipedia");
    node.setGroup(1);
    
    WikipediaSearchResult wikipediaSearchResult = new WikipediaSearchResult();
    wikipediaSearchResult.addNode(node);
    
    return wikipediaSearchResult;
  }
  
  @RequestMapping(value = "/{title}/expand/{nodeId}", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody WikipediaSearchResult expandWikipediaNode(@PathVariable(value = "title") String title, @PathVariable(value = "nodeId") int nodeId) {

    WikipediaSearchRepository wikipediaSearchRepository = new WikipediaSearchGraphDatabaseServiceRepository();
    
    return wikipediaSearchRepository.findDepthOneConnectionsByNodeId(nodeId, true);
    
  }
}