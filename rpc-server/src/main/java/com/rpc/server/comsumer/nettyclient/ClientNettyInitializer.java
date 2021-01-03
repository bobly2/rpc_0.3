package com.rpc.server.comsumer.nettyclient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2020/12/31 10:49
 * @Version: 1.0
 */
public class ClientNettyInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder",
                new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
        socketChannel.pipeline().addLast("encoder", new ObjectEncoder());
        socketChannel.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
        socketChannel.pipeline().addLast(new NettyClientHandler());


    }
}
