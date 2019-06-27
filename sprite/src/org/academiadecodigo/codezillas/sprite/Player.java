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
    private static final int VERTICAL_SPEED = 20;

    private int currentIndex;
    private int idleIndex;
    private int jumpIndex;
    private boolean dead;
    private boolean jump;
    private boolean playerIdle = true;

    Player(){
        keyboard = new Keyboard(this);
        player = new Picture(0,0, PlayerUtils.PLAYER_IDLE[0]);
        player.draw();
    }

    public boolean isJump() {
        return jump;
    }

    boolean isPlayerIdle() {
        return playerIdle;
    }

    boolean isDead() {
        return dead;
    }

    void jumpAnimation(){


        for (int i=0; i < PlayerUtils.PLAYER_JUMP.length;i++){

            try { Thread.sleep(15); }catch (InterruptedException ex){ }

            if (i >= PlayerUtils.PLAYER_JUMP.length/2){
                jumpRightDown();
                player.load(PlayerUtils.PLAYER_JUMP[i]);
                continue;
            }

            jumpRightUp();
            player.load(PlayerUtils.PLAYER_JUMP[i]);
        }

        jump = false;
        playerIdle = true;
    }

    void runningAnimation(){

        if (currentIndex == PlayerUtils.PLAYER_RUN.length){currentIndex = 0;}
        player.load(PlayerUtils.PLAYER_RUN[currentIndex]);
        moveRight();
        currentIndex++;
    }
    void idleAnimation(){

        if (idleIndex == PlayerUtils.PLAYER_IDLE.length){idleIndex = 0;}
        player.load(PlayerUtils.PLAYER_IDLE[idleIndex]);
        idleIndex++;
    }

    void dyingAnimation(){

        if (dead){
            for (String s : PlayerUtils.PLAYER_DEAD){
                try { Thread.sleep(25); }catch (InterruptedException ex){}
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

    private void moveRight(){
        player.translate(HORIZONTAL_SPEED,0);
    }

    private void moveLeft(){
        player.translate(-HORIZONTAL_SPEED,0);
    }

    private void jumpRightUp(){
        player.translate(HORIZONTAL_SPEED, -VERTICAL_SPEED);
    }

    private void jumpRightDown(){
        player.translate(HORIZONTAL_SPEED, VERTICAL_SPEED);
    }

    boolean isRight() {
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
                //dead = true;
                jump = true;
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
