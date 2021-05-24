package game;

import javafx.animation.AnimationTimer;
import view.Jeu;

public class Animation extends AnimationTimer {
    long lastTick = 0;

    public void handle(long now) {
        if (lastTick == 0) {
            lastTick = now;
            View.tick(Jeu.canvas.getGraphicsContext2D());
            return;
        }
        //1000000000
        if (now - lastTick > 1000000000 / Jeu.speed) {
            lastTick = now;
            View.tick(Jeu.canvas.getGraphicsContext2D());
        }
    }
}
