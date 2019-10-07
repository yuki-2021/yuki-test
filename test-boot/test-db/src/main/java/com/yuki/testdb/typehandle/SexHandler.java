package com.yuki.testdb.typehandle;

import com.yuki.testdb.beans.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//JDBC类型
@MappedJdbcTypes(JdbcType.INTEGER)
//Java类型
@MappedTypes(SexEnum.class)
public class SexHandler extends BaseTypeHandler<SexEnum> {
    //设置非空性别参数
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEnum.getId());
    }

    //列名读取性别
    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex = resultSet.getInt(s);
        return SexEnum.getEnumById(sex);
    }

    //读取性别
    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        return SexEnum.getEnumById(sex);
    }

    //存储过程读取性别
    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        return SexEnum.getEnumById(sex);
    }
}
