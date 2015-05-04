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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.neo4art.colour.domain.ColourAnalysis;

/**
 * 
 * @author Mattia Zaratin
 * @since 2 May 2015
 */
public class WriteFileCsv implements IWriteFile
{

  private String csv;

  public WriteFileCsv(String csv)
  {
    this.csv = csv + ".csv";
  }

  public void savePixel(Color[] c)
  {
    try
    {
      int i = 0;
      FileInputStream inp = new FileInputStream(csv);
      HSSFWorkbook workbook = new HSSFWorkbook(inp);
      HSSFSheet worksheet = workbook.getSheet("Pixel image");
      while (i < c.length)
      {
        HSSFRow row = worksheet.createRow(worksheet.getLastRowNum() + 1);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        HSSFCell cell1 = row.createCell(0);
        cell1.setCellStyle(cellStyle);
        HSSFCell cell2 = row.createCell(1);
        cell2.setCellStyle(cellStyle);
        HSSFCell cell3 = row.createCell(2);
        cell3.setCellStyle(cellStyle);

        cell1.setCellValue(c[i].getRed());
        cell2.setCellValue(c[i].getGreen());
        cell3.setCellValue(c[i].getBlue());
        FileOutputStream fileOut = new FileOutputStream(csv);
        workbook.write(fileOut);
        i++;
      }
      workbook.close();
    }
    catch (Exception e)
    {

    }
  }

  public void saveReport(ColourAnalysis image)
  {
    try
    {
      Color[] temp = new Color[3];
      FileInputStream inp = new FileInputStream(csv);
      HSSFWorkbook workbook = new HSSFWorkbook(inp);

      HSSFSheet worksheet = workbook.getSheet("Report");
      CellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
      HSSFRow row = worksheet.createRow((short) 7);
      HSSFRow row1 = worksheet.createRow((short) 9);
      HSSFRow row3 = worksheet.createRow((short) 11);
      HSSFRow row4 = worksheet.createRow((short) 13);

      HSSFCell cell1 = row.createCell(0);
      cell1.setCellStyle(cellStyle);
      HSSFCell cell3 = row1.createCell(0);
      cell3.setCellStyle(cellStyle);
      HSSFCell cell4 = row3.createCell(0);
      cell4.setCellStyle(cellStyle);
      HSSFCell cell5 = row4.createCell(0);
      cell5.setCellStyle(cellStyle);

      cell1.setCellValue(image.getAverageClosestColour().getName());
      cell3.setCellValue(image.getMaximumClosestColour().getName());
      cell4.setCellValue(image.getMinimumClosestColour().getName());
      cell5.setCellValue(image.getIncrement());

      temp[0] = image.getAverageColour();
      temp[1] = image.getMinimumColour();
      temp[2] = image.getMaximumColour();
      int j = 1;
      for (int i = 0; i < 3; i++)
      {
        Color c = temp[i];

        HSSFRow row2 = worksheet.createRow((short) j);

        HSSFCell cell1a = row2.createCell(0);
        cell1a.setCellStyle(cellStyle);
        HSSFCell cell2a = row2.createCell(1);
        cell2a.setCellStyle(cellStyle);
        HSSFCell cell3a = row2.createCell(2);
        cell3a.setCellStyle(cellStyle);

        cell1a.setCellValue(c.getRed());
        cell2a.setCellValue(c.getGreen());
        cell3a.setCellValue(c.getBlue());

        FileOutputStream fileOut = new FileOutputStream(csv);
        workbook.write(fileOut);
        workbook.close();
        j = j + 2;
      }

    }
    catch (Exception e)
    {

    }
  }

  public void createSheet()
  {
    try
    {
      FileOutputStream fileOut = new FileOutputStream(csv);
      HSSFWorkbook workbook = new HSSFWorkbook();
      HSSFSheet worksheet = workbook.createSheet("Pixel image");
      HSSFRow row1 = worksheet.createRow((short) 0);

      HSSFCell cellA1 = row1.createCell(0);
      HSSFCell cellA2 = row1.createCell(1);
      HSSFCell cellA3 = row1.createCell(2);

      cellA1.setCellValue("RED");
      cellA2.setCellValue("GREEN");
      cellA3.setCellValue("BLUE");

      CellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
      HSSFSheet worksheetReport = workbook.createSheet("Report");
      HSSFRow rowReport = worksheetReport.createRow((short) 0);
      HSSFRow rowReport1 = worksheetReport.createRow((short) 2);
      HSSFRow rowReport2 = worksheetReport.createRow((short) 4);
      HSSFRow rowReport3 = worksheetReport.createRow((short) 6);
      HSSFRow rowReport4 = worksheetReport.createRow((short) 8);
      HSSFRow rowReport5 = worksheetReport.createRow((short) 10);
      HSSFRow rowReport6 = worksheetReport.createRow((short) 12);

      worksheetReport.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(6, 6, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(7, 7, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(8, 8, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(9, 9, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(10, 10, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(11, 11, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(12, 12, 0, 2));
      worksheetReport.addMergedRegion(new CellRangeAddress(13, 13, 0, 2));

      HSSFCell cellB1 = rowReport.createCell(0);
      cellB1.setCellStyle(cellStyle);
      HSSFCell cellB2 = rowReport1.createCell(0);
      cellB2.setCellStyle(cellStyle);
      HSSFCell cellB3 = rowReport2.createCell(0);
      cellB3.setCellStyle(cellStyle);
      HSSFCell cellB4 = rowReport3.createCell(0);
      cellB4.setCellStyle(cellStyle);
      HSSFCell cellB5 = rowReport4.createCell(0);
      cellB5.setCellStyle(cellStyle);
      HSSFCell cellB6 = rowReport5.createCell(0);
      cellB6.setCellStyle(cellStyle);
      HSSFCell cellB7 = rowReport6.createCell(0);
      cellB7.setCellStyle(cellStyle);

      cellB1.setCellValue("MAX RGB");
      cellB2.setCellValue("MIN RGB");
      cellB3.setCellValue("AVG RGB");
      cellB4.setCellValue("NAME RGB AVG");
      cellB5.setCellValue("NAME RGB MAX");
      cellB6.setCellValue("NAME RGB MIN");
      cellB7.setCellValue("INCREMENT");

      workbook.write(fileOut);
      workbook.close();
      fileOut.flush();
      fileOut.close();
    }
    catch (Exception e)
    {

    }
  }

  public void isFileCsv()
  {
    File f = new File(csv);
    boolean flag = f.exists();
    if (flag == true)
    {
      f.delete();
    }
  }
}
