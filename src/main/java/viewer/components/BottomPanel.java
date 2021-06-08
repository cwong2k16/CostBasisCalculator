package viewer.components;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;

public class BottomPanel {
    private HBox hBox;
    private Button browse;
    private FileChooser fileChooser;
    private File selectFile;
    private String file;

    public HBox build(){
        browse = new Button();
        browse.setText("Choose CSV Statement");
        browse.setOnAction(arg0 -> openFileChooser());

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(browse);

        return hBox;
    }

    private void openFileChooser() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Excel/CSV","*.xslx", "*.csv");

        fileChooser.getExtensionFilters().add(fileExtensions);
        selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null) {
            file = selectFile.getAbsolutePath();
        }
    }
}
