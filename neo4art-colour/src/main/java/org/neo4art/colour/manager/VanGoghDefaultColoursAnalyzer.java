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

package org.neo4art.colour.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.exception.ImageParserException;
import org.neo4art.colour.service.ImageDefaultManager;
import org.neo4art.colour.service.ImageManager;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class VanGoghDefaultColoursAnalyzer implements VanGoghColoursAnalyzer
{
  private static Log logger = LogFactory.getLog(VanGoghDefaultColoursAnalyzer.class);
  
  /**
   * @see org.neo4art.colour.manager.VanGoghColoursAnalyzer#analyzeArtworksColors()
   */
  @Override
  public List<ColourAnalysis> analyzeArtworksColors() throws ImageParserException, IOException
  {
    List<ColourAnalysis> listVanGogImageColor = new ArrayList<ColourAnalysis>();
    
    ImageManager imageManager = new ImageDefaultManager();

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("vangoghartworks/vangoghartworks.txt").getFile());

    BufferedReader input = new BufferedReader(new FileReader(file));
    String line;
    
    while ((line = input.readLine()) != null)
    {
      String[] splitLine = line.split("-");
      
      ColourAnalysis colourAnalysis = imageManager.analyzeImageByUrl(new URL(splitLine[1]), splitLine[0]);
      
      logger.info("Colour analysis for " + splitLine[0] + " from " + splitLine[1] + " done.");
      
      listVanGogImageColor.add(colourAnalysis);
    }
    
    input.close();

    return listVanGogImageColor;
  }

}
