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
package org.neo4art.importer.wikipedia.manager;

import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.springframework.stereotype.Component;

/**
 * Factory class for specific Wikipedia Pages (Main, Category, Project, Template, etc).
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Component
public class WikipediaElementManagerFactory {

	public static WikipediaElementManager getInstance(WikipediaType wikipediaType) {
	  
	  switch (wikipediaType) {
	    case PAGE:
	      return new WikipediaPageManager();
	    case ARTIST_PAGE:
	      return new WikipediaArtistPageManager();
	    case ARTWORK_PAGE:
	      return new WikipediaArtworkPageManager();
	    case ART_MOVEMENT_PAGE:
	      return new WikipediaArtMovementPageManager();
	    case MUSEUM_PAGE:
	      return new WikipediaMuseumPageManager();
	    case MONUMENT_PAGE:
	      return new WikipediaMonumentPageManager();
	    case RELIGIOUS_BUILDING_PAGE:
	      return new WikipediaReligiousBuildingPageManager();
	      
	    case SETTLEMENT_PAGE:
	      return new WikipediaSettlementPageManager();
	    case COUNTRY_PAGE:
	      return new WikipediaCountryPageManager();
	      
	    case CATEGORY:
	      return new WikipediaCategoryManager();
	    case FILE:
	      return new WikipediaFileManager();
	    case PROJECT:
	      return new WikipediaProjectManager();
	    case TEMPLATE:
	      return new WikipediaTemplateManager();
	    case GENERIC:
	    default:
	      return new WikipediaGenericManager();
	      
	  }
	}
}
