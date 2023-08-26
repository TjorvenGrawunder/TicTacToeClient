package com.tjorven.tictactoeclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TicTacToeClientHandler extends ChannelInboundHandlerAdapter {

    GameModel model;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        model.interpret((String) msg);
        System.out.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }



    public void setModel(GameModel model) {
        this.model = model;
    }
}
