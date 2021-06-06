import calculator.*;
import object.*;
import parser.*;

import java.util.*;

public class Driver {
    public static void main (String [] args) {
        CSVParser csvParser = new CSVParser();
        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(csvParser.parse("src\\main\\resources\\fills.csv"));
        ArrayList<CostBasis> costBases = costBasisCalculator.calculate();
        HashMap<String, PNL> pnlHashMap = costBasisCalculator.getPnlMap();

        for(CostBasis costBasis : costBases) {
            double profit = pnlHashMap.getOrDefault(costBasis.getName(), new PNL()).getProfit();
            double loss = pnlHashMap.getOrDefault(costBasis.getName(), new PNL()).getLoss();
            System.out.println("Cryptocurrency: " + costBasis.getName() + "\n" +
                               "Coins: " + costBasis.getCoins() + "\n" +
                               "Cost Basis: " + costBasis.getCostBasis() + "\n" +
                               "Profit: " + profit + "\n" +
                               "Loss: " + loss + "\n" +
                               "Net PNL: " + (profit + loss) + "\n");

        }
    }
}
