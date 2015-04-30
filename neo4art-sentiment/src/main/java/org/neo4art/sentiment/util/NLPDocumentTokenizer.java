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

package org.neo4art.sentiment.util;

import java.util.Locale;

import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.TokenizerME;

import org.neo4art.literature.domain.Document;
import org.neo4art.sentiment.bean.NLPDocument;
import org.neo4art.sentiment.bean.NLPElement;
import org.neo4art.sentiment.bean.NLPSentence;

/**
 * This utility class transforms the content of a document into a {@link NLPDocument} by
 * splitting it into {@link NLPSentence}, each of which is divided into a list of words as well.
 * POS (Part Of Speech) tag is associated to every {@link NLPElement}.
 * 
 * @author Lorenzo Speranzoni
 * @since 27 Apr 2015
 */
public class NLPDocumentTokenizer
{
  private SentenceDetectorME sentenceDetectorME;
  private TokenizerME        sentenceTokenizerME;
  private POSTaggerME        posTaggerME;

  private boolean initialized;

  public NLPDocumentTokenizer()
  {
    this.initialized = false;
  }

  public void train()
  {
    Locale locale = Locale.ENGLISH;
    
    this.sentenceDetectorME  = NLPUtils.getSentenceDetectorME (locale);
    this.sentenceTokenizerME = NLPUtils.getSentenceTokenizerME(locale);
    this.posTaggerME         = NLPUtils.getPOSTaggerME        (locale);
    
    this.initialized = true;
  }
  
  /**
   * 
   * @param documentContent
   * @return
   */
  public NLPDocument tokenize(Document document)
  {
    if (!initialized)
    {
      train();
    }
    
    NLPDocument nlpDocument = null;
    
    String documentContentForNLP = DictionaryUtils.forNLP(document.getContent());
    
    String[] sentences = sentenceDetectorME.sentDetect(documentContentForNLP);

    if (sentences != null && sentences.length > 0)
    {
      nlpDocument = new NLPDocument(document);
      
      for (int s = 0; s < sentences.length; s++)
      {
        String sentence = sentences[s];
        
        String[] tokenizedSentences = sentenceTokenizerME.tokenize(sentence);

        if (tokenizedSentences != null && tokenizedSentences.length > 0)
        {
          NLPSentence nlpSentente = new NLPSentence();
        
          String[] tags = posTaggerME.tag(tokenizedSentences);

          for (int t = 0; t < tokenizedSentences.length; t++)
          {
            nlpSentente.add(new NLPElement(tokenizedSentences[t], tags[t]));
          }
          
          nlpDocument.add(nlpSentente);
        }
      }
    }
    
    return nlpDocument;
  }
}
