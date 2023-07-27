package com.tjorven.tictactoeclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TicTacToeClient {
    private String host;
    private int port;

    public TicTacToeClient(String host, int port){
        this.host = host;
        this.port = port;
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
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TicTacToeClientHandler());
                }
            });

            ChannelFuture f = b.connect(host, port).sync();

            f.channel().closeFuture().sync();
        } finally{
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args){
        try {
            new TicTacToeClient("87.161.26.237",8080).start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
