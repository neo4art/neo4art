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

package org.neo4art.literature.manager;

import java.io.IOException;
import java.util.List;

import org.neo4art.literature.analyzer.DocumentsAnalyzer;
import org.neo4art.literature.analyzer.DocumentsNLPLinkedListAnalyzer;
import org.neo4art.literature.domain.Document;
import org.neo4art.literature.domain.Letter;
import org.neo4art.literature.exception.LetterParserException;
import org.neo4art.literature.service.LetterDefaultService;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Lorenzo Speranzoni
 * @since 22 Apr 2015
 */
public class VanGoghLettersManager
{
  /**
   * 
   * @return
   * @throws IOException
   * @throws LetterParserException
   */
  public List<Letter> loadLetters() throws LetterParserException, IOException
  {
    return new LetterDefaultService().getLettersFromPath(new ClassPathResource("vangoghletters").getFile().toPath());
  }

  /**
   * 
   * @throws LetterParserException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  public void runVanGoghLetterAnalysis() throws LetterParserException, IOException
  {
    List<Document> vanGoghLetters = (List<Document>)(List<?>) loadLetters();

    DocumentsAnalyzer documentsAnalyzer = new DocumentsNLPLinkedListAnalyzer();
    
    documentsAnalyzer.givenThese(vanGoghLetters)
                     .storeItOnGraph()
                     .computeNLPAnalysis()
                     .computeRedundancyStats(5, 15);
  }
}
