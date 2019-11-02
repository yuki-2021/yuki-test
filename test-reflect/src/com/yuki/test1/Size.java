package com.yuki.test1;

enum Size {
    SMALL,
    BIG
}

class SizeDemo{
    public static void main(String[] args) {
        Size size = Size.BIG;
        System.out.println(size.toString());
        System.out.println(size.name());
        System.out.println(size.compareTo(Size.SMALL));

        for(Size s : Size.values()){
            System.out.println(s.name());
        }


    }
}
