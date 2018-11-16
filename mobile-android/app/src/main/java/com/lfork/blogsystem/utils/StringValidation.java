package com.lfork.blogsystem.utils;

import android.text.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidation {
    /**
     * 所运用的正则表达式的集合：
     * userId 的正则表达式："-?[1-9]\\d*" (数字)
     * userName的正则表达式："[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+" ()
     * userPassword 的正则表达式："^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$" (只能位数字或者字母)  //8位到16位
     * userEmail 的正则表达式："\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
     * userPhone 的正则表达式："0?(13|14|15|18)[0-9]{9}"
     */

    private static final String regex_UserId = "[1-9]\\d{9}";

    private static final String regex_UserName = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{5,16}";

    private static final String regex_UserPassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    private static final String REGEX_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    private static final String REGEX_PHONE = "0?(13|14|15|18)[0-9]{9}";

    // 对注册时的数据进行验证
    public static String RegisterValidation(String userId, String userName, String userPassword, String passwordRepeat) {

        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(passwordRepeat)) {
            return "请输入完整的注册信息";
        }

        if (!regexValidation(userId, regex_UserId)) {
            return "学号必须是10位";
        }

        if (!regexValidation(userName, regex_UserName)) {
            return "用户名不符合规范";
        }

        if (!regexValidation(userPassword, regex_UserPassword)) {
            return "密码不符合规范";
        }


        if (!userPassword.equals(passwordRepeat)) {
            return "两次输入的密码必须相同";
        }

        return null;
    }

    // 对登录的数据进行验证
    public static boolean LoginValidation(String userId, String userPassword) {
        return regexValidation(userId, regex_UserId) && regexValidation(userPassword, regex_UserPassword);
    }

    // 对修改用户信息后保存操作之前的数据进行修改
    public static boolean SaveValidation(String userId, String userName, String userPassword, String userEmail,
                                         String userPhone) {
        return regexValidation(userId, regex_UserId) && regexValidation(userName, regex_UserName)
                && regexValidation(userPassword, regex_UserPassword) && regexValidation(userEmail, REGEX_EMAIL)
                && regexValidation(userPhone, REGEX_PHONE);
    }


    /**
     * @param args 要验证的字段
     * @param regex 正则
     */
    private static boolean regexValidation(String args, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    /**
     * 只验证是否为中国的11位手机号码
     */
    public static boolean isPhoneValid(String phone) {
        return regexValidation(phone, REGEX_PHONE);
    }

    public static boolean isEmailValid(String email) {
        return regexValidation(email, REGEX_EMAIL);
    }

    public static boolean isPasswordValid(@NotNull String password) {
        return password.length() > 4;
    }

}
