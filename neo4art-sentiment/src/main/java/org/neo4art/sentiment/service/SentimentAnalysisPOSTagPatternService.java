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

import java.util.ArrayList;
import java.util.List;

import org.neo4art.domain.Artist;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.bean.SentimentAnalysisResult;
import org.neo4art.sentiment.domain.SentimentAnalysis;
import org.neo4art.sentiment.repository.DefaultSentimentAnalisysRepository;
import org.neo4art.sentiment.repository.SentimentAnalysisRepository;

import deprecated.Neo4ArtGraphDatabaseServiceSingleton;

/**
 * @author Lorenzo Speranzoni
 * @since 28 Apr 2015
 */
public class SentimentAnalysisPOSTagPatternService implements SentimentAnalysisService
{

  /**
   * @see org.neo4art.sentiment.service.SentimentAnalysisServiceTest#computeSentiment(org.neo4art.sentiment.bean.NLPDocument)
   */
  @Override
  public SentimentAnalysisResult computeSentiment(NLPDocument nlpDocument)
  {
    // TODO Auto-generated method stub
    throw new RuntimeException(new IllegalAccessException("Method not yet implemented."));
  }

  /* (non-Javadoc)
   * @see org.neo4art.sentiment.service.SentimentAnalysisService#makeSentimentAnalisysByArtist(org.neo4art.domain.Artist)
   */
  @Override
  public List<SentimentAnalysis> makeSentimentAnalisysByArtist(Artist artist)
  {
    List<SentimentAnalysis> results = new ArrayList<SentimentAnalysis>();
    
    try
    {
      Neo4ArtGraphDatabaseServiceSingleton.beginTransaction();
      
      SentimentAnalysisRepository sentimentAnalysisRepository = new DefaultSentimentAnalisysRepository();
      
      results = sentimentAnalysisRepository.makeSentimentAnalisysByArtist(artist);
      
      Neo4ArtGraphDatabaseServiceSingleton.commitTransaction();
    }
    catch (Exception e)
    {
      Neo4ArtGraphDatabaseServiceSingleton.rollbackTransaction();
    }
    
    return results;
  }

}
