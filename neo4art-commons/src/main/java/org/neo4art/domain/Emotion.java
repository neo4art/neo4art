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

package org.neo4art.domain;

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 3 May 2015
 */
public class Emotion implements Node
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Emotion };
  
  public static final String NAME_PROPERTY_NAME = "name";
  public static final String POLARITY_PROPERTY_NAME = "polarity";

  private Long nodeId;
  
  private String name;
  private String polarity;

  public Emotion()
  {
    this.name = null;
    this.polarity = null;
  }
  
  public Emotion(String name)
  {
    this.name = name;
    this.polarity = null;
  }
  
  public Emotion(String name, String polarity)
  {
    this.name = name;
    this.polarity = null;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPolarity()
  {
    return polarity;
  }

  public void setPolarity(String polarity)
  {
    this.polarity = polarity;
  }

  /**
   * @see org.neo4art.graphdb.Node#getNodeId()
   */
  @Override
  public Long getNodeId()
  {
    return this.nodeId;
  }

  /**
   * @see org.neo4art.graphdb.Node#setNodeId(long)
   */
  @Override
  public void setNodeId(long nodeId)
  {
    this.nodeId = nodeId;
  }

  /**
   * @see org.neo4art.graphdb.Node#getProperties()
   */
  @Override
  public Map<String, Object> getProperties()
  {
    Map<String, Object> properties = new HashMap<String, Object>();
    
    if (this.name != null)
    {
      properties.put(NAME_PROPERTY_NAME, this.name);
    }
    
    if (this.polarity != null)
    {
      properties.put(POLARITY_PROPERTY_NAME, this.polarity);      
    }
    
    return properties;
  }

  /**
   * @see org.neo4art.graphdb.Node#getLabels()
   */
  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}
