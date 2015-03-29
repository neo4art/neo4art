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
package org.neo4art.importer.wikipedia.service;

import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.neo4art.importer.wikipedia.repository.WikipediaGraphDatabaseServiceRepository;
import org.neo4art.importer.wikipedia.repository.WikipediaRepository;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Service for storing Wikipedia Pages into neo4j.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaPageManager extends WikipediaAbstractElementManager implements WikipediaElementManager {

	@Override
	public long createNodes(GraphDatabaseService graphDatabaseService, WikipediaElement wikipediaElement) {
		
    if (wikipediaElement.getType() != WikipediaType.PAGE) {
      throw new IllegalArgumentException("This method work only with wikipedia categories but you called it with " + wikipediaElement.getType());
    }

    WikipediaRepository wikipediaRepository = new WikipediaGraphDatabaseServiceRepository(graphDatabaseService);
    
    wikipediaRepository.addPage((WikipediaPage) wikipediaElement);

		return 1;
	}
}