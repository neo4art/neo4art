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

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Neo4ArtLabel;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4j.graphdb.Label;

/**
 * @author Enrico De Benetti
 * @since 01 Mag 2015
 *
 */
public class Colour implements Neo4ArtNode
{
  private static final Label[] LABELS             = new Label[] { Neo4ArtLabel.Colour };

  public static final String  NAME_PROPERTY_NAME = "name";
  public static final String  RGB_PROPERTY_NAME  = "rgb";

  private Long                 nodeId;

  private String               name;

  private Color                rgb;

  public Colour()
  {
    this.name = null;
    this.rgb = null;
  }

  public Colour(int rgb)
  {
    this.name = null;
    this.rgb = new Color(rgb);
  }

  public Colour(Color rgb)
  {
    this.name = null;
    this.rgb = rgb;
  }

  public Colour(String name, int red, int green, int blue)
  {
    this.name = name;
    this.rgb = new Color(red, green, blue);
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
      properties.put(NAME_PROPERTY_NAME, this.name);
    }

    if (this.rgb != null)
    {
      properties.put(RGB_PROPERTY_NAME, this.rgb.getRGB());
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

  public Color getColor()
  {
    return rgb;
  }

  public void setRgb(int rgb)
  {
    this.rgb = new Color(rgb);
  }

  public void setRgb(Color rgb)
  {
    this.rgb = rgb;
  }

  public int getRed()
  {
    return this.rgb.getRed();
  }

  public int getGreen()
  {
    return this.rgb.getGreen();
  }

  public int getBlue()
  {
    return this.rgb.getBlue();
  }
  
  public String getHexaDecimalColor(){
	  
    String hex = "";
	    
	if(this.rgb != null)
	{
	  hex = String.format("%02x%02x%02x", this.rgb.getRed(), this.rgb.getGreen(), this.rgb.getBlue());	  	
	}
	    
	return hex;
  }
}
