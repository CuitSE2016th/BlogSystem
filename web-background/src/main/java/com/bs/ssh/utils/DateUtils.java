package com.bs.ssh.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Create By ZZY on 2018/10/26
 */
public class DateUtils {

    public static long DateAddSomeDay(int dayNum){
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, dayNum);

        return calendar.getTimeInMillis();
    }

    public static Date DateToStr(String dateStr){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        Date resultDate = null;
        try{
            resultDate = dateFormat.parse(dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultDate;
    }

    public static Date RentDateToStr(String dateStr){

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        Date resultDate = null;

        try{
            resultDate = dateFormat.parse(dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultDate;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                    && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                    && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }




}
