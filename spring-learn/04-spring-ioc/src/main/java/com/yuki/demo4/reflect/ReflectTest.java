package com.yuki.demo4.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        // 1. 获取 Class元信息对象
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = classLoader.loadClass("com.yuki.demo4.reflect.Car");

        // 2. 创建对象
        Constructor<?> carConstruct = clazz.getDeclaredConstructor();
        Object car = carConstruct.newInstance();

        // 3. 设置属性
        Field brand = clazz.getDeclaredField("brand");
        brand.setAccessible(true);
        brand.set(car,"byd");
        Field color = clazz.getDeclaredField("color");
        color.setAccessible(true);
        color.set(car,"yellow");
        Field maxSpeed = clazz.getDeclaredField("maxSpeed");
        maxSpeed.setAccessible(true);
        maxSpeed.set(car,15);

        // 4. 调用方法
        Method introduce = clazz.getMethod("introduce");
        introduce.invoke(car);
    }
}
