package com.yuki.demo4.ioc;

public class Director {

    public static void main(String[] args) {
        new Director().direct();
    }

    private void direct() {
        MoAttack moAttack = new MoAttack();
        LiuDeHua liuDeHua = new LiuDeHua();
        moAttack.injectGeli(liuDeHua);
        moAttack.cityGateAsk();
    }
}
