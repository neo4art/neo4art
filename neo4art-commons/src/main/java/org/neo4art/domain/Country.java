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

/**
 * @author Lorenzo Speranzoni
 * @since 4 Apr 2015
 */
public class Country {

  //private String micronation;
  private String conventionalLongName;
  private String nativeName;
  private String commonName;
  //private String status;
  //private String imageFlag;
  //private String altFlag;
  //private String flagBorder;
  //private String imageFlag2;
  //private String altFlag2;
  //private String flag2Border;
  //private String imageCoat;
  //private String altCoat;
  //private String symbolType;
  //private String nationalMotto;
  //private String englishmotto;
  //private String nationalAnthem;
  //private String royalAnthem;
  //private String otherSymbolType;
  //private String otherSymbol;
  //private String imageMap;
  //private String loctext;
  //private String altMap;
  //private String mapCaption;
  //private String imageMap2;
  //private String altMap2;
  //private String mapCaption2;
  //private String capital;
  //private String capital2;
  private String latD;
  private String latM;
  private String latNS;
  private String longD;
  private String longM;
  private String longEW;
  //private String largestCity;
  //private String largestSettlementType;
  //private String largestSettlement;
  //private String officialLanguages;
  //private String nationalLanguages;
  //private String regionalLanguages;
  //private String languagesType;
  //private String languages;
  //private String languagesSub;
  //private String languages2Type;
  //private String languages2;
  //private String languages2Sub;
  //private String ethnicGroups;
  //private String ethnicGroupsYear;
  //private String nationalities;
  //private String religion;
  //private String demonym;
  //private String governmentType;
  //private String leaderTitle1;
  //private String leaderName1;
  //private String leaderTitle2;
  //private String leaderName2;
  //private String leaderTitle3;
  //private String leaderName3;
  //private String leaderTitle4;
  //private String leaderName4;
  //private String leaderTitle5;
  //private String leaderName5;
  //private String leaderTitle6;
  //private String leaderName6;
  //private String legislature;
  //private String upperHouse;
  //private String lowerHouse;
  //private String sovereigntyType;
  //private String sovereigntyNote;
  //private String establishedEvent1;
  //private String establishedDate1;
  //private String establishedEvent2;
  //private String establishedDate2;
  //private String establishedEvent3;
  //private String establishedDate3;
  //private String establishedEvent4;
  //private String establishedDate4;
  //private String establishedEvent5;
  //private String establishedDate5;
  //private String establishedEvent6;
  //private String establishedDate6;
  //private String establishedEvent7;
  //private String establishedDate7;
  //private String establishedEvent8;
  //private String establishedDate8;
  //private String establishedEvent9;
  //private String establishedDate9;
  //private String areaRank;
  //private String areaMagnitude;
  //private String area;
  //private String areaKm2;
  //private String areaSqMi;
  //private String areaFootnote;
  //private String percentWater;
  //private String areaLabel;
  //private String areaLabel2;
  //private String areaData2;
  //private String populationEstimate;
  //private String populationEstimateRank;
  //private String populationEstimateYear;
  //private String populationCensus;
  //private String populationCensusYear;
  //private String populationDensityKm2;
  //private String populationDensitySqMi;
  //private String populationDensityRank;
  //private String nummembers;
  //private String GDPPPP;
  //private String GDPPPPRank;
  //private String GDPPPPYear;
  //private String GDPPPPPerCapita;
  //private String GDPPPPPerCapitaRank;
  //private String GDPNominal;
  //private String GDPNominalRank;
  //private String GDPNominalYear;
  //private String GDPNominalPerCapita;
  //private String GDPNominalPerCapitaRank;
  //private String Gini;
  //private String GiniRef;
  //private String GiniRank;
  //private String GiniYear;
  //private String HDIYear;
  //private String HDI;
  //private String HDIChange;
  //private String HDIRank;
  //private String HDIRef;
  //private String currency;
  //private String currencyCode;
  //private String timeZone;
  //private String utcOffset;
  //private String timeZoneDST;
  //private String utcOffsetDST;
  //private String DSTNote;
  //private String antipodes;
  //private String dateFormat;
  //private String drivesOn;
  //private String cctld;
  //private String iso3166code;
  //private String callingCode;
  //private String imageMap3;
  //private String altMap3;
  //private String footnoteA;
  //private String footnoteB;
  //private String footnoteC;
  //private String footnoteD;
  //private String footnoteE;
  //private String footnoteF;
  //private String footnoteG;
  //private String footnoteH;
  //private String footnotes;
  
  public Country() {
  }

  public String getConventionalLongName() {
    return conventionalLongName;
  }

  public void setConventionalLongName(String conventionalLongName) {
    this.conventionalLongName = conventionalLongName;
  }

  public String getNativeName() {
    return nativeName;
  }

  public void setNativeName(String nativeName) {
    this.nativeName = nativeName;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getLatD() {
    return latD;
  }

  public void setLatD(String latD) {
    this.latD = latD;
  }

  public String getLatM() {
    return latM;
  }

  public void setLatM(String latM) {
    this.latM = latM;
  }

  public String getLatNS() {
    return latNS;
  }

  public void setLatNS(String latNS) {
    this.latNS = latNS;
  }

  public String getLongD() {
    return longD;
  }

  public void setLongD(String longD) {
    this.longD = longD;
  }

  public String getLongM() {
    return longM;
  }

  public void setLongM(String longM) {
    this.longM = longM;
  }

  public String getLongEW() {
    return longEW;
  }

  public void setLongEW(String longEW) {
    this.longEW = longEW;
  }
}