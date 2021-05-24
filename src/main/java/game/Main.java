package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class Main extends Application {


    // variable
    static int speed = 5;
    static int foodcolor = 0;
    static int width = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int cornersize = 25;
    static List<Body> snake = new ArrayList<>();
    static Direction direction = Direction.left;
    static boolean gameOver = false;
    static Random rand = new Random();
    static Canvas c;
    GraphicsContext gc;


    public void start(Stage primaryStage) {
        try {
            newFood();

            VBox root = new VBox();
            c = new Canvas(width * cornersize, height * cornersize);
            gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

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
            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // food
    public static void newFood() {
        start: while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for (Body c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);
            speed++;
            break;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}