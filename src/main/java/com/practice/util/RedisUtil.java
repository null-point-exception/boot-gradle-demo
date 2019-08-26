package com.practice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 言曌
 * @date 2018/12/16 下午6:57
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> template;

    // Key（键），简单的key-value操作

    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     */
    public long ttl(String key) {
        return template.getExpire(key);
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     */
    public void expire(String key, long timeout) {
        template.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：INCR key，增加key一次
     */
    public long incr(String key, long delta) {
        return template.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令： key，减少key一次
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
//            throw new RuntimeException("递减因子必须大于0");
            del(key);
            return 0;
        }
        return template.opsForValue().increment(key, -delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return template.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     */
    public void del(String key) {
        template.delete(key);
    }

    // String（字符串）

    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     */
    public void set(String key, Object value) {
        template.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param timeout （以秒为单位）
     */
    public void set(String key, Object value, long timeout) {
        template.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     */
    public Object get(String key) {
        return template.opsForValue().get(key);
    }

    // Hash（哈希表）

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     */
    public void hset(String key, Object field, Object value) {
        template.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     */
    public Object hget(String key, Object field) {
        return template.opsForHash().get(key, field);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     */
    public void hdel(String key, Object... fields) {
        template.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     */
    public Map<Object, Object> hgetall(String key) {
        return template.opsForHash().entries(key);
    }

    // List（列表）

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, Object value) {
        return template.opsForList().leftPush(key, value);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @return 列表key的头元素。
     */
    public Object lpop(String key) {
        return template.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, Object value) {
        return template.opsForList().rightPush(key, value);
    }

}
