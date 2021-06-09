package viewer;

import calculator.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import object.*;
import parser.*;
import viewer.components.*;

import java.text.*;
import java.util.*;

public class BuildUI {
    private Scene scene;
    private BorderPane borderPane;
    private BottomPanel bottomPanel;
    private CenterPanel centerPanel;
    private HBox browse;
    private DecimalFormat df = new DecimalFormat("$0.00");

    public Scene build() {
        bottomPanel = new BottomPanel();
        centerPanel = new CenterPanel();
        browse = bottomPanel.build();
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50, 10, 10, 10));
        borderPane.setBottom(browse);
        borderPane.setLeft(centerPanel.build());
        bottomPanel.getFileChooserButton().setOnAction(e-> execute(bottomPanel.openFileChooser()));
        scene = new Scene(borderPane);
        scene.getStylesheets().add("styles/style.css");
        return scene;
    }

    public void execute(String filePath) {
        CSVParser csvParser = new CSVParser();
        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(csvParser.parse(filePath));
        ArrayList<CostBasis> costBases = costBasisCalculator.calculate();
        HashMap<String, PNL> pnlHashMap = costBasisCalculator.getPnlMap();
        double totalProfit = 0;
        double totalLoss = 0;
        double netPNL;

        for(CostBasis costBasis : costBases) {
            double profit = pnlHashMap.getOrDefault(costBasis.getName(), new PNL()).getProfit();
            double loss = pnlHashMap.getOrDefault(costBasis.getName(), new PNL()).getLoss();
            System.out.println("Cryptocurrency: " + costBasis.getName() + "\n" +
                    "Coins: " + costBasis.getCoins() + "\n" +
                    "Cost Basis: " + costBasis.getCostBasis() + "\n" +
                    "Profit: " + profit + "\n" +
                    "Loss: " + loss + "\n" +
                    "Net PNL: " + (profit + loss) + "\n");
            totalProfit += profit;
            totalLoss += loss;
        }
        netPNL = totalProfit + totalLoss;

        centerPanel.setProfitValue(df.format(totalProfit));
        centerPanel.setLossValue(df.format(totalLoss));
        centerPanel.setNetPNLValue(df.format(netPNL));

        togglePNLColor(netPNL);
    }

    public void togglePNLColor(double netPNL) {
        if (netPNL > 0) {
            centerPanel.setNetPNLClass("profit");
        } else {
            centerPanel.setNetPNLClass("loss");
        }
    }
}
