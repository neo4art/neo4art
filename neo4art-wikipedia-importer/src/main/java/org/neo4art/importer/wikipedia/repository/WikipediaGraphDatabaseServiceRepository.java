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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4art.graph.WikipediaLabel;
import org.neo4art.graph.WikipediaRelationship;
import org.neo4art.importer.wikipedia.domain.WikipediaArtistPage;
import org.neo4art.importer.wikipedia.domain.WikipediaArtworkPage;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaFile;
import org.neo4art.importer.wikipedia.domain.WikipediaGeneric;
import org.neo4art.importer.wikipedia.domain.WikipediaMuseumPage;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaProject;
import org.neo4art.importer.wikipedia.domain.WikipediaTemplate;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.schema.Schema;

/**
 * It stores Wikipedia data into neo4j via <a href="http://neo4j.com/docs/stable/tutorials-java-embedded-setup.html">GraphDatabaseService</a>.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaGraphDatabaseServiceRepository implements WikipediaRepository {

  private GraphDatabaseService graphDatabaseService;

  public WikipediaGraphDatabaseServiceRepository(GraphDatabaseService graphDatabaseService) {
    this.graphDatabaseService = graphDatabaseService;
  }
  
  @Override
  public long addNode(WikipediaElement wikipediaElement) {
    
    Node newWikipediaNode = graphDatabaseService.createNode(wikipediaElement.getLabel(), WikipediaLabel.WIKIPEDIA);
    
    newWikipediaNode.setProperty("id"       , wikipediaElement.getId());
    newWikipediaNode.setProperty("title"    , wikipediaElement.getTitle());
    newWikipediaNode.setProperty("revision" , wikipediaElement.getRevision());
    newWikipediaNode.setProperty("timestamp", wikipediaElement.getTimestamp());
      
    return newWikipediaNode.getId();
  }
  
  @Override
  public long addRelationship(WikipediaElement wikipediaElementFrom, WikipediaElement wikipediaElementTo, WikipediaRelationship wikipediaRelationship) {
    
    String cql = "MATCH (from:" + wikipediaElementFrom.getLabel() + "{title:{1}}), (to:" + wikipediaElementTo.getLabel() + "{title:{2}}) " +
                 "MERGE (from)-[r:" + wikipediaRelationship + "]->(to) " +
                 "RETURN id(r) AS relationId";
    
    Map<String, Object> parameters = new HashMap<String, Object>();
    
    parameters.put("1", wikipediaElementFrom.getTitle());
    parameters.put("2", wikipediaElementTo.getTitle());
    
    Result result = graphDatabaseService.execute(cql, parameters);
    
    return result.hasNext() ? (long) result.columnAs("relationId").next() : -1;

    /*
    Node wikipediaNodeFrom = graphDatabaseService.findNode(wikipediaElementFrom.getLabel(), "title", wikipediaElementFrom.getTitle());
    Node wikipediaNodeTo   = graphDatabaseService.findNode(wikipediaElementTo  .getLabel(), "title", wikipediaElementTo  .getTitle());
  
    long relationshipId = -1;

    if (createRelationshipBetween(wikipediaNodeFrom, wikipediaNodeTo)) {
        relationshipId = wikipediaNodeFrom.createRelationshipTo(wikipediaNodeTo, wikipediaRelationship).getId();
    }
    
    return relationshipId;
    */
  }
  
  private boolean createRelationshipBetween(Node wikipediaNodeFrom, Node wikipediaNodeTo) {
    
    if (wikipediaNodeFrom != null && wikipediaNodeTo != null) {
      if (!wikipediaNodeFrom.hasRelationship()) {
        return true;
      }
      else {

        Iterator<Relationship> relationships = wikipediaNodeFrom.getRelationships().iterator();
        
        while (relationships.hasNext()) {
          if (relationships.next().getEndNode().getProperty("title").equals(wikipediaNodeTo.getProperty("title"))) {
            return false;
          }
        }
      }
    }
    
    return false;
  }

  @Override
  public long addArtistPage(WikipediaArtistPage wikipediaArtistPage) {
    return addNode(wikipediaArtistPage);
  }

  @Override
  public long addArtworkPage(WikipediaArtworkPage wikipediaArtworkPage) {
    return addNode(wikipediaArtworkPage);
  }

  @Override
  public long addMuseumPage(WikipediaMuseumPage wikipediaMuseumPage) {
    return addNode(wikipediaMuseumPage);
  }

  @Override
  public long addPage(WikipediaPage wikipediaPage) {
    return addNode(wikipediaPage);
  }

  @Override
  public long addCategory(WikipediaCategory wikipediaCategory) {
    return addNode(wikipediaCategory);
  }

  @Override
  public long addFile(WikipediaFile wikipediaFile) {
    return addNode(wikipediaFile);
  }

  @Override
  public long addProject(WikipediaProject wikipediaProject) {
    return addNode(wikipediaProject);
  }

  @Override
  public long addTemplate(WikipediaTemplate wikipediaTemplate) {
    return addNode(wikipediaTemplate);
  }

  @Override
  public long addGeneric(WikipediaGeneric wikipediaGeneric) {
    return addNode(wikipediaGeneric);
  }

  @Override
  public void createConstraints() {
    
    Schema schema = graphDatabaseService.schema();

    for (WikipediaLabel wikipediaLabel : WikipediaLabel.values()) {
      schema.constraintFor(wikipediaLabel).assertPropertyIsUnique("title").create();
    }
  }
}
