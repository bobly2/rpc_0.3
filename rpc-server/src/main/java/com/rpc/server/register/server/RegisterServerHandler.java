package com.rpc.server.register.server;

import com.rpc.server.register.config.ServerMap;
import com.rpc.server.rpcrequest.ServerDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2020/12/31 10:47
 * @Version: 1.0
 */

@Slf4j
public class RegisterServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel active......");
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("注册中心收到消息:" + msg.toString());
        this.getSaveServer(msg);
        //心跳连接，发送ping
        ctx.channel().writeAndFlush("ping");
        ctx.flush();
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    int readIdleTimes = 0;
    //在出现超时事件时会被触发，包括读空闲超时或者写空闲超时；
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;
        System.out.println("触发超时");
        String eventType = null;
        switch (event.state()) {
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes++; // 读空闲的计数加1
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                // 不处理
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                // 不处理
                break;
        }
        System.out.println(ctx.channel().remoteAddress() + "超时事件：" + eventType);
        if (readIdleTimes > 3) {
            System.out.println(" [server]读空闲超过3次，服务端关闭连接");
            ctx.channel().writeAndFlush("you are out");
            ctx.channel().close();
        }
    }

    //用netty接收的数据来实现
    public void getSaveServer(Object object) {
        // 调用服务
        if (object instanceof ServerDto) {
            ServerDto serverDto = (ServerDto) object;
            ServerMap.put(serverDto.getMethodName(), serverDto);
        }
    }



}
