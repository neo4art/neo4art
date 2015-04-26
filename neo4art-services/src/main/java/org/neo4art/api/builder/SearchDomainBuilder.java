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
package org.neo4art.api.builder;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.builder.mock.BuildMockAGArtists;
import org.neo4art.api.builder.mock.BuildMockVanGoghArtworks;
import org.neo4art.api.builder.mock.BuildMockColours;
import org.neo4art.api.builder.mock.BuildMockMuseums;
import org.neo4art.api.builder.mock.BuildMockNegativeAOSentiment;
import org.neo4art.api.builder.mock.BuildMockNegativePZSentiment;
import org.neo4art.api.builder.mock.BuildMockPositiveSentiment;
import org.neo4art.domain.SearchDomain;

/**
 * @author Enrico De Benetti
 * @since 25 Apr 2015
 *
 */
public class SearchDomainBuilder {

	/**
	 * 
	 * @return
	 */
	public static List<SearchDomain> buildDomainArtists(){
		
        List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
        BuildMockAGArtists mockAGArtists = new BuildMockAGArtists();
        mockAGArtists.loadMockArtists(listaSearchDomain);
        
		listaSearchDomain.add(new SearchDomain("Vincent Van gogh"));
		listaSearchDomain.add(new SearchDomain("Claude Monet"));
		
	  return listaSearchDomain;
	}
	
	/**
	 * 
	 * @return
	 */
    public static List<SearchDomain> buildDomainMuseums(){
		
        List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
        BuildMockMuseums mockMuseums = new BuildMockMuseums();
        mockMuseums.loadMockMuseums(listaSearchDomain);
        
	  return listaSearchDomain;
	}
	
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomainArtworks(){
		
        List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
        BuildMockVanGoghArtworks mockArtworks = new BuildMockVanGoghArtworks();
        mockArtworks.loadMockArtworks(listaSearchDomain);
		
	  return listaSearchDomain;
	}
	
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomainColors(){
		
        List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
        BuildMockColours mockColours = new BuildMockColours();
		mockColours.loadMockColours(listaSearchDomain);
        
	  return listaSearchDomain;
	}
    
    /**
     * 
     * @return
     */
    public static List<SearchDomain> buildDomainSentiments(){
		
        List<SearchDomain> listaSearchDomain = new ArrayList<SearchDomain>();
        BuildMockPositiveSentiment positive = new BuildMockPositiveSentiment();
        BuildMockNegativeAOSentiment negativeAO = new BuildMockNegativeAOSentiment();
        BuildMockNegativePZSentiment negativePZ = new BuildMockNegativePZSentiment();
        
        positive.loadMockSentiments(listaSearchDomain);
        negativeAO.loadMockSentiments(listaSearchDomain);
        negativePZ.loadMockSentiments(listaSearchDomain);
		
	  return listaSearchDomain;
	}
}
