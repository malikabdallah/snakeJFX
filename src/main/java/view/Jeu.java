package view;

import game.Animation;
import game.Body;
import game.Controlleur;
import game.Direction;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeu {

    private static Controlleur controlleur;
    public static int speed = 5;
    public static int foodcolor = 0;
    public static int width = 20;
    public static int height = 20;
    public static int foodX = 0;
    public static int foodY = 0;
    public static int cornersize = 25;
    public static List<Body> snake = new ArrayList<>();
    public static Direction direction = Direction.left;
    public static boolean gameOver = false;
    public static Random rand = new Random();
    public static Canvas canvas;
    public static GraphicsContext gc;

    public static Jeu creerEtAfficher(Controlleur c, Stage laStageUnique) {
        URL location = Jeu.class.getResource("/view/jeu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        AnchorPane root = null;
        try { root = fxmlLoader.<AnchorPane>load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jeu vue = fxmlLoader.getController();
        laStageUnique.setTitle("MENU");
        assert root != null;
        //laStageUnique.setScene(new Scene(root, 600, 400));

        //laStageUnique.show();
        controlleur=c;

            canvas = new Canvas(width * cornersize, height * cornersize);
            gc = canvas.getGraphicsContext2D();
           root.getChildren().add(canvas);

            AnimationTimer animationTimer=new Animation();
            animationTimer.start();
            Scene scene = new Scene(root, width * cornersize, height * cornersize);

            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.E) {
                    direction = Direction.up;
                }
                if (key.getCode() == KeyCode.S) {
                    direction = Direction.left;
                }
                if (key.getCode() == KeyCode.D) {
                    direction = Direction.down;
                }
                if (key.getCode() == KeyCode.F) {
                    direction = Direction.right;
                }

            });

            // add start snake parts
            snake.add(new Body(width / 2, height / 2));
            snake.add(new Body(width / 2, height / 2));
            snake.add(new Body(width / 2, height / 2));


            //If you do not want to use css style, you can just delete the next line.
            laStageUnique.setScene(scene);
            laStageUnique.setTitle("SNAKE GAME");
            laStageUnique.show();

        return vue;
    }


    public static void newFood() {
        start: while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for (Body c : snake) {
                if (c.getX() == foodX && c.getY() == foodY) {
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);
            speed++;
            break;

        }
    }


}
