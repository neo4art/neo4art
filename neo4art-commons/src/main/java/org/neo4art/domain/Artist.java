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
 * @since 29 Mar 2015
 */
public class Artist {

    private String honorificPrefix;
    private String name;
    private String honorificSuffix;
    private String image;
    private String imageSize;
    private String alt;
    private String caption;
    private String nativeName;
    private String nativeNameLang;
    private String birthName;
    private String birthDate;
    private String birthPlace;
    private String deathDate;
    private String deathPlace;
    private String restingPlace;
    private String restingPlaceCoordinates;
    private String nationality;
    private String education;
    private String almaMater;
    private String knownFor;
    private String notableWorks;
    private String style;
    private String movement;
    private String spouse;
    private String awards;
    private String elected;
    private String patrons;
    private String memorials;
    private String website;

    public Artist() {
    }
    
    public String getHonorificPrefix() {
      return honorificPrefix;
    }
    public void setHonorificPrefix(String honorificPrefix) {
      this.honorificPrefix = honorificPrefix;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getHonorificSuffix() {
      return honorificSuffix;
    }
    public void setHonorificSuffix(String honorificSuffix) {
      this.honorificSuffix = honorificSuffix;
    }
    public String getImage() {
      return image;
    }
    public void setImage(String image) {
      this.image = image;
    }
    public String getImageSize() {
      return imageSize;
    }
    public void setImageSize(String imageSize) {
      this.imageSize = imageSize;
    }
    public String getAlt() {
      return alt;
    }
    public void setAlt(String alt) {
      this.alt = alt;
    }
    public String getCaption() {
      return caption;
    }
    public void setCaption(String caption) {
      this.caption = caption;
    }
    public String getNativeName() {
      return nativeName;
    }
    public void setNativeName(String nativeName) {
      this.nativeName = nativeName;
    }
    public String getNativeNameLang() {
      return nativeNameLang;
    }
    public void setNativeNameLang(String nativeNameLang) {
      this.nativeNameLang = nativeNameLang;
    }
    public String getBirthName() {
      return birthName;
    }
    public void setBirthName(String birthName) {
      this.birthName = birthName;
    }
    public String getBirthDate() {
      return birthDate;
    }
    public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
    }
    public String getBirthPlace() {
      return birthPlace;
    }
    public void setBirthPlace(String birthPlace) {
      this.birthPlace = birthPlace;
    }
    public String getDeathDate() {
      return deathDate;
    }
    public void setDeathDate(String deathDate) {
      this.deathDate = deathDate;
    }
    public String getDeathPlace() {
      return deathPlace;
    }
    public void setDeathPlace(String deathPlace) {
      this.deathPlace = deathPlace;
    }
    public String getRestingPlace() {
      return restingPlace;
    }
    public void setRestingPlace(String restingPlace) {
      this.restingPlace = restingPlace;
    }
    public String getRestingPlaceCoordinates() {
      return restingPlaceCoordinates;
    }
    public void setRestingPlaceCoordinates(String restingPlaceCoordinates) {
      this.restingPlaceCoordinates = restingPlaceCoordinates;
    }
    public String getNationality() {
      return nationality;
    }
    public void setNationality(String nationality) {
      this.nationality = nationality;
    }
    public String getEducation() {
      return education;
    }
    public void setEducation(String education) {
      this.education = education;
    }
    public String getAlmaMater() {
      return almaMater;
    }
    public void setAlmaMater(String almaMater) {
      this.almaMater = almaMater;
    }
    public String getKnownFor() {
      return knownFor;
    }
    public void setKnownFor(String knownFor) {
      this.knownFor = knownFor;
    }
    public String getNotableWorks() {
      return notableWorks;
    }
    public void setNotableWorks(String notableWorks) {
      this.notableWorks = notableWorks;
    }
    public String getStyle() {
      return style;
    }
    public void setStyle(String style) {
      this.style = style;
    }
    public String getMovement() {
      return movement;
    }
    public void setMovement(String movement) {
      this.movement = movement;
    }
    public String getSpouse() {
      return spouse;
    }
    public void setSpouse(String spouse) {
      this.spouse = spouse;
    }
    public String getAwards() {
      return awards;
    }
    public void setAwards(String awards) {
      this.awards = awards;
    }
    public String getElected() {
      return elected;
    }
    public void setElected(String elected) {
      this.elected = elected;
    }
    public String getPatrons() {
      return patrons;
    }
    public void setPatrons(String patrons) {
      this.patrons = patrons;
    }
    public String getMemorials() {
      return memorials;
    }
    public void setMemorials(String memorials) {
      this.memorials = memorials;
    }
    public String getWebsite() {
      return website;
    }
    public void setWebsite(String website) {
      this.website = website;
    }
}
