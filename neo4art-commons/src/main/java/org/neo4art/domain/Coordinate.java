package org.neo4art.domain;

public class Coordinate
{
  private String name;
  private String map;

  private String latD;
  private String latM;
  private String latS;
  private String latNS;

  private String longD;
  private String longM;
  private String longS;
  private String longEW;
  
  private String latitude;
  private String longitude;

  private Double coordsRef;

  public Coordinate()
  {
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
    return Double.parseDouble(latD);
  }

  public void setLatD(String latD)
  {
    if (latD == null)
    {
      this.latD = null;
    }
    else
    {
      this.latD = latD;
    }
  }

  public double getLatM()
  {
    return Double.parseDouble(latM);
  }

  public void setLatM(String latM)
  {
    if (latM == null)
    {
      this.latM = null;
    }
    else
    {
      this.latM = latM;
    }
  }

  public double getLatS()
  {
    return Double.parseDouble(latS);
  }

  public void setLatS(String latS)
  {
    if (latS == null)
    {
      this.latS = null;
    }
    else
    {
      this.latS = latS;
    }
  }

  public String getLatNS()
  {
    return latNS;
  }

  public void setLatNS(String latNS)
  {
    if (latNS == null)
    {
      this.latNS = null;
    }
    else
    {
      this.latNS = latNS;
    }
  }

  public double getLongD()
  {
    return Double.parseDouble(longD);
  }

  public void setLongD(String longD)
  {
    if (longD == null)
    {
      this.longD = null;
    }
    else
    {
      this.longD = longD;
    }
  }

  public double getLongM()
  {
    return Double.parseDouble(longM);
  }

  public void setLongM(String longM)
  {
    if (longM == null)
    {
      this.longM = null;
    }
    else
    {
      this.longM = longM;
    }
  }

  public double getLongS()
  {
    return Double.parseDouble(longS);
  }

  public void setLongS(String longS)
  {
    if (longS == null)
    {
      this.longS = null;
    }
    else
    {
      this.longS = longS;
    }
  }

  public String getLongEW()
  {
    return longEW;
  }

  public void setLongEW(String longEW)
  {
    if (longEW == null)
    {
      this.longEW = null;
    }
    else
    {
      this.longEW = longEW;
    }
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
      latitude += Double.parseDouble(this.latD);

    if (this.latM != null)
      latitude += Double.parseDouble(this.latM) / 60;

    if (this.latS != null)
      latitude += Double.parseDouble(this.latS) / 3600;

    return latitude;
  }

  public double getLongitude()
  {
    double longitude = 0;
    if (this.latD != null)
      longitude += Double.parseDouble(this.longD);

    if (this.latM != null)
      longitude += Double.parseDouble(this.longM) / 60;

    if (this.latS != null)
      longitude += Double.parseDouble(this.longS) / 3600;

    return longitude;
  }
}
