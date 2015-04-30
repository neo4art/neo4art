package org.neo4art.domain;

public class Coordinate
{
  private String name;
  private String map;
  
  private Double latD;
  private Double latM;
  private Double latS;
  private String latNS;
  
  private Double longD;
  private Double longM;
  private Double longS;
  private String longEW;
  
  private Double coordsRef;

  public Coordinate()
  {
    this.name      = null;
    this.map       = null;
    this.latD      = null;
    this.latM      = null;
    this.latS      = null;
    this.latNS     = null;
    this.longD     = null;
    this.longM     = null;
    this.longS     = null;
    this.longEW    = null;
    this.coordsRef = null;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getMap()
  {
    return map;
  }

  public void setMap(String map)
  {
    this.map = map;
  }

  public double getLatD()
  {
    return latD;
  }

  public void setLatD(double latD)
  {
    this.latD = latD;
  }

  public double getLatM()
  {
    return latM;
  }

  public void setLatM(double latM)
  {
    this.latM = latM;
  }

  public double getLatS()
  {
    return latS;
  }

  public void setLatS(double latS)
  {
    this.latS = latS;
  }

  public String getLatNS()
  {
    return latNS;
  }

  public void setLatNS(String latNS)
  {
    this.latNS = latNS;
  }

  public double getLongD()
  {
    return longD;
  }

  public void setLongD(double longD)
  {
    this.longD = longD;
  }

  public double getLongM()
  {
    return longM;
  }

  public void setLongM(double longM)
  {
    this.longM = longM;
  }

  public double getLongS()
  {
    return longS;
  }

  public void setLongS(double longS)
  {
    this.longS = longS;
  }

  public String getLongEW()
  {
    return longEW;
  }

  public void setLongEW(String longEW)
  {
    this.longEW = longEW;
  }

  public double getCoordsRef()
  {
    return coordsRef;
  }

  public void setCoordsRef(double coordsRef)
  {
    this.coordsRef = coordsRef;
  }

  @Override
  public String toString()
  {
    return "Coordinate [name=" + name + ", map=" + map + ", latD=" + latD + ", latM=" + latM + ", latS=" + latS + ", latNS=" + latNS + ", longD=" + longD + ", longM=" + longM + ", longS=" + longS + ", longEW=" + longEW + ", coordsRef=" + coordsRef + "]";
  }

  public double getLatitude()
  {
    double latitude = 0;
    
    if (this.latD != null)
      latitude += this.latD;
    
    if (this.latM != null)
      latitude += this.latM / 60;
    
    if (this.latS != null)
      latitude += this.latS / 3600;
    
    return latitude;
  }

  public double getLongitude()
  {
    double longitude = 0;
    
    if (this.longD != null)
      longitude += this.longD;
    
    if (this.longM != null)
      longitude += this.longM / 60;
    
    if (this.longS != null)
      longitude += this.longS / 3600;
    
    return longitude;
  }
  
  @Deprecated
  public String getCoordinate()
  {

    return latD + " " + longD;
  }

  @Deprecated
  public String getCoordinateComplete()
  {

    return latD + " " + latM + " " + latS + "" + latNS + " " + longD + " " + longM + " " + longS + "" + longEW;
  }
}
