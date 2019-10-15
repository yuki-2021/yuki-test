package com.yuki.vola;

public class Switcher {
    private volatile boolean on;
    public boolean isOn() {
        return on;
    }
    public void setOn(boolean on) {
        this.on = on;
    }
}
