package com.alibaba.interfaces;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("apple")
public interface Fruit {
    @Adaptive("fruitName")
    void printName(URL url);
}
