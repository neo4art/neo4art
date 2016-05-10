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
package it.neo4art.api.search.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.neo4art.api.search.bean.WikipediaSearchResult;
import org.neo4art.api.search.bean.WikipediaSearchResultNode;
import org.neo4art.api.search.bean.WikipediaSearchResultRelationship;
import org.neo4art.api.search.repository.WikipediaSearchGraphDatabaseServiceRepository;
import org.neo4art.api.search.repository.WikipediaSearchRepository;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManager;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory;
import org.neo4art.graphdb.connection.GraphDatabaseConnectionManagerFactory.GraphDatabaseConnectionType;
import org.neo4art.graphdb.transaction.GraphDatabaseTransaction;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.neo4art.importer.wikipedia.graphdb.WikipediaRelationship;
import org.neo4j.helpers.collection.MapUtil;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 1 Nov 2015
 */
public class WikipediaSearchRepositoryTest {

  @Before
  public void cleanDatabase() throws IOException {

    FileUtils.deleteDirectory(new File(GraphDatabaseConnectionManager.NEO4J_STORE_DIR));
  }

  @Test
  public void shouldFindDepthOneConnectionsForVanGogh() {

    GraphDatabaseConnectionManager graphDatabaseConnectionManager = GraphDatabaseConnectionManagerFactory.getInstance(GraphDatabaseConnectionType.EMBEDDED_DATABASE);

    String vincentVanGogh = "Vincent van Gogh";
    String paulGauguin = "Paul Gauguin";
    String emileBernard = "Émile Bernard";

    try (GraphDatabaseTransaction tx = graphDatabaseConnectionManager.getTransaction()) {

      graphDatabaseConnectionManager.executeCypherQuery("CREATE (:" + WikipediaLabel.Wikipedia + " {title: {title}})", MapUtil.map("title", vincentVanGogh));
      graphDatabaseConnectionManager.executeCypherQuery("CREATE (:" + WikipediaLabel.Wikipedia + " {title: {title}})", MapUtil.map("title", paulGauguin));
      graphDatabaseConnectionManager.executeCypherQuery("CREATE (:" + WikipediaLabel.Wikipedia + " {title: {title}})", MapUtil.map("title", emileBernard));

      graphDatabaseConnectionManager.executeCypherQuery("MATCH (n:" + WikipediaLabel.Wikipedia + " {title: {title1}}), (m:" + WikipediaLabel.Wikipedia + " {title: {title2}}) CREATE (n)-[:" + WikipediaRelationship.REFERS + "]->(m)", MapUtil.map("title1", vincentVanGogh, "title2", paulGauguin));
      graphDatabaseConnectionManager.executeCypherQuery("MATCH (n:" + WikipediaLabel.Wikipedia + " {title: {title1}}), (m:" + WikipediaLabel.Wikipedia + " {title: {title2}}) CREATE (n)-[:" + WikipediaRelationship.REFERS + "]->(m)", MapUtil.map("title1", vincentVanGogh, "title2", emileBernard));
      graphDatabaseConnectionManager.executeCypherQuery("MATCH (n:" + WikipediaLabel.Wikipedia + " {title: {title1}}), (m:" + WikipediaLabel.Wikipedia + " {title: {title2}}) CREATE (n)-[:" + WikipediaRelationship.REFERS + "]->(m)", MapUtil.map("title1", paulGauguin, "title2", emileBernard));
      graphDatabaseConnectionManager.executeCypherQuery("MATCH (n:" + WikipediaLabel.Wikipedia + " {title: {title1}}), (m:" + WikipediaLabel.Wikipedia + " {title: {title2}}) CREATE (n)-[:" + WikipediaRelationship.REFERS + "]->(m)", MapUtil.map("title1", emileBernard, "title2", paulGauguin));

      tx.success();
    }

    WikipediaSearchRepository wikipediaSearchRepository = new WikipediaSearchGraphDatabaseServiceRepository();

    WikipediaSearchResult wikipediaSearchResult = wikipediaSearchRepository.findDepthOneConnectionsByPageTitle(vincentVanGogh, false);

    List<WikipediaSearchResultNode> nodes = wikipediaSearchResult.getNodes();

    for (WikipediaSearchResultNode node : nodes) {

      System.out.println(node.getId() + ": " + node.getName());
    }
    
    Assert.assertEquals(3, nodes.size());

    List<WikipediaSearchResultRelationship> relationships = wikipediaSearchResult.getRelationships();

    for (WikipediaSearchResultRelationship relationship : relationships) {

      System.out.println("(" + relationship.getSource() + ")-[:" + relationship.getLinkName() + "]->(" + relationship.getTarget() + ")");
    }

    Assert.assertEquals(2, relationships.size());
    
    graphDatabaseConnectionManager.close();
  }
}
