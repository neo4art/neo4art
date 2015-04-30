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

package org.neo4art.sentiment.deprecated;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

/**
 * @author Lorenzo Speranzoni
 * @since 27 Apr 2015
 */
@Deprecated
public class NLAnalyticsRepository
{
  /**
   * 
   * @param sentenceLenght how long is the redundant sentence
   * @param redundancyCount how many times the sentence is present
   * @return
   */
  @SuppressWarnings("unchecked")
  public void findRedundancy(int sentenceLenght, int redundancyCount)
  {
    String cql = "match p=(:Nlp)-[:NEXT_NLP*" + sentenceLenght + "]->(:Nlp) " +
                 "with nodes(p) as nodes, head(nodes(p)) as head " +
                 "match (letter:Letter)-[*]->(head) " +
                 "with letter.title as letter_title, extract(n in nodes | n.pos) as redundant_words " +
                 "with collect(letter_title) as letters, redundant_words " +
                 "where length(letters) = " + redundancyCount + " " +
                 "return letters, redundant_words";
    
    GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();
    
    long start = Calendar.getInstance().getTimeInMillis();
    System.out.println("START: " + start);

    try (Transaction tx = graphDatabaseService.beginTx();
         Result result = graphDatabaseService.execute(cql))
    {
      long end = Calendar.getInstance().getTimeInMillis();
      System.out.println("END: " + end);
      System.out.println("ELAPSED: " + (end - start));
      
      while (result.hasNext())
      {
        Map<String, Object> next = result.next();
        
        List<String> object = (List<String>) next.get("letters");
        
        System.out.println(object.get(0));
      }
    }
    finally
    {
      graphDatabaseService.shutdown();
    }
    
    System.out.println("FINISHED..");
  }
}
