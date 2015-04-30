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

package org.neo4art.literature.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabase;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4art.literature.domain.Document;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.service.NLPBasicService;
import org.neo4art.sentiment.service.NLPService;
import org.neo4art.sentiment.service.RedundancyCounterAggregatorTreeService;
import org.neo4art.sentiment.service.RedundancyCounterService;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class VanGoghLettersRedundancyTest
{
  @Test
  public void shouldCreateRedundancyCounterTreeFor20VanGoghLetters()
  {
    List<Document> documents = new ArrayList<Document>();
    
    // Setup
    // ----------------------------------------------------
    try
    {
      System.out.println("Loading data...");
      
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      VanGoghLettersManager vanGoghLettersManager = new VanGoghLettersManager();
      
      List<Letter> vanGoghLetter = vanGoghLettersManager.loadLetters();
      
      NLPService nlpService = new NLPBasicService();

      for (int i = 0; i < 20; i++)
      {
        Letter letter = vanGoghLetter.get(i);
        storeLetter(nlpService, letter);
        documents.add(letter);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();

      Assert.fail(e.getMessage());
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
    }
    
    // Test
    // ----------------------------------------------------
    try
    {
      System.out.println("Computing redundancy...");
      
      RedundancyCounterService redundancyCounterService = new RedundancyCounterAggregatorTreeService();
      
      redundancyCounterService.createIndexes();
      redundancyCounterService.computeRedundancyCount(documents, 3, 5);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }
    finally
    {
      Neo4ArtGraphDatabaseServiceSingleton.shutdownInstance();
    }
    
    System.out.println("Done!");
  }

  private void storeLetter(NLPService nlpService, Letter letter001)
  {
    long letter001NodeId = Neo4ArtBatchInserterSingleton.createNode(letter001);
    
    NLPDocument nlpDocument = nlpService.computeNLP(letter001);
    
    long nlpLinkedListStartingNodeId = nlpService.saveNLPAsLinkedList(nlpDocument);
    
    nlpService.connectDocumentToNLPLinkedList(letter001NodeId, nlpLinkedListStartingNodeId);
  }
}
