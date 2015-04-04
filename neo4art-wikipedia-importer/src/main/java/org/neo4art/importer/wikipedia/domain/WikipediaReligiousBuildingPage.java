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

import info.bliki.wiki.dump.WikiArticle;

import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.graph.WikipediaLabel;
import org.neo4art.importer.wikipedia.parser.WikipediaReligiousBuildingInfoboxParser;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Mar 2015
 */
public class WikipediaReligiousBuildingPage extends WikipediaPage implements WikipediaElement {

  private ReligiousBuilding religiousBuilding;

  public WikipediaReligiousBuildingPage() {
  }
  
  public WikipediaReligiousBuildingPage(WikiArticle article) {
    from(article);
  }

  @Override
  public WikipediaLabel getLabel() {
    return WikipediaLabel.WikipediaReligiousBuildingPage;
  }
  
  @Override
  public WikipediaType getType() {
    return WikipediaType.RELIGIOUS_BUILDING_PAGE;
  }

  public ReligiousBuilding getReligiousBuilding() {
    return religiousBuilding;
  }

  public WikipediaElement from(WikiArticle article) {
    
    WikipediaElementTransformer.toWikipediaElement(this, article);
    
    this.religiousBuilding = WikipediaReligiousBuildingInfoboxParser.parse(article.getText());
    
    return this;
  }
}
