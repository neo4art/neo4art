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
package org.neo4art.sentiment.deprecated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Lorenzo Speranzoni
 * @since 25 Apr 2015
 */
@Deprecated
public class SentimentInfo
{
  private List<String> relationshipProperty;

  public SentimentInfo()
  {
    this.relationshipProperty = new ArrayList<String>();
  }

  public SentimentInfo(long documentId, long position, String posTag)
  {
    this();

    this.add(documentId, position, posTag);
  }

  public void add(long documentId, long position, String posTag)
  {

    this.add(String.valueOf(documentId), String.valueOf(position), posTag);
  }

  public SentimentInfo add(String[] sentimentInfo)
  {
    CollectionUtils.addAll(this.relationshipProperty, sentimentInfo);

    return this;
  }

  private void add(String documentId, String position, String posTag)
  {

    this.relationshipProperty.add(documentId);
    this.relationshipProperty.add("P_" + position);
    this.relationshipProperty.add("T_" + posTag);
  }

  public String[] toArray()
  {

    return this.relationshipProperty.toArray(new String[this.relationshipProperty.size()]);
  }

  public Map<String, Object> toMap()
  {

    Map<String, Object> map = new HashMap<String, Object>();

    map.put("sentiment", toArray());

    return map;
  }
}
