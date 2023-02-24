package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;

public class Dog implements Animal {

    @Override
    public void echo(String msg) {
        System.out.println("msg = " + " dog is " + msg);
    }

    @Override
    public Animal makeAnimal(URL url) {
        return new Dog();
    }
}
