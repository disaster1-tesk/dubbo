package com.alibaba.extend.listener;

import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.ExporterListener;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 当有服务暴露时，触发该事件
 */
public class ProviderExportListener implements ExporterListener {

    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        System.out.println("ProviderExportListener -- "+exporter.getInvoker().getUrl());
    }

    @Override
    public void unexported(Exporter<?> exporter) {

    }
}
