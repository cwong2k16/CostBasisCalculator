import calculator.*;
import object.*;
import parser.*;

import java.util.*;

public class Driver {
    public static void main (String [] args) {
        ExcelParser excelParser = new ExcelParser("src\\main\\resources\\Crypto Tracker.xlsx");
        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(excelParser.parse());
        ArrayList<CostBasis> costBases = costBasisCalculator.calculate();

        for(CostBasis costBasis : costBases) {
            System.out.println("Coin: " + costBasis.getName());
            System.out.println("Coins: " + costBasis.getCoins());
            System.out.println("Cost Basis: " + costBasis.getCostBasis());
            System.out.println();
        }
    }

}
