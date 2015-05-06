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
package org.neo4art.sentiment.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Artist;
import org.neo4art.sentiment.domain.SentimentAnalysis;

/**
 * @author Enrico De Benetti
 * @since 06/mag/2015
 */
public class SentimentAnalysisServiceTest
{

  @Test
  public void testVanGoghSentimnetAnalysis(){
    
    SentimentAnalysisService sentimentAnalysisService = new SentimentAnalysisPOSTagPatternService();
    Artist artist = new Artist();
    artist.setName("Vincent van Gogh");
    List<SentimentAnalysis> makeSentimentAnalisysByArtist = sentimentAnalysisService.makeSentimentAnalisysByArtist(artist);
    
    Assert.assertNotNull(makeSentimentAnalisysByArtist);
    
    for (SentimentAnalysis sentimentAnalysis : makeSentimentAnalisysByArtist)
    {
      System.out.println("Title: "+sentimentAnalysis.getSource().getTitle()+" sentiment: "+sentimentAnalysis.getPolarity());
    }
    
  }
  
  
}
