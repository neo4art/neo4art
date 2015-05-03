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

import org.neo4art.colour.graphdb.ColourLegacyIndex;
import org.neo4art.domain.Colour;
import org.neo4art.graphdb.Neo4ArtLegacyIndex;
import org.neo4art.graphdb.connection.Neo4ArtBatchInserterSingleton;

/**
 * @author Lorenzo Speranzoni
 * @since 22 Apr 2015
 */
public class ColourBatchInserterRepository implements ColourRepository
{
  /**
   * @see org.neo4art.colour.repository.ColourRepository#createColourLegacyIndex()
   */
  @Override
  public void createColourLegacyIndex()
  {
    Neo4ArtBatchInserterSingleton.createLegacyNodeIndex(ColourLegacyIndex.COLOUR_LEGACY_INDEX.name(), Neo4ArtLegacyIndex.TYPE_EXACT, Colour.RGB_PROPERTY_NAME, 1_500);
  }

  @Override
  public long saveColour(Colour colour)
  {
    long nodeId = Neo4ArtBatchInserterSingleton.createNode(colour);

    colour.setNodeId(nodeId);

    Neo4ArtBatchInserterSingleton.addToLegacyNodeIndex(ColourLegacyIndex.COLOUR_LEGACY_INDEX.name(), colour);

    return nodeId;
  }
}
