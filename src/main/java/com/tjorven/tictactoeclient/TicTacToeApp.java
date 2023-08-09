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

    Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        //FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("gamepanel.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApp.class.getResource("connectWindow.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gamepanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), START_X, START_Y);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.setResizable(false);
        LoginController controller = fxmlLoader.getController();
        controller.setApplication(this);
    }

    public GameController switchToGame(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gamepanel.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, START_X, START_Y + 40);
        //scene.getStylesheets().add(getClass().getResource("gameWindow.css").toExternalForm());
        mainStage.setScene(scene);

        return fxmlLoader.getController();
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