package com.yuki;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class MethodAnnotations {
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam {
        String value();
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface DefaultValue {
        String value() default "";
    }

    public void hello(@QueryParam("action") String action,
                      @QueryParam("sort") @DefaultValue("asc") String sort) {

    }

    public static void main(String[] args) throws Exception {
        //获取Class
        Class<?> cls = MethodAnnotations.class;
        //获取Annotations
        Method method = cls.getMethod("hello",
                new Class[]{String.class, String.class});
        //获取注解
        Annotation[][] annts = method.getParameterAnnotations();
        for (int i = 0; i < annts.length; i++) {
            System.out.println("annotations for paramter " + (i + 1));
            Annotation[] anntArr = annts[i];
            for (Annotation annt : anntArr) {
                if (annt instanceof QueryParam) {
                    QueryParam qp = (QueryParam) annt;
                    System.out.println(qp.annotationType()
                            .getSimpleName() + ":" + qp.value());
                } else if (annt instanceof DefaultValue) {
                    DefaultValue dv = (DefaultValue) annt;
                    System.out.println(dv.annotationType()
                            .getSimpleName() + ":" + dv.value());
                }
            }
        }
    }
}
