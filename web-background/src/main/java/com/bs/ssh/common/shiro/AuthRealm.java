package com.bs.ssh.common.shiro;

import com.bs.ssh.beans.User;
import com.bs.ssh.service.ShiroService;
import com.bs.ssh.utils.RedisUtils;
import com.google.gson.Gson;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.concurrent.TimeUnit;

/**
 * 验证范围
 *
 * @author Egan
 * @date 2018/11/13 20:37
 **/
public class AuthRealm extends AuthorizingRealm {

    private int TIME_OUT_DAY = 7;

    /**
     * token 类型支持
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }


    /**
     * 认证（登录时调用）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getCredentials();

        if (RedisUtils.exist(accessToken)) {
            User user = new Gson().fromJson(RedisUtils.get(accessToken).toString(), User.class);
            RedisUtils.expireKey(accessToken, TIME_OUT_DAY, TimeUnit.DAYS);
            return new SimpleAuthenticationInfo(user, accessToken, getName());
        }else {
            //token过期
            return null;
        }



    }

    /**
     * 授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
