package com.tjorven.tictactoeclient;

import io.netty.channel.Channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GameModel{

    private int yourPlayer;

    private GameController controller;

    private int currentXSize;
    private int currentYSize;

    TicTacToeClient client;
    Channel msgChannel;

    public GameModel(int x, int y, GameController controller){
        this.controller = controller;
        currentXSize = x;
        currentYSize = y;
    }



    public void makeMove(double x, double y){
        int col = (int) x / (currentXSize / 3);
        int row = (int) y / (currentYSize / 3);

        msgChannel = client.getMsgChannel();
        msgChannel.writeAndFlush("clickedOn," + row + "," + col + "," + yourPlayer);
    }

    public void restart(){
        msgChannel = client.getMsgChannel();
        msgChannel.writeAndFlush("restart");
    }

    public void interpret(String msg){
        String[] msgParts = msg.split(",");

        switch (msgParts[0]){
            case "draw":
                controller.showClickedField(Integer.parseInt(msgParts[4]),Integer.parseInt(msgParts[2]),Integer.parseInt(msgParts[1]), true);
                if(msgParts[3].equals("true")){
                    controller.showWinner(Integer.parseInt(msgParts[5]), Integer.parseInt(msgParts[6]));
                }
                break;
            case "setPlayer":
                yourPlayer = Integer.parseInt(msgParts[1]);
                break;
            case "restart":
                controller.resetField();
                break;
            default:
                break;
        }
    }


    public void setClient(TicTacToeClient client) {
        this.client = client;
    }


}
