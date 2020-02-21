package com.yuki.demo7.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class GreetableAdvice extends DelegatingIntroductionInterceptor implements Greetable {

    static ThreadLocal<Boolean> GREET_LIST = new ThreadLocal<Boolean>();

    public void setGreetAble(Boolean b) {
        GREET_LIST.set(Boolean.FALSE);
    }

    public Boolean getGreetAble() {
        return GREET_LIST.get();
    }


    public Object invoke(MethodInvocation proceed) throws Throwable {
        boolean b = GREET_LIST.get() != null && GREET_LIST.get() !=false;
        if(b) {
            System.out.println("嘤嘤嘤(╥╯^╰╥)");
        }
        Object res = super.invoke(proceed);
        if(b) {
            System.out.println("嘤嘤嘤(╥╯^╰╥)");
        }
        return res;
    }
}
