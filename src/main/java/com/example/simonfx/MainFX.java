package com.example.simonfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Nicholas G
 *
 * Our main class starting our simon game.
 */

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        TilePane root = new TilePane();
        SimonGame gameMain = new SimonGame(400, 400);
        gameMain.gameStart();

        Button topLeftButton = gameMain.getButton(0); // Yellow
        Button topRightButton = gameMain.getButton(1); // Blue
        Button bottomLeftButton = gameMain.getButton(2); // Red
        Button bottomRightButton = gameMain.getButton(3); // Green

        for (int i = 0; i < 4; i++) {
            gameMain.buttonSetStyle(i, gameMain.defaultColors(i));
            gameMain.setSize(i, gameMain);
        }

        gameMain.mouseOn(topLeftButton,0, 0, gameMain);
        gameMain.mouseOff(topLeftButton, 0);

        gameMain.mouseOn(topRightButton, 1, 1, gameMain);
        gameMain.mouseOff(topRightButton, 1);

        gameMain.mouseOn(bottomLeftButton, 2, 2, gameMain);
        gameMain.mouseOff(bottomLeftButton, 2);

        gameMain.mouseOn(bottomRightButton, 3, 3, gameMain);
        gameMain.mouseOff(bottomRightButton, 3);

        Scene scene = new Scene(root, gameMain.getSceneX(), gameMain.getSceneY());
        root.getChildren().addAll(topLeftButton, topRightButton, bottomLeftButton, bottomRightButton);

        stage.setTitle("Java Simon");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}