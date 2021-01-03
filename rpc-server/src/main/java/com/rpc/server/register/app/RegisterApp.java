package com.rpc.server.register.app;


import com.rpc.server.register.server.RegisterServer;


import java.net.InetSocketAddress;

public class RegisterApp {

    public static void main(String[] args) {
        //启动注册中心
        RegisterServer registerServer = new RegisterServer();
        //监听8089端口
        registerServer.start(new InetSocketAddress("127.0.0.1", 8089));
    }
}
