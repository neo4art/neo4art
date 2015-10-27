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
 * 
 * @author Mattia Zaratin
 * @since 30 Apr 2015
 */
public class Coordinates {

  public static final int COMPUTED   = 0;
  public static final int UNCOMPLETE = 1;

  private String          name;
  private String          map;

  private Double          latD;
  private Double          latM;
  private Double          latS;
  private String          latNS;

  private Double          longD;
  private Double          longM;
  private Double          longS;
  private String          longEW;

  private Double          coordsRef;

  private Double          latitude;
  private Double          longitude;

  public Coordinates() {

    this.name = null;
    this.map = null;

    this.latD = null;
    this.latM = null;
    this.latS = null;
    this.latNS = null;

    this.longD = null;
    this.longM = null;
    this.longS = null;
    this.longEW = null;

    this.coordsRef = null;

    this.latitude = null;
    this.longitude = null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public Double getLatD() {
    return latD;
  }

  public void setLatD(Double latD) {
    this.latD = latD;
  }

  public Double getLatM() {
    return latM;
  }

  public void setLatM(Double latM) {
    this.latM = latM;
  }

  public Double getLatS() {
    return latS;
  }

  public void setLatS(Double latS) {
    this.latS = latS;
  }

  public String getLatNS() {
    return latNS;
  }

  public void setLatNS(String latNS) {
    this.latNS = latNS;
  }

  public Double getLongD() {
    return longD;
  }

  public void setLongD(Double longD) {
    this.longD = longD;
  }

  public Double getLongM() {
    return longM;
  }

  public void setLongM(Double longM) {
    this.longM = longM;
  }

  public Double getLongS() {
    return longS;
  }

  public void setLongS(Double longS) {
    this.longS = longS;
  }

  public String getLongEW() {
    return longEW;
  }

  public void setLongEW(String longEW) {
    this.longEW = longEW;
  }

  public Double getCoordsRef() {
    return coordsRef;
  }

  public void setCoordsRef(Double coordsRef) {
    this.coordsRef = coordsRef;
  }

  public Double getLatitude() {

    if (this.latitude == null && this.latD != null && this.latM != null && this.latS != null) {

      latitude  = this.latD;
      latitude += this.latM / 60;
      latitude += this.latS / 3600;
    }

    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {

    if (this.longitude == null && this.longD != null && this.longM != null && this.longS != null) {

      longitude  = this.longD;
      longitude += this.longM / 60;
      longitude += this.longS / 3600;
    }

    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public int getStatus() {
    return (this.getLatitude() != null && this.getLongitude() != null) ? COMPUTED : UNCOMPLETE;
  }
}