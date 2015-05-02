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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.literature.domain.Document;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.bean.NLPElement;
import org.neo4art.sentiment.bean.NLPSentence;
import org.neo4art.sentiment.domain.NLP;
import org.neo4art.sentiment.repository.NLPBatchInserterRepository;
import org.neo4art.sentiment.util.NLPDocumentTokenizer;

/**
 * @author Lorenzo Speranzoni
 * @since 26 Apr 2015
 */
public class NLPBasicService implements NLPService
{
  private NLPDocumentTokenizer nlpDocumentTokenizer;

  /**
   * 
   */
  public NLPBasicService()
  {
    this.nlpDocumentTokenizer = new NLPDocumentTokenizer();
  }

  /**
   * @see org.neo4art.sentiment.service.NLPService#computeNLP(org.neo4art.literature.domain.Document)
   */
  @Override
  public NLPDocument computeNLP(Document document)
  {
    return this.nlpDocumentTokenizer.tokenize(document);
  }

  /**
   * @see org.neo4art.sentiment.service.NLPService#saveNLPAsLinkedList(org.neo4art.sentiment.bean.NLPDocument)
   */
  @Override
  public long saveNLPAsLinkedList(NLPDocument nlpDocument)
  {
    Long firstNodeId    = null;
    Long previousNodeId = null;
    Long currentNodeId  = null;
    
    List<NLPSentence> nlpSentences = nlpDocument.getNlpSentences();

    if (CollectionUtils.isNotEmpty(nlpSentences))
    {
      NLPBatchInserterRepository nlpRepository = new NLPBatchInserterRepository();
      
      for (int s = 0; s < nlpSentences.size(); s++)
      {
        NLPSentence nlpSentence = nlpSentences.get(s);
        
        List<NLPElement> nlpElements = nlpSentence.getNlpElements();
        
        if (CollectionUtils.isNotEmpty(nlpElements))
        {
          for (int e = 0; e < nlpElements.size(); e++)
          {
            NLPElement nlpElement = nlpElements.get(e);
            
            currentNodeId = nlpRepository.addNode(new NLP(nlpElement.getPos(), nlpElement.getPosTag()));
            
            if (firstNodeId == null)
            {
               firstNodeId = currentNodeId; 
               
               nlpDocument.setFirstNodeId(firstNodeId);
            }
            
            if (previousNodeId != null)
            {
              nlpRepository.addRelationshipToWord(previousNodeId, currentNodeId);
            }
            
            previousNodeId = currentNodeId;
          }
        }
      }
    }

    return firstNodeId;
  }

  /**
   * @see org.neo4art.sentiment.service.NLPService#connectDocumentToNLPLinkedList(long, long, int)
   */
  @Override
  public long connectDocumentToNLPLinkedList(long documentNodeId, long nlpLinkedListStartingNodeId, int nlpSentenceLength)
  {
    NLPBatchInserterRepository nlpRepository = new NLPBatchInserterRepository();
    
    return nlpRepository.addRelationshipBetweenOriginalDocumentAndNLPLinkedList(documentNodeId, nlpLinkedListStartingNodeId, nlpSentenceLength);
  }

}
