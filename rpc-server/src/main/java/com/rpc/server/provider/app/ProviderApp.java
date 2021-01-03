package com.rpc.server.provider.app;


import com.rpc.server.comsumer.nettyclient.NettyClient;
import com.rpc.server.provider.nettyserver.NettyServer;
import com.rpc.server.provider.nettyserver.RegisterClient;
import com.rpc.server.provider.service.Calculator;
import com.rpc.server.provider.service.CalculatorImpl;
import com.rpc.server.register.config.ServerMap;
import com.rpc.server.rpcrequest.CalculateRpcRequest;
import com.rpc.server.rpcrequest.SendRpcRequest;
import com.rpc.server.rpcrequest.ServerDto;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 提供服务
 * @author:SC19002999
 * @create: 2020/4/23
 **/
@Slf4j
public class ProviderApp {


    private Calculator calculator = new CalculatorImpl();

    public static void main(String[] args) throws IOException {
//        new ProviderApp().runNetty();
        new ProviderApp().runRigster();
    }

    //通过netty通信
    private void runNetty() throws IOException {
        //启动服务端
        NettyServer nettyServer = new NettyServer();
        //监听9090端口
        nettyServer.start(new InetSocketAddress("127.0.0.1", 8090));
    }

    //向注册中心注册服务
    private void runRigster() throws IOException {
        RegisterClient registerClient = new RegisterClient();
        registerClient.run(this.setDto());
    }

    private ServerDto setDto() throws UnknownHostException {
        ServerDto serverDto = new ServerDto();
        serverDto.setIp(InetAddress.getLocalHost().getHostAddress());
        serverDto.setPort("8090");
        serverDto.setMethodName("add");
        List<String> params = new ArrayList<>();
        params.add("Integer");
        params.add("Integer");
        serverDto.setParams(params);
        serverDto.setTimeStamp(System.currentTimeMillis());
        return serverDto;
    }
}
