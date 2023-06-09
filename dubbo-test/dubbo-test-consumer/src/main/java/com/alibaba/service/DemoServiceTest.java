package com.alibaba.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceTest {
    @Reference
    private DemoService demoService;

    public String echo(String msg){
        return demoService.echo(msg);
    }
}
