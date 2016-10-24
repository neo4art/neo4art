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

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

/**
 * Default implementation of the importer listener.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaCVSImportBatchImporterListener extends WikipediaAbstractImporterListener
    implements WikipediaImporterListener {

	private static final String WIKIPEDIA_NODES_CVS_FILE = "wikipedia-nodes.csv";
	private static final String WIKIPEDIA_RELS_CVS_FILE = "wikipedia-rels.csv";

	private static Log logger = LogFactory.getLog(WikipediaCVSImportBatchImporterListener.class);

	private File wikipediaNodesFile;
	private File wikipediaRelsFile;

	private boolean firstRound;
	private boolean initialized;

	private HashMap<String, Long> titleMap;

	/**
	 * 
	 * @param cvsFileDir
	 */
	public WikipediaCVSImportBatchImporterListener(File cvsFileDir) {
		super();

		initFiles(cvsFileDir);

		this.firstRound = true;

		this.titleMap = new HashMap<String, Long>();
	}

	/**
	 * 
	 * @param cvsFileDir
	 */
	private void initFiles(File cvsFileDir) {

		try {

			this.wikipediaNodesFile = new File(cvsFileDir, WIKIPEDIA_NODES_CVS_FILE);
			this.wikipediaRelsFile = new File(cvsFileDir, WIKIPEDIA_RELS_CVS_FILE);

			this.wikipediaNodesFile.delete();
			this.wikipediaRelsFile.delete();

			FileUtils.writeStringToFile(wikipediaNodesFile, "id:ID,title,revision,timestamp,:LABEL\n", true);
			FileUtils.writeStringToFile(wikipediaRelsFile, ":START_ID,:END_ID,:TYPE\n", true);

			this.initialized = true;

		} catch (Exception e) {

			this.initialized = false;
		}

	}

	/**
	 * @see org.neo4art.importer.wikipedia.core.listener.WikipediaAbstractImporterListener#process(info.bliki.wiki.dump.WikiArticle,
	 *      info.bliki.wiki.dump.Siteinfo)
	 */
	@Override
	public void process(WikiArticle article, Siteinfo siteinfo) throws IOException {

		if (!this.initialized) {

			throw new IOException("Unable to create CVS files");
		} else {

			long pages = pageCount.incrementAndGet();

			if (pages % 500_000 == 0) {
				logger
				    .info(NumberFormat.getInstance(Locale.ITALY).format(pages) + " wikipedia pages parsed from dump so far...");
			}

			if (StringUtils.isNotBlank(article.getTitle())) {
				if (this.firstRound) {
					createNodes(article, siteinfo);
				} else {
					createRels(article, siteinfo);
				}
			}
		}
	}

	/**
	 * 
	 * @param article
	 * @param siteinfo
	 * @throws IOException
	 */
	private void createNodes(WikiArticle article, Siteinfo siteinfo) throws IOException {

		WikipediaElement wikipediaElement = WikipediaElementTransformer.toWikipediaElement(article);

		FileUtils.writeStringToFile(wikipediaNodesFile, buildWikipediaNodesLine(wikipediaElement), true);

		this.titleMap.put(wikipediaElement.getTitle(), wikipediaElement.getId());
	}

	/**
	 * 
	 * @param article
	 * @param siteinfo
	 * @throws IOException
	 */
	private void createRels(WikiArticle article, Siteinfo siteinfo) throws IOException {

		WikipediaElement wikipediaElement = WikipediaElementTransformer.toWikipediaElement(article);

		if (wikipediaElement.getType() == WikipediaType.REDIRECT_PAGE) {
			FileUtils.writeStringToFile(wikipediaRelsFile,
			    buildWikipediaRelsLine(wikipediaElement, wikipediaElement.getRedirect(), WikipediaRelationship.REDIRECTS_TO),
			    true);
		}

		if (CollectionUtils.isNotEmpty(wikipediaElement.getLinks())) {
			for (WikipediaElement link : wikipediaElement.getLinks()) {
				FileUtils.writeStringToFile(wikipediaRelsFile,
				    buildWikipediaRelsLine(wikipediaElement, link, WikipediaRelationship.REFERS), true);
			}
		}

		if (CollectionUtils.isNotEmpty(wikipediaElement.getCategories())) {
			for (WikipediaElement category : wikipediaElement.getCategories()) {
				FileUtils.writeStringToFile(wikipediaRelsFile,
				    buildWikipediaRelsLine(wikipediaElement, category, WikipediaRelationship.BELONGS_TO), true);
			}
		}
	}

	/**
	 * 
	 * @param wikipediaElement
	 * @return
	 */
	private String buildWikipediaNodesLine(WikipediaElement wikipediaElement) {

		String title = wikipediaElement.getTitle();
		
		if (title.endsWith("\\"))
			title = title + "\\";
		
		title = title.replace("\n", "");
		title = title.replace("\r", "");
		title = title.replace("\"", "\\\"");
		
		String line = wikipediaElement.getId() + "," + "\"" + title + "\""
		    + "," + wikipediaElement.getRevision() + "," + wikipediaElement.getTimestamp();

		for (int l = 0; l < wikipediaElement.getLabels().length; l++) {
			line += (l == 0) ? "," : ";";
			line += wikipediaElement.getLabels()[l].name();
		}

		line += "\n";

		return line;
	}

	private String buildWikipediaRelsLine(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo,
	    WikipediaRelationship wikipediaRelationship) {

		Long start = this.titleMap.get(wikipediaElementFrom.getTitle());
		Long end = this.titleMap.get(wikipediaElementTo.getTitle());

		if (start != null && end != null) {
			return start + "," + end + "," + wikipediaRelationship.name() + "\n";
		} else {
			return "";
		}
	}

	/**
	 * @see org.neo4art.importer.wikipedia.core.listener.WikipediaAbstractImporterListener#flush()
	 */
	@Override
	public void flush() {

		long pages = pageCount.get();

		logger.info(NumberFormat.getInstance(Locale.ITALY).format(pages) + " wikipedia pages parsed from dump so far...");

		this.firstRound = false;

		this.pageCount = new AtomicLong();
	}

	/**
	 * @see org.neo4art.importer.wikipedia.core.listener.WikipediaAbstractImporterListener#persist(org.neo4art.importer.wikipedia.domain.WikipediaElement)
	 */
	@Override
	protected long persist(WikipediaElement wikipediaElement) {

		throw new RuntimeException(new IllegalAccessException(
		    "Method not allowed in this implementation. Use 'persistNodes' and 'persistRepationships' instead."));
	}
}
