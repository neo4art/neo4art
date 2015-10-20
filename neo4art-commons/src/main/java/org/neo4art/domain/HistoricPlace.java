package org.neo4art.domain;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

public class HistoricPlace implements Node
{
  private static final Label[] LABELS = new Label[] { Neo4ArtLabel.HistoricPlace };

  private Long                 nodeId;

  private String               name;
  private String               nhrp_type;
  private Coordinate           coordinate;
  private String               website;
  private String               type;
  private URL                  image;

  public URL getImage()
  {
    return image;
  }

  public void setImage(URL image)
  {
    this.image = image;
  }

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

  public String getNhrp_type()
  {
    return nhrp_type;
  }

  public void setNhrp_type(String nhrp_type)
  {
    this.nhrp_type = nhrp_type;
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
