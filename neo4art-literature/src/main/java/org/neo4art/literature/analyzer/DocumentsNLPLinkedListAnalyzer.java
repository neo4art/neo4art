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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.graphdb.connection.Neo4ArtGraphDatabaseServiceSingleton;
import org.neo4art.literature.domain.Document;
import org.neo4art.literature.repository.DocumentBatchInserterRepository;
import org.neo4art.literature.repository.DocumentRepository;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.bean.RedundancyStatistics;
import org.neo4art.sentiment.bean.SentimentAnalysisResult;
import org.neo4art.sentiment.service.NLPBasicService;
import org.neo4art.sentiment.service.RedundancyCounterAggregatorTreeService;
import org.neo4art.sentiment.service.RedundancyCounterService;
import org.neo4art.sentiment.service.SentimentAnalysisPOSTagPatternService;
import org.neo4art.sentiment.service.SentimentAnalysisService;

/**
 * Implementation based on a linked list of NLP POS Tags.
 *           
 * @author Lorenzo Speranzoni
 * @since 28 Apr 2015
 */
public class DocumentsNLPLinkedListAnalyzer implements DocumentsAnalyzer
{
  private List<Document> documents;
  
  private boolean storedOnGraph;
  private boolean nlpAnalysisRun;

  private DocumentAnalyzerStatistics statistics;

  public DocumentsNLPLinkedListAnalyzer()
  {
    this.documents  = null;
    this.statistics = null;
    
    this.storedOnGraph  = false;
    this.nlpAnalysisRun = false;
  }
  
  /**
   * @see org.neo4art.literature.analyzer.DocumentsAnalyzer#givenThese(java.util.List)
   */
  @Override
  public DocumentsAnalyzer givenThese(List<Document> documents)
  {
    this.documents = documents;
    
    return this;
  }
  
  /**
   * @see org.neo4art.literature.analyzer.DocumentsAnalyzer#storeItOnGraph()
   */
  @Override
  public DocumentsAnalyzer storeItOnGraph()
  {
    assertDocumentListIsNotEmpty();
    
    try
    {
      DocumentRepository documentRepository = new DocumentBatchInserterRepository();

      for (Document document : this.documents)
      {
        documentRepository.saveDocument(document);
      }
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
      
      this.statistics = new DocumentAnalyzerStatistics();
      
      this.statistics.setDocuments(this.documents);
    
      this.storedOnGraph = true;
    }
    
    return this;
  }
  
  /**
   * @see org.neo4art.literature.analyzer.DocumentsAnalyzer#computeNLPAnalysis()
   */
  @Override
  public DocumentsAnalyzer computeNLPAnalysis()
  {
    assertDocumentListIsNotEmpty();
    assertDocumentListIsStoredOnGraph();
    
    List<NLPDocument> nlpDocuments = null;
    
    try
    {
      nlpDocuments = new ArrayList<NLPDocument>();
      
      NLPBasicService nlpService = new NLPBasicService();
      
      for (Document document : this.documents)
      {
        NLPDocument nlpDocument = nlpService.computeNLP(document);
        
        nlpService.saveNLPAsLinkedList(nlpDocument);
        
        nlpService.connectDocumentToNLPLinkedList(document.getNodeId(), nlpDocument.getFirstNodeId(), nlpDocument.getLength());
        
        nlpDocuments.add(nlpDocument);
      }
    }
    finally
    {
      Neo4ArtBatchInserterSingleton.shutdownBatchInserterInstance();
      
      this.statistics.setNLPDocuments(nlpDocuments);
      
      this.nlpAnalysisRun = true;
    }
    
    return this;
  }
  
  /**
   * @see org.neo4art.literature.analyzer.DocumentsAnalyzer#computeSentimentAnalysis()
   */
  @Override
  public DocumentsAnalyzer computeSentimentAnalysis()
  {
    assertDocumentListIsNotEmpty();
    assertDocumentListIsStoredOnGraph();
    assertNLPAnalysisDone();
    
    try
    {
      Neo4ArtGraphDatabaseServiceSingleton.beginTransaction();
      
      List<SentimentAnalysisResult> sentimentAnalysisStatistics = new ArrayList<SentimentAnalysisResult>();
      
      SentimentAnalysisService sentimentAnalysisService = new SentimentAnalysisPOSTagPatternService();
      
      for (NLPDocument nlpDocument : this.statistics.getNLPDocuments())
      {
        SentimentAnalysisResult sentimentAnalysisResult = sentimentAnalysisService.computeSentiment(nlpDocument);
        
        sentimentAnalysisStatistics.add(sentimentAnalysisResult);
      }
      
      this.statistics.setSentimentAnalysisStatistics(sentimentAnalysisStatistics);
      
      Neo4ArtGraphDatabaseServiceSingleton.commitTransaction();
    }
    finally
    {
      Neo4ArtGraphDatabaseServiceSingleton.shutdownInstance();
    }
    
    return this;
  }
  
  /**
   * @see org.neo4art.literature.analyzer.DocumentsAnalyzer#computeRedundancyStats(int, int)
   */
  @Override
  public DocumentsAnalyzer computeRedundancyStats(int minWordCount, int maxWordCount)
  {
    assertDocumentListIsNotEmpty();
    assertDocumentListIsStoredOnGraph();
    assertNLPAnalysisDone();
    
    try
    {
      Neo4ArtGraphDatabaseServiceSingleton.beginTransaction();
      
      RedundancyCounterService redundancyCounterService = new RedundancyCounterAggregatorTreeService();
      
      RedundancyStatistics redundancyStatistics = redundancyCounterService.computeRedundancyCount(this.documents, minWordCount, maxWordCount);
  
      this.statistics.setRedundancyStatistics(redundancyStatistics);
      
      Neo4ArtGraphDatabaseServiceSingleton.commitTransaction();
    }
    finally
    {
      Neo4ArtGraphDatabaseServiceSingleton.shutdownInstance();
    }
    
    return this;
  }
  
  /**
   * @see org.neo4art.literature.analyzer.DocumentsAnalyzer#getStatistics()
   */
  @Override
  public DocumentAnalyzerStatistics getStatistics()
  {
    return this.statistics;
  }

  /**
   * Support method meant to check if the collection of document is not empty
   * 
   * @throws IllegalAccessException if the collection of document is empty
   */
  private void assertDocumentListIsNotEmpty()
  {
    if (CollectionUtils.isEmpty(this.documents))
    {
      throw new RuntimeException(new IllegalAccessException("You must supply a non-empty set of documents to store on the graph"));
    }
  }

  /**
   * Support method meant to check you called {@link #storeItOnGraph()} before running all sort of statistics
   * 
   * @throws IllegalAccessException if documents aren't stored on graph
   */
  private void assertDocumentListIsStoredOnGraph()
  {
    if (!this.storedOnGraph)
    {
      throw new RuntimeException(new IllegalAccessException("You must save documents on the graph first by calling 'storeItOnTheGraph'"));
    }
  }
  
  /**
   * Support method meant to check you called {@link #computeNLPAnalysis()} before running the other kind of statistics:<br/>
   * This implementation requires the presence of linked list of NLP POS Tags stored on the graph, to compute Redundancy Statistics and Sentiment Analysis. 
   * 
   * @throws IllegalAccessException if documents aren't stored on graph
   */
  private void assertNLPAnalysisDone()
  {
    if (!this.nlpAnalysisRun)
    {
      throw new RuntimeException(new IllegalAccessException("You must run NLPAnalysis before running redundancy statistics"));
    }
  }
}


