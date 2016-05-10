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

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.ArtMovement;
import org.neo4art.domain.Artist;
import org.neo4art.domain.Artwork;
import org.neo4art.domain.Colour;
import org.neo4art.domain.Country;
import org.neo4art.domain.Document;
import org.neo4art.domain.Monument;
import org.neo4art.domain.Museum;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.domain.WikipediaCategory;
import org.neo4art.importer.wikipedia.domain.WikipediaDisambiguationPage;
import org.neo4art.importer.wikipedia.domain.WikipediaDomainPage;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.domain.WikipediaFile;
import org.neo4art.importer.wikipedia.domain.WikipediaModule;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;
import org.neo4art.importer.wikipedia.domain.WikipediaProject;
import org.neo4art.importer.wikipedia.domain.WikipediaRedirectPage;
import org.neo4art.importer.wikipedia.domain.WikipediaTemplate;
import org.neo4art.importer.wikipedia.domain.WikipediaType;
import org.neo4art.importer.wikipedia.graphdb.WikipediaLabel;
import org.neo4art.importer.wikipedia.parser.WikipediaArtMovementInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaArtistInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaArtworkInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaColourInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaCountryInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaMonumentInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaMuseumInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaReligiousBuildingInfoboxParser;
import org.neo4art.importer.wikipedia.parser.WikipediaSettlementInfoboxParser;
import org.neo4j.graphdb.Label;

import info.bliki.wiki.dump.WikiArticle;
import info.bliki.wiki.dump.WikiPatternMatcher;
import toberefactored.parser.WikipediaDocumentInfoboxParser;
import toberefactored.parser.WikipediaInfoboxParser;

/**
 * It transforms a generic {@link WikiArticle} into a specific {@link WikipediaElement}
 *
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaElementTransformer {
	
  private static Log logger = LogFactory.getLog(WikipediaElementTransformer.class);

  private enum ArticleParser { EXTENDED, SIMPLIFIED };
  
  public static WikipediaElement toWikipediaElement(WikiArticle article) {

    WikipediaElement wikipediaElement = null;

    WikiPatternMatcher articleTextParser = null;

    try {

      if (StringUtils.isNotEmpty(article.getText())) {
        articleTextParser = new WikiPatternMatcher(article.getText());
      }

      if ((wikipediaElement = from(article, articleTextParser, ArticleParser.SIMPLIFIED)) != null) {

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
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      logger.error("Error parsing article " + article.getTitle() + " : " + ExceptionUtils.getRootCauseMessage(e));
    }

    return wikipediaElement;
  }

  /**
   * @param article
   * @param articleTextParser
   * @return
   */
  private static WikipediaElement from(WikiArticle article, WikiPatternMatcher articleTextParser, ArticleParser articleParser) {
  	
  	if (articleParser == ArticleParser.SIMPLIFIED)
  		return fromSimplified(article, articleTextParser);
  	else
  		return fromExtended(article, articleTextParser);
  }
  
  	/**
  	 * @param article
  	 * @param articleTextParser
  	 * @return
  	 */
  	private static WikipediaElement fromSimplified(WikiArticle article, WikiPatternMatcher articleTextParser) {
  		
    WikipediaElement wikipediaElement = null;

    if (article.isMain()) {
      if (articleTextParser != null) {
        if (articleTextParser.isDisambiguationPage()) {
          return new WikipediaDisambiguationPage();
        }
        else if (articleTextParser.isRedirect()) {
        	WikipediaPage redirectTo = new WikipediaPage();
        	redirectTo.setTitle(articleTextParser.getRedirectText());
        	WikipediaRedirectPage redirect = new WikipediaRedirectPage();
        	redirect.setRedirect(redirectTo);
        	return redirect;
        }
        else if (articleTextParser.getInfoBox() != null) {
          if (WikipediaInfoboxParser.isArtist(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Artist(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtistPage }, WikipediaType.ARTIST_PAGE);
          }
          else if (WikipediaInfoboxParser.isArtwork(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Artwork(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtworkPage }, WikipediaType.ARTWORK_PAGE);
          }
          else if (WikipediaInfoboxParser.isArtMovement(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new ArtMovement(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtMovementPage }, WikipediaType.ART_MOVEMENT_PAGE);
          }
          else if (WikipediaInfoboxParser.isMuseum(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Museum(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MUSEUM_PAGE);
          }
          else if (WikipediaInfoboxParser.isMonument(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Monument(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MONUMENT_PAGE);
          }
          else if (WikipediaInfoboxParser.isReligiousBuilding(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new ReligiousBuilding(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaReligiousBuildingPage }, WikipediaType.RELIGIOUS_BUILDING_PAGE);
          }
          else if (WikipediaInfoboxParser.isSettlement(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Settlement(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaSettlementPage }, WikipediaType.SETTLEMENT_PAGE);
          }
          else if (WikipediaInfoboxParser.isCountry(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Country(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaCountryPage }, WikipediaType.COUNTRY_PAGE);
          }
          else if (WikipediaInfoboxParser.isDocument(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Document(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaDocumentPage }, WikipediaType.DOCUMENT_PAGE);
          }
          else if (WikipediaInfoboxParser.isColour(article.getText())) {
            wikipediaElement = new WikipediaDomainPage(new Colour(), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaColourPage }, WikipediaType.COLOUR_PAGE);
          }
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
    else if (article.isModule()) {
    	wikipediaElement = new WikipediaModule();
    }

    return (wikipediaElement != null) ? wikipediaElement : new WikipediaPage();
  }

	/**
	 * @param article
	 * @param articleTextParser
	 * @return
	 */
	private static WikipediaElement fromExtended(WikiArticle article, WikiPatternMatcher articleTextParser) {
	
	  WikipediaElement wikipediaElement = null;
	
	  if (article.isMain()) {
	    if (articleTextParser != null) {
	      if (articleTextParser.isDisambiguationPage() || articleTextParser.isStub()) {
	        // ----- DISAMBIGUATION AND STUB PAGES are ignored -----
	        return null;
	      }
	
	      if (articleTextParser.getInfoBox() != null) {
	        if (WikipediaInfoboxParser.isArtist(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaArtistInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtistPage }, WikipediaType.ARTIST_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isArtwork(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaArtworkInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtworkPage }, WikipediaType.ARTWORK_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isArtMovement(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaArtMovementInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaArtMovementPage }, WikipediaType.ART_MOVEMENT_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isMuseum(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaMuseumInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MUSEUM_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isMonument(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaMonumentInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaMuseumPage }, WikipediaType.MONUMENT_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isReligiousBuilding(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaReligiousBuildingInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaReligiousBuildingPage }, WikipediaType.RELIGIOUS_BUILDING_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isSettlement(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaSettlementInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaSettlementPage }, WikipediaType.SETTLEMENT_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isCountry(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaCountryInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaCountryPage }, WikipediaType.COUNTRY_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isDocument(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaDocumentInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaDocumentPage }, WikipediaType.DOCUMENT_PAGE);
	        }
	        else if (WikipediaInfoboxParser.isColour(article.getText())) {
	          wikipediaElement = new WikipediaDomainPage(WikipediaColourInfoboxParser.parse(articleTextParser.getInfoBox().dumpRaw()), new Label[] { WikipediaLabel.Wikipedia, WikipediaLabel.WikipediaColourPage }, WikipediaType.COLOUR_PAGE);
	        }
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
	
	  return (wikipediaElement != null) ? wikipediaElement : new WikipediaPage();
	}
}