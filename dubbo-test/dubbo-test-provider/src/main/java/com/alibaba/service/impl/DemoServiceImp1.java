package com.alibaba.service.impl;

import com.alibaba.service.DemoService;
import com.alibaba.service.User;
import com.alibaba.validation.ValidationParameter;

public class DemoServiceImp1 implements DemoService {
    @Override
    public String echo() {
        return "DemoServiceImp1 is running";
    }

    @Override
    public String echo(String msg) {
        return msg;
    }

    @Override
    public String setUser(User user) {
        return "service1: " + user.toString();
    }
}
