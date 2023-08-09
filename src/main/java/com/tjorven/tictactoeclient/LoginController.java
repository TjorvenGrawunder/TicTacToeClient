package com.tjorven.tictactoeclient;

import javafx.fxml.FXML;
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
        GameController gameController = application.switchToGame();
        gameController.getModel().setClient(client);
        gameController.setApplication(application);
        client.setModel(gameController.getModel());
    }

    public void setApplication(TicTacToeApp application) {
        this.application = application;
    }
}
