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

import org.neo4art.domain.Monument;
import org.neo4art.graph.WikipediaLabel;
import org.neo4art.importer.wikipedia.parser.WikipediaMonumentInfoboxParser;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Mar 2015
 */
public class WikipediaMonumentPage extends WikipediaPage implements WikipediaElement {

  private Monument monument;

  public WikipediaMonumentPage() {
  }
  
  public WikipediaMonumentPage(WikiArticle article) {
    from(article);
  }
  
  @Override
  public WikipediaType getType() {
    return WikipediaType.MONUMENT_PAGE;
  }

  @Override
  public WikipediaLabel getLabel() {
    return WikipediaLabel.WikipediaMonumentPage;
  }

  public Monument getMonument() {
    return monument;
  }
  
  public WikipediaElement from(WikiArticle article) {
    
    WikipediaElementTransformer.toWikipediaElement(this, article);
    
    this.monument = WikipediaMonumentInfoboxParser.parse(article.getText());
    
    return this;
  }
}
