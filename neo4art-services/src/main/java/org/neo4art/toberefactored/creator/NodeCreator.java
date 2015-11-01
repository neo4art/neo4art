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
package org.neo4art.toberefactored.creator;

import org.neo4art.api.search.bean.WikipediaSearchResultNode;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.graphdb.ColourLabel;
import org.neo4art.domain.ArtMovement;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Colour;
import org.neo4art.domain.HistoricPlace;
import org.neo4art.domain.HistoricSite;
import org.neo4art.domain.Monument;
import org.neo4art.domain.Museum;
import org.neo4art.domain.Neo4ArtLabel;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.domain.Settlement;
import org.neo4art.literature.domain.Letter;
import org.neo4art.literature.graphdb.LiteratureLabel;
import org.neo4art.sentiment.domain.Word;
import org.neo4art.sentiment.graphdb.NLPLabel;

/**
 * @author Enrico De Benetti
 * @since 01 Mag 2015
 *
 */
public class NodeCreator {

	private static NodeCreator instance = null;
	
	private NodeCreator(){
		
	}
	
	public static NodeCreator getInstance(){
		
		if(instance == null){
			
			instance = new NodeCreator();
		}
		
		return instance;
	}
	
	
	public WikipediaSearchResultNode createNodeFromArtist(Artist artist){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(artist.getNodeId());
	 //node.setLink(artist.getWebsite());
	 wikipediaSearchResultNode.setName(artist.getName());
	 //node.setThumbnail(artist.getImage()!=null ? artist.getImage().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.Artist.name());
	 
	 return wikipediaSearchResultNode;	
	}
	
	public WikipediaSearchResultNode createNodeFromArtMovement(ArtMovement artMovement){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(artMovement.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(artMovement.getName());
	 wikipediaSearchResultNode.setThumbnail(artMovement.getImage().toString());
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.ArtMovement.name());
		 
	 return wikipediaSearchResultNode;	
	}
	
	public WikipediaSearchResultNode createNodeFromArtwork(Artwork artwork){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(artwork.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(artwork.getTitle());
	 wikipediaSearchResultNode.setThumbnail(artwork.getUrl()!=null ? artwork.getUrl().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.Artwork.name());
		 
	 return wikipediaSearchResultNode;		
	}
	
	public WikipediaSearchResultNode createNodeFromMuseum(Museum museum){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(museum.getNodeId());
	 //node.setLink(museum.getWebsite());
	 wikipediaSearchResultNode.setName(museum.getName());
	 //node.setThumbnail(museum.getImage()!=null ? museum.getImage().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.Museum.name());
			 
	 return wikipediaSearchResultNode;		
	}
	
	public WikipediaSearchResultNode createNodeFromMonument(Monument monument){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(monument.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(monument.getName());
	 //node.setThumbnail(monument.getImage()!=null ? monument.getImage().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.Monument.name());
		 
	 return wikipediaSearchResultNode;		
	}
	
	public WikipediaSearchResultNode createNodeFromHistoricPlace(HistoricPlace historicPlace){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(historicPlace.getNodeId());
	 wikipediaSearchResultNode.setLink(historicPlace.getWebsite());
	 wikipediaSearchResultNode.setName(historicPlace.getName());
	 wikipediaSearchResultNode.setThumbnail(historicPlace.getImage()!=null ? historicPlace.getImage().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.HistoricPlace.name());
		 
	 return wikipediaSearchResultNode;		
	}
		
	public WikipediaSearchResultNode createNodeFromHistoricSite(HistoricSite historicSite){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(historicSite.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(historicSite.getName());
	 wikipediaSearchResultNode.setThumbnail(historicSite.getImage()!=null ? historicSite.getImage().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.HistoricSite.name());
		 
	 return wikipediaSearchResultNode;		
	}
		
	public WikipediaSearchResultNode createNodeFromReligiousBuilding(ReligiousBuilding religiousBuilding){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(religiousBuilding.getNodeId());
	 wikipediaSearchResultNode.setLink(religiousBuilding.getWebsite().toString());
	 wikipediaSearchResultNode.setName(religiousBuilding.getName());
	 wikipediaSearchResultNode.setThumbnail(religiousBuilding.getImage()!=null ? religiousBuilding.getImage().toString() : "");
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.ReligiousBuilding.name());
		 
	 return wikipediaSearchResultNode;		
	}
		
	public WikipediaSearchResultNode createNodeFromSettlement(Settlement settlement){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(settlement.getNodeId());
	 wikipediaSearchResultNode.setLink(settlement.getWebsite().toString());
	 wikipediaSearchResultNode.setName(settlement.getName());
	 wikipediaSearchResultNode.setThumbnail(null);
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.Settlement.name());
		 
	 return wikipediaSearchResultNode;		
	}
		
	public WikipediaSearchResultNode createNodeFromLetter(Letter letter){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(letter.getNodeId());
	 wikipediaSearchResultNode.setLink(letter.getUrl());
	 wikipediaSearchResultNode.setName(letter.getTitle());
	 wikipediaSearchResultNode.setThumbnail(letter.getUrl());
	 wikipediaSearchResultNode.setType(LiteratureLabel.Letter.name());
		 
	 return wikipediaSearchResultNode;		
	}
	
	public WikipediaSearchResultNode createNodeFromWord(Word word){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(word.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(word.getWord());
	 wikipediaSearchResultNode.setDescription(word.getPolarity());
	 wikipediaSearchResultNode.setThumbnail(null);
	 wikipediaSearchResultNode.setType(NLPLabel.Word.name());
		 
	 return wikipediaSearchResultNode;		
	}
		
	public WikipediaSearchResultNode createNodeFromColour(Colour colour){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(colour.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(colour.getName());
	 wikipediaSearchResultNode.setThumbnail(colour.getHexaDecimalColor());	 
	 
	 wikipediaSearchResultNode.setType(Neo4ArtLabel.Colour.name());
		 
	 return wikipediaSearchResultNode;		
	}
	
	public WikipediaSearchResultNode createNodeFromColourAnalysis(ColourAnalysis colourAnalysis){
		
	 WikipediaSearchResultNode wikipediaSearchResultNode = new WikipediaSearchResultNode();	
	 wikipediaSearchResultNode.setId(colourAnalysis.getNodeId());
	 wikipediaSearchResultNode.setLink(null);
	 wikipediaSearchResultNode.setName(null);
	 wikipediaSearchResultNode.setThumbnail(null);
	 wikipediaSearchResultNode.setType(ColourLabel.ColourAnalysis.name());
		 
	 return wikipediaSearchResultNode;		
	}
	
}
