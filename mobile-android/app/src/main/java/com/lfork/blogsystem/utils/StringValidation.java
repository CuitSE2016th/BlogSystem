package com.lfork.blogsystem.utils;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 98620
 */
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
