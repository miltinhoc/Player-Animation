package org.academiadecodigo.codezillas.sprite;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    int playerImage;
    Player player;

    public Game() {
        Rectangle rectangle = new Rectangle(10,10,1000,600);
        rectangle.setColor(Color.WHITE);
        rectangle.fill();

    }


    void start() {
        player = new Player();
        while (true)
        {
            try { Thread.sleep(25); }catch (InterruptedException ex){ }

            // Running animation
            if (player.isRight()) {
                player.runningAnimation();
            }

            // Idle animation
            if (player.isPlayerIdle()){
                player.idleAnimation();
            }

            //Dying animation
            if (player.isDead()){
                player.dyingAnimation();
            }


        }
    }
}
