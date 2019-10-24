package com.yuki.handler;

import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class MyHandler implements TypeHandler<String> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        return "aaa";
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        return "aaa";
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return "aaa";
    }
}
