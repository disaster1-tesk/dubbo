package com.alibaba.dubbo.rpc.cluster;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class ClusterExtensionTest {
    @Test
    public void clusterExtensionTest(){
        Cluster adaptiveExtension = ExtensionLoader.getExtensionLoader(Cluster.class).getAdaptiveExtension();
    }
}
