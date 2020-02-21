package com.yuki.demo7.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {

    private static List<String> NAME_LIST = new ArrayList<String>();
    static {
        NAME_LIST.add("yuki");
        NAME_LIST.add("yuker");
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("调用"+targetClass.getName()+"."+method.getName()+"做静态检查");
        return "greetTo".equals(method.getName());
    }

    public boolean matches(Method method, Class<?> aClass, Object[] objects) {
        System.out.println("调用"+aClass.getClass().getName()+"."+method.getName()+"做duang态检查");
        return NAME_LIST.contains(objects[0].toString());
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> aClass) {
                return aClass.isAssignableFrom(Waiter.class);
            }
        };
    }
}
