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
import org.neo4art.domain.Artist;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;


/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ArtistBatchInserterRepository implements ArtistRepository
{
  /**
   * @see org.neo4art.core.repository.ArtistRepository#createArtistLegacyIndex()
   */
  @Override
  public void createArtistLegacyIndex()
  {
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(CoreLegacyIndex.ARTIST_LEGACY_INDEX);
  }

  /**
   * @see org.neo4art.core.repository.ArtistRepository#saveArtist(org.neo4art.domain.Artist)
   */
  @Override
  public long saveArtist(Artist artist)
  {
    long nodeId = Neo4ArtBatchInserterSingleton.createNode(artist);
    
    Neo4ArtBatchInserterSingleton.addToLegacyNodeIndex(CoreLegacyIndex.ARTIST_LEGACY_INDEX, artist);
    
    artist.setNodeId(nodeId);
    
    return nodeId;
  }
}
