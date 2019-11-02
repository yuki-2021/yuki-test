package db;

import entity.User;
import entity.UserGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Db {

    static List<User> userList = new ArrayList<User>();

    static {

        User user = null;

        for (int i = 0; i < 4; i++) {
            user = new User();
            user.setUserId(i);
            user.setUsername(String.valueOf(i));
            user.setPassword(String.valueOf(i));
            user.setEmail(String.valueOf("@"));
            user.setSex(0);
            user.setUserGroup(new UserGroup("游客"));
            user.setCreateAt(new Date());
            user.setCustomizeField(45);
            userList.add(user);
            user = null;
        }
    }

    public static List<User> get() {
        return userList;
    }
}
