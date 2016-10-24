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

import org.junit.Assert;
import org.junit.Test;

/**
 * It tests the import of a Wikimedia Dump.
 * 
 * Dump samples are contained in directory src/test/resources. 
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaCVSImportBatchImporterTest {

	@Test
	public void shouldParseWikipediaDumpFile() throws IOException {
	  
		try {
		  
		  File dumpFile = new File("src/test/resources", "enwiki-20150112-pages-articles-multistream-test.xml");
			
			new WikipediaCVSImportBatchImporter().importDump(dumpFile);
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
			
			new WikipediaCVSImportBatchImporter().importDump(dumpFile);
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void shouldReplaceDoubleQuote() throws IOException {
		
		String title = "C:\n \r \\";
		
		System.out.println(title);
		
		if (title.endsWith("\\"))
			title = title + "\\";
		
		title = title.replace("\n", "");
		title = title.replace("\r", "");
		title = title.replace("\"", "\\\"");

		System.out.println(title);
	}
}
