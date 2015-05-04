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

import org.neo4art.api.builder.mock.BuildMockAGArtists;
import org.neo4art.api.builder.mock.BuildMockColours;
import org.neo4art.api.builder.mock.BuildMockMuseums;
import org.neo4art.api.builder.mock.BuildMockNegativeAOSentiment;
import org.neo4art.api.builder.mock.BuildMockNegativePZSentiment;
import org.neo4art.api.builder.mock.BuildMockPositiveSentiment;
import org.neo4art.api.builder.mock.BuildMockVanGoghArtworks;
import org.neo4art.api.creator.SearchDomainCreator;
import org.neo4art.api.domain.SearchDomain;
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
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.domain.Word;

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
     List<Neo4ArtNode>  entityList = new ArrayList<Neo4ArtNode>();
     
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
        
     for (Neo4ArtNode neo4ArtNode : entityList) {
    	 
      SearchDomain searchDomain = createSearchDomainFromEntity(neo4ArtNode);
      listaSearchDomain.add(searchDomain);
	}
		
	 return listaSearchDomain;
	}
    
    /**
	 * @param neo4ArtNode
	 * @return
	 */
	private static SearchDomain createSearchDomainFromEntity(Neo4ArtNode neo4ArtNode) {
		
	 SearchDomainCreator searchDomainCreator = SearchDomainCreator.getInstance();
	 SearchDomain result = null;
		  
	 if(neo4ArtNode instanceof Artist){
			  
	   result = searchDomainCreator.createSearchDomainFromArtist((Artist) neo4ArtNode);
	 }
		  
	 if(neo4ArtNode instanceof ArtMovement){
		  
	   result = searchDomainCreator.createSearchDomainFromArtMovement((ArtMovement) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Artwork){
		  
	   result = searchDomainCreator.createSearchDomainFromArtwork((Artwork) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Museum){
		  
	  result = searchDomainCreator.createSearchDomainFromMuseum((Museum) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Monument){
		  
	   result = searchDomainCreator.createSearchDomainFromMonument((Monument) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof HistoricPlace){
		  
	   result = searchDomainCreator.createSearchDomainFromHistoricPlace((HistoricPlace) neo4ArtNode);
	 }
	
	 if(neo4ArtNode instanceof HistoricSite){
		  
	   result = searchDomainCreator.createSearchDomainFromHistoricSite((HistoricSite) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof ReligiousBuilding){
		  
	   result = searchDomainCreator.createSearchDomainFromReligiousBuilding((ReligiousBuilding) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Settlement){
		  
	   result = searchDomainCreator.createSearchDomainFromSettlement((Settlement) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Letter){
		  
	   result = searchDomainCreator.createSearchDomainFromLetter((Letter) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Word){
		  
	   result = searchDomainCreator.createSearchDomainFromWord((Word) neo4ArtNode);
	 }
	 
	 if(neo4ArtNode instanceof Colour){
		  
	   result = searchDomainCreator.createSearchDomainFromColour((Colour) neo4ArtNode);
	 }
		  
	 return result;
	}
    
}
