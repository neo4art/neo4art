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
package org.neo4art.toberefactored.builder.mock;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.sentiment.domain.Word;

/**
 * @author Enrico De Benetti
 * @since 26 Apr 2015
 *
 */
public class BuildMockNegativePZSentiment {

	public List<Word> loadMockSentiments(){
		 
	 List<Word> wordList = new ArrayList<Word>();
		
	 Word word1 = new Word();
	 word1.setWord("pain");
	
	 Word word2 = new Word();
	 word2.setWord("painful");
	
	 Word word3 = new Word();
	 word3.setWord("painfull");
	
	 wordList.add(word1);
	 wordList.add(word2);
	 wordList.add(word3);
	      	        
	 return wordList;       
	}
	
}
