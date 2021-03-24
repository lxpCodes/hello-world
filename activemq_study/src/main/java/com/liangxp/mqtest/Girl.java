package com.liangxp.mqtest;

import java.io.Serializable;

/**
 * @ClassName Girl
 * @Description 测试实体类
 * @Author liangxp
 * @Date 2020/12/15 14:24
 **/
public class Girl implements Serializable {
    public Girl(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;
    private String name;

    @Override
    public String toString() {
        return "Girl{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
