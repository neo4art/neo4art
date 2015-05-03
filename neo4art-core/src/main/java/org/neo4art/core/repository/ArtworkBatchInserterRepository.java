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

package org.neo4art.core.repository;

import org.neo4art.core.graphdb.CoreLegacyIndex;
import org.neo4art.core.graphdb.CoreRelationship;
import org.neo4art.domain.Artwork;
import org.neo4art.graphdb.Neo4ArtLegacyIndex;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;
import org.neo4j.graphdb.index.IndexHits;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ArtworkBatchInserterRepository implements ArtworkRepository
{
  /**
   * @see org.neo4art.core.repository.ArtworkRepository#createArtworkLegacyIndex()
   */
  @Override
  public void createArtworkLegacyIndex()
  {
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(CoreLegacyIndex.ARTWORK_LEGACY_INDEX.name(), Neo4ArtLegacyIndex.TYPE_EXACT);
  }

  /**
   * @see org.neo4art.core.repository.ArtworkRepository#saveArtwork(org.neo4art.domain.Artwork)
   */
  @Override
  public long saveArtwork(Artwork artwork)
  {
    long nodeId = Neo4ArtBatchInserterSingleton.createNode(artwork);

    Neo4ArtBatchInserterSingleton.addToLegacyNodeIndex(CoreLegacyIndex.ARTWORK_LEGACY_INDEX.name(), artwork);

    artwork.setNodeId(nodeId);

    return nodeId;
  }

  /**
   * @see org.neo4art.core.repository.ArtworkRepository#getArtworkByTitle(java.lang.String)
   */
  @Override
  public Long getArtworkByTitle(String title)
  {
    Long nodeId = null;

    IndexHits<Long> indexHits = Neo4ArtBatchInserterSingleton.getFromLegacyNodeIndex(CoreLegacyIndex.ARTWORK_LEGACY_INDEX.name(), "title", title);

    if (indexHits.hasNext())
    {
      nodeId = indexHits.next();
    }

    indexHits.close();

    return nodeId;
  }

  /**
   * @see org.neo4art.core.repository.ArtworkRepository#connectArtworkToArtist(org.neo4art.domain.Artwork)
   */
  @Override
  public long connectArtworkToArtist(Artwork artwork)
  {
    return Neo4ArtBatchInserterSingleton.createRelationship(artwork.getArtist().getNodeId(), artwork.getNodeId(), CoreRelationship.REALIZED, null);
  }
}
