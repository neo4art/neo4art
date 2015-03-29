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

import org.neo4art.graph.util.Neo4ArtGraphDatabaseConnectionFactory;
import org.neo4art.importer.wikipedia.repository.WikipediaGraphDatabaseServiceRepository;
import org.neo4art.importer.wikipedia.repository.WikipediaRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class WikipediaDefaultGraphService implements WikipediaGraphService {

  @Override
  public void createConstraints() {
    
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseConnectionFactory.getInstance();
    
    try (Transaction tx = graphDatabaseService.beginTx()) {
    
      WikipediaRepository wikipediaRepository = new WikipediaGraphDatabaseServiceRepository(graphDatabaseService);
      
      wikipediaRepository.createConstraints();
      
      tx.success();
    }
  }
}
