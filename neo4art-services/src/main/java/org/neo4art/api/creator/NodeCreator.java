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
package org.neo4art.api.creator;

import org.neo4art.api.domain.Node;
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
	
	
	public Node createNodeFromArtist(Artist artist){
		
	 Node node = new Node();	
	 node.setId(artist.getNodeId());
	 //node.setLink(artist.getWebsite());
	 node.setName(artist.getName());
	 //node.setThumbnail(artist.getImage()!=null ? artist.getImage().toString() : "");
	 node.setType(Neo4ArtLabel.Artist.name());
	 
	 return node;	
	}
	
	public Node createNodeFromArtMovement(ArtMovement artMovement){
		
	 Node node = new Node();	
	 node.setId(artMovement.getNodeId());
	 node.setLink(null);
	 node.setName(artMovement.getName());
	 node.setThumbnail(artMovement.getImage());
	 node.setType(Neo4ArtLabel.ArtMovement.name());
		 
	 return node;	
	}
	
	public Node createNodeFromArtwork(Artwork artwork){
		
	 Node node = new Node();	
	 node.setId(artwork.getNodeId());
	 node.setLink(null);
	 node.setName(artwork.getTitle());
	 node.setThumbnail(artwork.getUrl()!=null ? artwork.getUrl().toString() : "");
	 node.setType(Neo4ArtLabel.Artwork.name());
		 
	 return node;		
	}
	
	public Node createNodeFromMuseum(Museum museum){
		
	 Node node = new Node();	
	 node.setId(museum.getNodeId());
	 node.setLink(museum.getWebsite());
	 node.setName(museum.getName());
	 node.setThumbnail(museum.getImage()!=null ? museum.getImage().toString() : "");
	 node.setType(Neo4ArtLabel.Museum.name());
			 
	 return node;		
	}
	
	public Node createNodeFromMonument(Monument monument){
		
	 Node node = new Node();	
	 node.setId(monument.getNodeId());
	 node.setLink(null);
	 node.setName(monument.getName());
	 //node.setThumbnail(monument.getImage()!=null ? monument.getImage().toString() : "");
	 node.setType(Neo4ArtLabel.Monument.name());
		 
	 return node;		
	}
	
	public Node createNodeFromHistoricPlace(HistoricPlace historicPlace){
		
	 Node node = new Node();	
	 node.setId(historicPlace.getNodeId());
	 node.setLink(historicPlace.getWebsite());
	 node.setName(historicPlace.getName());
	 node.setThumbnail(historicPlace.getImage()!=null ? historicPlace.getImage().toString() : "");
	 node.setType(Neo4ArtLabel.HistoricPlace.name());
		 
	 return node;		
	}
		
	public Node createNodeFromHistoricSite(HistoricSite historicSite){
		
	 Node node = new Node();	
	 node.setId(historicSite.getNodeId());
	 node.setLink(null);
	 node.setName(historicSite.getName());
	 node.setThumbnail(historicSite.getImage()!=null ? historicSite.getImage().toString() : "");
	 node.setType(Neo4ArtLabel.HistoricSite.name());
		 
	 return node;		
	}
		
	public Node createNodeFromReligiousBuilding(ReligiousBuilding religiousBuilding){
		
	 Node node = new Node();	
	 node.setId(religiousBuilding.getNodeId());
	 node.setLink(religiousBuilding.getWebsite());
	 node.setName(religiousBuilding.getBuildingName());
	 node.setThumbnail(religiousBuilding.getImage()!=null ? religiousBuilding.getImage().toString() : "");
	 node.setType(Neo4ArtLabel.ReligiousBuilding.name());
		 
	 return node;		
	}
		
	public Node createNodeFromSettlement(Settlement settlement){
		
	 Node node = new Node();	
	 node.setId(settlement.getNodeId());
	 node.setLink(settlement.getWebsite());
	 node.setName(settlement.getName());
	 node.setThumbnail(null);
	 node.setType(Neo4ArtLabel.Settlement.name());
		 
	 return node;		
	}
		
	public Node createNodeFromLetter(Letter letter){
		
	 Node node = new Node();	
	 node.setId(letter.getNodeId());
	 node.setLink(letter.getUrl());
	 node.setName(letter.getTitle());
	 node.setThumbnail(letter.getUrl());
	 node.setType(LiteratureLabel.Letter.name());
		 
	 return node;		
	}
	
	public Node createNodeFromWord(Word word){
		
	 Node node = new Node();	
	 node.setId(word.getNodeId());
	 node.setLink(null);
	 node.setName(word.getWord());
	 node.setDescription(word.getPolarity());
	 node.setThumbnail(null);
	 node.setType(NLPLabel.Word.name());
		 
	 return node;		
	}
		
	public Node createNodeFromColour(Colour colour){
		
	 Node node = new Node();	
	 node.setId(colour.getNodeId());
	 node.setLink(null);
	 node.setName(colour.getName());
	 node.setThumbnail(colour.getHexaDecimalColor());	 
	 
	 node.setType(Neo4ArtLabel.Colour.name());
		 
	 return node;		
	}
	
	public Node createNodeFromColourAnalysis(ColourAnalysis colourAnalysis){
		
	 Node node = new Node();	
	 node.setId(colourAnalysis.getNodeId());
	 node.setLink(null);
	 node.setName(null);
	 node.setThumbnail(null);
	 node.setType(ColourLabel.ColourAnalysis.name());
		 
	 return node;		
	}
	
}
