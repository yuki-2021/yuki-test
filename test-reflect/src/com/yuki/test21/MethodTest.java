package com.yuki.test21;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

    }

    /**
     * 反射执行方法
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<Integer> cls = Integer.class;
        Method method = cls.getMethod("parseInt", String.class);
        Object invoke = method.invoke(null, "123");
        System.out.println(invoke);
    }
}
