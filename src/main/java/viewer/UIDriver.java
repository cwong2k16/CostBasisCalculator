package viewer;
import javafx.application.Application;
import javafx.stage.*;

public class UIDriver extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Cost Basis Calculator");

        stage.setMaximized(true);
        stage.setScene(new BuildUI().build());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}