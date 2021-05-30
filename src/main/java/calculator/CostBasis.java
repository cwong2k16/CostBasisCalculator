package calculator;

import java.util.*;

public class CostBasis {
    ArrayList<ArrayList<ArrayList<Double>>> sheetList;

    public CostBasis(ArrayList<ArrayList<ArrayList<Double>>> sheetList) {
        this.sheetList = sheetList;
    }

    public void calculate() {
        int sheetIndex = 0;
        while (sheetIndex < this.sheetList.size()) {
            ArrayList<ArrayList<Double>> list = this.sheetList.get(sheetIndex);
            double coins = 0;
            double costBasis = 0;
            for (int i = 0; i < list.size(); i++) {
                // negative means sell
                if (list.get(i).get(1) < 0) {
                    coins -= list.get(i).get(0);
                } else {
                    double totalCoins = coins + list.get(i).get(0);
                    double newCostBasis = (coins / totalCoins * costBasis) +
                            (list.get(i).get(0) / totalCoins * list.get(i).get(1));
                    coins = totalCoins;
                    costBasis = newCostBasis;
                }
            }
            System.out.println("Coins: " + coins);
            System.out.println("Cost Basis: " + costBasis);
            System.out.println();
            sheetIndex++;
        }
    }
}
