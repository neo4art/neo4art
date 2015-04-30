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

package org.neo4art.literature.analyzer;

import java.util.List;

import org.neo4art.literature.domain.Document;

/**
 * Task class meant to run document analysis on a set of {@link Document}s.
 * <p/>
 * If you want to run the complete set of analysis you have to write something like that:
 * <p/>
 * <code>
 * analizer
 * <br/>&nbsp;&nbsp;&nbsp;.{@link #givenThese(List)}
 * <br/>&nbsp;&nbsp;&nbsp;.{@link #storeItOnGraph()}
 * <br/>&nbsp;&nbsp;&nbsp;.{@link #computeNLPAnalysis()}
 * <br/>&nbsp;&nbsp;&nbsp;.{@link #computeSentimentAnalysis()}
 * <br/>&nbsp;&nbsp;&nbsp;.{@link #computeRedundancyStats(int, int)}
 * </code>
 * 
 * @author Lorenzo Speranzoni
 * @since 29 Apr 2015
 */
public interface DocumentsAnalyzer
{
  /**
   * Setter method meant to provide the list of document over which to perform
   * all kind of analysis declared on this interface.
   *  
   * @param documents list of documents to be analyzed 
   * @return this instance
   */
  DocumentsAnalyzer givenThese(List<Document> documents);

  /**
   * This method store the list of {@link Document}s provided on the graph.<br/>
   * It creates a node for every document.
   * <p/>
   * It must be called after {@link #givenThese(List)} and before running
   * all kind of analysis declared on this interface
   * 
   * @return this instance
   * @throws IllegalAccessException if the method is called before {@link #givenThese(List)}
   */
  DocumentsAnalyzer storeItOnGraph();

  /**
   * 
   * @return this instance
   * @throws IllegalAccessException if the method is called before {@link #givenThese(List)} and {@link #storeItOnGraph()}
   */
  DocumentsAnalyzer computeNLPAnalysis();

  /**
   * 
   * @return this instance
   * @throws IllegalAccessException if the method is called before {@link #givenThese(List)}, {@link #storeItOnGraph()} and {@link #computeNLPAnalysis()}
   */
  DocumentsAnalyzer computeSentimentAnalysis();

  /**
   * 
   * @param minWordCount mimimum number of words where are interested to find redundancies on the list of {@link Document}s.
   * @param maxWordCount maximum number of words where are interested to find redundancies on the list of {@link Document}s.
   * @return this instance
   * @throws IllegalAccessException if the method is called before {@link #givenThese(List)}, {@link #storeItOnGraph()} and {@link #computeNLPAnalysis()}
   */
  DocumentsAnalyzer computeRedundancyStats(int minWordCount, int maxWordCount);

  /**
   * 
   * @return the statistics collected during the analysis you run
   * @throws IllegalAccessException if the method is called before {@link #givenThese(List)}, {@link #storeItOnGraph()} and {@link #computeNLPAnalysis()}
   */
  DocumentAnalyzerStatistics getStatistics();

}
