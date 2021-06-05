package parser;

import object.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class CSVParserTest {
    CSVParser csvParser = new CSVParser();
    ArrayList<RecordData> transactions = csvParser.parse("src\\test\\resources\\fills.csv");

    @Test
    public void shouldEqual4Sheets() {
        Assertions.assertEquals(transactions.size(), 40);
    }

    @Test
    public void shouldEqualLengths() {
        long btcCount = transactions.stream()
                                 .filter(x -> x.getName().equals("BTC"))
                                 .count();
        Assertions.assertEquals(btcCount, 17);

        long ethCount = transactions.stream()
                .filter(x -> x.getName().equals("ETH"))
                .count();
        Assertions.assertEquals(ethCount, 7);

        long mkrCount = transactions.stream()
                .filter(x -> x.getName().equals("MKR"))
                .count();
        Assertions.assertEquals(mkrCount, 8);

        long adaCount = transactions.stream()
                .filter(x -> x.getName().equals("ADA"))
                .count();
        Assertions.assertEquals(adaCount, 8);
    }

}
