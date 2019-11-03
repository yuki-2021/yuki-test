package com.yuki.actuator.monitor.endpotins;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "yukip")
public class YukiEndPotint {

    @ReadOperation
    public Map<String,String> hello(){
        HashMap<String, String> result = new HashMap<>();
        result.put("author","yuki");
        result.put("age","24");
        result.put("email","577746040@qq.com");
        return result;
    }
}
