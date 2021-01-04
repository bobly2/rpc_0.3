package com.example.demo.server.handler;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/1/4 16:52
 * @Version: 1.0
 */


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InboundHandler1 extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandler1.channelRead: ctx :" + ctx);
        // 通知执行下一个InboundHandler
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("InboundHandler1.channelReadComplete");
        ctx.flush();
    }
}
