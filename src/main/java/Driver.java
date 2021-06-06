import calculator.*;
import object.*;
import parser.*;

import java.util.*;

public class Driver {
    public static void main (String [] args) {
        CSVParser csvParser = new CSVParser();
        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(csvParser.parse("src\\main\\resources\\fills.csv"));
        ArrayList<CostBasis> costBases = costBasisCalculator.calculate();

        for(CostBasis costBasis : costBases) {
            System.out.println("Cryptocurrency: " + costBasis.getName() + "\n" +
                               "Coins: " + costBasis.getCoins() + "\n" +
                               "Cost Basis: " + costBasis.getCostBasis() + "\n");
        }
    }
}
