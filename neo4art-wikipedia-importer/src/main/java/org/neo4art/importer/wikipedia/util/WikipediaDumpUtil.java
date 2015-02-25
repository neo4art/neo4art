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
package org.neo4art.importer.wikipedia.util;

import org.neo4art.importer.wikipedia.service.handler.WikipediaDumpElementType;

import info.bliki.wiki.dump.WikiArticle;

/**
 * Support methods for Wikipedia Dump parsing process.
 * 
 * @author Lorenzo Speranzoni Speranzoni
 * @since 25.02.2015
 */
public class WikipediaDumpUtil {

	public static WikipediaDumpElementType toWikipediaDumpElementType(WikiArticle page) {
		if (page.isMain())
			return WikipediaDumpElementType.PAGE;
		else if (page.isCategory())
			return WikipediaDumpElementType.CATEGORY;
		else if (page.isFile())
			return WikipediaDumpElementType.FILE;
		else if (page.isProject())
			return WikipediaDumpElementType.PROJECT;
		else if (page.isTemplate())
			return WikipediaDumpElementType.TEMPLATE;
		else
			return WikipediaDumpElementType.OTHER;
	}
}
