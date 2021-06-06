package calculator;

import object.*;

public class PNLCalculator {

    /* If sold size is greater than bought size of the coin at a particular transaction
       then first calculate profit based on the bought size for that particular buy transaction.
       The rest of the sold coins' pnl can be calculated compared to the next buy transaction(s).

       EXAMPLE: You bought 0.20 coin at price $30, bought another 0.20 coins $40.
                Then you want to sell 0.40 coins at once at $50.
                This will calculate the PNL on the first set of 0.20 coins at $30
                from your sell of 0.20 coins at $50.
                Then after, it will calculate the PNL on the second set of 0.20 coins at $40
                from your sell of 0.20 coins at $50.
                  (0.20 * 50) - (0.20 * 30)
                + (0.20 * 50) - (0.20 * 40)
                                         6

       EXAMPLE: You bought 1 coin at price of $100. Then sold 0.5 coin at price of $200.
       0.5 * 200 - 0.5 * 100
                          50
     */
    public double calculate(RecordData boughtData, RecordData soldData) {
        double pivotSize = Math.min(soldData.getSize(), boughtData.getSize());

        double soldAmount = pivotSize * soldData.getPrice()*-1;
        double boughtAmount = pivotSize * boughtData.getPrice();

        double pnl = soldAmount - boughtAmount;

        return pnl;
    }
}
