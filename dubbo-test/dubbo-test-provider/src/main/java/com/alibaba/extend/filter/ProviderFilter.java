package com.alibaba.extend.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * 服务提供端的过滤器
 */
public class ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("provider_filter is running before");
        Result invoke = invoker.invoke(invocation);
        System.out.println("provider_filter is running after");
        return invoke;
    }
}
