package com.example.demo.server.handler;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/1/4 16:53
 * @Version: 1.0
 */


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutboundHandler1 extends ChannelOutboundHandlerAdapter {


    @Override
    // 向client发送消息
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHandler1.write");
        String response = "I am ok!";
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response);
//        ctx.write(encoded);
        ctx.write(response);
        ctx.flush();
    }


}
