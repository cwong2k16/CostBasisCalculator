package calculator;

import object.*;
import org.junit.jupiter.api.*;
import parser.*;

import java.text.*;
import java.util.*;

public class CostBasisCalculatorTest {
    private ExcelParser excelParser = new ExcelParser("src\\test\\resources\\Crypto Tracker.xlsx");
    private CostBasisCalculator costBasisCalculator = new CostBasisCalculator(excelParser.parse());
    private ArrayList<CostBasis> costBases = costBasisCalculator.calculate();
    private DecimalFormat df = new DecimalFormat("0.000");

    @Test
    public void shouldEqual4Sheets() {
        Assertions.assertEquals(costBases.size(), 4);
    }

    @Test
    public void assertFirstValues() throws ParseException {
        Assertions.assertEquals("BTC", costBases.get(0).getName());
        Assertions.assertEquals(0.015, df.parse(df.format(costBases.get(0).getCoins())));
        Assertions.assertEquals(50662.722, df.parse(df.format(costBases.get(0).getCostBasis())));
    }

    @Test
    public void assertSecondValues() throws ParseException {
        Assertions.assertEquals("MKR", costBases.get(1).getName());
        Assertions.assertEquals(0.031, df.parse(df.format(costBases.get(1).getCoins())));
        Assertions.assertEquals(3275.118, df.parse(df.format(costBases.get(1).getCostBasis())));
    }

    @Test
    public void assertThirdValues() throws ParseException {
        Assertions.assertEquals("ETH", costBases.get(2).getName());
        Assertions.assertEquals(0.154, df.parse(df.format(costBases.get(2).getCoins())));
        Assertions.assertEquals(2201.367, df.parse(df.format(costBases.get(2).getCostBasis())));
    }

    @Test
    public void assertFourthValues() throws ParseException {
        Assertions.assertEquals("ADA", costBases.get(3).getName());
        Assertions.assertEquals(49.49, df.parse(df.format(costBases.get(3).getCoins())));
        Assertions.assertEquals(1.749, df.parse(df.format(costBases.get(3).getCostBasis())));
    }
}
