package com.bs.ssh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Create By ZZY on 2018/10/26
 */
public class IDUtils {

    /**
     * 用户ID
     * @return
     */
    public static String UserID(){


        String dateToStr = dateToStr(new Date());
        int nextInt = (int) (Math.random() * 1000);



        return dateToStr+nextInt;
    }

    /**
     * 得到订单ID
     * @return
     */
    public static String OrderID(){

        String dateToStr = dateToStr(new Date());
        int nextInt = (int) (Math.random() * 100000);

        return dateToStr+nextInt;
    }

    /**
     * 得到商品ID
     * @return
     */

    public static String GoodsID(){

        String dateToStr = dateToStr(new Date());
        int nextInt = (int) (Math.random() * 10000);

        return dateToStr+nextInt;
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmssSSS");

        String s = dateFormat.format(date);

        return s;
    }


}
