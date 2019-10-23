package com.yuki.inject;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleContainer {




    //缓存加载过的实例
    private static Map<Class<?>, Object> instances = new ConcurrentHashMap<>();


    public static <T> T getInstance(Class<T> cls) {
        try {
            //非单例 创建一个
            boolean singleton = cls.isAnnotationPresent(SimpleSingleton.class);
            if(!singleton) {
                return createInstance(cls);
            }
            // 单例直接返回
            Object obj = instances.get(cls);
            if(obj != null) {
                return (T) obj;
            }

            //没有直接创建 然后放入
            synchronized (cls) {
                obj = instances.get(cls);
                if(obj == null) {
                    obj = createInstance(cls);
                    instances.put(cls, obj);
                }
            }

            //
            obj = createInstance(cls);
            instances.putIfAbsent(cls, obj);

            //返回obj
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T createInstance(Class<T> cls) throws Exception {
        T obj = cls.newInstance();
        Field[] fields = cls.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(SimpleInject.class)) {
                if(!f.isAccessible()) {
                    f.setAccessible(true);
                }
                Class<?> fieldCls = f.getType();
                f.set(obj, getInstance(fieldCls));
            }
        }
        return obj;
    }
}
