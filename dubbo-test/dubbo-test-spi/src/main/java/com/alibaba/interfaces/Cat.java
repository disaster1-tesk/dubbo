package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;

public class Cat implements Animal{

    @Override
    public void echo(String msg) {
        System.out.println("msg = " + " cat is " + msg);
    }

    @Override
    public Animal makeAnimal(URL url) {
        return new Cat();
    }
}
