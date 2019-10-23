package com.yuki.test16.lock;

import java.util.Random;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AccountMgr {

    //i define excep
    public static class NoEnoughMoneyException extends Exception {}

    //transfer
    public static void transfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {
        from.lock();
        try {
            to.lock();
            try {
                if(from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }


    //try transfer
    public static boolean tryTransfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {
        if(from.tryLock()) {
            try {
                if(to.tryLock()) {
                    try {
                        if(from.getMoney() >= money) {
                            from.reduce(money);
                            to.add(money);
                        } else {
                            throw new NoEnoughMoneyException();
                        }
                        return true;
                    } finally {
                        to.unlock();
                    }
                }
            } finally {
                from.unlock();
            }
        }
        return false;
    }

    //loop transfer
     public static void loopTransfer(Account from, Account to, double money) throws NoEnoughMoneyException {
        boolean success = false;
        do {
            success = tryTransfer(from, to, money);
            if(!success) {
                Thread.yield();
            }
        } while (!success);
    }

    //死锁
    public static void simulateDeadLock() {
        //create accounts
        final int accountNum = 10;
        final Account[] accounts = new Account[accountNum];
        final Random rnd = new Random();
        for(int i = 0; i < accountNum; i++) {
            accounts[i] = new Account(rnd.nextInt(10000));
        }

        //create threads
        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for(int i = 0; i < threadNum; i++) {
            threads[i] = new Thread() {
                public void run() {
                    int loopNum = 100;
                    for(int k = 0; k < loopNum; k++) {
                        int i = rnd.nextInt(accountNum);
                        int j = rnd.nextInt(accountNum);
                        int money = rnd.nextInt(10);
                        if(i != j) {
                            try {
                                transfer(accounts[i], accounts[j], money);
                            } catch (NoEnoughMoneyException e) {
                            }
                        }
                    }
                }
            };
            threads[i].start();
        }
    }
}
