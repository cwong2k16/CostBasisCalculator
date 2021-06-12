package object;

public class RowData {
    private CostBasis costBasis;
    private PNL pnl;

    public RowData(CostBasis costBasis, PNL pnl) {
        this.costBasis = costBasis;
        this.pnl = pnl;
    }

    public CostBasis getCostBasis() {
        return costBasis;
    }

    public PNL getPnl() {
        return pnl;
    }
}
