package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

public class Banana implements Fruit{
    private Animal animal;

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void printName(URL url) {
        Animal animal = this.animal.makeAnimal(url);
        animal.echo("test");
        System.out.println("I'm banana");
    }
}
