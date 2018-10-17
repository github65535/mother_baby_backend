package com.ypika.mp.common.utils;//package com.ypika.common.utils;
//
//import com.sunlands.feo.config.ConstantConfig;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.*;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by zxx on 2017/7/14.
// */
//@Component
//public class RedisUtil {
//
//    //日志记录
//    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
//    @Resource(name = "redisTemplate")
//    private ValueOperations<String, Object> valueOperations;
//    @Resource(name = "redisTemplate")
//    private RedisTemplate<String, Object> redisTemplate;
//    @Resource(name = "redisTemplate")
//    private HashOperations<String, String, Object> hashOperations;
//    @Resource(name = "redisTemplate")
//    private ZSetOperations<String, Object> zSetOperations;
//    @Resource(name = "redisTemplate")
//    private ListOperations<String, Object> listOperations;
//
//    /**
//     * 写入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @return 状态码
//     */
//    public boolean set(String key, Object value) {
//        boolean result = false;
//        try {
//            valueOperations.set(key, value);
//            result = true;
//        } catch (Exception e) {
//            logger.info("redis key：{}存贮异常", key);
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存，设置过期时间（秒）
//     *
//     * @param key        键
//     * @param value      值
//     * @param expireTime 过期时间
//     * @return 状态码
//     */
//    public boolean set(String key, Object value, Long expireTime) {
//        boolean result = false;
//        try {
//            valueOperations.set(key, value, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            logger.info("redis key：{}存贮异常", key);
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 删除key对应的value
//     *
//     * @param key 值
//     */
//    public void del(String key) {
//        if (StrUtil.isNotNull(key)) {
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 根据key获得缓存
//     *
//     * @param key 键
//     * @return 状态码
//     */
//    public Object get(String key) {
//        return valueOperations.get(key);
//    }
//
//    /**
//     * 根据key获取过期时间
//     *
//     * @param key 键
//     * @return 过期时间
//     */
//    public Long getExpire(String key) {
//        return redisTemplate.getExpire(key);
//    }
//
//    /**
//     * redis设置过期时间
//     *
//     * @param key        键
//     * @return 状态
//     */
//    public boolean expire(String key) {
//        return expire(key,0L);
//    }
//
//    /**
//     * redis设置过期时间
//     *
//     * @param key        键
//     * @param expireTime 过期时间
//     * @return 状态
//     */
//    public boolean expire(String key, Long expireTime) {
//        if (StrUtil.isNull(key)) {
//            return false;
//        }
//        boolean result = false;
//        try {
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            logger.info("redis key：{}设置过期时间异常", key);
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 检查key是否存在，返回boolean值
//     *
//     * @param key 键
//     * @return 是否存在
//     */
//    public boolean hasKey(String key) {
//        return redisTemplate.hasKey(key);//检查key是否存在，返回boolean值
//    }
//
//    /**
//     * hash储存
//     *
//     * @param key   键
//     * @param value 值
//     * @param store 储存
//     * @return 状态
//     */
//    public boolean hset(String key, String value, Object store) {
//        if (StrUtil.isNull(key) || StrUtil.isNull(value)) {
//            return false;
//        }
//        boolean result = false;
//        try {
//            hashOperations.put(key, value, store);
//            result = true;
//        } catch (Exception e) {
//            logger.info("redis key：{}；value：{}存贮异常", key, value);
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * hash储存，设置过期时间
//     *
//     * @param key        键
//     * @param value      值
//     * @param store      储存
//     * @param expireTime 过期时间
//     * @return 状态
//     */
//    public boolean hset(String key, String value, Object store, Long expireTime) {
//        if (StrUtil.isNull(key) || StrUtil.isNull(value) || StrUtil.isNull(expireTime)) {
//            return false;
//        }
//        boolean result = false;
//        try {
//            hashOperations.put(key, value, store);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info("redis key：{}；value：{}存贮异常", key, value);
//        }
//        return result;
//    }
//
//    /**
//     * hash 根据key，value删除缓存
//     *
//     * @param key 值
//     */
//    public void hdel(String key, String value) {
//        if (StrUtil.isNotNull(key) && StrUtil.isNotNull(value)) {
//            hashOperations.delete(key, value);
//        }
//    }
//
//    /**
//     * hash 根据key 获取hash中域的数量
//     *
//     * @param key
//     * @return
//     */
//    public Long hlen(String key) {
//        if (StrUtil.isNotNull(key)) {
//            Long size = hashOperations.size(key);
//            return size;
//        }
//        return 0L;
//    }
//
//    /**
//     * 根据键值取出store
//     *
//     * @param key   键
//     * @param value 值
//     * @return 状态
//     */
//    public Object hget(String key, String value) {
//        return hashOperations.get(key, value);
//    }
//
//    /**
//     * hash结构，值自增
//     *
//     * @param key   键
//     * @param value 值
//     * @return 状态
//     */
//    public Long hincrement(String key, String value, Long store, Long expireTime) {
//        if (StrUtil.isNull(key) || StrUtil.isNull(value) || StrUtil.isNull(store)) {
//            return null;
//        }
//        Long increment = hashOperations.increment(key, value, store);
//        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//        return increment;
//    }
//
//    /**
//     * 获得多个结果
//     *
//     * @param key    键
//     * @param params 集合
//     * @return 状态
//     */
//    public List<Object> multiGet(String key, List<String> params) {
//        AssertUtil.isNotNull(key);
//        AssertUtil.isNotNull(params);
//        return hashOperations.multiGet(key, params);
//    }
//
//    /**
//     * 有序集合 添加
//     *
//     * @param key
//     * @param object
//     * @param v
//     */
//    public void zSet(String key, Object object, Double v) {
//        zSetOperations.add(key, object, v);
//    }
//
//    /**
//     * 有序集合 查找指定区间对象
//     *
//     * @param key
//     * @param min
//     * @param max
//     * @return
//     */
//
//    public Set<Object> zRange(String key, long min, long max) {
//        return zSetOperations.range(key, min, max);
//    }
//
//    public Set<Object> zRange(String key, long max) {
//        return zRange(key, 0, max);
//    }
//
//    public Set<Object> reverseRange(String key, long min, long max) {
//        return zSetOperations.reverseRange(key, min, max);
//    }
//
//    /**
//     * 指定的内容删除
//     *
//     * @param key
//     * @param o
//     * @return
//     */
//    public long zRemove(String key, Object o) {
//        return zSetOperations.remove(key, o);
//    }
//
//    public long zRemove(String key, long min, long max) {
//        return zSetOperations.removeRange(key, min, max);
//    }
//
//    /**
//     * 批量获取
//     *
//     * @param collection
//     * @return
//     */
//    public List<Object> mget(Collection<String> collection) {
//        List<Object> objects = valueOperations.multiGet(collection);
//        return objects;
//    }
//
//
//    /**
//     * redis 锁
//     *
//     * @param key   要锁的对象
//     * @param value （当前时间+锁定时间）  转换成毫秒
//     * @return
//     */
//    public boolean lock(String key, String value) {
//        if (valueOperations.setIfAbsent(key, value)) {
//            return true;
//        }
//        String currentValue = (String) valueOperations.get(key);
//        //判断是否存在死锁 如果存在，从新锁定
//        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
//            String oldValue = (String) valueOperations.getAndSet(key, value);
//            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 解锁
//     *
//     * @param key
//     * @param value
//     */
//    public void unlock(String key, String value) {
//        try {
//            String currentValue = (String) valueOperations.get(key);
//            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
//                valueOperations.getOperations().delete(key);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 根据答题剩余秒数计算得分
//     *
//     * @param
//     * @return
//     */
//    public static String getSignInKey(Long userId) {
//        return String.format("%s-%d", ConstantConfig.SIGN_IN_DAY_Key, userId);
//    }
//
//    /**
//     * @param key
//     * @param incrNumber
//     * @return
//     */
//    public Long increment(String key, Integer incrNumber) {
//        return valueOperations.increment(key, incrNumber);
//    }
//
//
//    public Integer lPush(String key, Object value) {
//        return listOperations.leftPush(key, value).intValue();
//    }
//
//    public Integer lPushAll(String key, List<Object> valueList) {
//        return listOperations.leftPushAll(key, valueList).intValue();
//    }
//
//    public Object rPop(String key) {
//        return listOperations.rightPop(key);
//    }
//
//    /**
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     * @description 获取list中指定区间的元素
//     */
//    public List<Object> lRange(String key, int start, int end) {
//        return listOperations.range(key, start, end);
//    }
//
//    /**
//     * @param key
//     * @return list<Object>
//     * @description获取list中所有元素
//     */
//    public List<Object> lRange(String key) {
//        return this.lRange(key, 0, -1);
//    }
//
//
//    /**
//     * list 长度 没有 为 0
//     *
//     * @param key
//     * @return
//     */
//    public Integer listSize(String key) {
//        return listOperations.size(key).intValue();
//    }
//
//
//}
