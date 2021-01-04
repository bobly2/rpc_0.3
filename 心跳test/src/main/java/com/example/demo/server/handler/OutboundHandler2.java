package com.example.demo.server.handler;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/1/4 16:53
 * @Version: 1.0
 */


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OutboundHandler2 extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHandler2.write");
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }
}