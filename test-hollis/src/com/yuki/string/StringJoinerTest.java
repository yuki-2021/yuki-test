package com.yuki.string;

import java.util.StringJoiner;

public class StringJoinerTest {
    public static void main(String[] args) {

        StringJoiner sj = new StringJoiner("Hollis");
        sj.add("hollischuang").add("java");
        System.out.println(sj.toString());

        StringJoiner sj1 = new StringJoiner(":", "[", "]");
        sj1.add("hollis").add("hollischuang").add("java");
        System.out.println(sj1.toString());
    }
}
