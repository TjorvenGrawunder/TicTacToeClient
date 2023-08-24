package com.tjorven.tictactoeclient;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    public Label userField;
    public TextField userNameField;
    public TextField ipAdressField;

    TicTacToeApp application;
    TicTacToeClient client;
    public void connectToServer(MouseEvent mouseEvent) {
        client = new TicTacToeClient(ipAdressField.getText(), 8080);
        new Thread(client).start();
        synchronized (Runtime.getRuntime()){
            while(!client.getConnected()){
                try {
                    Runtime.getRuntime().wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(client.getSuccess()){
            GameController gameController = application.switchToGame();
            gameController.getModel().setClient(client);
            gameController.setApplication(application);
            client.setHandlerModel(gameController.getModel());
        }else{
            Platform.runLater(() -> {
                Alert winnerAlert = new Alert(Alert.AlertType.ERROR);
                winnerAlert.setTitle("Error");
                winnerAlert.setContentText("Connection nicht möglich!");

                winnerAlert.show();
            });
        }

    }

    public void setApplication(TicTacToeApp application) {
        this.application = application;
    }
}
