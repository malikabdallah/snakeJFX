package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.Jeu;

public class View {

    public static void tick(GraphicsContext gc) {
        //System.out.println("tick");
        if (Jeu.gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            gc.setFill(Color.GOLD);
            gc.setFont(new Font("", 30));
            gc.fillText("Score "+(Jeu.snake.size()-3),100,280);
            return;
        }

        for (int i = Jeu.snake.size() - 1; i >= 1; i--) {
            Jeu.snake.get(i).x = Jeu.snake.get(i - 1).x;
            Jeu.snake.get(i).y = Jeu.snake.get(i - 1).y;
        }

        switch (Jeu.direction) {
            case up:
                Jeu.snake.get(0).y--;
                if (Jeu.snake.get(0).y < 0) {
                    Jeu.gameOver = true;
                }
                break;
            case down:
                Jeu.snake.get(0).y++;
                if (Jeu.snake.get(0).y > Jeu.height) {
                    Jeu.gameOver = true;
                }
                break;
            case left:
                Jeu.snake.get(0).x--;
                if (Jeu.snake.get(0).x < 0) {
                    Jeu.gameOver = true;
                }
                break;
            case right:
                Jeu.snake.get(0).x++;
                if (Jeu.snake.get(0).x > Jeu.width) {
                    Jeu.gameOver = true;
                }
                break;

        }

        // eat food
        if (Jeu.foodX == Jeu.snake.get(0).x && Jeu.foodY == Jeu.snake.get(0).y) {
            Jeu.snake.add(new Body(-1, -1));
            Jeu.newFood();
        }

        // self destroy
        for (int i = 1; i < Jeu.snake.size(); i++) {
            if (Jeu.snake.get(0).x == Jeu.snake.get(i).x && Jeu.snake.get(0).y == Jeu.snake.get(i).y) {
                Jeu.gameOver = true;
            }
        }

        // fill
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Jeu.width * Jeu.cornersize, Jeu.height * Jeu.cornersize);

        // score
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("", 30));
        gc.fillText("Score "+(Jeu.snake.size()-3),10,30);
        // random foodcolor
        Color cc = Color.WHITE;

        switch (Jeu.foodcolor) {
            case 0:
                cc = Color.RED;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
        gc.setFill(cc);
        gc.fillOval(Jeu.foodX * Jeu.cornersize, Jeu.foodY * Jeu.cornersize,
                Jeu.cornersize, Jeu.cornersize);

        // snake
        for (Body c : Jeu.snake) {
          //  gc.setFill(Color.LIGHTGREEN);
           // gc.fillRect(c.x * Main.cornersize, c.y * Main.cornersize, Main.cornersize - 1, Main.cornersize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * Jeu.cornersize, c.y * Jeu.cornersize, Jeu.cornersize - 2, Jeu.cornersize - 2);

        }

    }
}
