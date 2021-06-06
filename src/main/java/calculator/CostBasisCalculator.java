package calculator;

import object.*;

import java.util.*;

public class CostBasisCalculator {
    ArrayList<RecordData> transactions;
    HashMap<String, Queue<RecordData>> coinTracker = new HashMap<>();
    HashMap<String, PNL> pnlMap = new HashMap<>();
    PNLCalculator pnlCalculator = new PNLCalculator();

    public CostBasisCalculator(ArrayList<RecordData> transactions) {
        this.transactions = transactions;
    }

    /* EXAMPLE
    Coin Amount	    Coin Price		Cost/Sold
    0.011192		4513.1946		50.51167396
    0.011756		4300			50.5508
    -0.02294		4500			103.23
    0.011709		4250			49.76325
    */
    public ArrayList<CostBasis> calculate() {
        calibrateHoldingWeights();
        return calculateCostBasis();
    }

    private void calibrateHoldingWeights() {
        for(RecordData recordData : transactions) {
            // BUY logic
            if(recordData.getAmount() > 0) {
                Queue <RecordData> data = coinTracker.getOrDefault(recordData.getName(), new LinkedList<>());
                data.add(recordData);
                coinTracker.put(recordData.getName(), data);
            }
            // SELL logic, FIFO strategy
            else {
                RecordData fromQueue = coinTracker.get(recordData.getName()).peek();
                double difference = fromQueue.getSize() - recordData.getSize();

                boolean finished = false;
                while(!finished) {
                    updatePNL(recordData, coinTracker.get(recordData.getName()).peek());
                    if(difference < 0) {
                        coinTracker.get(recordData.getName()).poll();
                        difference = coinTracker.get(recordData.getName()).peek().getSize() + difference;
                     }
                     else {
                        coinTracker.get(recordData.getName()).peek().setSize(difference);
                         if(difference == 0){
                             coinTracker.get(recordData.getName()).poll();
                         }
                        finished = true;
                     }
                }
            }
        }
    }

    private void updatePNL(RecordData recordData, RecordData fromQueue) {
        double pnl = pnlCalculator.calculate(fromQueue, recordData);
        if (pnlMap.get(recordData.getName()) == null) {
            pnlMap.put(recordData.getName(), new PNL());
        }
        if(pnl >= 0) {
            pnlMap.get(fromQueue.getName()).setProfit(pnlMap.get(fromQueue.getName()).getProfit() + pnl);
        }
        else{
            pnlMap.get(fromQueue.getName()).setLoss(pnlMap.get(fromQueue.getName()).getLoss() + pnl);
        }
    }

    private ArrayList<CostBasis> calculateCostBasis() {
        ArrayList<CostBasis> resultList = new ArrayList<>();
        Iterator<Map.Entry<String, Queue<RecordData>>> it = coinTracker.entrySet().iterator();
        while (it.hasNext()) {
            CostBasis costBasisObj = new CostBasis();
            Map.Entry<String, Queue<RecordData>> pair = it.next();
            String name = pair.getKey();
            double coins = 0;
            double costBasis = 0;
            Queue<RecordData> queue = pair.getValue();
            while (queue.peek() != null) {
                double totalCoins = coins + queue.peek().getSize();
                double newCostBasis = (coins / totalCoins * costBasis) +
                        (queue.peek().getSize() / totalCoins * queue.peek().getPrice());
                coins = totalCoins;
                costBasis = newCostBasis;
                queue.poll();
            }
            costBasisObj.setName(name);
            costBasisObj.setCostBasis(costBasis);
            costBasisObj.setCoins(coins);
            resultList.add(costBasisObj);
            it.remove(); // avoids a ConcurrentModificationException
        }
        return resultList;
    }

    public HashMap<String, PNL> getPnlMap(){
        return this.pnlMap;
    }
}
