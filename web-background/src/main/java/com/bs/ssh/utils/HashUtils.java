package com.bs.ssh.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * hash 工具类
 *
 * @author EtyMx
 * @date 2018-03-06
 */
public class HashUtils {

    public static String getSalt() {
        return hashBySha256(Long.toString(System.currentTimeMillis()));
    }

    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用 md5 算法进行散列计算
     *
     * @param input 加密原文
     * @return 散列后的 16 进制字符串
     */
    public static String hashByMd5(String input) {
        return hashByMd5(input.getBytes());
    }

    /**
     * 使用 md5 算法进行散列计算
     *
     * @param bytes byte 数组
     * @return 散列后的 16 进制字符串
     */
    public static String hashByMd5(byte[] bytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(bytes);
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 使用 sha1 算法进行散列计算
     *
     * @param input 加密原文
     * @return 散列后的 16 进制字符串
     */
    public static String hashBySha256(String input) {
        return hashBySha256(input.getBytes());
    }

    /**
     * 使用 sha1 算法进行散列计算
     *
     * @param bytes byte 数组
     * @return 散列后的 16 进制字符串
     */
    public static String hashBySha256(byte[] bytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(bytes);
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * byte 数组转 16 进制字符串
     *
     * @param bytes byte 数组
     * @return 16 进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder();
        int num;
        for (byte aByte : bytes) {
            num = aByte;
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }

    /**
     * 通过shiro工具实现加密
     * @param password
     * @param salt 盐
     * @return
     */
    public static String sha256(String password, String salt){


        SimpleHash simpleHash = new SimpleHash("SHA-256", password, salt, 1024);

        return simpleHash.toString();
    }



}
