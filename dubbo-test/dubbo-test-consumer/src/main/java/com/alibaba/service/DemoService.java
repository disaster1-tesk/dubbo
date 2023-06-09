package com.alibaba.service;

public interface DemoService {
    String echo();

    String echo(String msg);

    String setUser(User user);
}
