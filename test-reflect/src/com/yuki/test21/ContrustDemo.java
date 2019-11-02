package com.yuki.test21;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class ContrustDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {


    }

    private static void testConstruct() throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Constructor<StringBuilder> constructor = StringBuilder.class.getConstructor(Integer.class);
        StringBuilder stringBuilder = constructor.newInstance(100);
        System.out.println(stringBuilder.toString());
    }

    private static void testNewInstance() throws InstantiationException, IllegalAccessException {
        HashMap hashMap = HashMap.class.newInstance();
        hashMap.put("123","sdc");
    }
}
