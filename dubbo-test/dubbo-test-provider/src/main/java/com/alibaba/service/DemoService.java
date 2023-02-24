package com.alibaba.service;

import com.alibaba.validation.ValidationParameter;

public interface DemoService {
    String echo();

    String echo(String msg);

    String setUser(User user);
}
