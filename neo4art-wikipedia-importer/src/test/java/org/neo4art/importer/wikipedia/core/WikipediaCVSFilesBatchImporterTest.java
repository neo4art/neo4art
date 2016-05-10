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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * It tests the import of a Wikimedia Dump.
 * 
 * Dump samples are contained in directory src/test/resources. 
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaCVSFilesBatchImporterTest {

	@Test
	public void shouldParseWikipediaDumpFile() throws IOException {
	  
		try {
		  
			FileUtils.deleteDirectory(new File(GraphDatabaseConnectionManager.NEO4J_STORE_DIR));
			
		  File dumpFile = new File("src/test/resources", "enwiki-20150112-pages-articles-multistream-test.xml");
			
			long newNodesAndRelationships = new WikipediaCVSFilesBatchImporter().importDump(dumpFile);

			GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(new File(GraphDatabaseConnectionManager.NEO4J_STORE_DIR));
			
			try (Transaction tx = graphDatabaseService.beginTx()) {
			  
			  Object newNodesAndRelationshipsOnDB = graphDatabaseService.execute("match (n) optional match (n)-[r]->(m) return count(n) + count(r) as tot").next().get("tot");
			  
			  Assert.assertEquals(newNodesAndRelationships, newNodesAndRelationshipsOnDB);
			  
			  tx.success();
			}
		}
		catch (Exception e) {
		  
			e.printStackTrace();
			
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void shouldParseWikipediaFullDumpFile() throws IOException {
		
		try {
			

			File dumpFile = new File("/Users/lorenzo/Progetti/Neo4j/projects/neo4art/application/dump", "enwiki-20160501-pages-articles-multistream.xml");
			File storeDir = new File("/Users/lorenzo/Progetti/Neo4j/projects/neo4art/application/database/neo4j-enterprise-3.0.1/data/databases/graph.db");
			
			FileUtils.deleteDirectory(storeDir);
			
			long newNodesAndRelationships = new WikipediaCVSFilesBatchImporter().importDump(dumpFile, storeDir);
			
			GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(storeDir);
			
			try (Transaction tx = graphDatabaseService.beginTx()) {
				
				Object newNodesAndRelationshipsOnDB = graphDatabaseService.execute("match (n) optional match (n)-[r]->(m) return count(n) + count(r) as tot").next().get("tot");
				
				Assert.assertEquals(newNodesAndRelationships, newNodesAndRelationshipsOnDB);
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			Assert.fail(e.getMessage());
		}
	}
}
