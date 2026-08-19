package com.alibaba.otter.canal.config;

public interface CanalClusterConfig {

    String getAddresses();

    String getZkServers();

    String getDestination();

    String getUsername();

    String getPassword();

    int getSoTimeout();

    int getIdleTimeout();

    int getRetryTimes();

    int getRetryInterval();

}
