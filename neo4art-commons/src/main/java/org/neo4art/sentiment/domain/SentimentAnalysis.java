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
import java.util.List;
import java.util.Map;

import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4art.literature.domain.Document;
import org.neo4art.sentiment.graphdb.SentymentLabel;
import org.neo4j.graphdb.Label;

/**
 * @author Enrico De Benetti
 * @since 03 Mag 2015
 *
 */
public class SentimentAnalysis implements Neo4ArtNode{

   private static final Label[] LABELS = new Label[] { SentymentLabel.SentimentAnalysis };
	
   private Long                 nodeId;
   
   private String               polarity;
   
   private Document             source;
   
   private List<Word>           redundancy;
  
   public SentimentAnalysis(){
	   
	   
   }
   
   public SentimentAnalysis(String polarity)
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
	  
	if (this.polarity != null)
	{
	  properties.put("polarity", this.polarity);
	}
	
	if (this.getSource() != null)
    {
      properties.put("when", this.getSource().getDate());
    }
	  
	return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }

  public String getPolarity()
  {
	return polarity;
  }

  public void setPolarity(String polarity)
  {
	this.polarity = polarity;
  }

  public Document getSource()
  {
	return source;
  }

  public void setSource(Document source)
  {
	this.source = source;
  }

  public List<Word> getRedundancy()
  {
	return redundancy; 
  }

  public void setRedundancy(List<Word> redundancy)
  {
	this.redundancy = redundancy;
  }

}
