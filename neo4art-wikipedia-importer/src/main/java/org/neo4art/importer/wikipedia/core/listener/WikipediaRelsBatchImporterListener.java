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
package org.neo4art.importer.wikipedia.core.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;
import org.neo4art.importer.wikipedia.manager.WikipediaElementManager;
import org.neo4art.importer.wikipedia.manager.WikipediaElementManagerFactory;

/**
 * Default implementation of the importer listener.
 *  
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public class WikipediaRelsBatchImporterListener extends WikipediaAbstractImporterListener implements WikipediaImporterListener {

  private static Log logger = LogFactory.getLog(WikipediaRelsBatchImporterListener.class);
  
  @Override
  public void flush() {
    
    long newRelatioships = 0;
    
    for (WikipediaElement wikipediaElement : this.wikipediaElementBuffer) {
      
      WikipediaElementManager wikipediaElementManager =
          WikipediaElementManagerFactory.getInstance(wikipediaElement.getType());
      
      newRelatioships += wikipediaElementManager.createRelationships(wikipediaElement);
    }
      
    this.graphCount.addAndGet(newRelatioships);
    
    this.wikipediaElementBuffer.clear();
    
    logger.debug(newRelatioships + " new relationships created.");
  }
}
