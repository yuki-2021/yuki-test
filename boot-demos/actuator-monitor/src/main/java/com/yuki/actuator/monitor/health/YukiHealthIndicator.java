package com.yuki.actuator.monitor.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


public class YukiHealthIndicator implements HealthIndicator {

    private static final String YUKI_VERSION = "1.0.0";

    @Override
    public Health health() {
        int code= check();
        if(code != 0){
            return Health.down().withDetail("code",code).withDetail("version",YUKI_VERSION).build();
        }
        return Health.up().withDetail("code",code).withDetail("version",YUKI_VERSION).build();
    }


    public int check(){
        return 0;
    }
}
