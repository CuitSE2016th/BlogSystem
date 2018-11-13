package com.bs.ssh.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * 授权令牌类
 *
 * @author Egan
 * @date 2018/11/13 20:38
 **/
public class AuthToken implements AuthenticationToken, Serializable {

    private String value;

    public AuthToken(String token) {
        this.value = token;
    }

    @Override
    public Object getPrincipal() {
        return value;
    }

    @Override
    public Object getCredentials() {
        return value;
    }
}
