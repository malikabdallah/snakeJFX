package game;

import javafx.animation.AnimationTimer;

public class Animation extends AnimationTimer {
    long lastTick = 0;

    public void handle(long now) {
        if (lastTick == 0) {
            lastTick = now;
            View.tick(Main.c.getGraphicsContext2D());
            return;
        }
        //1000000000
        if (now - lastTick > 1000000000 / Main.speed) {
            lastTick = now;
            View.tick(Main.c.getGraphicsContext2D());
        }
    }
}
