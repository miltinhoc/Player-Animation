package org.academiadecodigo.codezillas.sprite;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    Player player;

    Game() {
        Rectangle rectangle = new Rectangle(10,10,1000,600);
        rectangle.setColor(Color.WHITE);
        rectangle.fill();
    }


    void start() {
        player = new Player();
        player.init();

        while (true)
        {
            try { Thread.sleep(15); }catch (InterruptedException ex){ }

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

            if (player.isJump()){
                player.jumpAnimation();
            }


        }
    }
}
