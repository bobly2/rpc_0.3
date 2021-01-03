package com.rpc.server.register.server;

import com.rpc.server.comsumer.nettyclient.NettyClientHandler;
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
public class ReigsterNettyInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast("decoder",
                new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
        socketChannel.pipeline().addLast("encoder", new ObjectEncoder());
        socketChannel.pipeline().addLast(new RegisterServerHandler());

        //readerIdleTimeSeconds: 读超时.
        //writerIdleTimeSeconds: 写超时
        //allIdleTimeSeconds: 读/写超时.  这三个参数默认的时间单位是秒,如果五秒内ChannelRead()方法未被调用则触发一次userEventTrigger()方法
        socketChannel.pipeline().addLast(new IdleStateHandler(5, 5, 5, TimeUnit.SECONDS));

    }
}
