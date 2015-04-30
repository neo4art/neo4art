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
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.sentiment.domain.Word;
import org.neo4art.sentiment.graphdb.NLPLegacyIndex;
import org.neo4art.sentiment.util.DictionaryUtils;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.helpers.collection.MapUtil;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class DictionaryBasicService implements DictionaryService
{
  private static Log logger = LogFactory.getLog(DictionaryBasicService.class);

  private Locale     locale;

  public DictionaryBasicService(Locale locale)
  {
    this.locale = locale;
  }

  /**
   * @see org.neo4art.sentiment.service.DictionaryService#createLegacyIndexes()
   */
  @Override
  public void createLegacyIndexes()
  {
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX.name()         , MapUtil.stringMap("type", "exact"), "word", 200_000);
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(NLPLegacyIndex.POSITIVE_WORD_LEGACY_INDEX.name(), MapUtil.stringMap("type", "exact"), "word",  20_000);
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(NLPLegacyIndex.NEGATIVE_WORD_LEGACY_INDEX.name(), MapUtil.stringMap("type", "exact"), "word",  20_000);
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(NLPLegacyIndex.NEGATION_WORD_LEGACY_INDEX.name(), MapUtil.stringMap("type", "exact"), "word",     100);
  }
  
  /**
   * @see org.neo4art.sentiment.service.DictionaryService#saveDictionary()
   */
  @Override
  public void saveDictionary() throws IOException
  {
    File dictionaryDirectory = new ClassPathResource("dictionary" + File.separator + locale.getLanguage()).getFile();

    File[] dictionaryFiles = dictionaryDirectory.listFiles();

    for (File dictionaryFile : dictionaryFiles)
    {
      logger.info("Importing dictionary file: " + dictionaryFile.getName());

      LineIterator dictionaryFileIterator = FileUtils.lineIterator(dictionaryFile);

      while (dictionaryFileIterator.hasNext())
      {
        this.mergeWordWithoutFlushing(dictionaryFileIterator.next());
      }
    }

    Neo4ArtBatchInserterSingleton.flushLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX.name());
  }

  /**
   * @see org.neo4art.sentiment.service.DictionaryService#addPolarity()
   */
  @Override 
  public void addPolarity() throws IOException
  {
    addPolarity("word-polarity" + File.separator + locale.getLanguage() + File.separator + "negative-words.txt", Word.NEGATIVE_WORD);
    addPolarity("word-polarity" + File.separator + locale.getLanguage() + File.separator + "positive-words.txt", Word.POSITIVE_WORD);
  }

  /**
   * 
   * @param file
   * @param polarity
   * @throws IOException
   */
  private void addPolarity(String file, int polarity) throws IOException
  {
    LineIterator polarityFile = FileUtils.lineIterator(new ClassPathResource(file).getFile());
  
    while (polarityFile.hasNext())
    {
      String word = polarityFile.nextLine();
  
      if (StringUtils.isNotEmpty(word) && !StringUtils.startsWith(word, ";"))
      {
        Long wordNodeId = this.mergeWordWithoutFlushing(DictionaryUtils.escapeWordForLuceneSearch(word));
  
        Neo4ArtBatchInserterSingleton.setNodeProperty(wordNodeId, Word.POLARITY_PROPERTY_NAME, polarity);
      }
    }
  
    Neo4ArtBatchInserterSingleton.flushLegacyNodeIndex(NLPLegacyIndex.WORD_LEGACY_INDEX.name());
  }

  /**
   * 
   * @param word
   * @return
   */
  @Override
  public long mergeWordAndFlush(String word)
  {
    return mergeWord(word, true);
  }

  /**
   * 
   * @param word
   * @return
   */
  @Override
  public long mergeWordWithoutFlushing(String word)
  {
    return mergeWord(word, false);
  }

  /**
   * 
   * @param word
   * @param flush
   * @return
   */
  private long mergeWord(String word, boolean flush)
  {
    String escapedWord = DictionaryUtils.escapeWordForLuceneSearch(word).toLowerCase();

    String indexName = NLPLegacyIndex.WORD_LEGACY_INDEX.name();
    
    IndexHits<Long> indexHits = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(indexName, Word.WORD_PROPERTY_NAME, escapedWord);

    Long newWordNodeId = indexHits.getSingle();

    if (newWordNodeId == null)
    {
      Word newWord = new Word(escapedWord, this.locale.getLanguage());
      
      newWordNodeId = Neo4ArtBatchInserterSingleton.createNode(newWord);

      Neo4ArtBatchInserterSingleton.addToLegacyNodeIndex(indexName, newWord);

      if (flush)
      {
        Neo4ArtBatchInserterSingleton.flushLegacyNodeIndex(indexName);
      }

      indexHits.close();
      
      logger.trace("Added word " + word + " [node id: " + newWordNodeId + "][escape: " + escapedWord + "] not found in the dictionary");
    }

    return newWordNodeId;
  }
}
