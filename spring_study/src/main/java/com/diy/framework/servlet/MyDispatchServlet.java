package com.diy.framework.servlet;

import com.diy.framework.annotation.MyAutowired;
import com.diy.framework.annotation.MyController;
import com.diy.framework.annotation.MyRequestMapping;
import com.diy.framework.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

/**
 * @ClassName MyDispatchServlet
 * @Description TODO
 * @Author liangxp
 * @Date 2020/7/16 16:28
 **/
public class MyDispatchServlet extends HttpServlet {

    private static final long serialVersionUID = 12313131131221131L;

    private static final String LOCATION = "contextConfigLocation";

    private Properties p = new Properties();

    //扫描到的类名
    private List<String> className = new ArrayList<String>();

    //容器，保存初始化的bean
    private Map<String,Object> ioc = new HashMap<String,Object>();

    //保存映射关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    public MyDispatchServlet(){
        super();
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500错误:" + e.getStackTrace());
        }

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (this.handlerMapping.isEmpty()) {
            return;
        }

        String url = req.getRequestURI();

        String contextPath = req.getContextPath();

        url = url.replace(contextPath,"").replaceAll("/+","/");
        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!!");
            return;
        }

        Map<String, String[]> parameterMap = req.getParameterMap();
        Method method = this.handlerMapping.get(url);

        Class<?>[] parameterTypes = method.getParameterTypes();

        //保存参数值
        Object[] paramValue = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];

            if (parameterType == HttpServletRequest.class){
                paramValue[i] = req;
                continue;
            } else if(parameterType == HttpServletResponse.class){
                paramValue[i] = resp;
                continue;
            } else if(parameterType == String.class){
                for (Entry<String,String[]> param: parameterMap.entrySet()) {
                    String value= Arrays.toString(param.getValue())
                            .replaceAll("\\[|\\]","")
                            .replaceAll(",\\s",",");
                    paramValue[i] = value;
                }
            }
        }

        String beanName = lowFirstCase(method.getDeclaringClass().getSimpleName());
        try {
            method.invoke(this.ioc.get(beanName),paramValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //1 加载配置文件
        doLoadConfig(servletConfig.getInitParameter(LOCATION));

        //2 扫描相关的类
        doScanner(p.getProperty("scanPackage"));
        
        //3 初始化类的实例，保存到IOC容器中
        doInstance();
        
        //4 依赖注入
        doAutowired();

        //5 构造handlerMapping
        initHandlerMapping();

        //6 等待请求，匹配url，定位方法，反射调用执行

        //提示
        System.out.println("my springframework is init");
    }

    private void initHandlerMapping() {
        System.out.println("匹配请求url");
        if (ioc.isEmpty()){
            return;
        }

        for (Entry<String,Object> entry: ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)){
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //获取method的url
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)){
                    continue;
                }

                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                String url = ("/" + baseUrl + requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.out.println("mapped:" + url + " : " + method);
            }


        }

    }

    private void doAutowired() {
        System.out.println("依赖注入");
        if (ioc.isEmpty()) {
            return;
        }

        for (Entry<String,Object> entry: ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field: fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    private void doInstance() {
        System.out.println("初始化bean实例");
        if (className.size() == 0) {
            return;
        }
        try {
            for (String className: className) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    //默认首字母小写作为beanName
                    String beanName = lowFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,clazz.newInstance());
                } else if(clazz.isAnnotationPresent(MyService.class)){
                    MyService myService = clazz.getAnnotation(MyService.class);
                    String beanName = myService.value();
                    //如果用户自己设置了名字，就采纳用户的
                    if (!"".equals(beanName.trim())) {
                        ioc.put(beanName,clazz.newInstance());
                        continue;
                    }
                    Class<?>[] interfaces = clazz.getInterfaces();

                    for (Class<?> cla: interfaces) {
                        ioc.put(cla.getName(),clazz.newInstance());
                    }

                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doScanner(String scanPackage) {
        System.out.println("扫描包路径:" + scanPackage);
        //将包路径转为文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            } else {
                className.add(scanPackage + "." + file.getName().replace(".class","").trim());
            }
        }

    }

    private String lowFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doLoadConfig(String location) {
        System.out.println("加载配置文件:" + location);
        InputStream inputStream = null;

        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
