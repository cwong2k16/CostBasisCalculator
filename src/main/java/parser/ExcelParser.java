package parser;

import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelParser {
    private File excelFile;
    private FileInputStream fis = null;
    private HashMap<String, ArrayList<ArrayList<Double>>> sheetMap = new HashMap<>();

    public ExcelParser(String file) {
        this.excelFile = new File(file);
    }

    public HashMap<String, ArrayList<ArrayList<Double>>> parse() {
        try {
            fis = new FileInputStream(excelFile);
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
            ArrayList<ArrayList<Double>> dataList = new ArrayList<>();
            int rowIndex = 2;
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            XSSFRow row = sheet.getRow(rowIndex);
            while (row != null) {
                ArrayList<Double> innerDataSet = new ArrayList<>();
                innerDataSet.add(row.getCell(1).getNumericCellValue());
                innerDataSet.add(row.getCell(2).getNumericCellValue());
                innerDataSet.add(row.getCell(3).getNumericCellValue());
                dataList.add(innerDataSet);

                rowIndex++;
                row = sheet.getRow(rowIndex);
            }
            String coin = sheet.getRow(0).getCell(0).getStringCellValue();
            sheetMap.put(coin, dataList);
            sheetIndex++;
        }
        return sheetMap;
    }
}
