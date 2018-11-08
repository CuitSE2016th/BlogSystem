package com.bs.ssh.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * Created by zzy on 2018/8/8 11:21.
 */
public class MyRealm extends AuthenticatingRealm {


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//
//        String username = usernamePasswordToken.getUsername();
//
//        Wrapper<User> wrapper = new EntityWrapper<>();
//        wrapper.eq("username", username);
//
//        User user = userService.selectOne(wrapper);
//
//        if(user == null){
//            throw new UnknownAccountException("用户不存在");
//        }
//
//        Object principal = user;
//
//        Object credentials = user.getPassword();
//
//        //realmName :当前realm对象的名字，调用父类的getName（）方法即可
//        String realmName = getName();
//
//        ByteSource credentialSalt = ByteSource.Util.bytes(username);
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,credentials,credentialSalt,realmName);

        return null;
    }


}
