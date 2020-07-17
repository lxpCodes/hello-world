package com.diy.demo.service.impl;

import com.diy.demo.service.IMyDemoService;
import com.diy.framework.annotation.MyService;

/**
 * @ClassName MyService
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 17:03
 **/
@MyService
public class MyDemoService implements IMyDemoService {

    public String query(String name){
        return name;
    }
}
