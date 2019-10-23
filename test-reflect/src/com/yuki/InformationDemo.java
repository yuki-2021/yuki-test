package com.yuki;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        getNames();
        getFields();
        getMethods();
        newInstance();
    }



    public static void getNames() {
        System.out.println("输出类名称");
        Class<String> stringClass = String.class;
        System.out.println(stringClass.getName());
        System.out.println(stringClass.getCanonicalName());
    }

    public static  void getFields() throws IllegalAccessException {
        List<String> obj = Arrays.asList(new String[]{"AAA","BBB"});
        Class<?> cls = obj.getClass();
        for(Field f : cls.getDeclaredFields()){
            f.setAccessible(true);
            System.out.println(f.getName()+" - "+ f.get(obj));
        }
    }

    public static void getMethods(){
        Class<?> cls = Integer.class;


        try {
            Method method = cls.getMethod("parseInt", new Class[]{String.class});
            System.out.println(method.invoke(null, "123"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static  void newInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Map<String,Integer> map = HashMap.class.newInstance();
        map.put("hello", 123);
        System.out.println(map.get("hello"));

        Constructor<StringBuilder> contructor= StringBuilder.class
                .getConstructor(new Class[]{int.class});
        StringBuilder sb = contructor.newInstance(100);
    }
}
