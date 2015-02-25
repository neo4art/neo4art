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
package org.neo4art.importer.wikipedia.service.handler;

import java.util.Calendar;

import info.bliki.wiki.dump.WikiArticle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.repository.WikipediaRepository;
import org.neo4art.importer.wikipedia.transformer.WikipediaDumpElementTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for storing Wikipedia Pages into neo4j.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Component
public class WikipediaPageHandler implements WikipediaDumpElementHandler {

	private static Log logger = LogFactory.getLog(WikipediaPageHandler.class);
	
	@Autowired
	private WikipediaRepository wikipediaRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long savePage(WikiArticle page) {
		
		long newNodes = 0;

		long start = Calendar.getInstance().getTimeInMillis();
		
		WikipediaPage wikipediaPage = WikipediaDumpElementTransformer.toWikipediaPage(page);
		
		newNodes += this.wikipediaRepository.addPage(wikipediaPage);
		
		long node = Calendar.getInstance().getTimeInMillis();

		if (CollectionUtils.isNotEmpty(wikipediaPage.getLinks())) {
			for (String link : wikipediaPage.getLinks()) {
				newNodes += this.wikipediaRepository.addLink(wikipediaPage, link);
			}
		}
		
		long links = Calendar.getInstance().getTimeInMillis();

		if (CollectionUtils.isNotEmpty(wikipediaPage.getCategories())) {
			for (String category : wikipediaPage.getCategories()) {
				newNodes += this.wikipediaRepository.addCategory(wikipediaPage, category);
			}
		}

		long end = Calendar.getInstance().getTimeInMillis();
		
		logger.debug("Creation of node for page " + wikipediaPage.getTitle() + " took " + (end - start) + " ms: " 
		                                          + ((wikipediaPage.getLinks() != null) ? wikipediaPage.getLinks().size() : 0) + " lynks in " + (links - node) + " ms, "
		                                          + ((wikipediaPage.getCategories() != null) ? wikipediaPage.getCategories().size() : 0) + " categories in " + (end - links) + " ms.");
		
		return newNodes;
	}

	@Override
	public long flush() {
		// Intended for aggretated implementation only
		return 0;
	}
}