package com.alibaba.extend.protocol;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.protocol.AbstractInvoker;

public class DisasterInvoker<T> extends AbstractInvoker<T> {
    public DisasterInvoker(Class<T> type, URL url) {
        super(type, url);
    }

    @Override
    protected Result doInvoke(Invocation invocation) throws Throwable {
        return null;
    }
}
