package com.alibaba.service;

public class DemoServiceStub implements DemoService{
    private final DemoService demoService;

    // 构造函数传入真正的远程代理对象
    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String echo() {
        return null;
    }

    @Override
    public String echo(String msg) {
        return null;
    }

    @Override
    public String setUser(User user) {
        //在这里做一下逻辑操作
        if (user.getAge()<100){
            System.out.println("当前年龄小于100");
        }
        return demoService.setUser(user);
    }
}
