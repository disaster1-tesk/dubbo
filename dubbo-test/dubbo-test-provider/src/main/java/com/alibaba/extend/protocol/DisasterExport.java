package com.alibaba.extend.protocol;

import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;

public class DisasterExport<T> implements Exporter<T> {
    @Override
    public Invoker<T> getInvoker() {
        return null;
    }

    @Override
    public void unexport() {

    }
}
