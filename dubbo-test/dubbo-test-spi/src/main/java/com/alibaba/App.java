package com.alibaba;


import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;

public class App {
    public static void main(String[] args) {
        Protocol IProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("IProtocol");
        System.out.println(IProtocol.getDefaultPort());
    }
}
