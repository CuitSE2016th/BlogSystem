package com.bs.ssh.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis工具类
 *
 * @author Egan
 * @date 2018/11/11 17:18
 **/
public class RedisUtils {

    private static Logger logger = LogManager.getLogger(RedisUtils.class);

    private static RedisTemplate<Serializable, Object> template;

    @Autowired
    public RedisUtils(RedisTemplate<Serializable, Object> template){
        RedisUtils.template = template;
    }

    /**
     * 批量删除键
     *
     * @date 2018/11/11 17:20
     * @param keys
     * @return void
     **/
    public static void remove(String... keys){
        for (String key:keys)
            remove(key);
    }

    /**
     * 删除对应的值
     *
     * @date 2018/11/11 17:22
     * @param key
     * @return void
     **/
    public static void remove(String key){
        if(exist(key))
            template.delete(key);
    }


    /**
     * 获取对应的值
     *
     * @date 2018/11/11 17:23
     * @param key
     * @return java.lang.Object
     **/
    public static Object get(String key){
        ValueOperations<Serializable, Object> operations = template.opsForValue();
        return operations.get(key);
    }

    /**
     * 保存键值对
     *
     * @date 2018/11/11 17:26
     * @param key
	 * @param value
     * @return boolean
     **/
    public static boolean set(Serializable key, Object value){
        try {
            ValueOperations<Serializable, Object> operations = template.opsForValue();
            operations.set(key, value);

            return true;
        }catch (Exception e){
            logger.error("SAVE FAILED", e);
            return false;
        }
    }

    /**
     * 判断键是否存在
     *
     * @date 2018/11/11 17:21
     * @param key
     * @return boolean
     **/
    public static boolean exist(String key){
        return template.hasKey(key);
    }

    /**
     * 删除键的集合
     *
     * @date 2018/11/11 17:48
     * @param keys
     * @return void
     **/
    public static void delete(Collection<Serializable> keys) {
        template.delete(keys);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public static void expireKey(String key, long time, TimeUnit timeUnit) {
        template.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public static void expireKeyAt(String key, Date date) {
        template.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public static long getKeyExpire(String key, TimeUnit timeUnit) {
        return template.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public static void persistKey(String key) {
        template.persist(key);
    }

    public static RedisTemplate<Serializable, Object> getTemplate() {
        return template;
    }

}
