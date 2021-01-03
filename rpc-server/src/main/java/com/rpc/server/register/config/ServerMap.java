package com.rpc.server.register.config;

import com.rpc.server.rpcrequest.ServerDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//服务列表
public class ServerMap {
    private static final Map<String, ServerDto> ServerMAP = new ConcurrentHashMap<String, ServerDto>();

    public static void put(String methodName, ServerDto serverDto) {
        ServerMAP.put(methodName, serverDto);
    }

    public static Object get(String methodName) {
        return ServerMAP.get(methodName);
    }

}
