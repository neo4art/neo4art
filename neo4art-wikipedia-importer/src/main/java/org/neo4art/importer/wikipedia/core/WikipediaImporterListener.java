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
package org.neo4art.importer.wikipedia.core;

import info.bliki.wiki.dump.IArticleFilter;

/**
 * Listener of the Wikipedia Dump importer.
 * 
 * {@link IArticleFilter#process(WikiArticle, Siteinfo) } is called whenever parsing of a 'page' tag is completed.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public interface WikipediaImporterListener extends IArticleFilter {

  void setBatchSize(long size);
  
	long getPageCount();
  long getGraphCount();
  
  void flush();
}
