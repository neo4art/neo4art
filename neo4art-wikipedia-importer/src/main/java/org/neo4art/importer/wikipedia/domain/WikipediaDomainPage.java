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

package org.neo4art.importer.wikipedia.domain;

import java.util.Map;

import org.neo4art.graphdb.Node;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Mar 2015
 */
public class WikipediaDomainPage extends WikipediaAbstractElement implements WikipediaElement {

  private Node          artNode;

  private WikipediaType type;

  private Label[]       labels;

  public WikipediaDomainPage(Node artNode, Label[] labels, WikipediaType type) {
    this.artNode = artNode;
    this.labels = labels;
    this.type = type;
  }

  @Override
  public WikipediaType getType() {
    return this.type;
  }

  public Node getArtNode() {
    return artNode;
  }

  @Override
  public Label[] getLabels() {
    return this.labels;
  }
  
  /**
   * @see org.neo4art.importer.wikipedia.domain.WikipediaAbstractElement#getProperties()
   */
  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = super.getProperties();
    properties.putAll(this.artNode.getProperties());
    return properties;
  }
}
