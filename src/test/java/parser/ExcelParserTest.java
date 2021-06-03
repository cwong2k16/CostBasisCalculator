package parser;

import org.junit.jupiter.api.*;

import java.util.*;

public class ExcelParserTest {
    ExcelParser excelParser = new ExcelParser();
    HashMap<String, ArrayList<ArrayList<Double>>> sheetMap = excelParser.parse("src\\test\\resources\\Crypto Tracker.xlsx");

    @Test
    public void shouldEqual4Sheets() {
        Assertions.assertEquals(sheetMap.size(), 4);
    }

    @Test
    public void shouldEqualLengths() {
        Assertions.assertEquals(sheetMap.get("BTC").size(), 16);
        Assertions.assertEquals(sheetMap.get("ETH").size(), 7);
        Assertions.assertEquals(sheetMap.get("MKR").size(), 8);
        Assertions.assertEquals(sheetMap.get("ADA").size(), 7);
    }

}
