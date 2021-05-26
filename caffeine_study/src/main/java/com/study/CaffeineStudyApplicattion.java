package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName CaffeineStudyApplicattion
 * @Description Caffeine学习
 * @Author liangxp
 * @Date 2021/2/3 18:05
 **/
@SpringBootApplication
@EnableCaching
public class CaffeineStudyApplicattion {
    public static void main(String[] args) {
        SpringApplication.run(CaffeineStudyApplicattion.class,args);
    }
}
