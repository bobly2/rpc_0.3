package com.example.demo.server.handler;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/1/4 16:52
 * @Version: 1.0
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InboundHandler2 extends ChannelInboundHandlerAdapter {


    @Override
    // 读取Client发送的信息，并打印出来
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandler2.channelRead: ctx :" + ctx);
        String resultStr = msg.toString();
        System.out.println("Client said:" + resultStr);


        ctx.write(resultStr);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("InboundHandler2.channelReadComplete");
        ctx.flush();
    }

}
