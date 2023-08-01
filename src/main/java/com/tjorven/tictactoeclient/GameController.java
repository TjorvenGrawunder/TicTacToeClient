package com.tjorven.tictactoeclient;

import io.netty.channel.Channel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameController {


    private GameModel model = new GameModel(TicTacToeApp.getSTART_X(), TicTacToeApp.getSTART_Y(), this);


    @FXML
    private Circle circle_0_0;
    @FXML
    private Circle circle_1_0;
    @FXML
    private Circle circle_2_0;
    @FXML
    private Circle circle_0_1;
    @FXML
    private Circle circle_1_1;
    @FXML
    private Circle circle_2_1;
    @FXML
    private Circle circle_0_2;
    @FXML
    private Circle circle_1_2;
    @FXML
    private Circle circle_2_2;
    @FXML
    private Text x_0_0;
    @FXML
    private Text x_1_0;
    @FXML
    private Text x_2_0;
    @FXML
    private Text x_0_1;
    @FXML
    private Text x_1_1;
    @FXML
    private Text x_2_1;
    @FXML
    private Text x_0_2;
    @FXML
    private Text x_1_2;
    @FXML
    private Text x_2_2;
    @FXML
    private Line right_left_0;
    @FXML
    private Line right_left_1;
    @FXML
    private Line right_left_2;
    @FXML
    private Line up_down_0;
    @FXML
    private Line up_down_1;
    @FXML
    private Line up_down_2;
    @FXML
    private Line dia_0;
    @FXML
    private Line dia_1;

    TicTacToeClient client;


    @FXML
    public void fieldClicked(javafx.scene.input.MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        model.makeMove(x, y);
        Channel msgChannel = client.getMsgChannel();
        msgChannel.writeAndFlush("clicked on: " + x + ", " + y);
    }

    public void showWinner(int winner, int line){
        Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
        winnerAlert.setTitle("Sieger");
        if(winner == 0){
            winnerAlert.setContentText("Es gibt keinen Sieger!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }else{
            winnerAlert.setContentText("Der Sieger ist Spieler " + winner);
            showLine(line);
        }

        winnerAlert.show();

        //System.exit(0);
    }

    public void showClickedField(int player, int row, int col){
        if(player == 1){
            switch (row){
                case 0:
                    switch (col) {
                        case 0 -> circle_0_0.setVisible(true);
                        case 1 -> circle_0_1.setVisible(true);
                        case 2 -> circle_0_2.setVisible(true);
                    }
                    break;
                case 1:
                    switch (col) {
                        case 0 -> circle_1_0.setVisible(true);
                        case 1 -> circle_1_1.setVisible(true);
                        case 2 -> circle_1_2.setVisible(true);
                    }
                    break;
                case 2:
                    switch (col) {
                        case 0 -> circle_2_0.setVisible(true);
                        case 1 -> circle_2_1.setVisible(true);
                        case 2 -> circle_2_2.setVisible(true);
                    }
                    break;
            }
        }else if(player == 2){
            switch (row){
                case 0:
                    switch (col) {
                        case 0 -> x_0_0.setVisible(true);
                        case 1 -> x_0_1.setVisible(true);
                        case 2 -> x_0_2.setVisible(true);
                    }
                    break;
                case 1:
                    switch (col) {
                        case 0 -> x_1_0.setVisible(true);
                        case 1 -> x_1_1.setVisible(true);
                        case 2 -> x_1_2.setVisible(true);
                    }
                    break;
                case 2:
                    switch (col) {
                        case 0 -> x_2_0.setVisible(true);
                        case 1 -> x_2_1.setVisible(true);
                        case 2 -> x_2_2.setVisible(true);
                    }
                    break;
            }
        }
    }

    private void showLine(int line){
        switch (line){
            case 0:
                right_left_0.setVisible(true);
                break;
            case 1:
                right_left_1.setVisible(true);
                break;
            case 2:
                right_left_2.setVisible(true);
                break;
            case 3:
                up_down_0.setVisible(true);
                break;
            case 4:
                up_down_1.setVisible(true);
                break;
            case 5:
                up_down_2.setVisible(true);
                break;
            case 6:
                dia_0.setVisible(true);
                break;
            case 7:
                dia_1.setVisible(true);
                break;
        }
    }

    public void setModel(GameModel model) {
        this.model = model;
    }

    public void setClient(TicTacToeClient client) {
        this.client = client;
    }
}
