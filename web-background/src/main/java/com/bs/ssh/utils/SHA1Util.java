package com.bs.ssh.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Create By ZZY on 2018/11/12
 */
public class SHA1Util {

    public static String SHA1(String password, String email){
        ByteSource salt = ByteSource.Util.bytes(email);

        SimpleHash simpleHash = new SimpleHash("SHA1", password, salt, 1024);


        return simpleHash.toString();
    }



}
