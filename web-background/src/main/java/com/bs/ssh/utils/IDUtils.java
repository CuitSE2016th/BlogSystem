package com.bs.ssh.utils;

import com.bs.ssh.common.email163.MailUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Create By ZZY on 2018/10/26
 */
public class IDUtils {

    /**
     * 用户ID
     * @return 格式为当前日期加上 6位随机字符串 */
    public static String UserID(){

        String dateToStr = dateToStr("ssSSS");

        return dateToStr+ MailUtil.EmailCode(6);
    }

    /**
     * 用户ID
     * @return 格式为当前日期加上 6位随机字符串 */
    public static String UserTokenID(){

        String dateToStr = dateToStr("ssSSS");

        return dateToStr+ MailUtil.EmailCode(5);
    }



    public static String dateToStr(String regex) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ssSSS");

        String s = dateFormat.format(new Date());

        return s;
    }


}
