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

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graph.util.Neo4ArtGraphDatabaseConnectionFactory;
import org.neo4art.importer.wikipedia.core.WikipediaDefaultImporter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.io.fs.FileUtils;

/**
 * It tests the import of a Wikimedia Dump.
 * 
 * Dump samples are contained in directory src/test/resources. 
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaServiceTest {

	@Test
	public void shouldParseWikipediaDumpFile() {
	  
		try {
		  
		  FileUtils.deleteRecursively(new File(Neo4ArtGraphDatabaseConnectionFactory.NEO4J_STORE_DIR));
		  
			//File dumpFile = new File("src/test/resources", "enwiki-20150112-pages-articles-multistream-test-3000000.xml");
			File dumpFile = new File("/Users/lorenzo/Progetti/Neo4j/projects/neo4art/application/performance/wikipedia-import", "enwiki-20150112-pages-articles-multistream-test-3000000.xml");
			
			long newNodesAndRelationships = new WikipediaDefaultImporter().importOrUpdateDump(dumpFile);
			
			GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseConnectionFactory.getInstance();
			
			try (Transaction tx = graphDatabaseService.beginTx()) {
			  
			  Assert.assertEquals(newNodesAndRelationships, graphDatabaseService.execute("match (n) optional match (n)-[r]->(m) return count(n) + count(r) as tot").next().get("tot"));
			  
			  System.out.println(newNodesAndRelationships);
			  
			  tx.success();
			}
			
		} catch (Exception e) {
		  
			e.printStackTrace();
			
			Assert.fail(e.getMessage());
		}
	}
}
