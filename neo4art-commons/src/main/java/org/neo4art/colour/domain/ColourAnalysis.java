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
package org.neo4art.colour.domain;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.colour.graphdb.ColourLabel;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Colour;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4j.graphdb.Label;

/**
 * @author Enrico De Benetti
 * @since 01 Mag 2015
 *
 */
public class ColourAnalysis implements Neo4ArtNode
{
  private static final Label[] LABELS = new Label[] { ColourLabel.ColourAnalysis };

  private Long                 nodeId;

  private Artwork              artwork;

  private Color                averageColour;
  private Colour               averageClosestColour;

  private Color                minimumColour;
  private Colour               minimumClosestColour;
  
  private Color                maximumColour;
  private Colour               maximumClosestColour;

  private String               source;

  int                          increment;

  public ColourAnalysis()
  {
  }

  public ColourAnalysis(Color averageColour, Color minimumColour, Color maximumColour, int increment)
  {
    this.averageColour = averageColour;
    this.minimumColour = minimumColour;
    this.maximumColour = maximumColour;
    
    this.increment = increment;
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

    if (this.source != null)
    {
      properties.put("source", this.source);
    }

    if (this.averageColour != null)
    {
      properties.put("averageColour", this.averageColour.getRGB());
    }

    if (this.averageClosestColour != null)
    {
      properties.put("averageClosestColour", this.averageClosestColour.getColor().getRGB());
    }

    if (this.minimumColour != null)
    {
      properties.put("minimumColour", this.minimumColour.getRGB());
    }

    if (this.minimumClosestColour != null)
    {
      properties.put("minimumClosestColour", this.minimumClosestColour.getColor().getRGB());
    }

    if (this.maximumColour != null)
    {
      properties.put("maximumColour", this.maximumColour.getRGB());
    }

    if (this.maximumClosestColour != null)
    {
      properties.put("maximumClosestColour", this.maximumClosestColour.getColor().getRGB());
    }

    return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }

  public Artwork getArtwork()
  {
    return artwork;
  }

  public void setArtwork(Artwork artwork)
  {
    this.artwork = artwork;
  }

  public Color getAverageColour()
  {
    return averageColour;
  }

  public void setAverageColour(Color averageColour)
  {
    this.averageColour = averageColour;
  }

  public Colour getAverageClosestColour()
  {
    return averageClosestColour;
  }

  public void setAverageClosestColour(Colour averageClosestColour)
  {
    this.averageClosestColour = averageClosestColour;
  }

  public Color getMinimumColour()
  {
    return minimumColour;
  }

  public void setMinimumColour(Color minimumColour)
  {
    this.minimumColour = minimumColour;
  }

  public Colour getMinimumClosestColour()
  {
    return minimumClosestColour;
  }

  public void setMinimumClosestColour(Colour minimumClosestColour)
  {
    this.minimumClosestColour = minimumClosestColour;
  }

  public Color getMaximumColour()
  {
    return maximumColour;
  }

  public void setMaximumColour(Color maximumColour)
  {
    this.maximumColour = maximumColour;
  }

  public Colour getMaximumClosestColour()
  {
    return maximumClosestColour;
  }

  public void setMaximumClosestColour(Colour maximumClosestColour)
  {
    this.maximumClosestColour = maximumClosestColour;
  }

  public String getSource()
  {
    return source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }

  public int getIncrement()
  {
    return increment;
  }

  public void setIncrement(int increment)
  {
    this.increment = increment;
  }

  public void setNodeId(Long nodeId)
  {
    this.nodeId = nodeId;
  }
  
  public String getHexaDecimalAverageColor(){
	  
    String hex = "";
    
    if(this.averageColour != null)
    {
      hex = String.format("%02x%02x%02x", this.averageColour.getRed(), this.averageColour.getGreen(), this.averageColour.getBlue());	  	
    }
    
    return hex;
  }
}
