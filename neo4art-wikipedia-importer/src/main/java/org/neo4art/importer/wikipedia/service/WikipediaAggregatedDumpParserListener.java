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

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

import java.util.concurrent.atomic.AtomicLong;

import org.neo4art.importer.wikipedia.service.handler.WikipediaDumpElementHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

/**
 * Aggregated implementation of the observer.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Component
public class WikipediaAggregatedDumpParserListener implements WikipediaDumpParserListener {

	private AtomicLong pageCount;
	
	@Autowired
	@Qualifier("wikipediaDumpElementAggregator")
	private WikipediaDumpElementHandler wikipediaDumpElementAggregator;
	
	public WikipediaAggregatedDumpParserListener() {
		this.pageCount = new AtomicLong();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void process(WikiArticle page, Siteinfo siteinfo) throws SAXException {

		pageCount.incrementAndGet();
		
		this.wikipediaDumpElementAggregator.savePage(page);
	}

	@Override
	public void flush() {
		this.wikipediaDumpElementAggregator.flush();
	}
	
	@Override
	public long getPageCount() {
	  return this.pageCount.longValue();
	}
}
