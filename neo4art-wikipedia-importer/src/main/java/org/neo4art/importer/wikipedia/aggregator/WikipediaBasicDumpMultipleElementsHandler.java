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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.importer.wikipedia.service.handler.WikipediaDumpElementHandler;
import org.neo4art.importer.wikipedia.service.handler.WikipediaDumpElementHandlerFactory;
import org.neo4art.importer.wikipedia.util.WikipediaDumpUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * It opens a new transaction to persist the list of wikipedia articles it receives.
 * It works in combination with the aggregator {@link WikipediaDumpElementAggregator}.
 * 
 * @author Lorenzo Speranzoni Speranzoni
 * @since 25.02.2015
 */
@Component
public class WikipediaBasicDumpMultipleElementsHandler implements WikipediaDumpMultipleElementsHandler {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public long savePages(List<WikiArticle> pages) {
		long newNodes = 0;
		if (CollectionUtils.isNotEmpty(pages)) {
			for (WikiArticle page : pages) {
				WikipediaDumpElementHandler wikipediaDumpElementHandler =
						WikipediaDumpElementHandlerFactory.getInstance(
								WikipediaDumpUtil.toWikipediaDumpElementType(page));
				
				newNodes += wikipediaDumpElementHandler.savePage(page);
			}
		}
		return newNodes;
	}
}
