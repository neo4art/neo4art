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

package org.neo4art.colour.graphdb;

import org.neo4art.graphdb.Neo4ArtLegacyIndex;
import org.neo4art.graphdb.Neo4ArtLegacyIndexType;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
public interface ColourLegacyIndex
{
  public static final Neo4ArtLegacyIndex COLOUR_LEGACY_INDEX = new Neo4ArtLegacyIndex()
  {
    @Override
    public String getName()
    {
      return "COLOUR";
    }
    
    @Override
    public Neo4ArtLegacyIndexType getType()
    {
      return Neo4ArtLegacyIndexType.TYPE_EXACT;
    }
  }; 
}
