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
package org.neo4art.toberefactored.builder.mock;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Colour;
import org.neo4art.domain.Museum;
import org.neo4art.graphdb.Graph;
import org.neo4art.graphdb.Node;
import org.neo4art.graphdb.Relationship;
import org.neo4art.sentiment.domain.Word;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class BuildSearchResultMock {

	
	public Graph getSearchResult(){
		
	 List<Node> listaSearch = new ArrayList<Node>();	
	 List<Relationship> relationshpiList = new ArrayList<Relationship>();
	 
	  try{
		  
		Artist artist = new Artist();
		artist.setNodeId(0);
		artist.setName("Van Gogh");
		//artist.setWebsite("http://en.wikipedia.org/wiki/Vincent_van_Gogh");

		Artwork artwork1 = new Artwork();
		artwork1.setNodeId(1);
		artwork1.setTitle("Water Mill at Gennep I");
		artwork1.setUrl(new URL("http://www.vggallery.com/painting/f_0046.jpg"));
		
		Artwork artwork2 = new Artwork();
		artwork2.setNodeId(2);
		artwork2.setTitle("Village at Sunset");
		artwork2.setUrl(new URL("http://www.vggallery.com/painting/f_0190.jpg"));
		
		Colour color = new Colour();
		color.setNodeId(3);
		color.setName("red");
        color.setRgb(new Color(255, 0, 0));
		
		Word word = new Word();
		word.setNodeId(4);
		word.setWord("good");
		word.setPolarity("positive");
		
		Artwork artwork3 = new Artwork();
		artwork3.setNodeId(5);
		artwork3.setTitle("Weaver, Interior with Three Small Windows");
		artwork3.setUrl(new URL("http://www.vggallery.com/painting/f_0037.jpg"));
		
		Museum museum = new Museum();
		museum.setNodeId(6);
		museum.setName("Van Gogh Museum");
		//museum.setImage(new URL("http://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Van_Gogh_Museum.jpg/1024px-Van_Gogh_Museum.jpg"));
		//museum.setWebsite("http://it.wikipedia.org/wiki/Van_Gogh_Museum");
		
		Artwork artwork4 = new Artwork();
		artwork4.setNodeId(7);
		artwork4.setTitle("Head of a Man I");
		artwork4.setUrl(new URL("http://www.vggallery.com/painting/f_0179r.jpg"));
		
		Artwork artwork5 = new Artwork();
		artwork5.setNodeId(8);
		artwork5.setTitle("The Potato Eaters I");
		artwork5.setUrl(new URL("http://www.vggallery.com/painting/f_0082.jpg"));
		
		Artwork artwork6 = new Artwork();
		artwork6.setNodeId(9);
		artwork6.setTitle("Landscape with Pollard Willows");
		artwork6.setUrl(new URL("http://www.vggallery.com/painting/f_0031.jpg"));
		
		listaSearch.add(artist);
		listaSearch.add(artwork1);
		listaSearch.add(artwork2);
		listaSearch.add(artwork3);
		listaSearch.add(artwork4);
		listaSearch.add(artwork5);
		listaSearch.add(artwork6);
		listaSearch.add(museum);
		listaSearch.add(color);
		listaSearch.add(word);
		
	   Relationship relationship1 = new Relationship();
	   relationship1.setStartNode(artwork1);
	   relationship1.setEndNode(artist);
		
	   Relationship relationship2 = new Relationship();
	   relationship2.setStartNode(artwork2);
	   relationship2.setEndNode(artist);
	   
	   Relationship relationship3 = new Relationship();
	   relationship3.setStartNode(artwork3);
	   relationship3.setEndNode(artist);
	   
	   Relationship relationship4 = new Relationship();
	   relationship4.setStartNode(artwork4);
	   relationship4.setEndNode(artist);
	   
	   Relationship relationship5 = new Relationship();
	   relationship5.setStartNode(artwork5);
	   relationship5.setEndNode(artist);
	   
	   Relationship relationship6 = new Relationship();
	   relationship6.setStartNode(museum);
	   relationship6.setEndNode(artwork4);
	   
	   Relationship relationship7 = new Relationship();
	   relationship7.setStartNode(artwork6);
	   relationship7.setEndNode(artwork4);
	   
	   Relationship relationship8 = new Relationship();
	   relationship8.setStartNode(artwork3);
	   relationship8.setEndNode(artwork6);
	   
	   Relationship relationship9 = new Relationship();
	   relationship9.setStartNode(word);
	   relationship9.setEndNode(artwork3);
	   
	   Relationship relationship10 = new Relationship();
	   relationship10.setStartNode(color);
	   relationship10.setEndNode(artwork3);
	   
	   Relationship relationship11 = new Relationship();
	   relationship11.setStartNode(artwork4);
	   relationship11.setEndNode(artwork3);
	   
	   Relationship relationship12 = new Relationship();
	   relationship12.setStartNode(artwork1);
	   relationship12.setEndNode(color);
	   
	   Relationship relationship13 = new Relationship();
	   relationship13.setStartNode(artwork2);
	   relationship13.setEndNode(color);
	   
	   Relationship relationship14 = new Relationship();
	   relationship14.setStartNode(artwork6);
	   relationship14.setEndNode(color);
	   
	   Relationship relationship15 = new Relationship();
	   relationship15.setStartNode(artwork5);
	   relationship15.setEndNode(color);
	   
	   Relationship relationship16 = new Relationship();
	   relationship16.setStartNode(artwork6);
	   relationship16.setEndNode(artist);
	   
	   Relationship relationship17 = new Relationship();
	   relationship17.setStartNode(artwork6);
	   relationship17.setEndNode(artwork2);
	   
	   Relationship relationship18 = new Relationship();
	   relationship18.setStartNode(museum);
	   relationship18.setEndNode(artwork6);
	   
	   Relationship relationship19 = new Relationship();
	   relationship19.setStartNode(artwork4);
	   relationship19.setEndNode(artwork6);
	   
	   Relationship relationship20 = new Relationship();
	   relationship20.setStartNode(artwork5);
	   relationship20.setEndNode(artwork6);
	   
	   relationshpiList.add(relationship1);
	   relationshpiList.add(relationship2);
	   relationshpiList.add(relationship3);
	   relationshpiList.add(relationship4);
	   relationshpiList.add(relationship5);
	   relationshpiList.add(relationship6);
	   relationshpiList.add(relationship7);
	   relationshpiList.add(relationship8);
	   relationshpiList.add(relationship9);
	   relationshpiList.add(relationship10);
	   relationshpiList.add(relationship11);
	   relationshpiList.add(relationship12);
	   relationshpiList.add(relationship13);
	   relationshpiList.add(relationship14);
	   relationshpiList.add(relationship15);
	   relationshpiList.add(relationship16);
	   relationshpiList.add(relationship17);
	   relationshpiList.add(relationship18);
	   relationshpiList.add(relationship19);
	   relationshpiList.add(relationship20);
	   
	  }
	  catch(Exception e){
		  
		  System.out.println("ERRORE: "+e.getMessage());
	  }
		
	 Graph graph = new Graph(new HashSet<Node>(listaSearch), new HashSet<Relationship>(relationshpiList));
		
	 return graph;	
	}
	
    public Graph getDetailSearchNode(){
		
     List<Node> nodes = new ArrayList<Node>();	
     List<Relationship> relationships = new ArrayList<Relationship>();
    	 
     try{	
    	
    	Artwork artwork1 = new Artwork();
 		artwork1.setNodeId(10);
 		artwork1.setTitle("Garden in Auvers");
 		artwork1.setUrl(new URL("http://www.vggallery.com/painting/f_0814.jpg"));
    	 
 		Artwork artwork2 = new Artwork();
 		artwork2.setNodeId(11);
 		artwork2.setTitle("Plaster Statuette of a Female Torso VIII");
 		artwork2.setUrl(new URL("http://www.vggallery.com/painting/f_0216.jpg"));
 		
 		Artwork artwork3 = new Artwork();
 		artwork3.setNodeId(12);
 		artwork3.setTitle("Portrait of PÃ¨re Tanguy I");
 		artwork3.setUrl(new URL("http://www.vggallery.com/painting/f_0364.jpg"));
 		
 		Artwork artwork4 = new Artwork();
 		artwork4.setNodeId(13);
 		artwork4.setTitle("Still Life with Meadow Flowers and Roses");
 		artwork4.setUrl(new URL("http://www.vggallery.com/painting/f_0278.jpg"));
 				
 		Artwork artwork5 = new Artwork();
 		artwork5.setNodeId(14);
 		artwork5.setTitle("Portrait of a Man with a Skull Cap");
 		artwork5.setUrl(new URL("http://www.vggallery.com/painting/f_0289.jpg"));
 		
 		Artwork artwork6 = new Artwork();
 		artwork6.setNodeId(15);
 		artwork6.setTitle("Head of a Woman VIII");
 		artwork6.setUrl(new URL("http://www.vggallery.com/painting/f_0156.jpg"));
 		
 		Artwork artwork7 = new Artwork();
 		artwork7.setNodeId(16);
 		artwork7.setTitle("Thatched Cottages in Jorgus");
 		artwork7.setUrl(new URL("http://www.vggallery.com/painting/f_0758.jpg"));
 		
 		Artwork artwork8 = new Artwork();
 		artwork8.setNodeId(17);
 		artwork8.setTitle("Still Life: Glass with Carnations");
 		artwork8.setUrl(new URL("http://www.vggallery.com/painting/f_0598.jpg"));
 		
 		Artwork artwork9 = new Artwork();
 		artwork9.setNodeId(18);
 		artwork9.setTitle("Portrait of Adeline Ravoux II");
 		artwork9.setUrl(new URL("http://www.vggallery.com/painting/f_0768.jpg"));
 		
 		Artwork artwork10 = new Artwork();
 		artwork10.setNodeId(19);
 		artwork10.setTitle("The Fields");
 		artwork10.setUrl(new URL("http://www.vggallery.com/painting/f_0761.jpg"));
 		
 		nodes.add(artwork1);
 		nodes.add(artwork2);
 		nodes.add(artwork3);
 		nodes.add(artwork4);
 		nodes.add(artwork5);
 		nodes.add(artwork6);
 		nodes.add(artwork7);
 		nodes.add(artwork8);
 		nodes.add(artwork9);
 		nodes.add(artwork10);
 		
 		
 	   Relationship relationship1 = new Relationship();
 	   relationship1.setStartNode(artwork2);
 	   relationship1.setEndNode(artwork1);
 	   
 	   Relationship relationship2 = new Relationship();
	   relationship2.setStartNode(artwork3);
	   relationship2.setEndNode(artwork1);
	   
	   Relationship relationship3 = new Relationship();
 	   relationship3.setStartNode(artwork4);
 	   relationship3.setEndNode(artwork1);
 	   
 	   Relationship relationship4 = new Relationship();
	   relationship4.setStartNode(artwork5);
	   relationship4.setEndNode(artwork1);
	   
	   Relationship relationship5 = new Relationship();
 	   relationship5.setStartNode(artwork6);
 	   relationship5.setEndNode(artwork1);
 	   
 	   Relationship relationship6 = new Relationship();
	   relationship6.setStartNode(artwork7);
	   relationship6.setEndNode(artwork8);
	   
	   Relationship relationship7 = new Relationship();
 	   relationship7.setStartNode(artwork10);
 	   relationship7.setEndNode(artwork8);
 	   
 	   Relationship relationship8 = new Relationship();
	   relationship8.setStartNode(artwork6);
	   relationship8.setEndNode(artwork10);
	   
	   Relationship relationship9 = new Relationship();
 	   relationship9.setStartNode(artwork5);
 	   relationship9.setEndNode(artwork6);
 	   
 	   Relationship relationship10 = new Relationship();
	   relationship10.setStartNode(artwork4);
	   relationship10.setEndNode(artwork6);
	   
	   Relationship relationship11 = new Relationship();
 	   relationship11.setStartNode(artwork8);
 	   relationship11.setEndNode(artwork6);
 	   
 	   Relationship relationship12 = new Relationship();
	   relationship12.setStartNode(artwork2);
	   relationship12.setEndNode(artwork4);
	   
	   Relationship relationship13 = new Relationship();
 	   relationship13.setStartNode(artwork3);
 	   relationship13.setEndNode(artwork4);
 	   
 	   Relationship relationship14 = new Relationship();
	   relationship14.setStartNode(artwork10);
	   relationship14.setEndNode(artwork4);
	   
	   Relationship relationship15 = new Relationship();
 	   relationship15.setStartNode(artwork9);
 	   relationship15.setEndNode(artwork4);
 	   
 	   Relationship relationship16 = new Relationship();
	   relationship16.setStartNode(artwork10);
	   relationship16.setEndNode(artwork1);
	   
	   Relationship relationship17 = new Relationship();
 	   relationship17.setStartNode(artwork10);
 	   relationship17.setEndNode(artwork3);
 	   
 	   Relationship relationship18 = new Relationship();
	   relationship18.setStartNode(artwork7);
	   relationship18.setEndNode(artwork10);
	   
	   Relationship relationship19 = new Relationship();
 	   relationship19.setStartNode(artwork8);
 	   relationship19.setEndNode(artwork10);
 	   
 	   Relationship relationship20 = new Relationship();
	   relationship20.setStartNode(artwork9);
	   relationship20.setEndNode(artwork10);
 	   
 	   relationships.add(relationship1);
	   relationships.add(relationship2);
	   relationships.add(relationship3);
	   relationships.add(relationship4);
	   relationships.add(relationship5);
	   relationships.add(relationship6);
	   relationships.add(relationship7);
	   relationships.add(relationship8);
	   relationships.add(relationship9);
	   relationships.add(relationship10);
	   relationships.add(relationship11);
	   relationships.add(relationship12);
	   relationships.add(relationship13);
	   relationships.add(relationship14);
	   relationships.add(relationship15);
	   relationships.add(relationship16);
	   relationships.add(relationship17);
	   relationships.add(relationship18);
	   relationships.add(relationship19);
	   relationships.add(relationship20);
     }
	  catch(Exception e){
		  
		  System.out.println("ERRORE: "+e.getMessage());
	  }
		
     Graph graph = new Graph(new HashSet<Node>(nodes), new HashSet<Relationship>(relationships));
		
	 return graph;		
	}
    
}