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

package org.neo4art.core.service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.neo4art.core.repository.ColourBatchInserterRepository;
import org.neo4art.core.repository.ColourRepository;
import org.neo4art.domain.Colour;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ColourDefaultService implements ColourService
{
  /**
   * @see org.neo4art.core.service.ColourService#createIndexes()
   */
  @Override
  public void createIndexes()
  {
    ColourRepository colourRepository = new ColourBatchInserterRepository();
    
    colourRepository.createColourLegacyIndex();
  }

  /**
   * @throws IOException 
   * @see org.neo4art.core.service.ColourService#getColours()
   */
  @Override
  public List<Colour> getColours() throws IOException
  {
    List<Colour> result = null;
    
    URL url = getClass().getClassLoader().getResource("colours.csv");
    CSVParser csvParser = CSVParser.parse(url, Charset.defaultCharset(), CSVFormat.EXCEL.withDelimiter(',').withQuote('\'').withEscape('\\').withIgnoreSurroundingSpaces(true));

    List<CSVRecord> records = csvParser.getRecords();

    if (CollectionUtils.isNotEmpty(records))
    {
      result = new ArrayList<Colour>();
      
      for (CSVRecord csvRecord : records)
      {
        int r = Integer.parseInt(csvRecord.get(1).substring(1, 3), 16);
        int g = Integer.parseInt(csvRecord.get(1).substring(3, 5), 16);
        int b = Integer.parseInt(csvRecord.get(1).substring(5, 7), 16);
  
        result.add(new Colour(csvRecord.get(0), r, g, b));
      }
    }
    
    return result;
  }

  /**
   * @see org.neo4art.core.service.ColourService#saveColours(java.util.List)
   */
  @Override
  public void saveColours(List<Colour> colours)
  {
    if (CollectionUtils.isNotEmpty(colours))
    {
      for (Colour colour : colours)
      {
        saveColour(colour);
      }
    }
  }

  /**
   * @see org.neo4art.core.service.ColourService#saveColour(org.neo4art.domain.Colour)
   */
  @Override
  public void saveColour(Colour colour)
  {
    ColourRepository colourRepository = new ColourBatchInserterRepository();
    
    colourRepository.saveColour(colour);
  }
}
