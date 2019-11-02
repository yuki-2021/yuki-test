package com.yuki.test16.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;


/**
 * 解决ABA问题
 *
 * @Author sdc
 */
public class ABA {
    public static void main(String[] args) {
        //解决ABA
        int stamp = 1;
        AtomicStampedReference<Integer> pairRef = new
                AtomicStampedReference<Integer>(10, stamp);
        int newStamp = 2;
        pairRef.compareAndSet(20, 12, stamp, newStamp);
    }
}
