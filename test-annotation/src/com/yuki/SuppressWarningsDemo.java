package com.yuki;

import java.util.Date;

public class SuppressWarningsDemo {

    @SuppressWarnings({"deprecation","unused"})
    public static void main(String[] args) {
        Date date = new Date(2017, 4, 12);
        int year = date.getYear();
    }
}
