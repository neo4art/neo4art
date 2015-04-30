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

package org.neo4art.literature.deprecated;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.neo4art.sentiment.deprecated.SentimentInfo;
import org.neo4art.sentiment.graphdb.NLPRelationship;
import org.neo4art.sentiment.service.DictionaryBasicService;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchRelationship;

/**
 * @author Lorenzo Speranzoni, Enrico De Benetti
 * @since 22 Apr 2015
 */
@Deprecated
public class SentimentBatchInserterRepository implements SentimentRepository
{
  @Override
  public long addWord(long currentNodeId, String word, SentimentInfo sentimentInfo)
  {
    throw new RuntimeException(new IllegalAccessException("Not yet implemented"));
  }
  
  @Override
  public long addWordByRedundantPath(long currentNodeId, String word, SentimentInfo sentimentInfo)
  {
    long wordNodeId = new DictionaryBasicService(Locale.ENGLISH).mergeWordAndFlush(word);
    
    mergeRelationship(currentNodeId, wordNodeId, sentimentInfo, true);

    return wordNodeId;
  }

  @Override
  public void mergeRelationship(long startNodeId, long endNodeId, SentimentInfo sentimentInfo, boolean wordExists)
  {
    BatchInserter batchInserter = null; //Neo4ArtBatchInserterSingleton.getBatchInserterInstance();

    boolean relationshipExists = false;

    if (wordExists)
    {
      Iterable<BatchRelationship> relationships = batchInserter.getRelationships(startNodeId);

      if (relationships != null)
      {
        Iterator<BatchRelationship> relationshipsIterator = relationships.iterator();

        while (!relationshipExists && relationshipsIterator.hasNext())
        {
          BatchRelationship batchRelationship = relationshipsIterator.next();

          relationshipExists = batchRelationship.getType().name().equals(NLPRelationship.NEXT_NLP.name()) && batchRelationship.getEndNode() == endNodeId;

          if (relationshipExists)
          {
            Map<String, Object> relationshipProperties = batchInserter.getRelationshipProperties(batchRelationship.getId());

            long[] x = (long[]) relationshipProperties.get("docs");
            long[] y = new long[x.length + 1];
            for (int i = 0; i < x.length; i++)
              y[i] = x[i];
            y[y.length-1] = Long.parseLong(sentimentInfo.toArray()[0]);
            relationshipProperties.put("docs"      , y);
            relationshipProperties.put("sentiment" , sentimentInfo.add((String[]) relationshipProperties.get("sentiment")).toArray());
            relationshipProperties.put("redundancy", (int) relationshipProperties.get("redundancy") + 1);
            
            batchInserter.setRelationshipProperties(batchRelationship.getId(), relationshipProperties);
          }
        }
      }
    }

    if (!wordExists || !relationshipExists)
    {
      Map<String, Object> relationshipProperties = new HashMap<String, Object>();
      
      long[] docs = new long[] { Long.parseLong(sentimentInfo.toArray()[0]) };

      relationshipProperties.put("docs"      , docs);
      relationshipProperties.put("redundancy", 1);
      relationshipProperties.put("sentiment" , sentimentInfo.toArray());
      
      batchInserter.createRelationship(startNodeId, endNodeId, NLPRelationship.NEXT_NLP, relationshipProperties);
    }
  }
  
  @Override
  public void createRelationship(long startNodeId, long endNodeId, SentimentInfo sentimentInfo, boolean wordExists)
  {
    BatchInserter batchInserter = null; //Neo4ArtBatchInserterSingleton.getBatchInserterInstance();

    Map<String, Object> properties = new HashMap<String, Object>();
    
    String[] data = sentimentInfo.toArray();
    
    properties.put("doc", data[0]);
    properties.put("pos", data[1]);
    properties.put("tag", data[2]);
    
    batchInserter.createRelationship(startNodeId, endNodeId, NLPRelationship.NEXT_NLP, properties);
  }
}
