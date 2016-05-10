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

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

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
	public void process(WikiArticle article, Siteinfo siteinfo) throws IOException {
	  
    long pages = pageCount.incrementAndGet();
    
    if (pages % 500_000 == 0) {
      logger.info(NumberFormat.getInstance(Locale.ITALY).format(pages) + " wikipedia pages parsed from dump so far...");
    }
    
	  if (StringUtils.isNotBlank(article.getTitle())) {
      
      WikipediaElement wikipediaElement = WikipediaElementTransformer.toWikipediaElement(article);
      
      if (wikipediaElement != null) {
        this.wikipediaElementBuffer.add(wikipediaElement);
      }

      if (this.wikipediaElementBuffer.size() == this.batchSize) {
        if (this.batchSize != WikipediaImporterListener.NO_BUFFER_LIMITS_FOR_FULL_IN_MEMORY_MANAGEMENT) {
          flush();
        }
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
    
    long graphElementsCreated = 0;
    long flushStartDate = Calendar.getInstance().getTimeInMillis();
    
    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.EMBEDDED_DATABASE);

    try (GraphDatabaseTransaction tx = graphDatabaseConnectionManager.getTransaction()) {
      
      for (WikipediaElement wikipediaElement : this.wikipediaElementBuffer) {
        graphElementsCreated += persist(wikipediaElement);
      }
      
      tx.success();
    }
    
    long flushEndDate = Calendar.getInstance().getTimeInMillis();
    this.graphCount.addAndGet(graphElementsCreated);
    logger.debug(NumberFormat.getInstance(Locale.ITALY).format(graphElementsCreated) + " new graph database elements created for " + NumberFormat.getInstance(Locale.ITALY).format(this.wikipediaElementBuffer.size()) + " wikipedia elements in " + (flushEndDate - flushStartDate) + " ms.");
    this.wikipediaElementBuffer.clear();
  }

  protected abstract long persist(WikipediaElement wikipediaElement);
}
