package com.example.simonfx;

import javafx.concurrent.Task;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author Nicholas G
 *
 * The brains behind our simon game.
 * Controls all the timed events and "keys" associated with
 * what the user enters and what random key is generated.
 */

public class SimonGame extends ButtonAttributes {
    private final double sceneX;
    private final double sceneY;
    private ArrayList<Integer> userKey = new ArrayList<>();
    private ArrayList<Integer> finalKey = new ArrayList<>();
    private int roundLength = 0;

    public SimonGame(int sceneX, int sceneY){
        this.sceneX = sceneX;
        this.sceneY = sceneY;
    }

    /**
     * Starts the process of the game
     * Defaults the length of the game to 1 iteration of Simon.
     */
    public void gameStart(){
        roundLength = 1; // Starts the current length at 1 for 1 color flashes.
        createButtonsOfArrLength();
        setRandomSelection();
        flashOrder();
    }

    /**
     * Acknowledges a level has been beaten, adding one more length
     * to the finalKey upon the next iteration of Simon.
     */
    private void doTurn(){
        roundLength++;
        setRandomSelection();
        System.out.println("Turn Done");
        flashOrder();
    }

    /**
     * Gets series of random numbers from 0-3 based off of the length of current turns
     */
    private void setRandomSelection(){
        keyReset(this.userKey);
        keyReset(this.finalKey);
        for (int i = 0; i < roundLength; i++) {
            finalKey.add(i, randomSelector());
        }
//        System.out.println("Final Key Is " + finalKey);
    }

    /**
     * Checks if the key created in correlation to the players button presses is equal
     * to the key created randomly. If they are the game goes on, otherwise
     * the game resets.
     * @param userKey the key created by the player
     * @param randomKey the key randomly created
     */
    private void keyCheck(ArrayList<Integer> userKey, ArrayList<Integer> randomKey){
        int matchAmount = 0;
        for (int i = 0; i < roundLength; i++) {
            if (Objects.equals(userKey.get(i), randomKey.get(i))){
                matchAmount++;
                if (matchAmount == roundLength){
                    doTurn();
                    break;
                }
            }
            else {
                System.out.println("You Have Lost");
                System.exit(0); // exiting the game upon losing.
            }
        }
    }

    /**
     * Adds the number represented by the Simon box to the ArrayList.
     * Once the userKey is full, it will check if the number(s) associated with the finalKey match
     * @param number the number that is represented by the square.
     */
    public void addToUserKey(int number){
        userKey.add(number);
        if (userKey.size() == roundLength){
//            System.out.println("User Key is " + userKey);
            keyCheck(userKey, finalKey);
        }
    }

    /**
     * Flashes the key for the simon game in order of the array.
     */
    private void flashOrder(){
        for (int i = 0; i < roundLength; i++) {
            int correctBox = finalKey.get(i);
            delay((i+1) * 1000, () -> buttonSetStyle(correctBox, brightColors(correctBox)));
            delay((i+1) * 1000 + 500, () -> buttonSetStyle(correctBox, defaultColors(correctBox)));
        }

    }

    /**
     * Pauses the thread and continues it on another one until the duration is complete
     * then continues the process back on the original thread.
     * @param millis the amount of delay you would like to add in-between flashes.
     */
    private static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    /**
     * Clears any of our ArrayLists
     * @param key a desired ArrayList
     */
    private void keyReset(ArrayList<Integer> key){
        key.clear();
    }

    private int randomSelector(){
        return new Random().nextInt(0,4);
    }

    public int getTurns(){
        return roundLength;
    }

    public double getSceneX(){
        return sceneX;
    }

    public double getSceneY(){
        return sceneY;
    }

}
