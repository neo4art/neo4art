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
package org.neo4art.importer.wikipedia.core;

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;
import org.xml.sax.SAXException;

/**
 * Default implementation of the importer listener.
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
abstract class WikipediaAbstractImporterListener implements WikipediaImporterListener {

  private static Log logger = LogFactory.getLog(WikipediaAbstractImporterListener.class);
  
	protected AtomicLong pageCount;
	protected AtomicLong graphCount;
	
	protected long batchSize;
	
	protected List<WikipediaElement> wikipediaElementBuffer;
	
	protected WikipediaAbstractImporterListener() {
	  
		this.graphCount = new AtomicLong();
		this.pageCount  = new AtomicLong();
		
		this.batchSize = 1;
		
		this.wikipediaElementBuffer  = new ArrayList<WikipediaElement>();
	}
	
	@Override
	public void setBatchSize(long size) {
	  this.batchSize = size;
	}

	@Override
	public void process(WikiArticle article, Siteinfo siteinfo) throws SAXException {

	  if (StringUtils.isNotEmpty(article.getTitle())) {
	    
  		long currentPageCount = pageCount.incrementAndGet();
  		
      if (currentPageCount % 50000 == 0)
  		  logger.trace("Parsed " + currentPageCount + " pages so far...");
  		
  		if (this.wikipediaElementBuffer.size() == this.batchSize)
  		  flush();
  		
  		this.wikipediaElementBuffer.add(WikipediaElementTransformer.toWikipediaElement(article));
	  }
	}

	@Override
	public long getPageCount() {
	  return this.pageCount.longValue();
	}

  @Override
  public long getGraphCount() {
    return this.graphCount.longValue();
  }
  
  public abstract void flush();
}
