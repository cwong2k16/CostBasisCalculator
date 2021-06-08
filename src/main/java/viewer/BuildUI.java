package viewer;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import viewer.components.*;

public class BuildUI {
    private Scene scene;
    private BorderPane borderPane;

    public Scene build() {
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setBottom(new BottomPanel().build());

        scene = new Scene(borderPane);
        scene.getStylesheets().add("styles/style.css");
        return scene;
    }
}
