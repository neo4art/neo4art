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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.colour.domain.ColourAnalysis;
import org.neo4art.domain.Artwork;

/**
 * @author Lorenzo Speranzoni
 * @since 4 May 2015
 */
public class VanGoghArtworksColourAnalysisManagerTest
{
  @Test
  public void shouldAnalyze5VanGoghArtworks()
  {
    try
    {
      VanGoghArtworksColourAnalysisManager vanGoghArtworksColourAnalysisManager = new VanGoghArtworksColourAnalysisDefaultManager();

      List<Artwork> artworks = vanGoghArtworksColourAnalysisManager.loadArtworksFromFile("vangoghartworks/van-gogh-artworks.csv");

      artworks = artworks.subList(0, 5);

      ArtworksColoursAnalyzer artworksColoursAnalyzer = new ArtworksDefaultColoursAnalyzer();

      // -------

      List<ColourAnalysis> analyseVanGoghImage = artworksColoursAnalyzer.analyzeArtworksColours(artworks);

      Assert.assertNotNull(analyseVanGoghImage);
      Assert.assertEquals(5, analyseVanGoghImage.size());

      for (ColourAnalysis imageColor : analyseVanGoghImage)
      {
        System.out.println("Image Name: " + imageColor.getArtwork().getTitle());
        System.out.println("AVG name:" + imageColor.getAverageClosestColour().getName());
        System.out.println("MAX name:" + imageColor.getMaximumClosestColour().getName());
        System.out.println("MIN name:" + imageColor.getMinimumClosestColour().getName());
        System.out.println("AVG :" + imageColor.getAverageColour().toString());
        System.out.println("Max :" + imageColor.getMaximumColour().toString());
        System.out.println("Min :" + imageColor.getMinimumColour().toString());
        System.out.println("------------------------------------------------\n");

      }
    }
    catch (Exception e)
    {
      e.printStackTrace();

      Assert.fail(e.getMessage());
    }
  }
}
