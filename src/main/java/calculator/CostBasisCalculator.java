package calculator;

import object.*;

import java.util.*;

public class CostBasisCalculator {
    ArrayList<RecordData> transactions;
    HashMap<String, Queue<RecordData>> coinTracker = new HashMap<>();

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
        for(RecordData recordData : transactions) {
            // BUY logic
            if(recordData.getAmount() > 0) {
                Queue <RecordData> data = coinTracker.getOrDefault(recordData.getName(), new LinkedList<>());
                data.add(recordData);
                coinTracker.put(recordData.getName(), data);
            }
            // SELL logic, FIFO strategy
            else {
                boolean finished = false;
                RecordData fromQueue = coinTracker.get(recordData.getName()).peek();
                double difference = fromQueue.getSize() - recordData.getSize();

                while(!finished) {
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
        System.out.println(coinTracker);
        return null;
    }
}
