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
import org.neo4art.importer.wikipedia.domain.WikipediaArtElement;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaFile;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaProject;
import org.neo4art.importer.wikipedia.domain.WikipediaTemplate;
import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.neo4art.importer.wikipedia.parser.WikipediaArtMovementInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaArtistInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaArtworkInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaMonumentInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaMuseumInfoboxParser;
import org.neo4j.graphdb.Label;

import toberefactored.parser.WikipediaInfoboxParser;

/**
 * It transforms a generic {@link WikiArticle} into a specific {@link WikipediaElement}
 *
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaElementTransformer {
  
  public static WikipediaElement toWikipediaElement(WikiArticle article) {

    WikiPatternMatcher articleTextParser = null;
    
    if (StringUtils.isNotEmpty(article.getText())) {
      
      articleTextParser = new WikiPatternMatcher(article.getText());
    }
    
    WikipediaElement wikipediaElement = from(article, articleTextParser); 
    
    if (wikipediaElement == null) {
      
      // ----- DISAMBIGUATION, STUB AND REDIRECTED PAGES are ignored -----
      return null;
    }
    
    wikipediaElement.setId(Long.parseLong(article.getId()));
    wikipediaElement.setTitle(article.getTitle());
    wikipediaElement.setRevision(Long.parseLong(article.getRevisionId()));
    wikipediaElement.setTimestamp(DatatypeConverter.parseDateTime(article.getTimeStamp()).getTimeInMillis());
        
    if (articleTextParser != null) {
      
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
    }

    return wikipediaElement;
  }

  /**
   * @param article
   * @param articleTextParser
   * @return
   */
  private static WikipediaElement from(WikiArticle article, WikiPatternMatcher articleTextParser) {
    
    WikipediaElement wikipediaElement = null;
    
    if (article.isMain()) {
      
      if (articleTextParser != null) {
        
        if (articleTextParser.isDisambiguationPage() || !articleTextParser.isStub() || !articleTextParser.isRedirect()) {
          
          // ----- DISAMBIGUATION, STUB AND REDIRECTED PAGES are ignored -----
          return null;
        }        

        if (articleTextParser.getInfoBox() != null) {
          
          if (WikipediaInfoboxParser.isArtist(article.getText())) {
            wikipediaElement = new WikipediaArtElement(WikipediaArtistInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtistPage }, WikipediaType.ARTIST_PAGE);
          }
          else if (WikipediaInfoboxParser.isArtwork(article.getText())) {
            wikipediaElement = new WikipediaArtElement(WikipediaArtworkInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtworkPage }, WikipediaType.ARTWORK_PAGE);
          }
          else if (WikipediaInfoboxParser.isArtMovement(article.getText())) {
            wikipediaElement = new WikipediaArtElement(WikipediaArtMovementInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtMovementPage }, WikipediaType.ART_MOVEMENT_PAGE);
          }
          else if (WikipediaInfoboxParser.isMuseum(article.getText())) {
            wikipediaElement = new WikipediaArtElement(WikipediaMuseumInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MUSEUM_PAGE);
          }
          else if (WikipediaInfoboxParser.isMonument(article.getText())) {
            wikipediaElement = new WikipediaArtElement(WikipediaMonumentInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MONUMENT_PAGE);
          }
          // TODO NOT YET REFACTORED else if (WikipediaInfoboxParser.isReligiousBuilding(article.getText())) {
          // TODO NOT YET REFACTORED   wikipediaElement = new WikipediaArtElement(WikipediaReligiousBuildingInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaReligiousBuildingPage }, WikipediaType.RELIGIOUS_BUILDING_PAGE);
          // TODO NOT YET REFACTORED }
          // TODO NOT YET REFACTORED else if (WikipediaInfoboxParser.isSettlement(article.getText())) {
          // TODO NOT YET REFACTORED   wikipediaElement = new WikipediaArtElement(WikipediaSettlementInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaSettlementPage }, WikipediaType.SETTLEMENT_PAGE);
          // TODO NOT YET REFACTORED }
          // TODO NOT YET REFACTORED else if (WikipediaInfoboxParser.isCountry(article.getText())) {
          // TODO NOT YET REFACTORED   wikipediaElement = new WikipediaArtElement(WikipediaCountryInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaCountryPage }, WikipediaType.COUNTRY_PAGE);
          // TODO NOT YET REFACTORED }
          // TODO NOT YET REFACTORED else if (WikipediaInfoboxParser.isDocument(article.getText())) {
          // TODO NOT YET REFACTORED   wikipediaElement = new WikipediaArtElement(WikipediaDocumentInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaDocumentPage }, WikipediaType.DOCUMENT_PAGE);
          // TODO NOT YET REFACTORED }
          // TODO NOT YET REFACTORED else if (WikipediaInfoboxParser.isColour(article.getText())) {
          // TODO NOT YET REFACTORED   wikipediaElement = new WikipediaArtElement(WikipediaColourInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaColourPage }, WikipediaType.COLOUR_PAGE);
          // TODO NOT YET REFACTORED }
        }
      }        
    }
    else if (article.isCategory()) {
      
      wikipediaElement = new WikipediaCategory();
    }
    else if (article.isTemplate()) {
      
      wikipediaElement = new WikipediaTemplate();
    }
    else if (article.isFile()) {
      
      wikipediaElement = new WikipediaFile();
    }
    else if (article.isProject()) {
      
      wikipediaElement = new WikipediaProject();
    }
    
    if (wikipediaElement == null)
    {
      wikipediaElement = new WikipediaPage();
    }
    
    return wikipediaElement;
  }
}