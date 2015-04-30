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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Neo4ArtLabel;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Apr 2015
 */
public class Monument implements Neo4ArtNode
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Monument };

  private Long                 nodeId;

  public String                monumentName;
  public String                nativeName;
  public URL                   image;
  public String                caption;
  public Coordinate            coordinates;
  public String                location;
  public String                designer;
  public String                type;
  public String                material;
  public String                length;
  public String                width;
  public String                height;
  public String                begin;
  public String                complete;
  public String                open;
  public String                dedicatedTo;
  public String                mapImage;
  public String                mapText;
  public String                mapWidth;
  public String                relief;
  public Coordinate            coordinate;
  public String                lat;
  public String                lon;
  public String                extra;

  public Monument()
  {
  }

  public String getMonumentName()
  {
    return monumentName;
  }

  public void setMonumentName(String monumentName)
  {
    this.monumentName = monumentName;
  }

  public String getNativeName()
  {
    return nativeName;
  }

  public void setNativeName(String nativeName)
  {
    this.nativeName = nativeName;
  }

  public URL getImage()
  {
    return image;
  }

  public void setImage(URL url)
  {
    this.image = url;
  }

  public String getCaption()
  {
    return caption;
  }

  public void setCaption(String caption)
  {
    this.caption = caption;
  }

  public Coordinate getCoordinates()
  {
    return coordinates;
  }

  public void setCoordinates(Coordinate coordinate)
  {
    this.coordinates = coordinate;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getDesigner()
  {
    return designer;
  }

  public void setDesigner(String designer)
  {
    this.designer = designer;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getMaterial()
  {
    return material;
  }

  public void setMaterial(String material)
  {
    this.material = material;
  }

  public String getLength()
  {
    return length;
  }

  public void setLength(String length)
  {
    this.length = length;
  }

  public String getWidth()
  {
    return width;
  }

  public void setWidth(String width)
  {
    this.width = width;
  }

  public String getHeight()
  {
    return height;
  }

  public void setHeight(String height)
  {
    this.height = height;
  }

  public String getBegin()
  {
    return begin;
  }

  public void setBegin(String begin)
  {
    this.begin = begin;
  }

  public String getComplete()
  {
    return complete;
  }

  public void setComplete(String complete)
  {
    this.complete = complete;
  }

  public String getOpen()
  {
    return open;
  }

  public void setOpen(String open)
  {
    this.open = open;
  }

  public String getDedicatedTo()
  {
    return dedicatedTo;
  }

  public void setDedicatedTo(String dedicatedTo)
  {
    this.dedicatedTo = dedicatedTo;
  }

  public String getMapImage()
  {
    return mapImage;
  }

  public void setMapImage(String mapImage)
  {
    this.mapImage = mapImage;
  }

  public String getMapText()
  {
    return mapText;
  }

  public void setMapText(String mapText)
  {
    this.mapText = mapText;
  }

  public String getMapWidth()
  {
    return mapWidth;
  }

  public void setMapWidth(String mapWidth)
  {
    this.mapWidth = mapWidth;
  }

  public String getRelief()
  {
    return relief;
  }

  public void setRelief(String relief)
  {
    this.relief = relief;
  }

  public Coordinate getCoordinate()
  {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate)
  {
    this.coordinate = coordinate;
  }

  public String getLat()
  {
    return lat;
  }

  public void setLat(String lat)
  {
    this.lat = lat;
  }

  public String getLon()
  {
    return lon;
  }

  public void setLon(String lon)
  {
    this.lon = lon;
  }

  public String getExtra()
  {
    return extra;
  }

  public void setExtra(String extra)
  {
    this.extra = extra;
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

    if (this.monumentName != null)
    {
      properties.put("monumentName", this.monumentName);
    }
    
    if (this.coordinate != null)
    {
      properties.put("lat", this.coordinate.getLatitude());
      properties.put("lon", this.coordinate.getLongitude());
    }
    
    return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}
