package game;

import javafx.stage.Stage;
import view.Jeu;
import view.Menu;

public class Controlleur {


    final Stage laStageUnique;


    private Menu menuView;
    private Jeu jeu;

    public Controlleur(Stage stage) {
        laStageUnique = stage;

        menuView = Menu.creerEtAfficher(this,stage);

    }

    public void goToJeu() {
        jeu=Jeu.creerEtAfficher(this,laStageUnique);

    }
}
