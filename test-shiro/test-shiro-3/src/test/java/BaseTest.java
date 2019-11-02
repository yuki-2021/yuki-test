
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.After;
import org.apache.shiro.util.ThreadContext;

public abstract class BaseTest {

    @After
    public void teardown(){
        //解除绑定到线程
        ThreadContext.unbindSubject();
    }

    protected void login(String configFile, String username, String password){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
