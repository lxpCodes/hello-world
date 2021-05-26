package com.lxp.myself;

import com.sun.deploy.util.StringUtils;

/**
 * @ClassName StartService
 * @Description TODO
 * @Author liangxp
 * @Date 2021/4/8 15:55
 **/
public class StartService {
    private String config;

    public StartService(String config){
        this.config = config;
    }

    public String[] split(String separatorChar){
        return StringUtils.splitString(this.config,separatorChar);
    }
}
