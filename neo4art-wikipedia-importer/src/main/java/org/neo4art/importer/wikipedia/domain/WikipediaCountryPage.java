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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Country;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.neo4art.importer.wikipedia.parser.WikipediaCountryInfoboxParser;
import org.neo4art.importer.wikipedia.transformer.WikipediaElementTransformer;
import org.neo4j.graphdb.Label;

/**
 * @author Lorenzo Speranzoni
 * @since 4 Mar 2015
 */
public class WikipediaCountryPage extends WikipediaPage implements WikipediaElement
{
  private static Log logger = LogFactory.getLog(WikipediaCountryPage.class);

  private static final Label[] LABELS = new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaCountryPage };

  private Country country;

  public WikipediaCountryPage()
  {
  }

  public WikipediaCountryPage(WikiArticle article)
  {
    from(article);
  }

  @Override
  public WikipediaType getType()
  {
    return WikipediaType.COUNTRY_PAGE;
  }

  public Country getCountry()
  {
    return country;
  }

  public WikipediaElement from(WikiArticle article)
  {
    String infobox = WikipediaElementTransformer.toWikipediaElement(this, article);

    if (StringUtils.isNoneEmpty(infobox))
    {
      try
      {
        this.country = WikipediaCountryInfoboxParser.parse(infobox);
      }
      catch (Exception e)
      {
        logger.error("Error parsing Country infobox for page: " + article.getTitle() + ". Cause: " + e.getMessage());
      }
    }

    return this;
  }

  @Override
  public Label[] getLabels()
  {
    return LABELS;
  }
}
