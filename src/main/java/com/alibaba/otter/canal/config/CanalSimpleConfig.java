package com.alibaba.otter.canal.config;

public interface CanalSimpleConfig {

    String getHost();

    Integer getPort();

    String getDestination();

    String getUsername();

    String getPassword();

    int getSoTimeout();

    int getIdleTimeout();

}
