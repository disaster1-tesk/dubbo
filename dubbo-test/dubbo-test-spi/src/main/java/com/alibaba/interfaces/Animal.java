package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("cat")
public interface Animal {
    void echo(String msg);

    @Adaptive("animalName")
    Animal makeAnimal(URL url);
}
