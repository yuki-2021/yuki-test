package com.yuki.thread;

public class MYThread extends Thread {

    @Override
    public void run() {

    }


    public static void main(String[] args) {
        MYThread myThread = new MYThread();
        myThread.start();
    }
}
