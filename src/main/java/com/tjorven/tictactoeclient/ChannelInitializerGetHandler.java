package com.tjorven.tictactoeclient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class ChannelInitializerGetHandler extends ChannelInitializer<SocketChannel> {
    TicTacToeClientHandler handler = new TicTacToeClientHandler();
    @Override
    protected void initChannel(SocketChannel socketChannel)  {
        socketChannel.pipeline().addLast(
                new StringDecoder(),
                new StringEncoder(),
                handler
                );
    }

    public TicTacToeClientHandler getHandler() {
        return handler;
    }
}
