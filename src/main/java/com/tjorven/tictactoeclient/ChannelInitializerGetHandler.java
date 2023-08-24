package com.tjorven.tictactoeclient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.channels.SocketChannel;

public class ChannelInitializerGetHandler extends ChannelInitializer<io.netty.channel.socket.SocketChannel> {
    TicTacToeClientHandler handler = new TicTacToeClientHandler();
    @Override
    protected void initChannel(io.netty.channel.socket.SocketChannel socketChannel) throws Exception {
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
