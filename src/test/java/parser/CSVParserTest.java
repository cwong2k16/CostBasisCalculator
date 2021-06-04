package parser;

import org.junit.jupiter.api.*;

import java.util.*;

public class CSVParserTest {
    CSVParser csvParser = new CSVParser();
    HashMap<String, ArrayList<ArrayList<Double>>> sheetMap = csvParser.parse("src\\test\\resources\\fills.csv");

    @Test
    public void shouldEqual4Sheets() {
        Assertions.assertEquals(sheetMap.size(), 4);
    }

    @Test
    public void shouldEqualLengths() {
        Assertions.assertEquals(sheetMap.get("BTC").size(), 17);
        Assertions.assertEquals(sheetMap.get("ETH").size(), 7);
        Assertions.assertEquals(sheetMap.get("MKR").size(), 8);
        Assertions.assertEquals(sheetMap.get("ADA").size(), 8);
    }

}
