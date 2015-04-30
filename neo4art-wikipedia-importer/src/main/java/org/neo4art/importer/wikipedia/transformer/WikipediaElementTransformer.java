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
package org.neo4art.importer.wikipedia.transformer;

import info.bliki.wiki.dump.WikiArticle;
import info.bliki.wiki.dump.WikiPatternMatcher;

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.neo4art.importer.wikipedia.domain.WikipediaArtMovementPage;
import org.neo4art.importer.wikipedia.domain.WikipediaArtistPage;
import org.neo4art.importer.wikipedia.domain.WikipediaArtworkPage;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaCountryPage;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaFile;
import org.neo4art.importer.wikipedia.domain.WikipediaGeneric;
import org.neo4art.importer.wikipedia.domain.WikipediaMonumentPage;
import org.neo4art.importer.wikipedia.domain.WikipediaMuseumPage;
import org.neo4art.importer.wikipedia.domain.WikipediaOnlyTitleElement;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaProject;
import org.neo4art.importer.wikipedia.domain.WikipediaReligiousBuildingPage;
import org.neo4art.importer.wikipedia.domain.WikipediaSettlementPage;
import org.neo4art.importer.wikipedia.domain.WikipediaTemplate;
import org.neo4art.importer.wikipedia.util.WikipediaElementUtils;

/**
 * It transforms a generic {@link WikiArticle} into a specific {@link WikipediaElement}
 *
 * Work is actually delegated to more more specific transformer.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaElementTransformer {

  /**
   * 
   * @param article
   * @return
   */
  public static WikipediaElement toWikipediaElement(WikiArticle article) {

    switch (WikipediaElementUtils.getWikipediaElementType(article)) {

      case ARTIST_PAGE:
        return new WikipediaArtistPage().from(article);
      case ARTWORK_PAGE:
        return new WikipediaArtworkPage().from(article);
      case ART_MOVEMENT_PAGE:
        return new WikipediaArtMovementPage().from(article);
      case MUSEUM_PAGE:
        return new WikipediaMuseumPage().from(article);
      case MONUMENT_PAGE:
        return new WikipediaMonumentPage().from(article);
      case RELIGIOUS_BUILDING_PAGE:
        return new WikipediaReligiousBuildingPage().from(article);

      case SETTLEMENT_PAGE:
        return new WikipediaSettlementPage().from(article);
      case COUNTRY_PAGE:
        return new WikipediaCountryPage().from(article);

      case PAGE:
        return new WikipediaPage().from(article);
      case CATEGORY:
        return new WikipediaCategory().from(article);
      case TEMPLATE:
        return new WikipediaTemplate().from(article);
      case FILE:
        return new WikipediaFile().from(article);
      case PROJECT:
        return new WikipediaProject().from(article);
      case GENERIC:
      default:
        return new WikipediaGeneric().from(article);
    }
  }

  /**
   * 
   * @param wikipediaElement
   * @param article
   */
  public static String toWikipediaElement(WikipediaElement wikipediaElement, WikiArticle article) {
    
    String infobox = null;

    wikipediaElement.setId(Long.parseLong(article.getId()));
    wikipediaElement.setTitle(article.getTitle());
    wikipediaElement.setRevision(Long.parseLong(article.getRevisionId()));
    wikipediaElement.setTimestamp(DatatypeConverter.parseDateTime(article.getTimeStamp()).getTimeInMillis());

    if (StringUtils.isNotEmpty(article.getText())) {

      WikiPatternMatcher textParser = new WikiPatternMatcher(article.getText());

      // ----- DISAMBIGUATION PAGES are ignored -----

      if (textParser.isDisambiguationPage()) {
      }

      // ----- STUB PAGES are ignored -----

      if (textParser.isStub()) {
      }

      // ----- REDIRECT PAGE -----

      if (textParser.isRedirect()) {
        wikipediaElement.setRedirect(new WikipediaOnlyTitleElement(textParser.getRedirectText()));
      } else {

        // ----- LINKS -----

        List<String> links = textParser.getLinks();
        if (CollectionUtils.isNotEmpty(links)) {
          for (String link : links) {
            wikipediaElement.addLink(new WikipediaOnlyTitleElement(link));
          }
        }

        // ----- CATEGORIES -----

        List<String> categories = textParser.getCategories();
        if (CollectionUtils.isNotEmpty(categories)) {
          for (String category : categories) {
            wikipediaElement.addCategory(new WikipediaCategory(category));
          }
        }
      }

      if (textParser.getInfoBox() != null) {
        infobox = textParser.getInfoBox().dumpRaw();
      }
    }

    return infobox;
  }
}