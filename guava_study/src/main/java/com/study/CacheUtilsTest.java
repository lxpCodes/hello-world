package com.study;

import com.google.common.cache.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheUtilsTest
 * @Description 本地缓存工具类
 * @Author liangxp
 * @Date 2021/2/1 15:33
 **/
public class CacheUtilsTest {

    // Callable形式的Cache
    private static final Cache<String,String> CALLABLE_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(5,TimeUnit.SECONDS)
            .maximumSize(1000)
            .recordStats()
            .removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(RemovalNotification<Object, Object> notification) {
                    System.out.println("key:" + notification.getKey());
                    System.out.println("value:" + notification.getValue());
                    System.out.println("cause_name:"+notification.getCause().name());
                }
            }).build();

    // CacheLoader形式的Cache
    private static final LoadingCache<String,String> LOADER_CACHE = CacheBuilder.newBuilder()
            .expireAfterAccess(3,TimeUnit.SECONDS).maximumSize(1000).recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return key + "_load_cache";
                }
            });


    public static void main(String[] args) throws Exception {
       int times = 10;
       while (times -- > 0){
           Thread.sleep(2000);

           CALLABLE_CACHE.get("key", new Callable<String>() {
               @Override
               public String call() throws Exception {
                   return "key_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
               }
           });

           System.out.println(LOADER_CACHE.get("key"));

       }


    }
}
