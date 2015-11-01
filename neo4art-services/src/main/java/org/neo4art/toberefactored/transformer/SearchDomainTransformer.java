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
import org.neo4art.graphdb.Node;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.domain.Word;
import org.neo4art.toberefactored.builder.mock.BuildMockAGArtists;
import org.neo4art.toberefactored.builder.mock.BuildMockColours;
import org.neo4art.toberefactored.builder.mock.BuildMockMuseums;
import org.neo4art.toberefactored.builder.mock.BuildMockNegativeAOSentiment;
import org.neo4art.toberefactored.builder.mock.BuildMockNegativePZSentiment;
import org.neo4art.toberefactored.builder.mock.BuildMockPositiveSentiment;
import org.neo4art.toberefactored.builder.mock.BuildMockVanGoghArtworks;
import org.neo4art.toberefactored.creator.SearchDomainCreator;
import org.neo4art.toberefactored.domain.SearchDomain;

/**
 * @author Enrico De Benetti
 * @since 25 Apr 2015
 *
 */
public class SearchDomainTransformer {

	/**
	 * 
	 * @return
	 */
	public static List<SearchDomain> buildDomainArtists(){
		
     List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
     BuildMockAGArtists mockAGArtists = new BuildMockAGArtists();
     List<Artist> loadMockArtists = mockAGArtists.loadMockArtists();
     SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();
        
     for (Artist artist : loadMockArtists) {
        	
       SearchDomain searchDomainArtist = searchDomainCreator.createSearchDomainFromArtist(artist);
       listaSearchDomain.add(searchDomainArtist);
	 }
		
	 return listaSearchDomain;
	}
	
	/**
	 * 
	 * @return
	 */
    public static List<SearchDomain> buildDomainMuseums(){
		
     SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();
     List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
     BuildMockMuseums mockMuseums = new BuildMockMuseums();
     List<Museum> loadMockMuseums = mockMuseums.loadMockMuseums();
     
      for (Museum museum : loadMockMuseums) {

    	SearchDomain searchDomainMuseum = searchDomainCreator.createSearchDomainFromMuseum(museum); 
    	listaSearchDomain.add(searchDomainMuseum); 
	  }
        
	 return listaSearchDomain;
	}
	
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomainArtworks(){
		
     SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();
     List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
     BuildMockVanGoghArtworks mockArtworks = new BuildMockVanGoghArtworks();
     List<Artwork> loadMockArtworks = mockArtworks.loadMockArtworks();
        
      for (Artwork artwork : loadMockArtworks) {
		
    	SearchDomain searchDomainArtwork = searchDomainCreator.createSearchDomainFromArtwork(artwork);
    	listaSearchDomain.add(searchDomainArtwork);
	  }   
		
	 return listaSearchDomain;
	}
	
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomainColors(){
		
     SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();	
     List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
     BuildMockColours mockColours = new BuildMockColours();
	 List<Colour> loadMockColours = mockColours.loadMockColours();
     
	  for (Colour colour : loadMockColours) {
		
	    SearchDomain searchDomainColour = searchDomainCreator.createSearchDomainFromColour(colour);
	    listaSearchDomain.add(searchDomainColour);
	  }
	 
	 return listaSearchDomain;
	}
    
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomainSentiments(){
		
     SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();
     List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
     BuildMockPositiveSentiment positive = new BuildMockPositiveSentiment();
     BuildMockNegativeAOSentiment negativeAO = new BuildMockNegativeAOSentiment();
     BuildMockNegativePZSentiment negativePZ = new BuildMockNegativePZSentiment();
        
     List<Word> sentimentsList = new ArrayList<Word>();
     sentimentsList.addAll(positive.loadMockSentiments());
     sentimentsList.addAll(negativeAO.loadMockSentiments());
     sentimentsList.addAll(negativePZ.loadMockSentiments());
    	
      for (Word word : sentimentsList) {
		
    	SearchDomain searchDomainWord = searchDomainCreator.createSearchDomainFromWord(word);
    	listaSearchDomain.add(searchDomainWord);
	  }
     
	 return listaSearchDomain;
	}
    
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomains(){
		
     List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
     List<Node>  entityList = new ArrayList<Node>();
     
     BuildMockAGArtists mockAGArtists = new BuildMockAGArtists();
     BuildMockMuseums mockMuseums = new BuildMockMuseums();
     BuildMockVanGoghArtworks mockArtworks = new BuildMockVanGoghArtworks();
     BuildMockColours mockColours = new BuildMockColours();
	 BuildMockPositiveSentiment positive = new BuildMockPositiveSentiment();
     BuildMockNegativeAOSentiment negativeAO = new BuildMockNegativeAOSentiment();
     BuildMockNegativePZSentiment negativePZ = new BuildMockNegativePZSentiment();
    
     entityList.addAll(mockAGArtists.loadMockArtists());  
     entityList.addAll(mockMuseums.loadMockMuseums());
     entityList.addAll(mockArtworks.loadMockArtworks()); 
     entityList.addAll(mockColours.loadMockColours()); 
     entityList.addAll(positive.loadMockSentiments());
     entityList.addAll(negativeAO.loadMockSentiments());
     entityList.addAll(negativePZ.loadMockSentiments());  
        
     for (Node node : entityList) {
    	 
      SearchDomain searchDomain = createSearchDomainFromEntity(node);
      listaSearchDomain.add(searchDomain);
	}
		
	 return listaSearchDomain;
	}
    
    /**
	 * @param node
	 * @return
	 */
	private static SearchDomain createSearchDomainFromEntity(Node node) {
		
	 SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();
	 SearchDomain result = null;
		  
	 if(node instanceof Artist){
			  
	   result = searchDomainCreator.createSearchDomainFromArtist((Artist) node);
	 }
		  
	 if(node instanceof ArtMovement){
		  
	   result = searchDomainCreator.createSearchDomainFromArtMovement((ArtMovement) node);
	 }
	 
	 if(node instanceof Artwork){
		  
	   result = searchDomainCreator.createSearchDomainFromArtwork((Artwork) node);
	 }
	 
	 if(node instanceof Museum){
		  
	  result = searchDomainCreator.createSearchDomainFromMuseum((Museum) node);
	 }
	 
	 if(node instanceof Monument){
		  
	   result = searchDomainCreator.createSearchDomainFromMonument((Monument) node);
	 }
	 
	 if(node instanceof HistoricPlace){
		  
	   result = searchDomainCreator.createSearchDomainFromHistoricPlace((HistoricPlace) node);
	 }
	
	 if(node instanceof HistoricSite){
		  
	   result = searchDomainCreator.createSearchDomainFromHistoricSite((HistoricSite) node);
	 }
	 
	 if(node instanceof ReligiousBuilding){
		  
	   result = searchDomainCreator.createSearchDomainFromReligiousBuilding((ReligiousBuilding) node);
	 }
	 
	 if(node instanceof Settlement){
		  
	   result = searchDomainCreator.createSearchDomainFromSettlement((Settlement) node);
	 }
	 
	 if(node instanceof Letter){
		  
	   result = searchDomainCreator.createSearchDomainFromLetter((Letter) node);
	 }
	 
	 if(node instanceof Word){
		  
	   result = searchDomainCreator.createSearchDomainFromWord((Word) node);
	 }
	 
	 if(node instanceof Colour){
		  
	   result = searchDomainCreator.createSearchDomainFromColour((Colour) node);
	 }
		  
	 return result;
	}
    
}
