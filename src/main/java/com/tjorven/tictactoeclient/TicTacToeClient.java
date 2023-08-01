package com.tjorven.tictactoeclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TicTacToeClient implements Runnable{
    private final String HOST;
    private final int PORT;
    private Channel msgChannel;

    public TicTacToeClient(String host, int port){
        this.HOST = host;
        this.PORT = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder(),new TicTacToeClientHandler());
                }
            });

            ChannelFuture f = b.connect(HOST, PORT).sync();
            msgChannel = f.channel();
            msgChannel.writeAndFlush("Test Message");

            f.channel().closeFuture().sync();
        } finally{
            workerGroup.shutdownGracefully().sync();
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Channel getMsgChannel() {
        return msgChannel;
    }
}
