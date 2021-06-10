package viewer.components;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LeftPanel {
    private Label profitValue;
    private Label lossValue;
    private Label netPNLValue;

    public VBox build() {
        Label profitLabel = new Label("Profit");
        profitLabel.setPadding(new Insets(10, 10, 0, 10));
        profitValue = new Label();
        profitValue.setPadding(new Insets(0, 10, 10, 10));
        profitValue.setId("profit");

        Label lossLabel = new Label("Loss");
        lossLabel.setPadding(new Insets(10, 10, 5, 10));
        lossValue = new Label();
        lossValue.setPadding(new Insets(0, 10, 10, 10));
        lossValue.setId("loss");

        Label pnlLabel = new Label("Net Profit/Loss");
        pnlLabel.setPadding(new Insets(10, 10, 0, 10));
        netPNLValue = new Label();
        netPNLValue.setPadding(new Insets(0, 10, 10, 10));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(profitLabel, profitValue, lossLabel, lossValue, pnlLabel, netPNLValue);

        return vBox;
    }

    public void setProfitValue(String profitValue) {
        this.profitValue.setText(profitValue);
    }

    public void setLossValue(String lossValue) {
        this.lossValue.setText(lossValue);
    }

    public void setNetPNLValue(String netPNLValue) {
        this.netPNLValue.setText(netPNLValue);
    }

    public void setNetPNLClass(String color) {
        this.netPNLValue.setId(color);
    }
}
