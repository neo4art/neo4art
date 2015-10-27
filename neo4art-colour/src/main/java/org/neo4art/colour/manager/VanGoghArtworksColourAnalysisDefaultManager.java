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

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.colour.service.ColourAnalysisDefaultService;
import org.neo4art.colour.service.ColourAnalysisService;
import org.neo4art.domain.Artwork;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class VanGoghArtworksColourAnalysisDefaultManager implements VanGoghArtworksColourAnalysisManager
{
  private static Log logger = LogFactory.getLog(VanGoghArtworksColourAnalysisDefaultManager.class);

  public VanGoghArtworksColourAnalysisDefaultManager()
  {
  }

  /**
   * @see org.neo4art.colour.manager.VanGoghArtworksColourAnalysisManager#loadArtworksFromFile(java.lang.String)
   */
  @Override
  public List<Artwork> loadArtworksFromFile(String fileName) throws IOException
  {
    List<Artwork> artworks = null;

    URL url = getClass().getClassLoader().getResource(fileName);

    CSVParser csvParser = CSVParser.parse(url, Charset.defaultCharset(), CSVFormat.EXCEL.withIgnoreSurroundingSpaces(true));

    List<CSVRecord> cvsRecords = csvParser.getRecords();

    if (CollectionUtils.isNotEmpty(cvsRecords) && CollectionUtils.size(cvsRecords) > 1)
    {
      artworks = new ArrayList<Artwork>();

      for (int i = 1; i < cvsRecords.size(); i++)
      {
        CSVRecord csvRecord = cvsRecords.get(i);

        Artwork artwork = new Artwork();

        Calendar completionDate = Calendar.getInstance();
        completionDate.set(Calendar.YEAR, Integer.parseInt(csvRecord.get(2)));
        completionDate.set(Calendar.MONTH, Integer.parseInt(csvRecord.get(3)));

        artwork.setTitle(csvRecord.get(0));
        artwork.setType(csvRecord.get(1));
        artwork.setYear(new Date(Integer.parseInt(csvRecord.get(2)), Calendar.JANUARY, 1));
        artwork.setCompletionDate(completionDate.getTime());
        artwork.setImageFile(csvRecord.get(4));
        artwork.setCatalogue("F: " + csvRecord.get(5) + ", JH: " + csvRecord.get(6));

        artworks.add(artwork);
      }
    }

    return artworks;
  }

  /**
   * @see org.neo4art.colour.manager.VanGoghArtworksColourAnalysisManager#computeAndSaveColourAnalyses(java.util.List)
   */
  @Override
  public void computeAndSaveColourAnalyses(List<Artwork> artworks)
  {
    logger.info("Starting Van Gogh's artworks colours analysis...");

    ArtworksColoursAnalyzer artworksColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();

    if (CollectionUtils.isNotEmpty(artworks))
    {
      logger.info(artworks.size() + " artworks to be analyzed.");

      List<ColourAnalysis> coloursAnalyses = artworksColoursAnalyzer.analyzeArtworksColours(artworks);

      logger.info("Analysis done!");

      if (CollectionUtils.isNotEmpty(coloursAnalyses))
      {
        logger.info("Saving " + coloursAnalyses.size() + " analyses to graph...");

        ColourAnalysisService colourAnalysisService = new ColourAnalysisDefaultService();

        for (ColourAnalysis colourAnalysis : coloursAnalyses)
        {
          colourAnalysisService.saveColourAnalysis(colourAnalysis);
        }

        logger.info("Saved!");
      }
    }
  }
}
