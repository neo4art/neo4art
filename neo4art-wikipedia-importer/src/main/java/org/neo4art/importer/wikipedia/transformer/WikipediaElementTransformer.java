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
import org.neo4art.importer.wikipedia.domain.WikipediaArtElement;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaFile;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaProject;
import org.neo4art.importer.wikipedia.domain.WikipediaTemplate;
import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.neo4art.importer.wikipedia.parser.WikipediaArtistInfoboxParser;
import org.neo4j.graphdb.Label;

import toberefactored.parser.WikipediaArtMovementInfoboxParser;
import toberefactored.parser.WikipediaArtworkInfoboxParser;
import toberefactored.parser.WikipediaColourInfoboxParser;
import toberefactored.parser.WikipediaCountryInfoboxParser;
import toberefactored.parser.WikipediaDocumentInfoboxParser;
import toberefactored.parser.WikipediaInfoboxParser;
import toberefactored.parser.WikipediaMuseumInfoboxParser;
import toberefactored.parser.WikipediaReligiousBuildingInfoboxParser;
import toberefactored.parser.WikipediaSettlementInfoboxParser;

/**
 * It transforms a generic {@link WikiArticle} into a specific {@link WikipediaElement}
 *
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaElementTransformer {
  public static WikipediaElement toWikipediaElement(WikiArticle article) {

    WikiPatternMatcher articleTextParser = new WikiPatternMatcher(article.getText());

    // ----- DISAMBIGUATION, STUB AND REDIRECTED PAGES are ignored -----
    if (articleTextParser.isDisambiguationPage() || articleTextParser.isStub() || articleTextParser.isRedirect()) {
      
      return null;
    }
    else {
      
      WikipediaElement wikipediaElement = from(article, articleTextParser);
  
      wikipediaElement.setId(Long.parseLong(article.getId()));
      wikipediaElement.setTitle(article.getTitle());
      wikipediaElement.setRevision(Long.parseLong(article.getRevisionId()));
      wikipediaElement.setTimestamp(DatatypeConverter.parseDateTime(article.getTimeStamp()).getTimeInMillis());

      // ----- LINKS -----
      List<String> links = articleTextParser.getLinks();
      if (CollectionUtils.isNotEmpty(links)) {
        for (String link : links) {
          WikipediaPage page = new WikipediaPage();
          page.setTitle(link);
          wikipediaElement.addLink(page);
        }
      }
      // ----- CATEGORIES -----
      List<String> categorieNames = articleTextParser.getCategories();
      if (CollectionUtils.isNotEmpty(categorieNames)) {
        for (String categoryName : categorieNames) {
          WikipediaCategory category = new WikipediaCategory();
          category.setTitle(categoryName);
          wikipediaElement.addCategory(category);
        }
      }
      
      return wikipediaElement;
    }
  }

  /**
   * 
   * @param article
   * @return
   */
  public static WikipediaElement from(WikiArticle article, WikiPatternMatcher articleTextParser) {
    
    if (article.isMain()) {
      
      if (articleTextParser.getInfoBox() != null) {
        
        if (WikipediaInfoboxParser.isArtist(article.getText())) {
          return new WikipediaArtElement(WikipediaArtistInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtistPage }, WikipediaType.ARTIST_PAGE);
        }
        else if (WikipediaInfoboxParser.isArtwork(article.getText())) {
          return new WikipediaArtElement(WikipediaArtworkInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtworkPage }, WikipediaType.ARTWORK_PAGE);
        }
        else if (WikipediaInfoboxParser.isArtMovement(article.getText())) {
          return new WikipediaArtElement(WikipediaArtMovementInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtMovementPage }, WikipediaType.ART_MOVEMENT_PAGE);
        }
        else if (WikipediaInfoboxParser.isMuseum(article.getText())) {
          return new WikipediaArtElement(WikipediaMuseumInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MUSEUM_PAGE);
        }
        else if (WikipediaInfoboxParser.isMonument(article.getText())) {
          return new WikipediaArtElement(WikipediaMuseumInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MONUMENT_PAGE);
        }
        else if (WikipediaInfoboxParser.isReligiousBuilding(article.getText())) {
          return new WikipediaArtElement(WikipediaReligiousBuildingInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaReligiousBuildingPage }, WikipediaType.RELIGIOUS_BUILDING_PAGE);
        }
        else if (WikipediaInfoboxParser.isSettlement(article.getText())) {
          return new WikipediaArtElement(WikipediaSettlementInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaSettlementPage }, WikipediaType.SETTLEMENT_PAGE);
        }
        else if (WikipediaInfoboxParser.isCountry(article.getText())) {
          return new WikipediaArtElement(WikipediaCountryInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaCountryPage }, WikipediaType.COUNTRY_PAGE);
        }
        else if (WikipediaInfoboxParser.isDocument(article.getText())) {
          return new WikipediaArtElement(WikipediaDocumentInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaDocumentPage }, WikipediaType.DOCUMENT_PAGE);
        }
        else if (WikipediaInfoboxParser.isColour(article.getText())) {
          return new WikipediaArtElement(WikipediaColourInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaColourPage }, WikipediaType.COLOUR_PAGE);
        }
      }
    }
    else if (article.isCategory()) {
      
      return new WikipediaCategory();
    }
    else if (article.isTemplate()) {
      
      return new WikipediaTemplate();
    }
    else if (article.isFile()) {
      
      return new WikipediaFile();
    }
    else if (article.isProject()) {
      
      return new WikipediaProject();
    }
      
    return new WikipediaPage();
  }
}