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
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabase;
import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.domain.NLP;
import org.neo4art.sentiment.vangoghletters.VanGoghLetter001;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class NLPServiceTest
{
  @Test
  public void shouldCreateNLPLinkedList()
  {
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      NLPBasicService nlpService = new NLPBasicService();
      
      Letter document = new Letter();
      
      document.setContent(VanGoghLetter001.TEXT);
      
      NLPDocument nlpDocument = nlpService.computeNLP(document);
      
      long nlpLinkedListStartingNodeId = nlpService.saveNLPAsLinkedList(nlpDocument);
      
      Map<String, Object> nlpLinkedListStartingNodeProperties = Neo4ArtBatchInserterSingleton.getNodeProperties(nlpLinkedListStartingNodeId);
      
      Assert.assertNotNull(nlpLinkedListStartingNodeProperties);
      Assert.assertEquals("My", nlpLinkedListStartingNodeProperties.get(NLP.POS_PROPERTY_NAME));
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
  }
}
