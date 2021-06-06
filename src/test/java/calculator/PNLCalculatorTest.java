package calculator;

import object.*;
import org.junit.jupiter.api.*;

public class PNLCalculatorTest {
    PNLCalculator pnlCalculator = new PNLCalculator();

    @Test
    public void testProfit() {
        RecordData bought = new RecordData("ADA",1.0, 100.0);
        RecordData sold = new RecordData("ADA", 1.0, -200.0);

        double netPNL = pnlCalculator.calculate(bought, sold);

        Assertions.assertEquals(netPNL, 100.0);
    }

    @Test
    public void testLoss() {
        RecordData bought = new RecordData("ADA",1.0, 100.0);
        RecordData sold = new RecordData("ADA", 1.0, -75.0);

        double netPNL = pnlCalculator.calculate(bought, sold);

        Assertions.assertEquals(netPNL, -25.0);
    }

    @Test
    public void netPNL() {
        RecordData bought = new RecordData("ADA",1.0, 100.0);
        RecordData sold = new RecordData("ADA", 0.5, -75.0);

        double loss = pnlCalculator.calculate(bought, sold);

        RecordData sold2 = new RecordData("ADA", 0.5, -200.0);

        double profit = pnlCalculator.calculate(bought, sold2);

        // -12.5 + 50 = 37.5
        Assertions.assertEquals(profit+loss, 37.5);
    }
}
