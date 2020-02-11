package com.yuki.demo5.idname;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IdNameTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:idname/applicationContext.xml");
        Foo foo = context.getBean("foo1",Foo.class);
        Foo foo2 = context.getBean("foo1",Foo.class);
        System.out.println(foo);
        System.out.println(foo2);
    }
}
