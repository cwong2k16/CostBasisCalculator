import calculator.*;
import object.*;
import parser.*;

import java.util.*;

public class Driver {
    public static void main (String [] args) {
        ExcelParser excelParser = new ExcelParser();
        System.out.println(excelParser.parse("src\\main\\resources\\Crypto Tracker.xlsx"));
//        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(csvParser.parse("src\\main\\resources\\fills.csv"));
//        ArrayList<CostBasis> costBasesCSV = costBasisCalculator.calculate();

//        for(CostBasis costBasis : costBasesCSV) {
//            System.out.println("Coin: " + costBasis.getName());
//            System.out.println("Coins: " + costBasis.getCoins());
//            System.out.println("Cost Basis: " + costBasis.getCostBasis());
//            System.out.println();
//        }
    }

}
