package com.tjorven.tictactoeclient;

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

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8080;

    private Socket server;
    private String serverResponse;
    private BufferedReader streamReader;


    int line;

    public GameModel(int x, int y){
        //this.controller = controller;
        //init();
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
        if(!won) {
            int col = (int) x / (currentXSize / 3);
            int row = (int) y / (currentYSize / 3);
            if (currentGameState[row][col] == 0) {
                //System.out.println("Player: " + currentPlayer + " col: " + col + " row: " + row);
                currentGameState[row][col] = currentPlayer;
                //printGameState();
                controller.showClickedField(currentPlayer, col, row);
                checkWin();
                turn++;
                nextPlayer();
            }
        }
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

    public void connect() throws IOException {
        server = new Socket(SERVER_ADDRESS, PORT);
        streamReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private void printGameState(){
        for (int i = 0; i < 3; i++){
            System.out.println(currentGameState[i][0] + "," + currentGameState[i][1] + "," + currentGameState[i][2]);
        }
    }

}
