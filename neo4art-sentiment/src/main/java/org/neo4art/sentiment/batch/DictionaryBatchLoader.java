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

package org.neo4art.sentiment.batch;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.sentiment.service.DictionaryBasicService;
import org.neo4art.sentiment.service.DictionaryService;

import deprecated.Neo4ArtBatchInserterSingleton;

/**
 * @author Lorenzo Speranzoni
 * @since 3 May 2015
 */
public class DictionaryBatchLoader
{
  private static Log logger = LogFactory.getLog(DictionaryBatchLoader.class);
  
  public static void main(String[] args)
  {
    try
    {
      DictionaryService dictionaryService = new DictionaryBasicService(Locale.ENGLISH);

      dictionaryService.saveDictionary();
      dictionaryService.addPolarity();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      logger.error("Error saving dictionary " + Locale.ENGLISH.getLanguage() + ": " + e.getMessage());
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
    }
  }
}
