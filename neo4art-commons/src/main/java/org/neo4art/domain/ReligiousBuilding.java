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
public class ReligiousBuilding implements Neo4ArtNode
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.ReligiousBuilding };

  private Long                 nodeId;

  private String               type;
  private String               buildingName;
  private String               nativeName;
  private String               nativeNameLang;
  private URL                  image;
  private String               imageSize;
  private String               alt;
  private String               caption;
  private String               mapType;
  private String               mapSize;
  private String               mapCaption;
  private Settlement           location;
  private Coordinate           coordinate;
  private String               latitude;
  private String               longitude;
  private String               coordinatesRegion;
  private String               religiousAffiliation;
  private String               rite;
  private String               region;
  private String               state;
  private String               province;
  private String               territory;
  private String               prefecture;
  private String               sector;
  private String               district;
  private String               cercle;
  private String               municipality;
  private String               consecrationYear;
  private String               status;
  private String               functionalStatus;
  private String               heritageDesignation;
  private String               leadership;
  private String               patron;
  private String               website;
  private String               architecture;
  private String               architect;
  private String               architectureType;
  private String               architectureStyle;
  private String               generalContractor;
  private String               facadeDirection;
  private String               groundbreaking;
  private String               yearCompleted;
  private String               constructionCost;
  private String               specifications;
  private String               capacity;
  private String               length;
  private String               width;
  private String               widthNave;
  private String               heightMax;
  private String               domeQuantity;
  private String               domeHeightOuter;
  private String               domeHeightInner;
  private String               domeDiaOuter;
  private String               domeDiaInner;
  private String               minaretQuantity;
  private String               minaretHeight;
  private String               spireQuantity;
  private String               spireHeight;
  private String               materials;
  private String               nrhp;
  private String               added;
  private String               refnum;
  private String               designated;

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public ReligiousBuilding()
  {
  }

  public String getBuildingName()
  {
    return buildingName;
  }

  public void setBuildingName(String buildingName)
  {
    this.buildingName = buildingName;
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

  public String getImageSize()
  {
    return imageSize;
  }

  public void setImageSize(String imageSize)
  {
    this.imageSize = imageSize;
  }

  public String getAlt()
  {
    return alt;
  }

  public void setAlt(String alt)
  {
    this.alt = alt;
  }

  public String getCaption()
  {
    return caption;
  }

  public void setCaption(String caption)
  {
    this.caption = caption;
  }

  public String getMapType()
  {
    return mapType;
  }

  public void setMapType(String mapType)
  {
    this.mapType = mapType;
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

  public Settlement getLocation()
  {
    return location;
  }

  public void setLocation(Settlement settlement)
  {
    this.location = settlement;
  }

  public Coordinate getCoordinates()
  {
    return coordinate;
  }

  public void setCoordinates(Coordinate coordinate)
  {
    this.coordinate = coordinate;
  }

  public String getLatitude()
  {
    return latitude;
  }

  public void setLatitude(String latitude)
  {
    this.latitude = latitude;
  }

  public String getLongitude()
  {
    return longitude;
  }

  public void setLongitude(String longitude)
  {
    this.longitude = longitude;
  }

  public String getCoordinatesRegion()
  {
    return coordinatesRegion;
  }

  public void setCoordinatesRegion(String coordinatesRegion)
  {
    this.coordinatesRegion = coordinatesRegion;
  }

  public String getReligiousAffiliation()
  {
    return religiousAffiliation;
  }

  public void setReligiousAffiliation(String religiousAffiliation)
  {
    this.religiousAffiliation = religiousAffiliation;
  }

  public String getRite()
  {
    return rite;
  }

  public void setRite(String rite)
  {
    this.rite = rite;
  }

  public String getRegion()
  {
    return region;
  }

  public void setRegion(String region)
  {
    this.region = region;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String getProvince()
  {
    return province;
  }

  public void setProvince(String province)
  {
    this.province = province;
  }

  public String getTerritory()
  {
    return territory;
  }

  public void setTerritory(String territory)
  {
    this.territory = territory;
  }

  public String getPrefecture()
  {
    return prefecture;
  }

  public void setPrefecture(String prefecture)
  {
    this.prefecture = prefecture;
  }

  public String getSector()
  {
    return sector;
  }

  public void setSector(String sector)
  {
    this.sector = sector;
  }

  public String getDistrict()
  {
    return district;
  }

  public void setDistrict(String district)
  {
    this.district = district;
  }

  public String getCercle()
  {
    return cercle;
  }

  public void setCercle(String cercle)
  {
    this.cercle = cercle;
  }

  public String getMunicipality()
  {
    return municipality;
  }

  public void setMunicipality(String municipality)
  {
    this.municipality = municipality;
  }

  public String getConsecrationYear()
  {
    return consecrationYear;
  }

  public void setConsecrationYear(String consecrationYear)
  {
    this.consecrationYear = consecrationYear;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getFunctionalStatus()
  {
    return functionalStatus;
  }

  public void setFunctionalStatus(String functionalStatus)
  {
    this.functionalStatus = functionalStatus;
  }

  public String getHeritageDesignation()
  {
    return heritageDesignation;
  }

  public void setHeritageDesignation(String heritageDesignation)
  {
    this.heritageDesignation = heritageDesignation;
  }

  public String getLeadership()
  {
    return leadership;
  }

  public void setLeadership(String leadership)
  {
    this.leadership = leadership;
  }

  public String getPatron()
  {
    return patron;
  }

  public void setPatron(String patron)
  {
    this.patron = patron;
  }

  public String getWebsite()
  {
    return website;
  }

  public void setWebsite(String website)
  {
    this.website = website;
  }

  public String getArchitecture()
  {
    return architecture;
  }

  public void setArchitecture(String architecture)
  {
    this.architecture = architecture;
  }

  public String getArchitect()
  {
    return architect;
  }

  public void setArchitect(String architect)
  {
    this.architect = architect;
  }

  public String getArchitectureType()
  {
    return architectureType;
  }

  public void setArchitectureType(String architectureType)
  {
    this.architectureType = architectureType;
  }

  public String getArchitectureStyle()
  {
    return architectureStyle;
  }

  public void setArchitectureStyle(String architectureStyle)
  {
    this.architectureStyle = architectureStyle;
  }

  public String getGeneralContractor()
  {
    return generalContractor;
  }

  public void setGeneralContractor(String generalContractor)
  {
    this.generalContractor = generalContractor;
  }

  public String getFacadeDirection()
  {
    return facadeDirection;
  }

  public void setFacadeDirection(String facadeDirection)
  {
    this.facadeDirection = facadeDirection;
  }

  public String getGroundbreaking()
  {
    return groundbreaking;
  }

  public void setGroundbreaking(String groundbreaking)
  {
    this.groundbreaking = groundbreaking;
  }

  public String getYearCompleted()
  {
    return yearCompleted;
  }

  public void setYearCompleted(String yearCompleted)
  {
    this.yearCompleted = yearCompleted;
  }

  public String getConstructionCost()
  {
    return constructionCost;
  }

  public void setConstructionCost(String constructionCost)
  {
    this.constructionCost = constructionCost;
  }

  public String getSpecifications()
  {
    return specifications;
  }

  public void setSpecifications(String specifications)
  {
    this.specifications = specifications;
  }

  public String getCapacity()
  {
    return capacity;
  }

  public void setCapacity(String capacity)
  {
    this.capacity = capacity;
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

  public String getWidthNave()
  {
    return widthNave;
  }

  public void setWidthNave(String widthNave)
  {
    this.widthNave = widthNave;
  }

  public String getHeightMax()
  {
    return heightMax;
  }

  public void setHeightMax(String heightMax)
  {
    this.heightMax = heightMax;
  }

  public String getDomeQuantity()
  {
    return domeQuantity;
  }

  public void setDomeQuantity(String domeQuantity)
  {
    this.domeQuantity = domeQuantity;
  }

  public String getDomeHeightOuter()
  {
    return domeHeightOuter;
  }

  public void setDomeHeightOuter(String domeHeightOuter)
  {
    this.domeHeightOuter = domeHeightOuter;
  }

  public String getDomeHeightInner()
  {
    return domeHeightInner;
  }

  public void setDomeHeightInner(String domeHeightInner)
  {
    this.domeHeightInner = domeHeightInner;
  }

  public String getDomeDiaOuter()
  {
    return domeDiaOuter;
  }

  public void setDomeDiaOuter(String domeDiaOuter)
  {
    this.domeDiaOuter = domeDiaOuter;
  }

  public String getDomeDiaInner()
  {
    return domeDiaInner;
  }

  public void setDomeDiaInner(String domeDiaInner)
  {
    this.domeDiaInner = domeDiaInner;
  }

  public String getMinaretQuantity()
  {
    return minaretQuantity;
  }

  public void setMinaretQuantity(String minaretQuantity)
  {
    this.minaretQuantity = minaretQuantity;
  }

  public String getMinaretHeight()
  {
    return minaretHeight;
  }

  public void setMinaretHeight(String minaretHeight)
  {
    this.minaretHeight = minaretHeight;
  }

  public String getSpireQuantity()
  {
    return spireQuantity;
  }

  public void setSpireQuantity(String spireQuantity)
  {
    this.spireQuantity = spireQuantity;
  }

  public String getSpireHeight()
  {
    return spireHeight;
  }

  public void setSpireHeight(String spireHeight)
  {
    this.spireHeight = spireHeight;
  }

  public String getMaterials()
  {
    return materials;
  }

  public void setMaterials(String materials)
  {
    this.materials = materials;
  }

  public String getNrhp()
  {
    return nrhp;
  }

  public void setNrhp(String nrhp)
  {
    this.nrhp = nrhp;
  }

  public String getAdded()
  {
    return added;
  }

  public void setAdded(String added)
  {
    this.added = added;
  }

  public String getRefnum()
  {
    return refnum;
  }

  public void setRefnum(String refnum)
  {
    this.refnum = refnum;
  }

  public String getDesignated()
  {
    return designated;
  }

  public void setDesignated(String designated)
  {
    this.designated = designated;
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

    if (this.buildingName != null)
    {
      properties.put("buildingName", this.buildingName);
    }
    
    return properties;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}