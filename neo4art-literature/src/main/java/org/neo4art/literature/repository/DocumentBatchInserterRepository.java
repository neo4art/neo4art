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

package org.neo4art.literature.repository;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4art.literature.domain.Document;

/**
 * @author Lorenzo Speranzoni
 * @since 22 Apr 2015
 */
public class DocumentBatchInserterRepository implements DocumentRepository
{
  /** 
   * @see org.neo4art.literature.repository.DocumentRepository#saveDocument(org.neo4art.literature.domain.Document)
   */
  @Override
  public long saveDocument(Document document)
  {
    long documentNodeId = Neo4ArtBatchInserterSingleton.createNode(document);

    document.setNodeId(documentNodeId);

    return documentNodeId;
  }

  /**
   * @see org.neo4art.literature.repository.DocumentRepository#saveDocuments(java.util.List)
   */
  @Override
  public void saveDocuments(List<Document> documentList)
  {
    if(CollectionUtils.isNotEmpty(documentList))
    {
      for (Document document : documentList)
      {
        saveDocument(document);
      }
    }
  }
}
