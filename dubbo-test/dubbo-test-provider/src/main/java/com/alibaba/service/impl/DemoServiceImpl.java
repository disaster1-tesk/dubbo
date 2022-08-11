package com.alibaba.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    public String echo() {
        return "hello world";
    }
}
