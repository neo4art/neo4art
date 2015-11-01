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
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.domain.Word;
import org.neo4art.toberefactored.domain.SearchDomain;

/**
 * @author Enrico De Benetti
 * @since 02 Mag 2015
 *
 */
public class SearchDomainCreator {

	private static SearchDomainCreator instance = null;
	
	private SearchDomainCreator() {
	
	}
	
    public static SearchDomainCreator getInstance(){
		
		if(instance == null){
			
			instance = new SearchDomainCreator();
		}
		
		return instance;
	}
    
    public SearchDomain createSearchDomainFromArtist(Artist artist){
		
     SearchDomain searchDomain = new SearchDomain();	
     searchDomain.setText(artist.getName());
   	 
   	 return searchDomain;	
   	}
    
    public SearchDomain createSearchDomainFromArtMovement(ArtMovement artMovement){
		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(artMovement.getName());
   		 
   	 return searchDomain;	
   	}
   	
   	public SearchDomain createSearchDomainFromArtwork(Artwork artwork){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(artwork.getTitle());
   		 
   	 return searchDomain;		
   	}
   	
   	public SearchDomain createSearchDomainFromMuseum(Museum museum){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(museum.getName());
   			 
   	 return searchDomain;		
   	}
   	
   	public SearchDomain createSearchDomainFromMonument(Monument monument){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(monument.getName());
   		 
   	 return searchDomain;		
   	}
   	
   	public SearchDomain createSearchDomainFromHistoricPlace(HistoricPlace historicPlace){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(historicPlace.getName());
   		 
   	 return searchDomain;		
   	}
   		
   	public SearchDomain createSearchDomainFromHistoricSite(HistoricSite historicSite){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(historicSite.getName());
   		 
   	 return searchDomain;		
   	}
   		
   	public SearchDomain createSearchDomainFromReligiousBuilding(ReligiousBuilding religiousBuilding){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(religiousBuilding.getName());
   		 
   	 return searchDomain;		
   	}
   		
   	public SearchDomain createSearchDomainFromSettlement(Settlement settlement){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(settlement.getName());
   		 
   	 return searchDomain;		
   	}
   		
   	public SearchDomain createSearchDomainFromLetter(Letter letter){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(letter.getTitle());
   		 
   	 return searchDomain;		
   	}
   	
   	public SearchDomain createSearchDomainFromWord(Word word){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(word.getWord());
   		 
   	 return searchDomain;		
   	}
   		
   	public SearchDomain createSearchDomainFromColour(Colour colour){
   		
   	 SearchDomain searchDomain = new SearchDomain();	
   	 searchDomain.setText(colour.getName());
   		 
   	 return searchDomain;		
   	}
	
}