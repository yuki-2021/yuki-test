package com.yuki.testdb.converter;

import com.yuki.testdb.beans.SexEnum;

import javax.persistence.AttributeConverter;

public class SexConverter implements AttributeConverter<SexEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return  sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getEnumById(integer);
    }
}
