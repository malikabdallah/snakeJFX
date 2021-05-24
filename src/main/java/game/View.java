package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class View {

    public static void tick(GraphicsContext gc) {
        //System.out.println("tick");
        if (Main.gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            gc.setFill(Color.GOLD);
            gc.setFont(new Font("", 30));
            gc.fillText("Score "+(Main.snake.size()-3),100,280);
            return;
        }

        for (int i = Main.snake.size() - 1; i >= 1; i--) {
            Main.snake.get(i).x = Main.snake.get(i - 1).x;
            Main.snake.get(i).y = Main.snake.get(i - 1).y;
        }

        switch (Main.direction) {
            case up:
                Main.snake.get(0).y--;
                if (Main.snake.get(0).y < 0) {
                    Main.gameOver = true;
                }
                break;
            case down:
                Main.snake.get(0).y++;
                if (Main.snake.get(0).y > Main.height) {
                    Main.gameOver = true;
                }
                break;
            case left:
                Main.snake.get(0).x--;
                if (Main.snake.get(0).x < 0) {
                    Main.gameOver = true;
                }
                break;
            case right:
                Main.snake.get(0).x++;
                if (Main.snake.get(0).x > Main.width) {
                    Main.gameOver = true;
                }
                break;

        }

        // eat food
        if (Main.foodX == Main.snake.get(0).x && Main.foodY == Main.snake.get(0).y) {
            Main.snake.add(new Body(-1, -1));
            Main.newFood();
        }

        // self destroy
        for (int i = 1; i < Main.snake.size(); i++) {
            if (Main.snake.get(0).x == Main.snake.get(i).x && Main.snake.get(0).y == Main.snake.get(i).y) {
                Main.gameOver = true;
            }
        }

        // fill
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Main.width * Main.cornersize, Main.height * Main.cornersize);

        // score
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("", 30));
        gc.fillText("Score "+(Main.snake.size()-3),10,30);
        // random foodcolor
        Color cc = Color.WHITE;

        switch (Main.foodcolor) {
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
        gc.fillOval(Main.foodX * Main.cornersize, Main.foodY * Main.cornersize, Main.cornersize, Main.cornersize);

        // snake
        for (Body c : Main.snake) {
          //  gc.setFill(Color.LIGHTGREEN);
           // gc.fillRect(c.x * Main.cornersize, c.y * Main.cornersize, Main.cornersize - 1, Main.cornersize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * Main.cornersize, c.y * Main.cornersize, Main.cornersize - 2, Main.cornersize - 2);

        }

    }
}
