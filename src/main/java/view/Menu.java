package view;

import game.Controlleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Menu  {

    private static Controlleur controlleur;

    public static Menu creerEtAfficher(Controlleur c, Stage laStageUnique) {
        URL location = Menu.class.getResource("/view/menu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Menu vue = fxmlLoader.getController();
        laStageUnique.setTitle("MENU");
        assert root != null;
        laStageUnique.setScene(new Scene(root, 600, 400));
        laStageUnique.show();
        controlleur=c;
        return vue;
    }

    public void jouer(MouseEvent mouseEvent) {
        controlleur.goToJeu();
    }
}
