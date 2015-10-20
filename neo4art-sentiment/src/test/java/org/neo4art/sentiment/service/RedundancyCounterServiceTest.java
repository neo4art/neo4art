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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.literature.domain.Document;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.vangoghletters.VanGoghLetter001;
import org.neo4art.sentiment.vangoghletters.VanGoghLetter002;

import deprecated.Neo4ArtBatchInserterSingleton;
import deprecated.Neo4ArtGraphDatabase;
import deprecated.Neo4ArtGraphDatabaseServiceSingleton;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class RedundancyCounterServiceTest
{
  @Test
  public void shouldCreateRedundancyCounterTree()
  {
    Letter letter001 = new Letter(null, VanGoghLetter001.TEXT, null, null, null, null, null);
    Letter letter002 = new Letter(null, VanGoghLetter002.TEXT, null, null, null, null, null);
    
    List<Document> documents = new ArrayList<Document>();
    
    documents.add(letter001);
    documents.add(letter002);
    
    // Setup
    // ----------------------------------------------------
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      NLPService nlpService = new NLPBasicService();
      
      storeLetter(nlpService, letter001);
      storeLetter(nlpService, letter002);
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
  }

  private void storeLetter(NLPService nlpService, Letter letter001)
  {
    long letter001NodeId = Neo4ArtBatchInserterSingleton.createNode(letter001);
    
    NLPDocument nlpDocument = nlpService.computeNLP(letter001);
    
    long nlpLinkedListStartingNodeId = nlpService.saveNLPAsLinkedList(nlpDocument);
    
    nlpService.connectDocumentToNLPLinkedList(letter001NodeId, nlpLinkedListStartingNodeId, nlpDocument.getLength());
  }
}
