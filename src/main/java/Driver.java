import calculator.*;
import object.*;
import parser.*;

import java.util.*;

public class Driver {
    public static void main (String [] args) {
        CSVParser csvParser = new CSVParser();
        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(csvParser.parse("src\\main\\resources\\fills.csv"));
        costBasisCalculator.calculate();
    }
}
