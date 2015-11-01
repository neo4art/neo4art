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
package org.neo4art.toberefactored.transformer;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.search.bean.WikipediaSearchResult;
import org.neo4art.api.search.bean.WikipediaSearchResultNode;
import org.neo4art.api.search.bean.WikipediaSearchResultRelationship;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.domain.ArtMovement;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Colour;
import org.neo4art.domain.HistoricPlace;
import org.neo4art.domain.HistoricSite;
import org.neo4art.domain.Monument;
import org.neo4art.domain.Museum;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.domain.Settlement;
import org.neo4art.graphdb.Graph;
import org.neo4art.graphdb.Relationship;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.domain.Word;
import org.neo4art.toberefactored.builder.mock.BuildSearchResultMock;
import org.neo4art.toberefactored.creator.LinkCreator;
import org.neo4art.toberefactored.creator.NodeCreator;

/**
 * @author Lorenzo Speranzoni, Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class WikipediaSearchResultNodeBuilder {

  /**
   * 
   * @return
   */
  public static WikipediaSearchResult buildSearchResult() {

    BuildSearchResultMock mockSearchResult = new BuildSearchResultMock();
    Graph graph = mockSearchResult.getSearchResult();

    List<WikipediaSearchResultNode> nodeList = new ArrayList<WikipediaSearchResultNode>();
    List<WikipediaSearchResultRelationship> linkList = new ArrayList<WikipediaSearchResultRelationship>();

    for (org.neo4art.graphdb.Node node : graph.getNodes()) {

      nodeList.add(createNodeFromEntity(node));
    }

    for (Relationship relationship : graph.getRelationships()) {

      LinkCreator linkCreator = LinkCreator.getInstance();
      WikipediaSearchResultRelationship wikipediaSearchResultRelationship = linkCreator.createLink(relationship);
      linkList.add(wikipediaSearchResultRelationship);
    }

    WikipediaSearchResult wikipediaSearchResult = new WikipediaSearchResult();
    wikipediaSearchResult.setRelationships(linkList);
    wikipediaSearchResult.setNodes(nodeList);

    return wikipediaSearchResult;
  }

  /**
   * @param node
   * @return
   */
  private static WikipediaSearchResultNode createNodeFromEntity(org.neo4art.graphdb.Node node) {

    NodeCreator nodeCreator = NodeCreator.getInstance();
    WikipediaSearchResultNode result = null;

    if (node instanceof Artist) {

      result = nodeCreator.createNodeFromArtist((Artist) node);
    }

    if (node instanceof ArtMovement) {

      result = nodeCreator.createNodeFromArtMovement((ArtMovement) node);
    }

    if (node instanceof Artwork) {

      result = nodeCreator.createNodeFromArtwork((Artwork) node);
    }

    if (node instanceof Museum) {

      result = nodeCreator.createNodeFromMuseum((Museum) node);
    }

    if (node instanceof Monument) {

      result = nodeCreator.createNodeFromMonument((Monument) node);
    }

    if (node instanceof HistoricPlace) {

      result = nodeCreator.createNodeFromHistoricPlace((HistoricPlace) node);
    }

    if (node instanceof HistoricSite) {

      result = nodeCreator.createNodeFromHistoricSite((HistoricSite) node);
    }

    if (node instanceof ReligiousBuilding) {

      result = nodeCreator.createNodeFromReligiousBuilding((ReligiousBuilding) node);
    }

    if (node instanceof Settlement) {

      result = nodeCreator.createNodeFromSettlement((Settlement) node);
    }

    if (node instanceof Letter) {

      result = nodeCreator.createNodeFromLetter((Letter) node);
    }

    if (node instanceof Word) {

      result = nodeCreator.createNodeFromWord((Word) node);
    }

    if (node instanceof Colour) {

      result = nodeCreator.createNodeFromColour((Colour) node);
    }

    if (node instanceof ColourAnalysis) {

      result = nodeCreator.createNodeFromColourAnalysis((ColourAnalysis) node);
    }

    return result;
  }

  /**
   * 
   * @return
   */
  public static WikipediaSearchResult buildDetailNodeSearch() {

    BuildSearchResultMock mockSearchResult = new BuildSearchResultMock();
    Graph graph = mockSearchResult.getDetailSearchNode();

    List<WikipediaSearchResultNode> nodeList = new ArrayList<WikipediaSearchResultNode>();
    List<WikipediaSearchResultRelationship> linkList = new ArrayList<WikipediaSearchResultRelationship>();

    for (org.neo4art.graphdb.Node node : graph.getNodes()) {

      nodeList.add(createNodeFromEntity(node));
    }

    for (Relationship relationship : graph.getRelationships()) {

      LinkCreator linkCreator = LinkCreator.getInstance();
      WikipediaSearchResultRelationship wikipediaSearchResultRelationship = linkCreator.createLink(relationship);
      linkList.add(wikipediaSearchResultRelationship);
    }

    WikipediaSearchResult wikipediaSearchResult = new WikipediaSearchResult();
    wikipediaSearchResult.setRelationships(linkList);
    wikipediaSearchResult.setNodes(nodeList);

    return wikipediaSearchResult;
  }

}
