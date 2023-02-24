package com.alibaba.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.service.DemoService;
import com.alibaba.service.User;
import com.alibaba.validation.ValidationParameter;

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
        return user.toString();
    }
}
