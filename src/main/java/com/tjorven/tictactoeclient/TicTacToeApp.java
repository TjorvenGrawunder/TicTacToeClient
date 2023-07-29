package com.tjorven.tictactoeclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApp extends Application {
    private static final int START_X = 400;
    private static final int START_Y = 400;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("gamepanel.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("connectWindow.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gamepanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), START_X, START_Y);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static int getSTART_X() {
        return START_X;
    }

    public static int getSTART_Y() {
        return START_Y;
    }

    public static void main(String[] args) {
        launch();
    }

}