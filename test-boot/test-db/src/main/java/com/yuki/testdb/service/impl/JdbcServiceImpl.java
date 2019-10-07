package com.yuki.testdb.service.impl;

import com.yuki.testdb.beans.SexEnum;
import com.yuki.testdb.beans.User;
import com.yuki.testdb.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取映射关系
    private RowMapper<User> getUserMapper() {
        RowMapper<User> userRowMapper = (rs, i) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            int sex = rs.getInt("sex");
            SexEnum enumById = SexEnum.getEnumById(sex);
            user.setSex(enumById);
            user.setNote(rs.getString("note"));
            return user;
        };
        return userRowMapper;
    }


    @Override
    public User getUser(Long id) {
        String sql = "select * from t_user where id = ?";
        Object[] params = new Object[]{id};
        User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());
        return user;
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = "select * from t_user where user_name like concat('%',?,'%') and note like concat('%',?,'%')";
        Object[] params = new Object[]{userName,note};
        List<User> users = jdbcTemplate.query(sql, params, getUserMapper());
        return users;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user(user_name,sex,note) values(?,?,?)";
        return jdbcTemplate.update(sql,user.getUserName(),user.getSex().getId(),user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set user_name = ?,sex = ?,note = ? where id = ?";
        return jdbcTemplate.update(sql,user.getUserName(),user.getSex().getId(),user.getNote(),user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user where id = ?";
        return  jdbcTemplate.update(sql,id);
    }

    //事务 可以使用ConnectionCallback 或者 StatementCallback
    public void statementCallback(){
        String sql1 = "insert t_user(user_name,sex,note) values('sdc3',1,'sss')";
        String sql2 = "insert t_user(user_name,sex,note,comment) values('sdc3',1,'sss')";
        jdbcTemplate.execute(new StatementCallback<User>() {
            @Override
            public User doInStatement(Statement statement) throws SQLException, DataAccessException {
                int i = statement.executeUpdate(sql1);
                int j = statement.executeUpdate(sql2);
                return null;
            }
        });
    }
}
