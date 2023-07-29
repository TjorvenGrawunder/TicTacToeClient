package com.tjorven.tictactoeclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LoginController {
    @FXML
    public Label userField;
    TicTacToeClient client = new TicTacToeClient("localhost", 8080);
    public void connectToServer(MouseEvent mouseEvent) {
        try {
            client.start(userField.getText());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
