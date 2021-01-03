package com.rpc.server.provider.nettyserver;

import com.rpc.server.comsumer.nettyclient.ClientNettyInitializer;
import com.rpc.server.register.config.ServerMap;
import com.rpc.server.rpcrequest.ServerDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2020/12/31 10:50
 * @Version: 1.0
 */
@Slf4j
public class RegisterClient {

    public void run(ServerDto serverDto) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new ServerNettyInitializer());

        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8089).sync();
            log.info("链接注册中心成功....");
            //发送 数据体
            future.channel().writeAndFlush(serverDto);
            System.out.println("发送方法成功");
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
