package calculator;

import object.*;

import java.util.*;

public class CostBasisCalculator {
    HashMap<String, ArrayList<ArrayList<Double>>> sheetMap;

    public CostBasisCalculator(HashMap<String, ArrayList<ArrayList<Double>>> sheetMap) {
        this.sheetMap = sheetMap;
    }

    /* EXAMPLE
    Coin Amount	    Coin Price		Cost/Sold
    0.011192		4513.1946		50.51167396
    0.011756		4300			50.5508
    -0.02294		4500			103.23
    0.011709		4250			49.76325
    */
    public ArrayList<CostBasis> calculate() {
        ArrayList<CostBasis> resultList = new ArrayList<>();
        Iterator<Map.Entry<String, ArrayList<ArrayList<Double>>>> it = sheetMap.entrySet().iterator();
        while (it.hasNext()) {
            CostBasis costBasisObj = new CostBasis();
            Map.Entry<String, ArrayList<ArrayList<Double>>> pair = it.next();
            String name = pair.getKey();
            double coins = 0;
            double costBasis = 0;
            ArrayList<ArrayList<Double>> list = pair.getValue();
            for (int i = 0; i < list.size(); i++) {
                // negative means sell
                if (list.get(i).get(1) < 0) {
                    coins -= list.get(i).get(0);
                    costBasis = coins == 0 ? 0 : costBasis;
                } else {
                    double totalCoins = coins + list.get(i).get(0);
                    double newCostBasis = (coins / totalCoins * costBasis) +
                            (list.get(i).get(0) / totalCoins * list.get(i).get(1));
                    coins = totalCoins;
                    costBasis = newCostBasis;
                }
            }
            costBasisObj.setName(name);
            costBasisObj.setCostBasis(costBasis);
            costBasisObj.setCoins(coins);
            resultList.add(costBasisObj);
            it.remove(); // avoids a ConcurrentModificationException
        }
        return resultList;
    }
}
