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

package org.neo4art.sentiment.repository;

import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.sentiment.domain.NLP;
import org.neo4art.sentiment.graphdb.NLPRelationship;

/**
 * @author Lorenzo Speranzoni
 * @since 26 Apr 2015
 */
public class NLPBatchInserterRepository implements NLPRepository
{
  public NLPBatchInserterRepository()
  {
  }
  
  /**
   * @see org.neo4art.sentiment.repository.NLPRepository#addNode(org.neo4art.sentiment.domain.NLP)
   */
  @Override
  public Long addNode(NLP nlpNode)
  {
    return Neo4ArtBatchInserterSingleton.createNode(nlpNode);
  }

  /**
   * @see org.neo4art.sentiment.repository.NLPRepository#addRelationshipToWord(java.lang.Long, java.lang.Long)
   */
  @Override
  public long addRelationshipToWord(Long previousNodeId, Long currentNodeId)
  {
    return Neo4ArtBatchInserterSingleton.createRelationship(previousNodeId, currentNodeId, NLPRelationship.NEXT_NLP, null);
  }

  /**
   * @see org.neo4art.sentiment.repository.NLPRepository#addRelationshipToPunctuation(java.lang.Long, java.lang.Long)
   */
  @Override
  public long addRelationshipToPunctuation(Long previousNodeId, Long currentNodeId)
  {
    return Neo4ArtBatchInserterSingleton.createRelationship(previousNodeId, currentNodeId, NLPRelationship.NEXT_NLP_PUNCTUATION, null);
  }

  /**
   * @see org.neo4art.sentiment.repository.NLPRepository#addRelationshipBetweenOriginalDocumentAndNLPLinkedList(long, long)
   */
  @Override
  public long addRelationshipBetweenOriginalDocumentAndNLPLinkedList(long documentNodeId, long nlpLinkedListStartingNodeId)
  {
    return Neo4ArtBatchInserterSingleton.createRelationship(documentNodeId, nlpLinkedListStartingNodeId, NLPRelationship.TOKENIZED_IN_POS, null);
  }
}
