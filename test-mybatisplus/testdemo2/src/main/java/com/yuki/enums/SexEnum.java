package com.yuki.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum SexEnum implements IEnum<Integer> {

    //定义字段
    MALE(0, "男"),
    FEMALE(1, "女");


    SexEnum(int value, String descp) {
        this.value = value;
        this.descp = descp;
    }

    private final int value;
    private final String descp;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
