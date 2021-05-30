package parser;

import org.junit.jupiter.api.*;

import java.util.*;

public class ExcelParserTest {
    ExcelParser excelParser = new ExcelParser("src\\main\\resources\\Crypto Tracker.xlsx");
    ArrayList<ArrayList<ArrayList<Double>>> sheetList = excelParser.parse();

    @Test
    public void shouldEqual4Sheets() {
        Assertions.assertEquals(sheetList.size(), 4);
    }

    @Test
    public void shouldEqualLengths() {
        Assertions.assertEquals(sheetList.get(0).size(), 16);
        Assertions.assertEquals(sheetList.get(1).size(), 8);
        Assertions.assertEquals(sheetList.get(2).size(), 7);
        Assertions.assertEquals(sheetList.get(3).size(), 7);
    }

}
