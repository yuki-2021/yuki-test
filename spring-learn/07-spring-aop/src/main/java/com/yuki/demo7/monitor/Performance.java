package com.yuki.demo7.monitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Performance {
    long start;
    long end;
    long expend;


    private long calcPerform() {
        expend = end - start;
        return expend;
    }

    public void printPerform() {
        calcPerform();
        System.out.println(calcPerform());
    }
}
