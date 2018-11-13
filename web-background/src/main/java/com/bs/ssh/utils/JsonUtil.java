package com.bs.ssh.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.io.PrintWriter;

/** en?
 *
 * @author 98620
 * @date 2018/2/1
 */

public class JsonUtil {

    private static Logger logger = LogManager.getLogger(JsonUtil.class);

    /**
     *
     * @param jsonData 符合JSON语法规则的字符串
     * @param token    传递参数的时候这样写：new TypeToken<Class对象>() {}，  比如：parseJson(str1, new TypeToken<List<Result<User>>>(){})
     *        泛型会被擦除, 所以Token需要单独传参数
     * @return fromJson会自动根据JSONData的内容转化为POJO 或者是List
     */
    public static <T> T parseJson(String jsonData, TypeToken<?> token) {
        try {
            return new Gson().fromJson(jsonData, token.getType());
        } catch (Exception e) {
            logger.error("parse failure", e);
            return null;
        }
    }

    public static <T> String toJson(T object){
        try {
            return new Gson().toJson(object);
        } catch (Exception e) {
            logger.error("conversion failed", e);
            return null;
        }
    }

    public static <T> String toJsonExposed(T object){
        try {
            return new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create().toJson(object);
        } catch (Exception e) {
            logger.error("conversion failed", e);
            return null;
        }
    }

    public static  <T> void returnJson(T data) throws IOException {
        PrintWriter out= ServletActionContext.getResponse().getWriter();
        out.print(toJson(data));
        out.flush();
        out.close();
    }

}
