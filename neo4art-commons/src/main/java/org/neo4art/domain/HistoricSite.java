package org.neo4art.domain;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Neo4ArtLabel;
import org.neo4art.graphdb.Neo4ArtNode;
import org.neo4j.graphdb.Label;

public class HistoricSite implements Neo4ArtNode
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.HistoricSite };

  private Long                 nodeId;

  private String               name;
  private Coordinate           coordinate;
  private URL                  image;
  private Country              country;
  private String               caption;
  private String               location;
  private String               type;

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Coordinate getCoordinate()
  {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate)
  {
    this.coordinate = coordinate;
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

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public Country getCountry()
  {
    return country;
  }

  public void setCountry(Country country)
  {
    this.country = country;
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
