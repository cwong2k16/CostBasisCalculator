package viewer.components;

import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.*;
import object.*;

public class CenterPanel {
    private final TableView<RowData> cryptoDataTable = new TableView<>();
    private VBox content;

    public CenterPanel() {
        content = new VBox();
        content.setSpacing(5);
        content.setPadding(new Insets(10, 10, 10, 10));

        TableColumn cryptoNameCol = new TableColumn("Name");
        cryptoNameCol.setMinWidth(100);
        cryptoNameCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().getCostBasis().getName()));


        TableColumn numberOfCoinsCol = new TableColumn("# of Coins");
        numberOfCoinsCol.setMinWidth(100);
        numberOfCoinsCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>) param -> new SimpleStringProperty(Double.toString(param.getValue().getCostBasis().getCoins())));

        TableColumn costBasisCol = new TableColumn("Cost Basis");
        costBasisCol.setMinWidth(100);
        costBasisCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>) param -> new SimpleStringProperty(Double.toString(param.getValue().getCostBasis().getCostBasis())));

        TableColumn profitCol = new TableColumn("Profit");
        profitCol.setMinWidth(100);
        profitCol.setId("profit");
        profitCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>) param -> new SimpleStringProperty(Double.toString(param.getValue().getPnl().getProfit())));

        TableColumn lossCol = new TableColumn("Loss");
        lossCol.setMinWidth(100);
        lossCol.setId("loss");
        lossCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>) param -> new SimpleStringProperty(Double.toString(param.getValue().getPnl().getLoss())));


        TableColumn netPNLCol = new TableColumn("Net PNL");
        netPNLCol.setMinWidth(100);
        netPNLCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>) param -> new SimpleStringProperty(Double.toString(param.getValue().getPnl().getProfit() + param.getValue().getPnl().getLoss())));

        cryptoDataTable.getColumns().addAll(cryptoNameCol, numberOfCoinsCol, costBasisCol, profitCol, lossCol, netPNLCol);
        cryptoDataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        content.getChildren().add(cryptoDataTable);
    }

    public VBox build() {
        return content;
    }

    public void setData(ObservableList<RowData> data) {
        cryptoDataTable.setItems(data);
    }
}
