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
package org.neo4art.importer.wikipedia.aggregator;

import info.bliki.wiki.dump.WikiArticle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.importer.wikipedia.service.handler.WikipediaDumpElementHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Aggregator of wikipedia articles meant to periodically commit the pages we are acquiring from the dump 
 * 
 * @author Lorenzo Speranzoni Speranzoni
 * @since 25.02.2015
 */
@Component
public class WikipediaDumpElementAggregator implements WikipediaDumpElementHandler {

	private static Log logger = LogFactory.getLog(WikipediaDumpElementAggregator.class);
	
	private static final long bufferSize = 1000;
	
	private List<WikiArticle> aggregatedPageList;
	
	@Autowired
	private WikipediaDumpMultipleElementsHandler wikipediaDumpListElementHandler;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long savePage(WikiArticle page) {
		
		long newNodes = 0;
		
		if (this.aggregatedPageList == null) {
			this.aggregatedPageList = new ArrayList<WikiArticle>();
		}
		
		if (this.aggregatedPageList.size() < bufferSize) {
			this.aggregatedPageList.add(page);
		}
		else {
			logger.debug("Persisting next " + this.aggregatedPageList.size() + " articles...");
			
			newNodes = this.wikipediaDumpListElementHandler.savePages(this.aggregatedPageList);
			this.aggregatedPageList.clear();
		}
		
		return newNodes;
	}

	@Override
	public long flush() {
		
		long newNodes = 0;
		
		if (CollectionUtils.isNotEmpty(aggregatedPageList)) {
			logger.debug("Persisting last " + this.aggregatedPageList.size() + " articles...");
			
			newNodes = this.wikipediaDumpListElementHandler.savePages(aggregatedPageList);
			this.aggregatedPageList.clear();
		}
		
		return newNodes;
	}
}