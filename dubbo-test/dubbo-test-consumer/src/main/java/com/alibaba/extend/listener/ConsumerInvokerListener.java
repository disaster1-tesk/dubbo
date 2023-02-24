package com.alibaba.extend.listener;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.InvokerListener;
import com.alibaba.dubbo.rpc.RpcException;

public class ConsumerInvokerListener implements InvokerListener {
    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        System.out.println(invoker.getUrl());
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
    }
}
