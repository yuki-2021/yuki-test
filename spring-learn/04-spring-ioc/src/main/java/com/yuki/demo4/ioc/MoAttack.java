package com.yuki.demo4.ioc;

public class MoAttack implements ActorArrangable {

    private GeLi geli;

    public void injectGeli(GeLi geli) {
        this.geli = geli;
    }

    public void cityGateAsk() {
        geli.responseAsk("莫泽隔离");
    }
}
