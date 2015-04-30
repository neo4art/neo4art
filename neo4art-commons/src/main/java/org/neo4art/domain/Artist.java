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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Neo4ArtLabel;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public class Artist implements Neo4ArtNode
{
  private static final Label[]   LABELS = new Label[] { Neo4ArtLabel.Artist };

  private Long                   nodeId;

  private String                 honorificPrefix;
  private String                 name;
  private String                 honorificSuffix;
  private URL                    image;
  private String                 imageSize;
  private String                 alt;
  private String                 caption;
  private String                 nativeName;
  private String                 nativeNameLang;
  private String                 birthName;
  private Calendar               birthDate;
  private String                 birthPlace;
  private Calendar               deathDate;
  private String                 deathPlace;
  private Coordinate             restingPlace;
  private Coordinate             restingPlaceCoordinates;
  private Country                nationality;
  private String                 education;
  private String                 almaMater;
  private String                 knownFor;
  private ArrayList<Artwork>     notableWorks;
  private String                 style;
  private ArrayList<ArtMovement> movement;
  private String                 spouse;
  private String                 awards;
  private String                 elected;
  private String                 patrons;
  private String                 memorials;
  private String                 website;
  private String                 field;
  private String                 type;

  public Artist()
  {
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getField()
  {
    return field;
  }

  public void setField(String field)
  {
    this.field = field;
  }

  public String getTrainer()
  {
    return trainer;
  }

  public void setTrainer(String trainer)
  {
    this.trainer = trainer;
  }

  private String trainer;

  public String getHonorificPrefix()
  {
    return honorificPrefix;
  }

  public void setHonorificPrefix(String honorificPrefix)
  {
    this.honorificPrefix = honorificPrefix;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getHonorificSuffix()
  {
    return honorificSuffix;
  }

  public void setHonorificSuffix(String honorificSuffix)
  {
    this.honorificSuffix = honorificSuffix;
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

  public void setImageSize(String string)
  {
    this.imageSize = string;
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

  public String getBirthName()
  {
    return birthName;
  }

  public void setBirthName(String birthName)
  {
    this.birthName = birthName;
  }

  public Calendar getBirthDate()
  {
    return birthDate;
  }

  public void setBirthDate(Calendar calendar)
  {
    this.birthDate = calendar;
  }

  public String getBirthPlace()
  {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace)
  {
    this.birthPlace = birthPlace;
  }

  public Calendar getDeathDate()
  {
    return deathDate;
  }

  public void setDeathDate(Calendar date)
  {

    this.deathDate = date;
  }

  public String getDeathPlace()
  {
    return deathPlace;
  }

  public void setDeathPlace(String deathPlace)
  {
    this.deathPlace = deathPlace;
  }

  public Coordinate getRestingPlace()
  {
    return restingPlace;
  }

  public void setRestingPlace(Coordinate coordinate)
  {
    this.restingPlace = coordinate;
  }

  public Coordinate getRestingPlaceCoordinates()
  {
    return restingPlaceCoordinates;
  }

  public void setRestingPlaceCoordinates(Coordinate coordinate)
  {
    this.restingPlaceCoordinates = coordinate;
  }

  public Country getNationality()
  {
    return nationality;
  }

  public void setNationality(Country country)
  {
    this.nationality = country;
  }

  public String getEducation()
  {
    return education;
  }

  public void setEducation(String education)
  {
    this.education = education;
  }

  public String getAlmaMater()
  {
    return almaMater;
  }

  public void setAlmaMater(String almaMater)
  {
    this.almaMater = almaMater;
  }

  public String getKnownFor()
  {
    return knownFor;
  }

  public void setKnownFor(String knownFor)
  {
    this.knownFor = knownFor;
  }

  public ArrayList<Artwork> getNotableWorks()
  {
    return notableWorks;
  }

  public void setNotableWorks(ArrayList<Artwork> arrayList)
  {
    this.notableWorks = arrayList;
  }

  public String getStyle()
  {
    return style;
  }

  public void setStyle(String style)
  {
    this.style = style;
  }

  public ArrayList<ArtMovement> getMovement()
  {
    return movement;
  }

  public void setMovement(ArrayList<ArtMovement> arrayList)
  {
    this.movement = arrayList;
  }

  public String getSpouse()
  {
    return spouse;
  }

  public void setSpouse(String spouse)
  {
    this.spouse = spouse;
  }

  public String getAwards()
  {
    return awards;
  }

  public void setAwards(String awards)
  {
    this.awards = awards;
  }

  public String getElected()
  {
    return elected;
  }

  public void setElected(String elected)
  {
    this.elected = elected;
  }

  public String getPatrons()
  {
    return patrons;
  }

  public void setPatrons(String patrons)
  {
    this.patrons = patrons;
  }

  public String getMemorials()
  {
    return memorials;
  }

  public void setMemorials(String memorials)
  {
    this.memorials = memorials;
  }

  public String getWebsite()
  {
    return website;
  }

  public void setWebsite(String website)
  {
    this.website = website;
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
