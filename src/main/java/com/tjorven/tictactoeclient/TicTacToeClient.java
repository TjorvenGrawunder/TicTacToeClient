package com.tjorven.tictactoeclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.ConnectException;

public class TicTacToeClient implements Runnable{
    private final String HOST;
    private final int PORT;
    private Channel msgChannel;
    private boolean connected = false;
    private boolean success = false;

    private final ChannelInitializerGetHandler initializer = new ChannelInitializerGetHandler();


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
            b.handler(initializer);
            ChannelFuture f = b.connect(HOST, PORT).sync();
            synchronized (Runtime.getRuntime()){
                connected = true;
                success = true;
                Runtime.getRuntime().notifyAll();
            }
            System.out.println("Test");
            msgChannel = f.channel();

            f.channel().closeFuture().sync();
        } catch(Exception e){
            synchronized (Runtime.getRuntime()){
                connected = true;
                success = false;
                Runtime.getRuntime().notifyAll();
            }
        }
        finally{
            workerGroup.shutdownGracefully().sync();
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
            System.out.println("Not Connected!");
        }
    }

    public Channel getMsgChannel() {
        return msgChannel;
    }

    public boolean getConnected(){
        return connected;
    }
    public boolean getSuccess(){
        return success;
    }

    public void setHandlerModel(GameModel model){
        this.initializer.getHandler().setModel(model);
    }
}
