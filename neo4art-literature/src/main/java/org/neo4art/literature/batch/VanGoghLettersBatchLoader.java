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
package org.neo4art.literature.batch;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.core.graphdb.CoreLegacyIndex;
import org.neo4art.core.graphdb.CoreRelationship;
import org.neo4art.literature.domain.Letter;
import org.neo4art.literature.graphdb.LiteratureRelationship;
import org.neo4art.literature.manager.VanGoghLettersManager;
import org.neo4art.literature.repository.DocumentBatchInserterRepository;
import org.neo4art.literature.repository.DocumentRepository;
import org.neo4art.sentiment.domain.SentimentAnalysis;

import deprecated.Neo4ArtBatchInserterSingleton;

/**
 * @author Enrico De Benetti
 * @since 06/mag/2015
 */
public class VanGoghLettersBatchLoader
{

  private static Log logger = LogFactory.getLog(VanGoghLettersBatchLoader.class);
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {

    try
    {
      String fileName = "vangoghsentiment/van-gogh-sentiments.csv";
      DocumentRepository documentRepository = new DocumentBatchInserterRepository();
      VanGoghLettersManager vanGoghLettersManager = new VanGoghLettersManager();
      
//      logger.debug("STEP 1 Starting saving Van Gogh letters.");
//      
//      List<Letter> vanGoghLetters = vanGoghLettersManager.loadLetters();
//    
//      if(CollectionUtils.isNotEmpty(vanGoghLetters))
//      {
//       
//        logger.debug(vanGoghLetters.size()+" letters to save."); 
//        
//       for (Letter letter : vanGoghLetters)
//       {
//        documentRepository.saveDocument(letter);       
//       } 
//      }
//    
//      logger.debug("Success end saving Van Gogh letters.");
      
      logger.debug("STEP 2 ADD SENTIMENT NODE");
      List<SentimentAnalysis> loadSentimentsFromFile = vanGoghLettersManager.loadSentimentsFromFile(fileName);
      
      if(CollectionUtils.isNotEmpty(loadSentimentsFromFile))
      {
        logger.debug(loadSentimentsFromFile.size()+" sentiments to save.");
        
        for (SentimentAnalysis sentimentAnalysis : loadSentimentsFromFile)
        {
          //TODO da razionalizzare....
          long nodeId = Neo4ArtBatchInserterSingleton.createNode(sentimentAnalysis);
          sentimentAnalysis.setNodeId(nodeId);

          documentRepository.saveDocument(sentimentAnalysis.getSource()); 
          Neo4ArtBatchInserterSingleton.createRelationship(sentimentAnalysis.getNodeId(), sentimentAnalysis.getSource().getNodeId(), LiteratureRelationship.HAS_SENTIMENT, null);
        }
      }
      logger.debug("STEP 3 ADD RELATIONSHIP LETTER - SENTIMENT");
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.error("Error saving: "+e.getMessage());
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
      //logger.debug("shutdownBatchInserterInstance");
    }

  }

}
