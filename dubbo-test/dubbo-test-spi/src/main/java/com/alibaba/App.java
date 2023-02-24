package com.alibaba;


import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.interfaces.Fruit;



public class App {
    public static void main(String[] args) {
        Protocol IProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("IProtocol");
        System.out.println(IProtocol.getDefaultPort());
        ExtensionLoader<Fruit> extensionLoader = ExtensionLoader.getExtensionLoader(Fruit.class);
        Fruit defaultExtension = extensionLoader.getDefaultExtension();
//        URL url = URL.valueOf("dubbo://localhost:20880?fruitName=apple");
        URL url = URL.valueOf("dubbo://localhost:20880?fruitName=banana&animalName=dog");
        Fruit adaptiveExtension = extensionLoader.getAdaptiveExtension();
//        URL url1 = url.addParameter("activate", "banana");
//        List<Fruit> activateExtension = extensionLoader.getActivateExtension(url1, "activate", "2");
//        activateExtension.forEach(fruit -> {
//            fruit.printName(url);
//        });
        adaptiveExtension.printName(url);

    }
}
