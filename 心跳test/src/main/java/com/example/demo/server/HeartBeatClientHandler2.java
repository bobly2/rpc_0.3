//package com.example.demo.server;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
///**
// * @Author: SC19002999
// * @Description:
// * @Date: 2021/1/4 14:56
// * @Version: 1.0
// */
//public class HeartBeatClientHandler2 extends ChannelInboundHandlerAdapter  {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
////        log.info("Channel active......");
//    }
//
//    /**
//     * 客户端发消息会触发
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("服务端HeartBeatClientHandler2 ：" + msg);
//        ctx.writeAndFlush("pong");
//    }
//
//    /**
//     * 发生异常触发
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}