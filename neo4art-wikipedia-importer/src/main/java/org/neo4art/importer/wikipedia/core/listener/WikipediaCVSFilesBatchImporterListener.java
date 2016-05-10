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
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;
import org.neo4j.graphdb.Result;

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

/**
 * Default implementation of the importer listener.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaCVSFilesBatchImporterListener extends WikipediaAbstractImporterListener
    implements WikipediaImporterListener {

	private static final String WIKIPEDIA_NODES_CVS_FILE = "wikipedia-nodes.cvs";
	private static final String WIKIPEDIA_LINKS_CVS_FILE = "wikipedia-links.cvs";
	private static final String WIKIPEDIA_CATEGORIES_CVS_FILE = "wikipedia-categories.cvs";
	private static final String WIKIPEDIA_REDIRECTS_CVS_FILE = "wikipedia-redirects.cvs";

	private static Log logger = LogFactory.getLog(WikipediaCVSFilesBatchImporterListener.class);

	private File wikipediaNodesFile;
	private File wikipediaLinksFile;
	private File wikipediaCategoriesFile;
	private File wikipediaRedirectsFile;

	public WikipediaCVSFilesBatchImporterListener(File cvsFileDir) {
		super();

		this.wikipediaNodesFile = new File(cvsFileDir, WIKIPEDIA_NODES_CVS_FILE);
		this.wikipediaLinksFile = new File(cvsFileDir, WIKIPEDIA_LINKS_CVS_FILE);
		this.wikipediaCategoriesFile = new File(cvsFileDir, WIKIPEDIA_CATEGORIES_CVS_FILE);
		this.wikipediaRedirectsFile = new File(cvsFileDir, WIKIPEDIA_REDIRECTS_CVS_FILE);

		this.wikipediaNodesFile.delete();
		this.wikipediaLinksFile.delete();
		this.wikipediaCategoriesFile.delete();
		this.wikipediaRedirectsFile.delete();
	}

	@Override
	public void process(WikiArticle article, Siteinfo siteinfo) throws IOException {

		long pages = pageCount.incrementAndGet();

		if (pages % 500_000 == 0) {
			logger.info(NumberFormat.getInstance(Locale.ITALY).format(pages) + " wikipedia pages parsed from dump so far...");
		}

		if (StringUtils.isNotBlank(article.getTitle())) {

			WikipediaElement wikipediaElement = WikipediaElementTransformer.toWikipediaElement(article);

			FileUtils.writeStringToFile(wikipediaNodesFile, buildWikipediaNodesLine(wikipediaElement), true);

			if (wikipediaElement.getType() == WikipediaType.REDIRECT_PAGE) {
				FileUtils.writeStringToFile(wikipediaRedirectsFile, buildWikipediaRedirectLine(wikipediaElement), true);
			}
			
			if (CollectionUtils.isNotEmpty(wikipediaElement.getLinks())) {
				for (WikipediaElement link : wikipediaElement.getLinks()) {
					FileUtils.writeStringToFile(wikipediaLinksFile, buildWikipediaLinksLine(wikipediaElement, link), true);
				}
			}
			
			if (CollectionUtils.isNotEmpty(wikipediaElement.getCategories())) {
				for (WikipediaElement category : wikipediaElement.getCategories()) {
					FileUtils.writeStringToFile(wikipediaCategoriesFile, buildWikipediaCategoriesLine(wikipediaElement, category),
					    true);
				}
			}
		}
	}

	private String buildWikipediaNodesLine(WikipediaElement wikipediaElement) {
		String line = wikipediaElement.getId() + "," + "\"" + wikipediaElement.getTitle() + "\"" + ","
		    + wikipediaElement.getRevision() + "," + wikipediaElement.getTimestamp();

		for (int l = 0; l < wikipediaElement.getLabels().length; l++) {
			line += "," + "\"" + wikipediaElement.getLabels()[l].name() + "\"";
		}

		line += "\n";
		return line;
	}

	private String buildWikipediaRedirectLine(WikipediaElement wikipediaElement) {
		return "\"" + wikipediaElement.getTitle() + "\"" + "," + "\"" + wikipediaElement.getRedirect().getTitle() + "\"" + "\n";
	}

	private String buildWikipediaLinksLine(WikipediaElement wikipediaElement, WikipediaElement link) {
		return "\"" + wikipediaElement.getTitle() + "\"" + "," + "\"" + link.getTitle() + "\"" + "\n";
	}

	private String buildWikipediaCategoriesLine(WikipediaElement wikipediaElement, WikipediaElement category) {
		return "\"" + wikipediaElement.getTitle() + "\"" + "," + "\"" + category.getTitle() + "\"" + "\n";
	}

	/**
	 * @see org.neo4art.importer.wikipedia.core.listener.WikipediaAbstractImporterListener#flush()
	 */
	@Override
	public void flush() {

		long graphElementsCreated = 0;
		long flushStartDate = Calendar.getInstance().getTimeInMillis();

		GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory
		    .getInstance(GraphDatabaseConnectionType.EMBEDDED_DATABASE);

		graphElementsCreated += loadWikipediaNodesFile(graphDatabaseConnectionManager);
		graphElementsCreated += loadWikipediaLinksFile(graphDatabaseConnectionManager);
		graphElementsCreated += loadWikipediaCategoriesFile(graphDatabaseConnectionManager);
		graphElementsCreated += loadWikipediaRedirectsFile(graphDatabaseConnectionManager);

		long flushEndDate = Calendar.getInstance().getTimeInMillis();
		this.graphCount.addAndGet(graphElementsCreated);
		logger.debug(graphElementsCreated + " new graph database elements created for " + this.wikipediaElementBuffer.size()
		    + " wikipedia elements in " + (flushEndDate - flushStartDate) + " ms.");
		this.wikipediaElementBuffer.clear();
	}

	private int loadWikipediaNodesFile(GraphDatabaseConnectionManager graphDatabaseConnectionManager) {
		
		String cql =
			"USING PERIODIC COMMIT" + "\n" + 
			"LOAD CSV FROM \"file:///" + this.wikipediaNodesFile.getAbsoluteFile() + "\" AS csvLine" + "\n" +
			"CREATE (n:Wikipedia { id: toInt(csvLine[0]), title: csvLine[1], revision: toInt(csvLine[2]), timestamp: toInt(csvLine[3]), type: csvLine[5]})";
		
		System.out.println(cql);
		
		Result result = graphDatabaseConnectionManager.executeCypherQuery(cql, null);
		
		return result.getQueryStatistics().getNodesCreated();
	}
	
	private int loadWikipediaLinksFile(GraphDatabaseConnectionManager graphDatabaseConnectionManager) {
		
		String cql =
			"USING PERIODIC COMMIT" + "\n" + 
			"LOAD CSV FROM \"file:///" + this.wikipediaLinksFile.getAbsoluteFile() + "\" AS csvLine" + "\n" +
			"MATCH (n:Wikipedia { title: csvLine[0] }), (m:Wikipedia { title: csvLine[1] })" + "\n" +
			"CREATE (n)-[:" + WikipediaRelationship.REFERS.name() + "]->(m)";
		
		System.out.println(cql);
		
		Result result = graphDatabaseConnectionManager.executeCypherQuery(cql, null);
		
		return result.getQueryStatistics().getRelationshipsCreated(); 
	}

	private int loadWikipediaCategoriesFile(GraphDatabaseConnectionManager graphDatabaseConnectionManager) {
		
		String cql =
			"USING PERIODIC COMMIT" + "\n" + 
			"LOAD CSV FROM \"file:///" + this.wikipediaCategoriesFile.getAbsoluteFile() + "\" AS csvLine" + "\n" +
			"MATCH (n:Wikipedia { title: csvLine[0] }), (m:Wikipedia { title: csvLine[1] })" + "\n" +
			"CREATE (n)-[:" + WikipediaRelationship.BELONGS_TO.name() + "]->(m)";
		
		System.out.println(cql);
		
		Result result = graphDatabaseConnectionManager.executeCypherQuery(cql, null);
		
		return result.getQueryStatistics().getRelationshipsCreated();
	}
	
	private int loadWikipediaRedirectsFile(GraphDatabaseConnectionManager graphDatabaseConnectionManager) {
		
		String cql =
			"USING PERIODIC COMMIT" + "\n" + 
			"LOAD CSV FROM \"file:///" + this.wikipediaRedirectsFile.getAbsoluteFile() + "\" AS csvLine" + "\n" +
			"MATCH (n:Wikipedia { title: csvLine[0] }), (m:Wikipedia { title: csvLine[1] })" + "\n" +
			"CREATE (n)-[:" + WikipediaRelationship.REDIRECTS_TO.name() + "]->(m)";
		
		System.out.println(cql);
		
		Result result = graphDatabaseConnectionManager.executeCypherQuery(cql, null);
		
		return result.getQueryStatistics().getRelationshipsCreated();
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
