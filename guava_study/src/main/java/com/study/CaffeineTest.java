package com.study;

import com.github.benmanes.caffeine.cache.*;
import com.sun.istack.internal.Nullable;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName CaffeineTest
 * @Description Caffeine测试
 * @Author liangxp
 * @Date 2021/2/3 17:19
 **/
public class CaffeineTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Cache<String, String> cache =  Caffeine.newBuilder()
                //数量上限
                .maximumSize(3)
                // 过期设置
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // 弱引用key
//                .weakKeys()
                // 弱引用value
//                .weakValues()
                // 剔除监听
//                .removalListener((RemovalListener<String, String>) (key, value, cause) ->
//                        System.out.println("key:" + key + ", value:" + value + ", 删除原因:" + cause.toString()))
                .build();
        String key = "hello";
        cache.put(key,"world");
        System.out.println("world".equals(cache.getIfPresent(key)));

        String key2 = "func";
        cache.get(key2,s -> s +"test");
        System.out.println("functest".equals(cache.getIfPresent("func")));

        cache.put(key,"world2");
        System.out.println("world2".equals(cache.getIfPresent(key)));

        cache.invalidate(key2);
        System.out.println(null == cache.getIfPresent(key2));

        TimeUnit.SECONDS.sleep(5);
        System.out.println(null == cache.getIfPresent(key));

        System.out.println("finish");
    }


}
