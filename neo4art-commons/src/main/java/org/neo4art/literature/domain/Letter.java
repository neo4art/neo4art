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

package org.neo4art.literature.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4art.literature.graphdb.LiteratureLabel;
import org.neo4j.graphdb.Label;

/**
 * 
 * @author Enrico De Benetti
 * @since 28 Apr 2015
 */
public class Letter implements Document
{
  private static final Label[] LABELS = new Label[] { LiteratureLabel.Letter };

  private Long                 nodeId;

  private String               place;
  private String               content;
  private String               from;
  private String               to;
  private String               date;
  private String               url;
  private String               title;
  private String               museum;

  private List<String>         link;

  public Letter()
  {
    this.nodeId = null;
    this.place = "";
    this.content = "";
    this.from = "";
    this.to = "";
    this.date = "";
    this.url = "";
    this.title = "";
    this.museum = "";
  }

  public Letter(String place, String content, String from, String to, String date, String url, String museum)
  {
    this.nodeId = null;
    this.place = place;
    this.content = content;
    this.from = from;
    this.to = to;
    this.date = date;
    this.url = url;
    this.museum = museum;
  }

  public void setLink(List<String> links)
  {
    this.link = links;
  }

  public List<String> getLink()
  {
    return link;
  }

  public String getMuseum()
  {
    return museum;
  }

  public void setMuseum(String museum)
  {
    this.museum = museum;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String text)
  {
    this.content = text;
  }

  public String getFrom()
  {
    return from;
  }

  public void setFrom(String from)
  {
    this.from = from;
  }

  public String getTo()
  {
    return to;
  }

  public void setTo(String to)
  {
    this.to = to;
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getPlace()
  {
    return place;
  }

  public void setPlace(String place)
  {
    this.place = place;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
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

  public Map<String, Object> getProperties()
  {
    Map<String, Object> properties = new HashMap<String, Object>();

    if (this.getFrom() != null)
    {
      properties.put("from", this.getFrom());
    }
    
    if (this.getTo() != null)
    {
      properties.put("to", this.getTo());
    }
    
    if (this.getPlace() != null)
    {
      properties.put("place", this.getPlace());
    }
    
    if (this.getDate() != null)
    {
      properties.put("when", this.getDate());
    }
    
    if (this.getTitle() != null)
    {
      properties.put("title", this.getTitle());
    }
    
    if (this.getUrl() != null)
    {
      properties.put("source", this.getUrl());
    }
    
    if (this.getMuseum() != null)
    {
      properties.put("museum", this.getMuseum());
    }
    
    if (this.getLink() != null)
    {
      properties.put("links", this.getLink().toArray(new String[this.getLink().size()]));
    }
    
    return properties;
  }

  public Label[] getLabels()
  {
    return LABELS;
  }
}
