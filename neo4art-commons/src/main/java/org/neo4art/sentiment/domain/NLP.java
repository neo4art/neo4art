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
public class NLP implements Node
{
  private static final Label[] LABELS = new Label[] { NLPLabel.Nlp };
  
  public static final String POS_PROPERTY_NAME     = "pos";
  public static final String POS_TAG_PROPERTY_NAME = "posTag";

  private Long               nodeId;
  private String             pos;
  private String             posTag;

  public NLP()
  {
    this.nodeId = null;
    this.pos = null;
    this.posTag = null;
  }

  /**
   * @param pos
   * @param posTag
   */
  public NLP(String pos, String posTag)
  {
    super();

    this.pos = pos;
    this.posTag = posTag;
  }

  public String getPos()
  {
    return pos;
  }

  public void setPos(String pos)
  {
    this.pos = pos;
  }

  public String getPosTag()
  {
    return posTag;
  }

  public void setPosTag(String posTag)
  {
    this.posTag = posTag;
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
    
    if (this.pos != null)
    {
      properties.put(POS_PROPERTY_NAME, this.pos);
    }
    
    if (this.posTag != null)
    {
      properties.put(POS_TAG_PROPERTY_NAME, this.posTag);      
    }
    
    return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}
