package com.alibaba.service;

public class DemoServiceMock implements DemoService{
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
        //伪装数据
        return "null";
    }
}
