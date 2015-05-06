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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.neo4art.literature.analyzer.DocumentsAnalyzer;
import org.neo4art.literature.analyzer.DocumentsNLPLinkedListAnalyzer;
import org.neo4art.literature.domain.Document;
import org.neo4art.literature.domain.Letter;
import org.neo4art.literature.exception.LetterParserException;
import org.neo4art.literature.service.LetterDefaultService;
import org.neo4art.sentiment.domain.SentimentAnalysis;
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
    
   public List<SentimentAnalysis> loadSentimentsFromFile(String fileName) throws IOException
   {
     
     List<SentimentAnalysis>  sentimentAnalysisList = new ArrayList<SentimentAnalysis>();
     
     URL url = getClass().getClassLoader().getResource(fileName);

     CSVParser csvParser = CSVParser.parse(url, Charset.defaultCharset(), CSVFormat.EXCEL.withIgnoreSurroundingSpaces(true));

     List<CSVRecord> cvsRecords = csvParser.getRecords();

     if (CollectionUtils.isNotEmpty(cvsRecords) && CollectionUtils.size(cvsRecords) > 1)
     {

       for (int i = 1; i < cvsRecords.size(); i++)
       {
         CSVRecord csvRecord = cvsRecords.get(i);

         SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
         Letter letter = new Letter();
         letter.setTitle(csvRecord.get(0));
		     String polarityType = csvRecord.get(1);
		     String polarity="";
		     
		     if(polarityType.equalsIgnoreCase("0"))
		     {
		       polarity = "neutral";
		     }
		     else if(polarityType.equalsIgnoreCase("1") || polarityType.equalsIgnoreCase("2"))
		     {
		       polarity = "negative";
		     }
		     else if(polarityType.equalsIgnoreCase("3") || polarityType.equalsIgnoreCase("4"))
		     {
		       polarity = "positive";
		     }

		     letter.setDate(csvRecord.get(2));
		     
		     sentimentAnalysis.setPolarity(polarity);
         sentimentAnalysis.setSource(letter);

         sentimentAnalysisList.add(sentimentAnalysis);
       }
     }
     
     return sentimentAnalysisList;
   }
  
}
