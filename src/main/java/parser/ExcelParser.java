package parser;

import object.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelParser extends Parser {
    public ArrayList<RecordData> parse(String filePath) {
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int numberOfSheets = workbook.getNumberOfSheets();

        /* Example sheet : data starts at index 2
            |    BTC     |
            | Trade Date |   Size   |   Quantity   |   Price   | ... |
            | 2021-18-04 | 0.00020..|   56,295.24  |   11.738  | ... |
            | 2021-18-04 | 0.00028..|   55,792.72  |   15.748  | ... |
            | 2021-19-04 | 0.00270..|   55,120.51  |   148.95  | ... |
         */

        int sheetIndex = 0;
        while(sheetIndex < numberOfSheets) {
            int rowIndex = 2;
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            XSSFRow row = sheet.getRow(rowIndex);
            while (row != null) {
                String unit = sheet.getRow(0).getCell(0).getStringCellValue();
                double size = row.getCell(1).getNumericCellValue();
                double price = row.getCell(2).getNumericCellValue();
                double amount = row.getCell(3).getNumericCellValue();

                RecordData recordData = new RecordData(unit, size, price, amount);
                transactions.add(recordData);

                rowIndex++;
                row = sheet.getRow(rowIndex);
            }
            sheetIndex++;
        }
        return transactions;
    }
}
