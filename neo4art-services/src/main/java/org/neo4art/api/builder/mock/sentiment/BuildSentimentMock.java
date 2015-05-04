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
package org.neo4art.api.builder.mock.sentiment;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.literature.domain.Letter;
import org.neo4art.sentiment.domain.SentimentAnalysis;

/**
 * @author Enrico De Benetti
 * @since 03 Mag 2015
 *
 */
public class BuildSentimentMock {

	
	public List<SentimentAnalysis> getSentimentAnalisys(String inputSearch){

	  List<SentimentAnalysis> resultList = new ArrayList<SentimentAnalysis>();  	
		
	  SentimentAnalysis sentimentAnalysis1 = new SentimentAnalysis();
	  sentimentAnalysis1.setNodeId(1);
	  sentimentAnalysis1.setPolarity("positive");
	  Letter sourceDocument1 = new Letter();
	  sourceDocument1.setDate("Sunday, 29 September 1884");
	  sentimentAnalysis1.setSource(sourceDocument1);
	  
	  SentimentAnalysis sentimentAnalysis2 = new SentimentAnalysis();
	  sentimentAnalysis2.setNodeId(2);
	  sentimentAnalysis2.setPolarity("negative");
	  Letter sourceDocument2 = new Letter();
	  sourceDocument2.setDate("Monday, 30 September 1884");
	  sentimentAnalysis2.setSource(sourceDocument2);
	  
	  SentimentAnalysis sentimentAnalysis3 = new SentimentAnalysis();
	  sentimentAnalysis3.setNodeId(3);
	  sentimentAnalysis3.setPolarity("positive");
	  Letter sourceDocument3 = new Letter();
	  sourceDocument3.setDate("Saturday, 26 October 1884");
	  sentimentAnalysis3.setSource(sourceDocument3);
	  
	  SentimentAnalysis sentimentAnalysis4 = new SentimentAnalysis();
	  sentimentAnalysis4.setNodeId(4);
	  sentimentAnalysis4.setPolarity("negative");
	  Letter sourceDocument4 = new Letter();
	  sourceDocument4.setDate("Friday, 13 December 1884");
	  sentimentAnalysis4.setSource(sourceDocument4);
	  
	  SentimentAnalysis sentimentAnalysis5 = new SentimentAnalysis();
	  sentimentAnalysis5.setNodeId(5);
	  sentimentAnalysis5.setPolarity("negative");
	  Letter sourceDocument5 = new Letter();
	  sourceDocument5.setDate("Tuesday, 28 January 1885");
	  sentimentAnalysis5.setSource(sourceDocument5);	
	  
	  resultList.add(sentimentAnalysis1);	
	  resultList.add(sentimentAnalysis2);
	  resultList.add(sentimentAnalysis3);
	  resultList.add(sentimentAnalysis4);
	  resultList.add(sentimentAnalysis5);
		
	 return resultList;
	}
	
}
