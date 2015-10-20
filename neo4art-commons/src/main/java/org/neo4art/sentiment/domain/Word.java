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

package org.neo4art.sentiment.domain;

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4art.sentiment.graphdb.NLPLabel;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 26 Apr 2015
 */
public class Word implements Node
{
  private static final Label[] LABELS = new Label[] { NLPLabel.Word };
  
  public static final String WORD_PROPERTY_NAME     = "word";
  public static final String LANGUAGE_PROPERTY_NAME = "lang";
  public static final String POLARITY_PROPERTY_NAME = "polarity";

  private Long               nodeId;
  
  private String             word;
  private String             lang;
  private String             polarity;


  public Word()
  {
    this.nodeId = null;
    this.word = null;
    this.lang = null;
    this.polarity = null;
  }

  public Word(String word, String lang)
  {
    this.nodeId = null;
    this.word = word;
    this.lang = lang;
    this.polarity = null;
  }
  
  public Word(String word, String lang, String polarity)
  {
    this.nodeId = null;
    this.word = word;
    this.lang = lang;
    this.polarity = polarity;
  }

  public String getWord()
  {
    return word;
  }

  public void setWord(String word)
  {
    this.word = word;
  }

  public String getLang()
  {
    return lang;
  }

  public void setLang(String lang)
  {
    this.lang = lang;
  }

  public String getPolarity()
  {
    return polarity;
  }

  public void setPolarity(String polarity)
  {
    this.polarity = polarity;
  }
  
  @Override
  public Long getNodeId()
  {
    return this.nodeId;
  }
  
  @Override
  public void setNodeId(long nodeId)
  {
    this.nodeId = nodeId;
  }
  
  @Override
  public Map<String, Object> getProperties()
  {
    Map<String, Object> properties = new HashMap<String, Object>();
    
    if (this.word != null)
    {
      properties.put(WORD_PROPERTY_NAME, this.word);
    }
    
    if (this.lang != null)
    {
      properties.put(LANGUAGE_PROPERTY_NAME, this.lang);      
    }
    
    if (this.polarity != null)
    {
      properties.put(POLARITY_PROPERTY_NAME, this.polarity);      
    }
    
    return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}
