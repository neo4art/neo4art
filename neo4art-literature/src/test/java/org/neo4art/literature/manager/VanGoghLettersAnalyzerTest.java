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
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.neo4art.sentiment.service.DictionaryBasicService;
import org.neo4art.sentiment.service.DictionaryService;

import deprecated.Neo4ArtBatchInserterSingleton;
import deprecated.Neo4ArtGraphDatabase;

/**
 * @author Lorenzo Speranzoni
 * @since 24 Apr 2015
 */
public class VanGoghLettersAnalyzerTest
{
  public static void main(String[] args)
  {
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      DictionaryService dictionaryService = new DictionaryBasicService(Locale.ENGLISH);

      // Step 1: Saving dictionary
      // ----------------------------------------------------------------------
      dictionaryService.saveDictionary();

      // Step 2: Adding polarity to dictionary
      // ----------------------------------------------------------------------
      dictionaryService.addPolarity();

      // Step 3: Saving Van Gogh Letters with NLP
      // ----------------------------------------------------------------------
      new VanGoghLettersManager().runVanGoghLetterAnalysis();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
    }
  }
}
