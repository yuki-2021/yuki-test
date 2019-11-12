package org.hscoder.springboot.shiro.modules.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroHashMatcher extends HashedCredentialsMatcher {

    public ShiroHashMatcher() {
        setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        setHashIterations(1024);
        setStoredCredentialsHexEncoded(true);
    }

    public String getCredentialHash(Object credentials, Object salt) {
        return new SimpleHash(this.getHashAlgorithmName(), credentials, salt, this.getHashIterations()).toHex();
    }

    public static void main(String[] args) {

        String username = "appuser";
        String salt = "123";
        String password = "111111";

        SimpleHash hash = new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, salt, 1024);
        System.out.println(hash.toHex());
    }
}
