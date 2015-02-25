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
package org.neo4art.importer.wikipedia.repository;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaLabel;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * It stores Wikipedia data into neo4j via spring-jdbc.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Repository
public class WikipediaJdbcRepository implements WikipediaRepository {

	private static Log logger = LogFactory.getLog(WikipediaJdbcRepository.class);
	
	@Autowired
    private JdbcTemplate template;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long addPage(WikipediaPage wikipediaPage) {
		return mergeNodeWithLabel(wikipediaPage,	WikipediaLabel.WIKIPEDIA_PAGE);
	}		

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long addCategory(WikipediaPage wikipediaPage) {
		return mergeNodeWithLabel(wikipediaPage,	WikipediaLabel.WIKIPEDIA_CATEGORY);
	}		
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long addGeneric(WikipediaPage wikipediaPage) {
		return mergeNodeWithLabel(wikipediaPage,	WikipediaLabel.WIKIPEDIA_GENERIC);
	}		
	
	private long mergeNodeWithLabel(WikipediaPage wikipediaPage, String label) {
		
		logger.trace("Creating node for page " + wikipediaPage.getTitle());
		
		long start = Calendar.getInstance().getTimeInMillis();
		
		String cql = 
				"MERGE (node:" + WikipediaLabel.WIKIPEDIA + " {title:{1}})" +
						" ON CREATE SET node.title={2}," +
									  " node.id={3}," +
									  " node.revision={4}," +
									  " node.namespace={5}," +
									  " node.timestamp={6}," +
									  " node:" + label +
						 " ON MATCH SET node.id={7}," +
									  " node.revision={8}," +
									  " node.namespace={9}," +
									  " node.timestamp={10}," +
									  " node:" + label;
			
		long newNodes = template.update(cql, wikipediaPage.getTitle(), 
                                             wikipediaPage.getTitle(), wikipediaPage.getId(), wikipediaPage.getRevision(), wikipediaPage.getNamespace(), wikipediaPage.getTimestamp(),
                                                                       wikipediaPage.getId(), wikipediaPage.getRevision(), wikipediaPage.getNamespace(), wikipediaPage.getTimestamp());
		
		long end = Calendar.getInstance().getTimeInMillis();
		
		if (end - start > 100) {
			logger.debug("Creating node for page " + wikipediaPage.getTitle() + " took " + (end - start) + " milliseconds.");
		}
		
		return newNodes;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long addLink(WikipediaPage wikipediaPage, String link) {
		
		logger.trace("Adding link from page " + wikipediaPage.getTitle() + " to " + link);
		
		long start = Calendar.getInstance().getTimeInMillis();
		
		String cql =
			"MATCH (page:" + WikipediaLabel.WIKIPEDIA + " {title:{1}}) " +
			"MERGE (link:" + WikipediaLabel.WIKIPEDIA + " {title:{2}}) " +
			"MERGE (page)-[:REFERS]->(link)";
		
		long newNodes = template.update(cql, wikipediaPage.getTitle(), link);

		long end = Calendar.getInstance().getTimeInMillis();
		
		if (end - start > 100) {
			logger.debug("Adding link from page " + wikipediaPage.getTitle() + " to " + link + " took " + (end - start) + " milliseconds.");
		}
		
		return newNodes;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long addCategory(WikipediaPage wikipediaPage, String category) {

		logger.trace("Adding category " + category + " to page " + wikipediaPage.getTitle());
		
		long start = Calendar.getInstance().getTimeInMillis();
		
		String cql =
			"MERGE (page:" + WikipediaLabel.WIKIPEDIA + " {title:{1}}) " +
			"MERGE (category:" + WikipediaLabel.WIKIPEDIA + ":" + WikipediaLabel.WIKIPEDIA_CATEGORY + " {title:{2}}) " +
			"MERGE (page)-[:BELONGS_TO]->(category)";

		long newNodes = template.update(cql, wikipediaPage.getTitle(), category);
		
		long end = Calendar.getInstance().getTimeInMillis();
		
		if (end - start > 100) {
			logger.debug("Adding category " + category + " to page " + wikipediaPage.getTitle() + " took " + (end - start) + " milliseconds.");
		}
		
		return newNodes;
	}
}
