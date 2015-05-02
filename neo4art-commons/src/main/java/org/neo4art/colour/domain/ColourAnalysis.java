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

  private String               nameAverageColor;

  private Color                rgbAverageColor;

  private String               nameMinimumColor;

  private Color                rgbMinimumColor;

  private String               nameMaximumColor;

  private Color                rgbMaximumColor;

  private String               source;

  int                          increment;

  public ColourAnalysis()
  {
  }

  public ColourAnalysis(Color rgbAverageColor, Color rgbMinimumColor, Color rgbMaximumColor, int increment)
  {
    this.rgbAverageColor = rgbAverageColor;
    this.rgbMinimumColor = rgbMinimumColor;
    this.rgbMaximumColor = rgbMaximumColor;
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

  public String getNameAverageColor()
  {
    return nameAverageColor;
  }

  public void setNameAverageColor(String nameAverageColor)
  {
    this.nameAverageColor = nameAverageColor;
  }

  public Color getRgbAverageColor()
  {
    return rgbAverageColor;
  }

  public void setRgbAverageColor(Color rgbAverageColor)
  {
    this.rgbAverageColor = rgbAverageColor;
  }

  public String getNameMinimumColor()
  {
    return nameMinimumColor;
  }

  public void setNameMinimumColor(String nameMinimumColor)
  {
    this.nameMinimumColor = nameMinimumColor;
  }

  public Color getRgbMinimumColor()
  {
    return rgbMinimumColor;
  }

  public void setRgbMinimumColor(Color rgbMinimumColor)
  {
    this.rgbMinimumColor = rgbMinimumColor;
  }

  public String getNameMaximumColor()
  {
    return nameMaximumColor;
  }

  public void setNameMaximumColor(String nameMaximumColor)
  {
    this.nameMaximumColor = nameMaximumColor;
  }

  public Color getRgbMaximumColor()
  {
    return rgbMaximumColor;
  }

  public void setRgbMaximumColor(Color rgbMaximumColor)
  {
    this.rgbMaximumColor = rgbMaximumColor;
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
