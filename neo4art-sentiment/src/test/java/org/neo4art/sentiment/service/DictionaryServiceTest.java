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
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabase;
import org.neo4art.sentiment.domain.Word;
import org.neo4art.sentiment.graphdb.NLPLegacyIndex;
import org.neo4j.graphdb.index.IndexHits;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class DictionaryServiceTest
{
  @Test
  public void shouldSaveDictionary()
  {
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      DictionaryService dictionaryService = new DictionaryBasicService(Locale.ENGLISH);
      
      dictionaryService.createLegacyIndexes();
      dictionaryService.saveDictionary();
      
      IndexHits<Long> indexHits = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX.name(), "word", "art");

      Assert.assertEquals(1, indexHits.size());
      Assert.assertTrue(indexHits.getSingle() > 0);
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

  @Test
  public void shouldSaveDictionaryWithPolarity()
  {
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      DictionaryService dictionaryService = new DictionaryBasicService(Locale.ENGLISH);
      
      dictionaryService.createLegacyIndexes();
      dictionaryService.saveDictionary();
      dictionaryService.addPolarity();

      {
        IndexHits<Long> indexHitsForWordLove = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX.name(), "word", "love");
        Assert.assertEquals(1, indexHitsForWordLove.size());
        Long loveNodeId = indexHitsForWordLove.getSingle();
        Assert.assertTrue(loveNodeId > 0);
        Map<String, Object> loveNodeProperties = Neo4ArtBatchInserterSingleton.getNodeProperties(loveNodeId);
        Assert.assertEquals(Word.POSITIVE_WORD, (int) loveNodeProperties.get(Word.POLARITY_PROPERTY_NAME));
        indexHitsForWordLove.close();
      }
      
      {
        IndexHits<Long> indexHitsForWordHate = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX.name(), "word", "hate");
        Assert.assertEquals(1, indexHitsForWordHate.size());
        Long hateNodeId = indexHitsForWordHate.getSingle();
        Assert.assertTrue(hateNodeId > 0);
        Map<String, Object> hateNodeProperties = Neo4ArtBatchInserterSingleton.getNodeProperties(hateNodeId);
        Assert.assertEquals(Word.NEGATIVE_WORD, (int) hateNodeProperties.get(Word.POLARITY_PROPERTY_NAME));
        indexHitsForWordHate.close();
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
  }
}
