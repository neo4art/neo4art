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
package org.neo4art.api.transformer;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.builder.mock.searchresult.BuildSearchResultMock;
import org.neo4art.api.creator.LinkCreator;
import org.neo4art.api.creator.NodeCreator;
import org.neo4art.api.domain.Link;
import org.neo4art.api.domain.Node;
import org.neo4art.api.domain.SearchResult;
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
import org.neo4art.graphdb.Neo4ArtGraph;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4art.graphdb.Neo4ArtRelationship;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.domain.Word;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class SearchResultTransformer {

	/**
	 * 
	 * @return
	 */
	public static SearchResult buildSearchResult(){
		
	  BuildSearchResultMock mockSearchResult = new BuildSearchResultMock();
	  Neo4ArtGraph neo4ArtGraph = mockSearchResult.getSearchResult();
	  
	  List<Node> nodeList = new ArrayList<Node>();
	  List<Link> linkList = new ArrayList<Link>();
	  
	  for (Neo4ArtNode neo4ArtNode : neo4ArtGraph.getNodes()) {
		  
		  Node node = createNodeFromEntity(neo4ArtNode);
		  nodeList.add(node);
	  }
	  
	  for (Neo4ArtRelationship neo4ArtRelationship : neo4ArtGraph.getRelationships()) {
		  
		  LinkCreator linkCreator = LinkCreator.getInstance();
		  Link link =linkCreator.createLink(neo4ArtRelationship);
		  linkList.add(link);
	  }
	  
	  SearchResult searchResult = new SearchResult();
      searchResult.setLinkList(linkList);
      searchResult.setNodeList(nodeList);
	  
      
	  return searchResult;
	}
	
	/**
	 * @param neo4ArtNode
	 * @return
	 */
	private static Node createNodeFromEntity(Neo4ArtNode neo4ArtNode) {
		
	 NodeCreator nodeCreator = NodeCreator.getInstance();
	 Node result = null;
		  
	 if(neo4ArtNode instanceof Artist){
			  
	   result = nodeCreator.createNodeFromArtist((Artist) neo4ArtNode);
	 }
		  
	 if(neo4ArtNode instanceof ArtMovement){
		  
	   result = nodeCreator.createNodeFromArtMovement((ArtMovement) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Artwork){
		  
	   result = nodeCreator.createNodeFromArtwork((Artwork) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Museum){
		  
	  result = nodeCreator.createNodeFromMuseum((Museum) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Monument){
		  
	   result = nodeCreator.createNodeFromMonument((Monument) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof HistoricPlace){
		  
	   result = nodeCreator.createNodeFromHistoricPlace((HistoricPlace) neo4ArtNode);
	 }
	
	 if(neo4ArtNode instanceof HistoricSite){
		  
	   result = nodeCreator.createNodeFromHistoricSite((HistoricSite) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof ReligiousBuilding){
		  
	   result = nodeCreator.createNodeFromReligiousBuilding((ReligiousBuilding) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Settlement){
		  
	   result = nodeCreator.createNodeFromSettlement((Settlement) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Letter){
		  
	   result = nodeCreator.createNodeFromLetter((Letter) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Word){
		  
	   result = nodeCreator.createNodeFromWord((Word) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Colour){
		  
	   result = nodeCreator.createNodeFromColour((Colour) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof ColourAnalysis){
		  
	   result = nodeCreator.createNodeFromColourAnalysis((ColourAnalysis) neo4ArtNode);
	 }
		  
		  
	 return result;
	}

	/**
	 * 
	 * @return
	 */
	public static SearchResult buildDetailNodeSearch(){
		
	  BuildSearchResultMock mockSearchResult = new BuildSearchResultMock();
	  Neo4ArtGraph neo4ArtGraph = mockSearchResult.getDetailSearchNode();
	  
	  List<Node> nodeList = new ArrayList<Node>();
	  List<Link> linkList = new ArrayList<Link>();
	  
	  for (Neo4ArtNode neo4ArtNode : neo4ArtGraph.getNodes()) {
		  
		  Node node = createNodeFromEntity(neo4ArtNode);
		  nodeList.add(node);
	  }
	  
	  for (Neo4ArtRelationship neo4ArtRelationship : neo4ArtGraph.getRelationships()) {
		  
		  LinkCreator linkCreator = LinkCreator.getInstance();
		  Link link =linkCreator.createLink(neo4ArtRelationship);
		  linkList.add(link);
	  }
	  
	  SearchResult searchResult = new SearchResult();
      searchResult.setLinkList(linkList);
      searchResult.setNodeList(nodeList);
	  
      
	  return searchResult;
	}
	
	
}
