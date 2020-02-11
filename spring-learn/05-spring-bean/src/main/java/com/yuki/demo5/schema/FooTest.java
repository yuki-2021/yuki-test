package com.yuki.demo5.schema;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FooTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:schema/applicationContext.xml");
        Foo foo = context.getBean(Foo.class);
        System.out.println(foo);
    }
}
