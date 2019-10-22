package com.yuki;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Student {

    public static final int MAX_NAME_LEN = 255;

    public static void main(String[] args) throws NoSuchFieldException {
        Field f = Student.class.getField("MAX_NAME_LEN");
        int mod = f.getModifiers();
        System.out.println(Modifier.toString(mod));
        System.out.println("isPublic: " + Modifier.isPublic(mod));
        System.out.println("isStatic: " + Modifier.isStatic(mod));
        System.out.println("isFinal: " + Modifier.isFinal(mod));
        System.out.println("isVolatile: " + Modifier.isVolatile(mod));
    }
}
