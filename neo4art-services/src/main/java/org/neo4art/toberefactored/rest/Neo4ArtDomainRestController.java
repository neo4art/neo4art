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
package org.neo4art.toberefactored.rest;

import java.util.List;

import org.neo4art.toberefactored.domain.SearchDomain;
import org.neo4art.toberefactored.transformer.SearchDomainTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lorenzo Speranzoni, Enrico De Benetti
 * @since 25 Apr 2015
 *
 */
@Controller
@RequestMapping("/")
public class Neo4ArtDomainRestController {

  @RequestMapping(value = "/domains.json", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<SearchDomain> getDomains(Model model) {

    return SearchDomainTransformer.buildDomains();
  }

  @RequestMapping(value = "/artists", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<SearchDomain> getArtistsDomain(Model model) {

    return SearchDomainTransformer.buildDomainArtists();
  }

  @RequestMapping(value = "/artworks", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<SearchDomain> getArtworkDomain(Model model) {

    return SearchDomainTransformer.buildDomainArtworks();
  }

  @RequestMapping(value = "/museums", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<SearchDomain> getMuseumsDomain(Model model) {

    return SearchDomainTransformer.buildDomainMuseums();
  }

  @RequestMapping(value = "/colours", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<SearchDomain> getColoursDomain(Model model) {

    return SearchDomainTransformer.buildDomainColors();
  }

  @RequestMapping(value = "/sentiments", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<SearchDomain> getSentimentsDomain(Model model) {

    return SearchDomainTransformer.buildDomainSentiments();
  }
}