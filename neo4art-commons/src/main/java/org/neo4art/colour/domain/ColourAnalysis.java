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

  private String               name;

  private String               imageName;

  private String               averageColourName;

  private Color                averageRGBColour;

  private String               minimumColourName;

  private Color                minimumRGBColour;

  private String               maximumColourName;

  private Color                maximumRGBColour;

  private String               source;

  int                          increment;

  public ColourAnalysis()
  {
  }

  public ColourAnalysis(Color rgbAverageColor, Color rgbMinimumColor, Color rgbMaximumColor, int increment)
  {
    this.averageRGBColour = rgbAverageColor;
    this.minimumRGBColour = rgbMinimumColor;
    this.maximumRGBColour = rgbMaximumColor;
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

    if (this.name != null)
    {
      properties.put("name", this.name);
    }

    if (this.imageName != null)
    {
      properties.put("imageName", this.imageName);
    }
    
    if (this.averageColourName != null)
    {
      properties.put("averageColourName", this.averageColourName);
    }

    if (this.averageRGBColour != null)
    {
      properties.put("averageRGBColour", this.averageRGBColour);
    }

    if (this.minimumColourName != null)
    {
      properties.put("minimumColourName", this.minimumColourName);
    }
    
    if (this.minimumRGBColour != null)
    {
      properties.put("minimumRGBColour", this.minimumRGBColour);
    }

    if (this.maximumColourName != null)
    {
      properties.put("maximumColourName", this.maximumColourName);
    }
    
    if (this.maximumRGBColour != null)
    {
      properties.put("maximumRGBColour", this.maximumRGBColour);
    }

    if (this.source != null)
    {
      properties.put("source", this.source);
    }

    return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getImageName()
  {
    return imageName;
  }

  public void setImageName(String imageName)
  {
    this.imageName = imageName;
  }

  public String getAverageColourName()
  {
    return averageColourName;
  }

  public void setAverageColourName(String averageColourName)
  {
    this.averageColourName = averageColourName;
  }

  public Color getAverageRGBColour()
  {
    return averageRGBColour;
  }

  public void setAverageRGBColour(Color averageRGBColour)
  {
    this.averageRGBColour = averageRGBColour;
  }

  public String getMinimumColourName()
  {
    return minimumColourName;
  }

  public void setMinimumColourName(String minimumColourName)
  {
    this.minimumColourName = minimumColourName;
  }

  public Color getMinimumRGBColour()
  {
    return minimumRGBColour;
  }

  public void setMinimumRGBColour(Color minimumRGBColour)
  {
    this.minimumRGBColour = minimumRGBColour;
  }

  public String getMaximumColourName()
  {
    return maximumColourName;
  }

  public void setMaximumColourName(String maximumColourName)
  {
    this.maximumColourName = maximumColourName;
  }

  public Color getMaximumRGBColour()
  {
    return maximumRGBColour;
  }

  public void setMaximumRGBColour(Color maximumRGBColour)
  {
    this.maximumRGBColour = maximumRGBColour;
  }

  public String getSource()
  {
    return source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }

  public void setNodeId(Long nodeId)
  {
    this.nodeId = nodeId;
  }
  
  public int getIncrement()
  {
    return increment;
  }

  public void setIncrement(int increment)
  {
    this.increment = increment;
  }
}
