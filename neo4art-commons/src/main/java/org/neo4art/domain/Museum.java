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
 * @since 29 Mar 2015
 */
public class Museum implements Neo4ArtNode
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Museum };

  private Long                 nodeId;

  private String               name;
  private int                  area;
  private String               nativeName;
  private String               buildingName;
  private String               nativeNameLang;
  private URL                  image;
  private String               imagesize;
  private String               caption;
  private String               alt;
  private String               mapType;
  private String               mapRelief;
  private String               mapSize;
  private String               mapCaption;
  private String               mapDotLabel;
  private Coordinate           coordinate;
  private String               coordinatesType;
  private String               coordinatesRegion;
  private String               coordinatesFormat;
  private String               coordinatesDisplay;
  private Coordinate           coordinates;
  private String               formerName;
  private String               established;
  private String               dissolved;
  private String               location;
  private String               type;
  private String               accreditation;
  private String               keyHoldings;
  private String               collections;
  private String               collectionSize;
  private String               visitors;
  private String               founder;
  private String               director;
  private String               president;
  private String               chairperson;
  private String               curator;
  private String               historian;
  private String               owner;
  private String               publictransit;
  private String               carPark;
  private String               parking;
  private String               network;
  private String               website;
  private String               embedded;

  public Museum()
  {
  }

  public int getArea()
  {
    return area;
  }

  public void setArea(int area)
  {
    this.area = area;
  }

  public String getName()
  {
    return name;
  }

  public void setBuildingName(String buildingName)
  {
    this.buildingName = buildingName;
  }
  
  public String getBuildingName()
  {
    return buildingName;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getNativeName()
  {
    return nativeName;
  }

  public void setNativeName(String nativeName)
  {
    this.nativeName = nativeName;
  }

  public String getNativeNameLang()
  {
    return nativeNameLang;
  }

  public void setNativeNameLang(String nativeNameLang)
  {
    this.nativeNameLang = nativeNameLang;
  }

  public URL getImage()
  {
    return image;
  }

  public void setImage(URL url)
  {
    this.image = url;
  }

  public String getImagesize()
  {
    return imagesize;
  }

  public void setImagesize(String imagesize)
  {
    this.imagesize = imagesize;
  }

  public String getCaption()
  {
    return caption;
  }

  public void setCaption(String caption)
  {
    this.caption = caption;
  }

  public String getAlt()
  {
    return alt;
  }

  public void setAlt(String alt)
  {
    this.alt = alt;
  }

  public String getMapType()
  {
    return mapType;
  }

  public void setMapType(String mapType)
  {
    this.mapType = mapType;
  }

  public String getMapRelief()
  {
    return mapRelief;
  }

  public void setMapRelief(String mapRelief)
  {
    this.mapRelief = mapRelief;
  }

  public String getMapSize()
  {
    return mapSize;
  }

  public void setMapSize(String mapSize)
  {
    this.mapSize = mapSize;
  }

  public String getMapCaption()
  {
    return mapCaption;
  }

  public void setMapCaption(String mapCaption)
  {
    this.mapCaption = mapCaption;
  }

  public String getMapDotLabel()
  {
    return mapDotLabel;
  }

  public void setMapDotLabel(String mapDotLabel)
  {
    this.mapDotLabel = mapDotLabel;
  }

  public Coordinate getCoordinate()
  {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate)
  {
    this.coordinate = coordinate;
  }

  public String getCoordinatesType()
  {
    return coordinatesType;
  }

  public void setCoordinatesType(String coordinatesType)
  {
    this.coordinatesType = coordinatesType;
  }

  public String getCoordinatesRegion()
  {
    return coordinatesRegion;
  }

  public void setCoordinatesRegion(String coordinatesRegion)
  {
    this.coordinatesRegion = coordinatesRegion;
  }

  public String getCoordinatesFormat()
  {
    return coordinatesFormat;
  }

  public void setCoordinatesFormat(String coordinatesFormat)
  {
    this.coordinatesFormat = coordinatesFormat;
  }

  public String getCoordinatesDisplay()
  {
    return coordinatesDisplay;
  }

  public void setCoordinatesDisplay(String coordinatesDisplay)
  {
    this.coordinatesDisplay = coordinatesDisplay;
  }

  public Coordinate getCoordinates()
  {
    return coordinates;
  }

  public void setCoordinates(Coordinate coordinate)
  {
    this.coordinates = coordinate;
  }

  public String getFormerName()
  {
    return formerName;
  }

  public void setFormerName(String formerName)
  {
    this.formerName = formerName;
  }

  public String getEstablished()
  {
    return established;
  }

  public void setEstablished(String string)
  {
    this.established = string;
  }

  public String getDissolved()
  {
    return dissolved;
  }

  public void setDissolved(String dissolved)
  {
    this.dissolved = dissolved;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getAccreditation()
  {
    return accreditation;
  }

  public void setAccreditation(String accreditation)
  {
    this.accreditation = accreditation;
  }

  public String getKeyHoldings()
  {
    return keyHoldings;
  }

  public void setKeyHoldings(String keyHoldings)
  {
    this.keyHoldings = keyHoldings;
  }

  public String getCollections()
  {
    return collections;
  }

  public void setCollections(String collections)
  {
    this.collections = collections;
  }

  public String getCollectionSize()
  {
    return collectionSize;
  }

  public void setCollectionSize(String collectionSize)
  {
    this.collectionSize = collectionSize;
  }

  public String getVisitors()
  {
    return visitors;
  }

  public void setVisitors(String visitors)
  {
    this.visitors = visitors;
  }

  public String getFounder()
  {
    return founder;
  }

  public void setFounder(String founder)
  {
    this.founder = founder;
  }

  public String getDirector()
  {
    return director;
  }

  public void setDirector(String director)
  {
    this.director = director;
  }

  public String getPresident()
  {
    return president;
  }

  public void setPresident(String president)
  {
    this.president = president;
  }

  public String getChairperson()
  {
    return chairperson;
  }

  public void setChairperson(String chairperson)
  {
    this.chairperson = chairperson;
  }

  public String getCurator()
  {
    return curator;
  }

  public void setCurator(String curator)
  {
    this.curator = curator;
  }

  public String getHistorian()
  {
    return historian;
  }

  public void setHistorian(String historian)
  {
    this.historian = historian;
  }

  public String getOwner()
  {
    return owner;
  }

  public void setOwner(String owner)
  {
    this.owner = owner;
  }

  public String getPublictransit()
  {
    return publictransit;
  }

  public void setPublictransit(String publictransit)
  {
    this.publictransit = publictransit;
  }

  public String getCarPark()
  {
    return carPark;
  }

  public void setCarPark(String carPark)
  {
    this.carPark = carPark;
  }

  public String getParking()
  {
    return parking;
  }

  public void setParking(String parking)
  {
    this.parking = parking;
  }

  public String getNetwork()
  {
    return network;
  }

  public void setNetwork(String network)
  {
    this.network = network;
  }

  public String getWebsite()
  {
    return website;
  }

  public void setWebsite(String website)
  {
    this.website = website;
  }

  public String getEmbedded()
  {
    return embedded;
  }

  public void setEmbedded(String embedded)
  {
    this.embedded = embedded;
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
}
