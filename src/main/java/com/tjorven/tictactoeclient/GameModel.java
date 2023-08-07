package com.tjorven.tictactoeclient;

import io.netty.channel.Channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GameModel{

    private int[][] currentGameState = new int[3][3];
    private int currentPlayer;

    private GameController controller;

    private int currentXSize;
    private int currentYSize;
    private int winner = 0;
    private int turn = 0;
    private boolean won = false;

    TicTacToeClient client;
    Channel msgChannel;


    int line;

    public GameModel(int x, int y, GameController controller){
        this.controller = controller;
        init();
        currentXSize = x;
        currentYSize = y;
    }

    private void init(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                currentGameState[i][j] = 0;
            }
        }
        currentPlayer = 1;
    }

    public void nextPlayer(){
        if(winner == 0 && turn < 9) {
            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else if (currentPlayer == 2) {
                currentPlayer = 1;
            } else {
                throw new RuntimeException("Kein Spieler ist momentan am Zug!");
            }
        }else{
            //printGameState();
            controller.showWinner(winner, line);
        }
    }

    public void makeMove(double x, double y){
        //if(!won) {
            int col = (int) x / (currentXSize / 3);
            int row = (int) y / (currentYSize / 3);
            msgChannel = client.getMsgChannel();
            msgChannel.writeAndFlush("clickedOn," + row + "," + col);
            /*if (currentGameState[row][col] == 0) {
                //System.out.println("Player: " + currentPlayer + " col: " + col + " row: " + row);
                currentGameState[row][col] = currentPlayer;
                //printGameState();
                controller.showClickedField(currentPlayer, col, row);
                checkWin();
                turn++;
                nextPlayer();
            }*/
        //}
    }

    private void checkWin(){
        // Überprüfe Zeilen und Spalten
        for (int i = 0; i < 3; i++) {
            if (currentGameState[i][0] == currentGameState[i][1] && currentGameState[i][1] == currentGameState[i][2] && currentGameState[i][0] != 0) {
                winner = currentGameState[i][0]; // Zeile i gewinnt
                line = i;
                won = true;
            }
            if (currentGameState[0][i] == currentGameState[1][i] && currentGameState[1][i] == currentGameState[2][i] && currentGameState[0][i] != 0) {
                winner = currentGameState[0][i]; // Spalte i gewinnt
                line = i + 3;
                won = true;
            }
        }

        // Überprüfe Diagonalen
        if (currentGameState[0][0] == currentGameState[1][1] && currentGameState[1][1] == currentGameState[2][2] && currentGameState[0][0] != 0) {
            winner = currentGameState[0][0]; // Hauptdiagonale gewinnt
            line = 6;
            won = true;
        }
        if (currentGameState[0][2] == currentGameState[1][1] && currentGameState[1][1] == currentGameState[2][0] && currentGameState[1][1] != 0) {
            winner = currentGameState[0][2]; // Nebendiagonale gewinnt
            line = 7;
            won = true;
        }
    }

    public void interpret(String msg){
        String[] msgParts = msg.split(",");

        switch (msgParts[0]){
            case "draw":
                controller.showClickedField(Integer.parseInt(msgParts[4]),Integer.parseInt(msgParts[2]),Integer.parseInt(msgParts[1]));
                if(msgParts[3].equals("true")){
                    controller.showWinner(Integer.parseInt(msgParts[5]), Integer.parseInt(msgParts[6]));
                }
                break;
        }
    }

    public void setClient(TicTacToeClient client) {
        this.client = client;
    }

    private void printGameState(){
        for (int i = 0; i < 3; i++){
            System.out.println(currentGameState[i][0] + "," + currentGameState[i][1] + "," + currentGameState[i][2]);
        }
    }

}
