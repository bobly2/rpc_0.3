package com.example.demo.server;

import com.example.demo.server.handler.InboundHandler1;
import com.example.demo.server.handler.InboundHandler2;
import com.example.demo.server.handler.OutboundHandler1;
import com.example.demo.server.handler.OutboundHandler2;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class HeartBeatInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        //意思就是客户端2秒没有读/写，这个超时时间就会被触发。超时事件触发就需要我们来处理了，这就是上的HeartBeatInitializer中最后一行的HeartBeatHandler所做的事情
        pipeline.addLast(new IdleStateHandler(2, 2, 2, TimeUnit.SECONDS));
        pipeline.addLast(new HeartBeatHandler());
//        pipeline.addLast(new HeartBeatClientHandler2());

        // 注册两个OutboundHandler，执行顺序为注册顺序的逆序，所以应该是OutboundHandler2 OutboundHandler1
        pipeline.addLast(new OutboundHandler1());
        pipeline.addLast(new OutboundHandler2());
        // 注册两个InboundHandler，执行顺序为注册顺序，所以应该是InboundHandler1 InboundHandler2
        pipeline.addLast(new InboundHandler1());
        pipeline.addLast(new InboundHandler2());
    }

}