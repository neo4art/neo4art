/**
 * Copyright 2015 the original author or auth3ors.
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

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lorenzo Speranzoni
 * @since 3 May 2015
 */
public class ArtistsArtworkCatalogTest
{
  @Test
  public void shouldSaveColours()
  {
    try
    {
      URL url = getClass().getClassLoader().getResource("artists-artworks-catalog.csv");
      CSVParser csvParser = CSVParser.parse(url, Charset.forName("ISO-8859-1"), CSVFormat.EXCEL);

      List<CSVRecord> records = csvParser.getRecords();

      if (CollectionUtils.isNotEmpty(records))
      {
        // AUTHOR;BORN-DIED;TITLE;DATE;TECHNIQUE;LOCATION;URL;FORM;TYPE;SCHOOL;TIMEFRAME
        
        for (int i = 1; i < records.size(); i++)
        {
          CSVRecord csvRecord = records.get(i);
          
          String record0 = csvRecord.get(0);
          
          if (record0.contains(","))
          {
            String[] author = StringUtils.split(record0, ',');
            System.out.println(WordUtils.capitalizeFully(StringUtils.trim(author[1])));
            System.out.println(WordUtils.capitalizeFully(StringUtils.trim(author[0])));
            System.out.println();
          }
          else
          {
            System.out.println(csvRecord.get(0)); 
            System.out.println();
          }
          /*
          String record1 = csvRecord.get(1);
          System.out.println("--|" + record1 + "|--");
          
          if (record1.startsWith("(b. ") && record1.contains("d.") && record1.contains(")"))
          {
            record1 = StringUtils.remove(record1, '(');
            record1 = StringUtils.remove(record1, ')');
            System.out.println(record1);
            String[] bornDied = StringUtils.split(record1, ',');
            System.out.println(bornDied[0].trim().substring(2).trim());
            System.out.println(bornDied[1].trim());
            System.out.println(bornDied[2].trim().substring(2).trim());
            System.out.println(bornDied[3].trim());
          }
          else
          {
            System.out.println(csvRecord.get(1));            
          }
          
          System.out.println(csvRecord.get(2));
          System.out.println(csvRecord.get(3));
          System.out.println(csvRecord.get(4));
          System.out.println(csvRecord.get(5));
          System.out.println(csvRecord.get(6));
          System.out.println(csvRecord.get(7));
          System.out.println(csvRecord.get(8));
          System.out.println(csvRecord.get(9));
          System.out.println(csvRecord.get(10));
          System.out.println();
          */
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }
  }
}
