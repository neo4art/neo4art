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
import info.bliki.wiki.namespaces.Namespace;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.importer.wikipedia.domain.WikipediaPage;

/**
 * It trasnforms {@link WikiArticle} into {@link WikipediaPage}
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaDumpElementTransformer {

	/**
	 * 
	 * @param wikiArticle
	 * @return
	 */
	public static WikipediaPage toWikipediaPage(WikiArticle wikiArticle) {

		WikipediaPage wikipediaPage = new WikipediaPage();
		
		wikipediaPage.setId(Long.parseLong(wikiArticle.getId()));
		wikipediaPage.setNamespace(new Namespace().getNamespaceByNumber(wikiArticle.getIntegerNamespace()).getCanonicalName());
		wikipediaPage.setTitle(wikiArticle.getTitle());
		wikipediaPage.setRevision(Long.parseLong(wikiArticle.getRevisionId()));
		wikipediaPage.setText(wikiArticle.getText());
		wikipediaPage.setTimestamp(DatatypeConverter.parseDateTime(wikiArticle.getTimeStamp()).getTimeInMillis());
		
		WikiPatternMatcher textParser = new WikiPatternMatcher(wikiArticle.getText());
		
		// ----- CATEGORIES -----
		
		List<String> categories = textParser.getCategories();
		if (CollectionUtils.isNotEmpty(categories)) {
			List<String> wikipediaCategories = new ArrayList<String>();
			for (String category : categories) {
				wikipediaCategories.add(category);
			}
			wikipediaPage.setCategories(wikipediaCategories);
		}
		
		// ----- LINKS -----
		
		List<String> links = textParser.getLinks();
		if (CollectionUtils.isNotEmpty(links)) {
			List<String> wikipediaLinkedPages = new ArrayList<String>();
			for (String link : links) {
				wikipediaLinkedPages.add(link);
			}
			wikipediaPage.setLinks(wikipediaLinkedPages);
		}
		
		// TODO WikiArticle data not yet managed
		
		// +++ textParser.getInfoBox();
		// --- textParser.getPlainText();
		// +   textParser.getRedirectText();
		// +++ textParser.getText();
		// +   textParser.isDisambiguationPage();
		// +   textParser.isRedirect();
		// +   textParser.isStub();
		
		return wikipediaPage;
	}
}
