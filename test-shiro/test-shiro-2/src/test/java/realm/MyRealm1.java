package realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;



/**
 * Shiro再Realm获取安全数据(用户/角色/权限)
 * SecurityManager再Realm获取用户 验证是否合法
 * 可以把Realm看成Datasource
 */
public class MyRealm1 implements Realm {
    //返回唯一Realm的名称
    public String getName() {
        return "myrealm1";
    }

    //仅支持UsernamePasswrdToken类型的Token
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }


    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       //获得用户名
        String username = (String) token.getPrincipal();
        //获取密码
        String password = new String((char[])token.getCredentials());
        //判断
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        //身份验证成功  AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
