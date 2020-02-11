package com.yuki.demo5.lookup;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class LookupTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("lookup/applicationContext.xml");

        // mCar1 == mCar2
        BossMagic boss = context.getBean(BossMagic.class);
        Car mCar1 = boss.getCar();
        Car mCar2 = boss.getCar();
        System.out.println("---- mCar1 == mCar2 ----");
        System.out.println(mCar1 == mCar2);

        // lCar1 == lCar2
        LookupBoss lookupBoss = context.getBean(LookupBoss.class);
        Car lCar1 = lookupBoss.getCar();
        Car lCar2 = lookupBoss.getCar();
        System.out.println("---- lCar1 == lCar2 ----");
        System.out.println(lCar1);
        System.out.println(lCar1 ==lCar2);

        // import
        List list = context.getBean("list", ArrayList.class);
        System.out.println("---- list ----");
        System.out.println(list);
    }
}
