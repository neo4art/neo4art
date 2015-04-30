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

package org.neo4art.sentiment.bean;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.literature.domain.Document;

/**
 * @author Lorenzo Speranzoni
 * @since 27 Apr 2015
 */
public class NLPDocument
{
  private Document originalDocument;
  
  private long firstNodeId;
  
  private List<NLPSentence> nlpSentences;
  
  public NLPDocument(Document document)
  {
    this.originalDocument = document;
    
    this.nlpSentences = new ArrayList<NLPSentence>();
  }

  public long getFirstNodeId()
  {
    return firstNodeId;
  }

  public void setFirstNodeId(long firstNodeId)
  {
    this.firstNodeId = firstNodeId;
  }

  public Document getOriginalDocument()
  {
    return originalDocument;
  }

  public List<NLPSentence> getNlpSentences()
  {
    return nlpSentences;
  }
  
  public boolean add(NLPSentence nlpSentente)
  {
    return this.nlpSentences.add(nlpSentente);
  }
}
