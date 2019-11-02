package com.yuki.test21;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * 根据反射访问字段
 *
 */
public class VisitFeild {

    public static final int MAX_NAME_LEN = 255;

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {

        getModifier();

    }

    /**
     * 访问修饰符
     *
     * @throws NoSuchFieldException
     */
    private static void getModifier() throws NoSuchFieldException {
        Field f = VisitFeild.class.getField("MAX_NAME_LEN");
        //返回静态修饰符
        int mod = f.getModifiers();
        System.out.println(Modifier.toString(mod));
        System.out.println("isPublic: " + Modifier.isPublic(mod));
        System.out.println("isStatic: " + Modifier.isStatic(mod));
        System.out.println("isFinal: " + Modifier.isFinal(mod));
        System.out.println("isVolatile: " + Modifier.isVolatile(mod));
    }

    private static void visitFields() throws IllegalAccessException {
        List<String> strings = Arrays.asList("A", "B");
        Class<? extends List> cls = strings.getClass();
        for (Field f : cls.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println(f.getName() + " - " + f.get(strings));
        }
    }
}
