package com.yuki.demo5.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Boss {

    Car car;
    List<String> girlFirends = new ArrayList<String>();
    Set<String> wifes = new HashSet<String>();
    Map<String,String> works = new HashMap();
    Properties things = new Properties();
}
