/**n
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
package org.neo4art.colour.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class VanGoghArtworksCVSTest
{
  @Test
  public void shouldParseVanGoghArtworksCVSFile()
  {
    try
    {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("vangoghartworks/van-gogh-artworks.csv").getFile());
      CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL.withDelimiter(',').withQuote('\'').withEscape('\\').withIgnoreSurroundingSpaces(true));

      List<CSVRecord> records = csvParser.getRecords();

      for (int i = 0; i < records.size(); i++)
      {
        CSVRecord csvRecord = records.get(i);
        
        System.out.println(csvRecord.get(0));
        System.out.println("  " + csvRecord.get(1));
        System.out.println("    " + csvRecord.get(2));
        System.out.println("      " + csvRecord.get(3));
        System.out.println("        " + csvRecord.get(4));
        System.out.println("          " + csvRecord.get(5));
        System.out.println("            " + csvRecord.get(6));
      }

      csvParser.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }
  }
}
