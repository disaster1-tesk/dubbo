package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

@Activate(group = "2",value = "apple")
public class Apple implements Fruit{
    @Override
    public void printName(URL url) {
        System.out.println("I'm apple");
    }
}
