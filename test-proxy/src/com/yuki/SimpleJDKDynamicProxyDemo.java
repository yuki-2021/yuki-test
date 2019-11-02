package com.yuki;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @Author sdc
 */
public class SimpleJDKDynamicProxyDemo {
    //interface
    static interface IService {
        public void sayHello();
    }
    //real service
    static class RealService implements IService {
        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }
    //
    static class SimpleInvocationHandler implements InvocationHandler {
        private Object realObj;
        public SimpleInvocationHandler(Object realObj) {
            this.realObj = realObj;
        }
        @Override
        public Object invoke(Object proxy, Method method,
                             Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(realObj, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }
    public static void main(String[] args) {
        //创建真实对象
        IService realService = new RealService();
        //创建代理对象
        IService proxyService = (IService) Proxy.newProxyInstance(
                //加载器
                IService.class.getClassLoader(),
                //需要实现的接口
                new Class<?>[] { IService.class },
                //handler:代理接口所有调用会转给该方法
                new SimpleInvocationHandler(realService));
        proxyService.sayHello();
    }
}
