package com.alibaba.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String echo() {
        return "hello world";
    }

    @Override
    public String echo(String msg) {
        Object index = RpcContext.getContext().getAttachment("index");// 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
        System.out.println("index = " + index);
        return msg;
    }

    @Override
    public String setUser(User user) {
        String isMock = RpcContext.getContext().getAttachment("isMock");
        Object isMock2 = RpcContext.getServerContext().get("isMock");
        String isMock1 = RpcContext.getServerContext().getAttachment("isMock");
        RpcContext.getContext().setAttachment("isMock","true");
        if (!StringUtils.isEmpty(isMock) && isMock.equals("true")) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return user.toString();
    }
}
