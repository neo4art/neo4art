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

import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Apr 2015
 */
public class Settlement implements Node
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.Settlement };

  private Long                 nodeId;

  private String               name;
  private String               officialName;
  private String               nativeName;
  private String               nativeNameLang;
  private String               otherName;
  private String               settlementType;
  private String               type;

  // private String translitLang1;
  // private String translitLang1Type;
  // private String translitLang1Info;
  // private String translitLang1Type1;
  // private String translitLang1Info1;
  // private String translitLang1Type2;
  // private String translitLang1Info2;
  // private String translitLang2;
  // private String translitLang2Type;
  // private String translitLang2Info;
  // private String translitLang2Type1;
  // private String translitLang2Info1;
  // private String translitLang2Type2;
  // private String translitLang2Info2;
  // private String imageSkyline;
  // private String imagesize;
  // private String imageAlt;
  // private String imageCaption;
  // private String image;
  // private String imageFlag;
  // private String flagSize;
  // private String flagAlt;
  // private String flagBorder;
  // private String flagLink;
  // private String imageSeal;
  // private String sealSize;
  // private String sealAlt;
  // private String sealLink;
  // private String sealType;
  // private String imageShield;
  // private String shieldSize;
  // private String shieldAlt;
  // private String shieldLink;
  // private String imageBlankEmblem;
  // private String blankEmblemType;
  // private String blankEmblemSize;
  // private String blankEmblemAlt;
  // private String blankEmblemLink;
  // private String etymology;
  // private String nickname;
  // private String motto;
  // private String anthem;
  // private String imageMap;
  // private String mapsize;
  // private String map_alt;
  // private String mapCaption;
  // private String imageMap1;
  // private String mapsize1;
  // private String mapAlt1;
  // private String mapCaption1;
  // private String pushpinMap;
  // private String pushpinLabelPosition;
  // private String pushpinLabel;
  // private String pushpinMapAlt;
  // private String pushpinMapsize;
  // private String pushpinRelief;
  // private String pushpinMapCaption;
  // private String pushpinMap1;
  // private String pushpinLabelPosition1;
  // private String pushpinLabel1;
  // private String pushpinMap_alt1;
  // private String pushpinMapsize1;
  // private String pushpinMapCaption1;
  private Coordinate           coordinate;
  // private String coorPinpoint;
  // private String coordinatesRegion;
  // private String coordinatesType;
  // private String coordinatesDisplay;
  // private String coordinatesWikidata;
  // private String coordinatesFormat;
  // private String coordinatesFootnotes;
  // private String gridName;
  // private String gridPosition;
  // private String subdivisionType;
  // private String subdivisionName;
  // private String subdivisionType1;
  // private String subdivisionName1;
  // private String subdivisionType2;
  // private String subdivisionName2;
  // private String establishedTitle;
  // private String establishedDate;
  // private String establishedTitle1;
  // private String establishedDate1;
  // private String establishedTitle2;
  // private String establishedDate2;
  // private String establishedTitle3;
  // private String establishedDate3;
  // private String establishedTitle4;
  // private String establishedDate4;
  // private String extinctTitle;
  // private String extinctDate;
  // private String founder;
  // private String namedFor;
  // private String seatType;
  // private String seat;
  // private String seat1Type;
  // private String seat1;
  // private String partsType;
  // private String partsStyle;
  // private String parts;
  // private String p1;
  // private String p2;
  // private String governmentFootnotes;
  // private String governmentType;
  // private String governingBody;
  // private String leaderParty;
  // private String leaderTitle;
  // private String leaderName;
  // private String leaderTitle1;
  // private String leaderName1;
  // private String totalType;
  // private String unitPref;
  // private String areaFootnotes;
  // private String areaMagnitude;
  // private String dunamLink;
  // private String areaTotalKm2;
  // private String areaTotalSqMi;
  // private String areaTotalHa;
  // private String areaTotalAcre;
  // private String areaTotalDunam;
  // private String areaLandKm2;
  // private String areaLandSqMi;
  // private String areaLandHa;
  // private String areaLandAcre;
  // private String areaLandDunam;
  // private String areaWaterKm2;
  // private String areaWaterSqMi;
  // private String areaWaterHa;
  // private String areaWaterAcre;
  // private String areaWaterDunam;
  // private String areaWaterPercent;
  // private String areaUrbanFootnotes;
  // private String areaUrbanKm2;
  // private String areaUrbanSqMi;
  // private String areaUrbanHa;
  // private String areaUrbanAcre;
  // private String areaUrbanDunam;
  // private String areaRuralFootnotes;
  // private String areaRuralKm2;
  // private String areaRuralSqMi;
  // private String areaRuralHa;
  // private String areaRuralAcre;
  // private String areaRuralDunam;
  // private String areaMetroFootnotes;
  // private String areaMetroKm2;
  // private String areaMetroSqMi;
  // private String areaMetroHa;
  // private String areaMetroAcre;
  // private String areaMetroDunam;
  // private String areaRank;
  // private String areaBlank1Title;
  // private String areaBlank1Km2;
  // private String areaBlank1SqMi;
  // private String areaBlank1Ha;
  // private String areaBlank1Acre;
  // private String areaBlank1Dunam;
  // private String areaBlank2Title;
  // private String areaBlank2Km2;
  // private String areaBlank2SqMi;
  // private String areaBlank2Ha;
  // private String areaBlank2Acre;
  // private String areaBlank2Dunam;
  // private String areaNote;
  // private String dimensionsFootnotes;
  // private String lengthKm;
  // private String lengthMi;
  // private String widthKm;
  // private String widthMi;
  // private String elevationFootnotes;
  // private String elevationM;
  // private String elevationFt;
  // private String elevationPoint;
  // private String elevationMaxFootnotes;
  // private String elevationMaxM;
  // private String elevationMaxFt;
  // private String elevationMaxPoint;
  // private String elevationMaxRank;
  // private String elevationMinFootnotes;
  // private String elevationMinM;
  // private String elevationMinFt;
  // private String elevationMinPoint;
  // private String elevationMinRank;
  // private String populationAsOf;
  // private String populationFootnotes;
  // private String populationTotal;
  // private String popEstAsOf;
  // private String popEstFootnotes;
  // private String populationEst;
  // private String populationRank;
  // private String populationDensityKm2;
  // private String populationDensitySqMi;
  // private String populationUrbanFootnotes;
  // private String populationUrban;
  // private String populationDensityUrbanKm2;
  // private String populationDensityUrbanSqMi;
  // private String populationRuralFootnotes;
  // private String populationRural;
  // private String populationDensityRuralKm2;
  // private String populationDensityRuralSqMi;
  // private String populationMetroFootnotes;
  // private String populationMetro;
  // private String populationDensityMetroKm2;
  // private String populationDensityMetroSqMi;
  // private String populationDensity;
  // private String populationDensityRank;
  // private String populationBlank1Title;
  // private String populationBlank1;
  // private String populationDensityBlank1Km2;
  // private String populationDensityBlank1SqMi;
  // private String populationBlank2Title;
  // private String populationBlank2;
  // private String populationDensityBlank2Km2;
  // private String populationDensityBlank2SqMi;
  // private String populationDemonym;
  // private String populationNote;
  // private String demographicsType1;
  // private String demographics1Footnotes;
  // private String demographics1Title1;
  // private String demographics1Info1;
  // private String demographicsType2;
  // private String demographics2Footnotes;
  // private String demographics2Title1;
  // private String demographics2Info1;
  // private String timezone1;
  // private String utcOffset1;
  // private String timezone1DST;
  // private String utcOffset1DST;
  // private String timezone2;
  // private String utcOffset2;
  // private String timezone2DST;
  // private String utcOffset2DST;
  // private String postalCodeType;
  // private String postalCode;
  // private String postal2CodeType;
  // private String postal2Code;
  // private String areaCodeType;
  // private String areaCode;
  // private String geocode;
  // private String isoCode;
  // private String registrationPlate;
  // private String blankNameSec1;
  // private String blankInfoSec1;
  // private String blank1NameSec1;
  // private String blank1InfoSec1;
  // private String blank2NameSec1;
  // private String blank2InfoSec1;
  // private String blankNameSec2;
  // private String blankInfoSec2;
  // private String blank1NameSec2;
  // private String blank1InfoSec2;
  // private String blank2NameSec2;
  // private String blank2InfoSec2;
  private String               website;

  // private String footnotes;

  public Settlement()
  {
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getOfficialName()
  {
    return officialName;
  }

  public void setOfficialName(String officialName)
  {
    this.officialName = officialName;
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

  public String getOtherName()
  {
    return otherName;
  }

  public void setOtherName(String otherName)
  {
    this.otherName = otherName;
  }

  public String getSettlementType()
  {
    return settlementType;
  }

  public void setSettlementType(String settlementType)
  {
    this.settlementType = settlementType;
  }

  public Coordinate getCoordinate()
  {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate)
  {
    this.coordinate = coordinate;
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
