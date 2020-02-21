package com.yuki.demo7.dynamic;

import com.yuki.demo7.monitor.Monitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PerformInvocationHandler implements InvocationHandler {


    private Object target;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Monitor.begin(method.getName());
        Object res = method.invoke(target,args);
        Monitor.end();
        return res;
    }
}
