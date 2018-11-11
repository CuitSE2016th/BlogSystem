package com.bs.ssh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By ZZY on 2018/11/9
 */
public class RegexString {

    /**
     * 所运用的正则表达式的集合：
     * userPassword 的正则表达式："^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$" (只能位数字或者字母)
     * userEmail 的正则表达式："\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
     * userPhone 的正则表达式："0?(13|14|15|18)[0-9]{9}"
     *
     */

    public static final String regex_UserEmail = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    public static final String regex_UserPhone = "0?(13|14|15|18)[0-9]{9}";

    public static final String regex_UserPassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    // 传入要验证的字段，以及需要的正则表达式， 进行字段的验证
    public static boolean ExecRegex(String args, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);
        System.out.println(matcher.matches());
        return matcher.matches();
    }



}
