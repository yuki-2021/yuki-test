package com.yuki.demo5.param;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParamTest {

    public static void main(String[] args) {
        //parent
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("param/applicationContext.xml");

        //son
        ClassPathXmlApplicationContext son =
                new ClassPathXmlApplicationContext(new String[]{"param/son.xml"},context);

        // set parent
        son.setParent(context);

        // sout
        Boss boss = son.getBean("boss",Boss.class);
        System.out.println(boss);

        Boss boss2 = son.getBean("boss2",Boss.class);
        System.out.println(boss2);

        Boss boss3 = son.getBean("boss3",Boss.class);
        System.out.println(boss3);

        Boss boss4 = son.getBean("boss4",Boss.class);
        System.out.println("-----");
        System.out.println(boss4);
    }
}
