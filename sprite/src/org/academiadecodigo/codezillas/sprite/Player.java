package org.academiadecodigo.codezillas.sprite;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Player implements KeyboardHandler {
    private Rectangle hitbox;
    private Picture player;
    private Keyboard keyboard;
    private boolean right;
    private static final int HORIZONTAL_SPEED = 17;
    private int currentIndex;
    private int idleIndex;
    private boolean dead;

    public boolean playerIdle = true;

    public static final String[] PLAYER_IDLE = {
            "resources/idle/Idle (1).png",
            "resources/idle/Idle (2).png",
            "resources/idle/Idle (3).png",
            "resources/idle/Idle (4).png",
            "resources/idle/Idle (5).png",
            "resources/idle/Idle (6).png",
            "resources/idle/Idle (7).png",
            "resources/idle/Idle (8).png",
            "resources/idle/Idle (9).png",
            "resources/idle/Idle (10).png"
    };

    public static final String[] PLAYER_RUN = {
            "resources/Run (1).png",
            "resources/Run (2).png",
            "resources/Run (3).png",
            "resources/Run (4).png",
            "resources/Run (5).png",
            "resources/Run (6).png",
            "resources/Run (7).png",
            "resources/Run (8).png"
    };

    public static final String[] PLAYER_DEAD = {
            "resources/dead/Dead (1).png",
            "resources/dead/Dead (2).png",
            "resources/dead/Dead (3).png",
            "resources/dead/Dead (4).png",
            "resources/dead/Dead (5).png",
            "resources/dead/Dead (6).png",
            "resources/dead/Dead (7).png",
            "resources/dead/Dead (8).png",
            "resources/dead/Dead (9).png",
            "resources/dead/Dead (10).png"
    };

    public Player(){
        keyboard = new Keyboard(this);
        player = new Picture(0,0, "resources/idle1.png");
        player.draw();
        init();
    }

    public boolean isPlayerIdle() {
        return playerIdle;
    }

    public boolean isDead() {
        return dead;
    }

    void runningAnimation(){
        if (currentIndex == PLAYER_RUN.length){currentIndex = 0;}
        player.load(PLAYER_RUN[currentIndex]);
        moveRight();
        currentIndex++;
    }
    void idleAnimation(){
        if (idleIndex == PLAYER_IDLE.length){idleIndex = 0;}
        player.load(PLAYER_IDLE[idleIndex]);
        idleIndex++;
    }

    void dyingAnimation(){
        if (dead){
            for (String s : PLAYER_DEAD){
                try { Thread.sleep(20); }catch (InterruptedException ex){}
                player.load(s);
            }
            dead = false;
        }
    }

    void init(){
        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent rightReleased = new KeyboardEvent();
        rightReleased.setKey(KeyboardEvent.KEY_RIGHT);
        rightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(right);
        keyboard.addEventListener(rightReleased);
        keyboard.addEventListener(space);
    }

    void moveRight(){
        player.translate(HORIZONTAL_SPEED,0);
    }

    public boolean isRight() {
        return right;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int key = keyboardEvent.getKey();

        switch (key){
            case KeyboardEvent.KEY_RIGHT:
                playerIdle = false;
                right = true;
                break;
            case KeyboardEvent.KEY_SPACE:
                dead = true;
                playerIdle = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        int key = keyboardEvent.getKey();

        switch (key){
            case KeyboardEvent.KEY_RIGHT:
                right = false;
                playerIdle = true;
                break;
        }
    }
}
