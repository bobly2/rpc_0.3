package com.example.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Random;

public class HeartBeatClient {
    Channel channel;
    Random random;
    int port;

    public HeartBeatClient(int port) {
        this.port = port;
        random = new Random();
    }

    public static void main(String[] args) throws Exception {
        HeartBeatClient client = new HeartBeatClient(8090);
        client.start();
    }

    public void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new HeartBeatClientInitializer());
            connect(bootstrap, port);
            String text = "客户端发送";
            while (channel.isActive()) {
                sendMsg(text);
            }
        } catch (Exception e) {
            // do something
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void connect(Bootstrap bootstrap, int port) throws Exception {
        channel = bootstrap.connect("localhost", 8090).sync().channel();
    }


    public void sendMsg(String text) throws Exception {
        int num = random.nextInt(10);
        Thread.sleep(num * 1000);
        channel.writeAndFlush(text);
    }

}
