package com.tjorven.tictactoeclient;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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
    public Rectangle rectangle_0_0;
    public Rectangle rectangle_1_0;
    public Rectangle rectangle_2_0;
    public Rectangle rectangle_0_1;
    public Rectangle rectangle_1_1;
    public Rectangle rectangle_2_1;
    public Rectangle rectangle_0_2;
    public Rectangle rectangle_1_2;
    public Rectangle rectangle_2_2;
    public Line right_left_0;
    public Line right_left_1;
    public Line right_left_2;
    public Line up_down_0;
    public Line up_down_1;
    public Line up_down_2;
    public Line dia_0;
    public Line dia_1;

    @FXML
    public void fieldClicked(javafx.scene.input.MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        model.makeMove(x, y);
    }

    public void showWinner(int winner, int line){
        Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
        winnerAlert.setTitle("Sieger");
        winnerAlert.setContentText("Der Sieger ist Spieler " + winner);
        winnerAlert.show();
        showLine(line);
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
                        case 0 -> rectangle_0_0.setVisible(true);
                        case 1 -> rectangle_0_1.setVisible(true);
                        case 2 -> rectangle_0_2.setVisible(true);
                    }
                    break;
                case 1:
                    switch (col) {
                        case 0 -> rectangle_1_0.setVisible(true);
                        case 1 -> rectangle_1_1.setVisible(true);
                        case 2 -> rectangle_1_2.setVisible(true);
                    }
                    break;
                case 2:
                    switch (col) {
                        case 0 -> rectangle_2_0.setVisible(true);
                        case 1 -> rectangle_2_1.setVisible(true);
                        case 2 -> rectangle_2_2.setVisible(true);
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
}
