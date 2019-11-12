package org.hscoder.springboot.shiro.modules.shiro;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.*;

public class ShiroUserManager {

    // 用户表
    private final Map<String, UserInfo> users = new HashMap<String, UserInfo>();
    // 角色权限表
    private final Map<String, List<RoleInfo>> userRoles = new HashMap<String, List<RoleInfo>>();

    private static final Logger logger = LoggerFactory.getLogger(ShiroUserManager.class);

    // 密钥匹配类
    private ShiroHashMatcher matcher;

    public ShiroUserManager(ShiroHashMatcher matcher) {
        this.matcher = matcher;
    }

    public ShiroHashMatcher getMatcher() {
        return this.matcher;
    }

    @PostConstruct
    private void init() {

        // 预置信息
        register("lilei", "111111", "123");
        grant("normal", new RoleInfo("customer", "customer.profile.read"));
        grant("normal", new RoleInfo("customer", "customer.profile.write"));
    }

    /**
     * 获取用户信息
     * 
     * @param username
     * @return
     */
    public UserInfo getUser(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return users.get(username);
    }

    /**
     * 获取权限信息
     * 
     * @param username
     * @return
     */
    public List<RoleInfo> getRoles(String username) {
        if (StringUtils.isEmpty(username)) {
            return Collections.emptyList();
        }
        return userRoles.get(username);
    }

    /**
     * 添加用户
     * 
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public UserInfo register(String username, String password, String salt) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(salt)) {
            return null;
        }

        // 生成加盐密码Hash值
        String passwordHash = matcher.getCredentialHash(password, salt);

        logger.info("user {} register with passHash :{}", username, passwordHash);
        UserInfo user = new UserInfo(username, passwordHash, salt);
        users.put(username, user);

        return user;
    }

    /**
     *   授权操作
     * 
     * @param username
     * @param role
     */
    public void grant(String username, RoleInfo role) {
        if (userRoles.containsKey(username)) {

            userRoles.get(username).add(role);
        } else {
            List<RoleInfo> roleList = new ArrayList<RoleInfo>();
            roleList.add(role);
            userRoles.put(username, roleList);
        }
    }

    public static class UserInfo {

        private String username;
        private String passwordHash;
        private String salt;

        public UserInfo() {
            super();
        }

        public UserInfo(String username, String passwordHash, String salt) {
            super();
            this.username = username;
            this.passwordHash = passwordHash;
            this.salt = salt;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

    }

    public static class RoleInfo {

        private String roleName;
        private List<String> perms;

        public RoleInfo(String roleName, String... perms) {
            super();
            this.roleName = roleName;
            this.perms = Arrays.asList(perms);
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public List<String> getPerms() {
            return perms;
        }

        public void setPerms(List<String> perms) {
            this.perms = perms;
        }

    }

}
