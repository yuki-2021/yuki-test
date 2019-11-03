package com.yuki.actuator.monitor.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;


public class YukiAbstractHealthIndicator extends AbstractHealthIndicator {

    private static final String YUKI_VERSION = "1.0.0";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int check = check();
        if(check != 0){
            builder.down().withDetail("code",check).withDetail("version",YUKI_VERSION).build();
        }
        builder.up().withDetail("code",check).withDetail("version",YUKI_VERSION).build();
    }

    private int check(){
        return  0;
    }
}
