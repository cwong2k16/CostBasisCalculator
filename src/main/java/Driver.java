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
        calculate(excelParser.parse());
    }

    public static void calculate(ArrayList<ArrayList<Double>> list){
        double coins = 0;
        double costBasis = 0;
        for(int i = 0; i < list.size(); i++) {
            // negative means sell
            if(list.get(i).get(0) < 0) {
                coins += list.get(i).get(0);
            }
            else {
                double totalCoins = coins + list.get(i).get(0);
                double newCostBasis = (coins/totalCoins * costBasis) +
                        (list.get(i).get(0)/totalCoins * list.get(i).get(1));
                coins = totalCoins;
                costBasis = newCostBasis;
            }
        }
        System.out.println("Coins: " + coins);
        System.out.println("Cost Basis: " + costBasis);

    }
}
