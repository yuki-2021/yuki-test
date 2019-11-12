package org.hscoder.springboot.shiro.modules.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.hscoder.springboot.shiro.modules.shiro.ShiroUserManager.RoleInfo;
import org.hscoder.springboot.shiro.modules.shiro.ShiroUserManager.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    private ShiroUserManager userManager;

    public ShiroRealm(ShiroUserManager userManager) {
        this.setCredentialsMatcher(userManager.getMatcher());
        this.userManager = userManager;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("check authorization info");

        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();

        // 获取当前用户
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();

        // 查询角色信息
        List<RoleInfo> roleInfos = userManager.getRoles(userInfo.getUsername());

        if (roleInfos != null) {
            for (RoleInfo roleInfo : roleInfos) {

                authInfo.addRole(roleInfo.getRoleName());

                if (roleInfo.getPerms() != null) {
                    for (String perm : roleInfo.getPerms()) {
                        authInfo.addStringPermission(perm);
                    }
                }
            }
        }

        return authInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("check authentication info");

        String username = (String) token.getPrincipal();

        // 获取用户信息
        UserInfo user = userManager.getUser(username);

        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPasswordHash(),
                ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }

}
