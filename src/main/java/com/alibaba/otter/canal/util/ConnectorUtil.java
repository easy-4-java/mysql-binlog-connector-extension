package com.alibaba.otter.canal.util;

import com.alibaba.otter.canal.client.impl.ClusterCanalConnector;
import com.alibaba.otter.canal.client.impl.ClusterNodeAccessStrategy;
import com.alibaba.otter.canal.client.impl.SimpleCanalConnector;
import com.alibaba.otter.canal.client.impl.SimpleNodeAccessStrategy;
import com.alibaba.otter.canal.common.zookeeper.ZkClientx;
import com.alibaba.otter.canal.config.CanalClusterConfig;
import com.alibaba.otter.canal.config.CanalSimpleConfig;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;

public class ConnectorUtil {

    /**
     * 创建集群模式的 Canal 连接器
     * @param instance 实例配置
     * @return Canal 连接器
     */
    public static ClusterCanalConnector createClusterCanalConnector(CanalClusterConfig instance) {
        if (StringUtils.hasText(instance.getZkServers())) {
            ClusterCanalConnector canalConnector = new ClusterCanalConnector(instance.getUsername(),
                    instance.getPassword(),
                    instance.getDestination(),
                    new ClusterNodeAccessStrategy(instance.getDestination(), ZkClientx.getZkClient(instance.getZkServers())));
            canalConnector.setSoTimeout(instance.getSoTimeout());
            canalConnector.setIdleTimeout(instance.getIdleTimeout());
            canalConnector.setRetryTimes(instance.getRetryTimes());
            canalConnector.setRetryInterval(instance.getRetryInterval());
            return canalConnector;
        }
        ClusterCanalConnector canalConnector = new ClusterCanalConnector(
                instance.getUsername(),
                instance.getPassword(),
                instance.getDestination(),
                new SimpleNodeAccessStrategy(AddressUtils.parseAddresses(instance.getAddresses())));
        canalConnector.setSoTimeout(instance.getSoTimeout());
        canalConnector.setIdleTimeout(instance.getIdleTimeout());
        canalConnector.setRetryTimes(instance.getRetryTimes());
        canalConnector.setRetryInterval(instance.getRetryInterval());
        return canalConnector;
    }

    /**
     * 创建单机模式的 Canal 连接器
     * @param instance 实例配置
     * @return Canal 连接器
     */
    public static SimpleCanalConnector createSimpleCanalConnector(CanalSimpleConfig instance) {
        InetSocketAddress address = new InetSocketAddress(instance.getHost(), instance.getPort());
        SimpleCanalConnector canalConnector = new SimpleCanalConnector(address,
                instance.getUsername(),
                instance.getPassword(),
                instance.getDestination());
        canalConnector.setSoTimeout(instance.getSoTimeout());
        canalConnector.setIdleTimeout(instance.getIdleTimeout());
        return canalConnector;
    }

}
