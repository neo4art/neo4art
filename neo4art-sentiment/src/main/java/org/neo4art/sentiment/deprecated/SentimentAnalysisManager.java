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

import java.util.Locale;

import org.neo4art.sentiment.service.DictionaryBasicService;
import org.neo4j.unsafe.batchinsert.BatchInserter;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
@Deprecated
public class SentimentAnalysisManager {

  public SentimentAnalysisManager() {
  }
  
  public void compute(String text) {
    
    BatchInserter batchInserter = null;

    DictionaryBasicService        dictionaryBasicService        = new DictionaryBasicService(Locale.ENGLISH);
    SentimentAnalysisPennTreeBackService sentimentAnalysisPennTreeBackService = new SentimentAnalysisPennTreeBackService();
    
    Long rootNodeId = null;
    
    try {
      
      batchInserter = null; //Neo4ArtBatchInserterSingleton.getBatchInserterInstance();
      
      System.out.println("STEP 1 - LOADING DICTIONARY...");
      
      //dictionaryBasicService.saveWordsWithPolarity();
      
      System.out.println("STEP 2 - SAVING NLP TREE...");
      
      rootNodeId = sentimentAnalysisPennTreeBackService.save(text);
    }
    catch (Exception e) {
      
      e.printStackTrace();
    }
    finally {
      
        if (batchInserter != null) {
          
          batchInserter.shutdown();
        }
    }
    
    if (rootNodeId != null) {
      
      System.out.println("STEP 3 - COMPUTING SENTIMENT ANALYSIS...");
      
      sentimentAnalysisPennTreeBackService.compute(rootNodeId);
    }
  }
}
