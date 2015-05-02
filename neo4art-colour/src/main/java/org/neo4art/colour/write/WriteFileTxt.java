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
package org.neo4art.colour.write;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.neo4art.colour.domain.ColourAnalysis;

/**
 * 
 * @author Mattia Zaratin
 * @since 2 May 2015
 */
public class WriteFileTxt implements IWriteFile
{

  private String file;

  public WriteFileTxt(String file)
  {
    this.file = file + ".txt";
  }

  public void savePixel(Color[] c)
  {
    int i = 0;
    try
    {
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
      while (i < c.length)
      {
        String testo = "Red:" + c[i].getRed() + "Green:" + c[i].getGreen() + "Blue:" + c[i].getBlue() + "\n";
        pw.print(testo);
        i++;
      }
      pw.close();
    }
    catch (Exception e)
    {

    }
  }

  public void saveReport(ColourAnalysis image)
  {
    try
    {
      String rAvg = "AVG RED: " + image.getAverageColour().getRed() + " AVG GREEN: " + image.getAverageColour().getGreen() + " AVG BLUE: " + image.getAverageColour().getBlue() + "\n";
      String rMin = "MIN RED: " + image.getMinimumColour().getRed() + " MIN GREEN: " + image.getMinimumColour().getGreen() + " MIN BLUE: " + image.getMinimumColour().getBlue() + "\n";
      String rMax = "MAX RED: " + image.getMaximumColour().getRed() + " MAX GREEN: " + image.getMaximumColour().getGreen() + " MAX BLUE: " + image.getMaximumColour().getBlue() + "\n";
      String rNameAvg = "NAME RGB AVG: " + image.getAverageClosestColour().getName() + "\n";
      String rNameMax = "NAME RGB MAX: " + image.getMaximumClosestColour().getName() + "\n";
      String rNameMin = "NAME RGB MIN: " + image.getMinimumClosestColour().getName() + "\n";
      String increment = "INCREMENT: " + image.getIncrement();
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
      pw.print(rAvg);
      pw.print(rMin);
      pw.print(rMax);
      pw.print(rNameAvg);
      pw.print(rNameMax);
      pw.print(rNameMin);
      pw.print(increment);
      pw.close();
    }
    catch (Exception e)
    {

    }
  }

  public void isFileTxt()
  {
    File f = new File(file);
    boolean flag = f.exists();
    if (flag == true)
    {
      f.delete();
    }
  }

}
