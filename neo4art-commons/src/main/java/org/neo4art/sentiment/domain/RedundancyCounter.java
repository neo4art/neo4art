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
import org.neo4art.sentiment.graphdb.RedundancyTreeLabel;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 30 Apr 2015
 */
public class RedundancyCounter implements Node
{
  private static final Label[] LABELS = new Label[] { RedundancyTreeLabel.RedundancyTree };
  
  public static final String REDUNDANT_PATH_PROPERTY_NAME     = "redundantPath";
  public static final String REDUNDANCY_COUNTER_PROPERTY_NAME = "redundantCounter";

  public Long nodeId;
  
  private String  redundantPath;
  private Integer redundantCounter;
  
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

  public String getRedundantPath()
  {
    return redundantPath;
  }

  public void setRedundantPath(String redundantPath)
  {
    this.redundantPath = redundantPath;
  }

  public Integer getRedundantCounter()
  {
    return redundantCounter;
  }

  public void setRedundantCounter(Integer redundantCounter)
  {
    this.redundantCounter = redundantCounter;
  }

  @Override
  public Map<String, Object> getProperties()
  {
    Map<String, Object> properties = new HashMap<String, Object>();
    
    if (this.redundantPath != null)
    {
      properties.put(REDUNDANT_PATH_PROPERTY_NAME, this.redundantPath);
    }
    
    if (this.getRedundantCounter() != null)
    {
      properties.put(REDUNDANCY_COUNTER_PROPERTY_NAME, this.getRedundantCounter());
    }
    
    return properties;
  }
  
  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}
