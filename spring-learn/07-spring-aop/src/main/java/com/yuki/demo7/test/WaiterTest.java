package com.yuki.demo7.test;

import com.yuki.demo7.service.NativeWaiter;
import com.yuki.demo7.service.Waiter;

public class WaiterTest {

    public static void main(String[] args) {
        Waiter waiter = new NativeWaiter();
        waiter.serveTo();
    }
}
