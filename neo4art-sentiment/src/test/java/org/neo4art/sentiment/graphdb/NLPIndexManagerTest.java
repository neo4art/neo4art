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

package org.neo4art.sentiment.graphdb;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabase;
import org.neo4art.sentiment.domain.Word;
import org.neo4j.graphdb.index.IndexHits;

/**
 * @author Lorenzo Speranzoni
 * @since 17 Apr 2015
 */
public class NLPIndexManagerTest
{
  @Test
  public void shouldRetrieveNodeFromIndex()
  {
    try
    {
      FileUtils.deleteDirectory(new File(Neo4ArtGraphDatabase.NEO4J_STORE_DIR));

      Word word = new Word("neo4art", null, null);

      long neo4artNodeId = Neo4ArtBatchInserterSingleton.createNode(word);

      Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX);
      Neo4ArtBatchInserterSingleton.addToLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX, word);
      Neo4ArtBatchInserterSingleton.flushLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX);

      IndexHits<Long> indexHits = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX, "word", "neo4art");

      Assert.assertEquals(1, indexHits.size());
      Assert.assertEquals(neo4artNodeId, indexHits.getSingle().longValue());
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
