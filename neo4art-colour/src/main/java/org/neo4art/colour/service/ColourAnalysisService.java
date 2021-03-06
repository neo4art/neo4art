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

package org.neo4art.colour.service;

import org.neo4art.colour.domain.ColourAnalysis;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public interface ColourAnalysisService
{
  /**
   * It stores a colour analysis node in the graph, connect it to the closest minimum, average, maximum colour nodes and to the artwork node.
   *  
   * @param colourAnalysis
   */
  void saveColourAnalysis(ColourAnalysis colourAnalysis);
}
