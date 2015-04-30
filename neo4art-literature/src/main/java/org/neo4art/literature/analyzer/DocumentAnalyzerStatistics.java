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
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.bean.RedundancyStatistics;
import org.neo4art.sentiment.bean.SentimentAnalysisResult;

/**
 * @author Lorenzo Speranzoni
 * @since 28 Apr 2015
 */
public class DocumentAnalyzerStatistics
{
  private List<Document>              documents;
  private List<NLPDocument>           nlpDocuments;
  private RedundancyStatistics        redundancyStatistics;
  private List<SentimentAnalysisResult> sentimentAnalysisResult;

  /**
   * @param documents
   */
  public DocumentAnalyzerStatistics()
  {
  }

  public List<Document> getDocuments()
  {
    return documents;
  }

  public void setDocuments(List<Document> documents)
  {
    this.documents = documents;
  }

  public List<NLPDocument> getNLPDocuments()
  {
    return nlpDocuments;
  }

  public void setNLPDocuments(List<NLPDocument> nlpDocuments)
  {
    this.nlpDocuments = nlpDocuments;
  }

  public RedundancyStatistics getRedundancyStatistics()
  {
    return redundancyStatistics;
  }

  public void setRedundancyStatistics(RedundancyStatistics redundancyStatistics)
  {
    this.redundancyStatistics = redundancyStatistics;
  }

  public List<SentimentAnalysisResult> getSentimentAnalysisStatistics()
  {
    return sentimentAnalysisResult;
  }

  public void setSentimentAnalysisStatistics(List<SentimentAnalysisResult> sentimentAnalysisStatistics)
  {
    this.sentimentAnalysisResult = sentimentAnalysisStatistics;
  }
}
