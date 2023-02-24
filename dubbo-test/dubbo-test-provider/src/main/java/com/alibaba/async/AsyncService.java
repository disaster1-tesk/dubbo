package com.alibaba.async;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<String> sayHello(String name);

    String sayHello1(String name);
}
