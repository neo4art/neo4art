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
package org.neo4art.importer.wikipedia.core.listener;

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.graphdb.transaction.GraphDatabaseTransaction;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;
import org.xml.sax.SAXException;

/**
 * Default implementation of the importer listener. It's an aggregator that processes pages in batch mode.
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public abstract class WikipediaAbstractImporterListener implements WikipediaImporterListener {
  
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
  		
  		if (this.wikipediaElementBuffer.size() == this.batchSize) {
  		  logger.info(currentPageCount + " pages parsed so far...");
  		  flush();
  		}
  		
      WikipediaElement wikipediaElement = WikipediaElementTransformer.toWikipediaElement(article);
      
      if (wikipediaElement != null) {
        this.wikipediaElementBuffer.add(wikipediaElement);
      }
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
  
  @Override
  public void flush() {
    
    logger.info(this.getPageCount() + " pages parsed so far... start flushing on graph...");
    
    long graphElementsCreated = 0;
    long flushStartDate = Calendar.getInstance().getTimeInMillis();
    
    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.GRAPH_DATABASE_SERVICE);

    try (GraphDatabaseTransaction tx = graphDatabaseConnectionManager.getTransactionManager()) {
      
      for (WikipediaElement wikipediaElement : this.wikipediaElementBuffer) {
        graphElementsCreated += persist(wikipediaElement);
      }
      
      tx.success();
    }
    
    long flushEndDate = Calendar.getInstance().getTimeInMillis();
    this.graphCount.addAndGet(graphElementsCreated);
    logger.debug(graphElementsCreated + " new graph database elements created for " + this.wikipediaElementBuffer.size() + " wikipedia elements in " + (flushEndDate - flushStartDate) + " ms.");
    this.wikipediaElementBuffer.clear();
  }

  abstract long persist(WikipediaElement wikipediaElement);
}
