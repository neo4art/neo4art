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
package org.neo4art.importer.wikipedia.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Factory class for specific Wikipedia Pages (Main, Category, Project, Template, etc).
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Component
public class WikipediaDumpElementHandlerFactory {

	private static WikipediaDumpElementHandler pageHandler;
	private static WikipediaDumpElementHandler categoryHandler;
	private static WikipediaDumpElementHandler genericHandler;
	
	@Autowired
	@Qualifier("wikipediaPageHandler")
	public void setPageHandler(WikipediaDumpElementHandler pageHandler) {
		WikipediaDumpElementHandlerFactory.pageHandler = pageHandler;
	}
	
	@Autowired
	@Qualifier("wikipediaCategoryHandler")
	public void setCategoryHandler(WikipediaDumpElementHandler categoryHandler) {
		WikipediaDumpElementHandlerFactory.categoryHandler = categoryHandler;
	}

	@Autowired
	@Qualifier("wikipediaGenericHandler")
	public void setDoNothingHandler(WikipediaDumpElementHandler doNothingHandler) {
		WikipediaDumpElementHandlerFactory.genericHandler = doNothingHandler;
	}
	
	public static WikipediaDumpElementHandler getInstance(WikipediaDumpElementType wikipediaDumpElementType) {
		if (wikipediaDumpElementType == WikipediaDumpElementType.PAGE) {
			return pageHandler;
		}
		else if (wikipediaDumpElementType == WikipediaDumpElementType.CATEGORY) {
			return categoryHandler;
		}
		
		return genericHandler;
	}
}
