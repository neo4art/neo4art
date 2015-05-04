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

import org.neo4art.colour.domain.ColourAnalysis;

/**
 * 
 * @author Mattia Zaratin
 * @since 2 May 2015
 */
public class WriteFileService
{
  private String       tipo;
  private WriteFileCsv writeFileCsv;
  private WriteFileTxt writeFileTxt;

  public WriteFileService(String file)
  {
    this.tipo = file;
    writeFileTxt = new WriteFileTxt(tipo);
    writeFileCsv = new WriteFileCsv(tipo);
  }

  public void saveReport(ColourAnalysis image, boolean flag)
  {
    IWriteFile writeFile = null;
    if (flag == true)
    {
      writeFile = new WriteFileTxt(tipo);

    }
    else
    {
      writeFile = new WriteFileCsv(tipo);
    }

    writeFile.saveReport(image);
  }

  public void savePixel(Color[] c, boolean flag)
  {
    IWriteFile writeFile = null;
    if (flag == true)
    {
      writeFile = new WriteFileTxt(tipo);
    }
    else
    {
      writeFile = new WriteFileCsv(tipo);
    }
    writeFile.savePixel(c);
  }

  public void inizializeFile()
  {
    writeFileTxt.isFileTxt();
    writeFileCsv.isFileCsv();
    writeFileCsv.createSheet();
  }
}
