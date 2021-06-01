import calculator.*;
import parser.*;

import java.util.*;

public class Driver {
    public static void main (String [] args) {
        ExcelParser excelParser = new ExcelParser("src\\main\\resources\\Crypto Tracker.xlsx");
        CostBasis costBasis = new CostBasis(excelParser.parse());
        costBasis.calculate();
    }

}
