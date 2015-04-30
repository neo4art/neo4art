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

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabase;
import org.neo4art.sentiment.deprecated.SentimentAnalysisManager;
import org.neo4art.sentiment.vangoghletters.VanGoghLetter001;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class SentimentAnalysisServiceTest {

  @Test
  public void shouldComputeSentimentAnalysisForSimpleText001() {

    try {
      
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));
      
      String text = "I like its colour, but I don't like either its dimension nor its weight.";

      new SentimentAnalysisManager().compute(text);
    }
    catch (Exception e) {
      
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }

  }

  @Test
  public void shouldComputeSentimentAnalysisForLetter001() {
  
    try {
      
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));
      
      new SentimentAnalysisManager().compute(VanGoghLetter001.TEXT);
    }
    catch (Exception e) {
      
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }
  }
}
