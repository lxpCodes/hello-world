package com.lxp.myself;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName StarterServiceProperties
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/8 15:57
 **/
@ConfigurationProperties("com.lxp.myself")
public class StarterServiceProperties {
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
