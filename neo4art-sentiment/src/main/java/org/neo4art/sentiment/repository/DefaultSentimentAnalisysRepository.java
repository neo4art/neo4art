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
package org.neo4art.sentiment.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4art.domain.Artist;
import org.neo4art.literature.domain.Letter;
import org.neo4art.literature.graphdb.LiteratureLabel;
import org.neo4art.literature.graphdb.LiteratureRelationship;
import org.neo4art.sentiment.domain.SentimentAnalysis;
import org.neo4art.sentiment.graphdb.SentymentLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.helpers.collection.MapUtil;

import deprecated.Neo4ArtGraphDatabaseServiceSingleton;

/**
 * @author Enrico De Benetti
 * @since 06/mag/2015
 */
public class DefaultSentimentAnalisysRepository implements SentimentAnalysisRepository
{

  @Override
  public List<SentimentAnalysis> makeSentimentAnalisysByArtist(Artist artist)
  {
    List<SentimentAnalysis> results = new ArrayList<SentimentAnalysis>();

    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();

    String cql = "MATCH (sentiment:" + SentymentLabel.SentimentAnalysis + ")-[:"+LiteratureRelationship.HAS_SENTIMENT+"]->(letter:" + LiteratureLabel.Letter + ") " + 
        //"WHERE letter.from={name} " + 
        "RETURN letter, sentiment ";

    Result result = graphDatabaseService.execute(cql, MapUtil.map("name", artist.getName()));

    //System.out.println("cql: "+cql);
    
    while (result.hasNext())
    {
      Map<String, Object> next = result.next();

      Node letterNode = (Node) next.get("letter");
      Node sentimentNode = (Node) next.get("sentiment");

      Letter letter = new Letter();
      letter.setTitle((String) letterNode.getProperty("title"));
      letter.setDate((String) letterNode.getProperty("when"));

      SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
      sentimentAnalysis.setSource(letter);
      sentimentAnalysis.setPolarity((String) sentimentNode.getProperty("polarity"));
      
      results.add(sentimentAnalysis);
    }

    return results;
  }

}
