package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Protocol;

public class Apple implements Fruit{
    private Animal animal;

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void printName(URL url) {
        animal.echo("test");
        System.out.println("I'm apple");
    }
}
