package object;

public class PNL {
    private double profit;
    private double loss;

    public PNL(){
        this.profit = 0;
        this.loss = 0;
    }

    public PNL(double profit, double loss){
        this.profit = profit;
        this.loss = loss;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }
}
