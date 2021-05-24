package view;

import javafx.stage.Stage;

public abstract class Vue {

    private final Stage stage;

    protected Vue(Stage stage) {
        this.stage = stage;
    }
}
