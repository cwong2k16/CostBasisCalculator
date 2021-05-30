import calculator.*;
import parser.*;

import java.util.*;

public class Driver {
    /* EXAMPLE
    Coin Fraction	Coin Price		Cost/Sold
    0.011192		4513.1946		50.51167396
    0.011756		4300			50.5508
    -0.02294		4500			103.23
    0.011709		4250			49.76325
    */
    public static void main (String [] args) {
        ExcelParser excelParser = new ExcelParser("src\\main\\resources\\Crypto Tracker.xlsx");
        CostBasis costBasis = new CostBasis(excelParser.parse());
        costBasis.calculate();
    }

}
