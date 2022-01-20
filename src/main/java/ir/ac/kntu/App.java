package ir.ac.kntu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class App extends Application {
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/main.fxml")));
        scene = new Scene(root, 900, 650, false, SceneAntialiasing.BALANCED);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("AI Course Project");
        stage.show();
//        stage.setAlwaysOnTop(true);
//        stage.setOnCloseRequest(status -> System.exit(0));
//        stage.getIcons().add(new Image("file://" + Objects.requireNonNull(getClass()
//                .getResource("/images/almas.jfif")).getFile()));

    }
}
