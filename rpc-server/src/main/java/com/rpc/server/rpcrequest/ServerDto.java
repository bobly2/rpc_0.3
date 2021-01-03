package com.rpc.server.rpcrequest;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


//注册服务所需的信息
@Data
public class ServerDto implements Serializable {
    private String methodName;
    private Long timeStamp;
    private String ip;
    private String port;
    private List<String> Params;
//    private List<String> ParamsType;
}
