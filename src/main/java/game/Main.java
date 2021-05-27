package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        Controlleur controlleur = new Controlleur(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}