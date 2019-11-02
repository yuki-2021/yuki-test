package com.yuki.test21;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class GenericDemo {

    static class GenericTest<U extends Comparable<U>, V> {
        U u;
        V v;
        List<String> list;
        public U test(List<? extends Number> numbers) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Class<?> cls = GenericTest.class;
        //获取类泛型参数
        for(TypeVariable t : cls.getTypeParameters()) {
            System.out.println(t.getName() + " extends " +
                    Arrays.toString(t.getBounds()));
        }

        //获取字段
        Field fu = cls.getDeclaredField("u");
        System.out.println(fu.getGenericType());

        //获取字段列表
        Field flist = cls.getDeclaredField("list");
        //输出rawType和实际泛型类型
        Type listType = flist.getGenericType();
        if(listType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) listType;
            System.out.println("raw type: " + pType.getRawType()
                    + ",type arguments:"
                    + Arrays.toString(pType.getActualTypeArguments()));
        }


        //获取泛型方法
        Method m = cls.getMethod("test", new Class[] { List.class });
        //获取方法参数类型
        for(Type t : m.getGenericParameterTypes()) {
            System.out.println(t);
        }
    }
}
