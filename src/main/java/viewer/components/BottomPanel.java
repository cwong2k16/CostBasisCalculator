package viewer.components;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;

public class BottomPanel {
    private Button browse;
    private FileChooser fileChooser;
    private File selectFile;
    private HBox hBox;

    public HBox build(){
        browse = new Button();
        browse.setText("Choose CSV Statement");
        browse.setOnAction(arg0 -> openFileChooser());

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(browse);

        return hBox;
    }

    public String openFileChooser() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Excel/CSV","*.xlsx", "*.csv");

        fileChooser.getExtensionFilters().add(fileExtensions);
        selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null) {
            return selectFile.getAbsolutePath();
        }
        return null;
    }

    public Button getFileChooserButton() {
        return browse;
    }
}
