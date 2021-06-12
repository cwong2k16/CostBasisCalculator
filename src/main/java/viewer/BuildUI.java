package viewer;

import calculator.*;
import javafx.collections.*;
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
    private LeftPanel leftPanel;
    private CenterPanel centerPanel;
    private HBox browse;
    private DecimalFormat df = new DecimalFormat("$0.00");

    public Scene build() {
        bottomPanel = new BottomPanel();
        leftPanel = new LeftPanel();
        centerPanel = new CenterPanel();
        browse = bottomPanel.build();
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50, 10, 10, 10));
        borderPane.setBottom(browse);
        borderPane.setLeft(leftPanel.build());
        borderPane.setCenter(centerPanel.build());
        bottomPanel.getFileChooserButton().setOnAction(e-> execute(bottomPanel.openFileChooser()));
        scene = new Scene(borderPane);
        scene.getStylesheets().add("styles/style.css");
        return scene;
    }

    public void execute(String filePath) {
        ArrayList<RecordData> recordData = filePath.contains(".csv") ? new CSVParser().parse(filePath)
                                                                      : new ExcelParser().parse(filePath);
        CostBasisCalculator costBasisCalculator = new CostBasisCalculator(recordData);
        ArrayList<CostBasis> costBases = costBasisCalculator.calculate();
        HashMap<String, PNL> pnlHashMap = costBasisCalculator.getPnlMap();
        double totalProfit = 0;
        double totalLoss = 0;
        double netPNL;

        ObservableList<RowData> rowDataList = FXCollections.observableArrayList();
        for(CostBasis costBasis : costBases) {
            double profit = pnlHashMap.getOrDefault(costBasis.getName(), new PNL()).getProfit();
            double loss = pnlHashMap.getOrDefault(costBasis.getName(), new PNL()).getLoss();
            PNL pnl = new PNL(profit, loss);

            RowData rowData = new RowData(costBasis, pnl);
            rowDataList.add(rowData);
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

        leftPanel.setProfitValue(df.format(totalProfit));
        leftPanel.setLossValue(df.format(totalLoss));
        leftPanel.setNetPNLValue(df.format(netPNL));

        centerPanel.setData(rowDataList);

        togglePNLColor(netPNL);
    }

    public void togglePNLColor(double netPNL) {
        if (netPNL > 0) {
            leftPanel.setNetPNLClass("profit");
        } else {
            leftPanel.setNetPNLClass("loss");
        }
    }
}
