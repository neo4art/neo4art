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
import org.junit.runner.RunWith;
import org.neo4art.importer.wikipedia.configuration.SpringBootConfiguration;
import org.neo4art.importer.wikipedia.core.WikipediaDumpImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * It tests the import of a Wikimedia Dump.
 * 
 * Dump samples are contained in directory src/test/resources. 
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootConfiguration.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WikipediaServiceTest {

	@Autowired
	private WikipediaDumpImporter wikipediaService;
	
	@Autowired
	private JdbcTemplate template;
	
	@Test
	public void shouldParseWikipediaDumpFile() {
		try {
			Assert.assertNotNull(this.wikipediaService);
			File dumpFile = new File("src/test/resources", "enwiki-20150112-pages-articles-multistream-test.xml");
			long pageCount = this.wikipediaService.importOrUpdateDump(dumpFile);
			Assert.assertEquals(pageCount, template.queryForObject("match (n) where has(n.id) return count(n)", Long.class).longValue());
			System.out.println(pageCount);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
