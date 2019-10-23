package com.yuki;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Date;

import static java.lang.Class.forName;

public class ClassDemo {

    public static void main(String[] args) {

        //类对应的Class
        Class<Date> dateClass = Date.class;
        //接口对应的Class
        Class<Comparable> comparableClass = Comparable.class;
        //基本类型class
        Class<Double> doubleClass = Double.class;
        //数组对应class
        String[] strArr = new String[10];
        Class<? extends String[]> aClass = strArr.getClass();

        //根据类名加载class
        try {
            Class<?> aClass1 = forName("java.util.HashMap");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
