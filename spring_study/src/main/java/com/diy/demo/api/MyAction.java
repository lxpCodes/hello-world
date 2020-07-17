package com.diy.demo.api;

import com.diy.demo.service.IMyDemoService;
import com.diy.demo.service.impl.MyDemoService;
import com.diy.framework.annotation.MyAutowired;
import com.diy.framework.annotation.MyController;
import com.diy.framework.annotation.MyRequestMapping;
import com.diy.framework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyAction
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 17:02
 **/
@MyController
@MyRequestMapping("/mydemo")
public class MyAction {

    @MyAutowired
    private IMyDemoService myDemoService;


    @MyRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("name") String name){
        String result = myDemoService.query(name);
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
