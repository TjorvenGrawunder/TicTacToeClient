package com.tjorven.tictactoeclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    public Label userField;

    TicTacToeApp application;
    TicTacToeClient client = new TicTacToeClient("localhost", 8080);
    public void connectToServer(MouseEvent mouseEvent) {
        new Thread(client).start();
        GameController gameController = application.switchToGame();
        gameController.getModel().setClient(client);
        client.setModel(gameController.getModel());
    }

    public void setApplication(TicTacToeApp application) {
        this.application = application;
    }
}
