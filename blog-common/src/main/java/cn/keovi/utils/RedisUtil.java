package cn.keovi.utils;

/**
 * @ClassName RedisUtil
 * @Description redis工具类
 * @Author gustavo
 * @Date 2021/12/26/12:51
 */

import org.apache.ibatis.cache.Cache;
import org.mybatis.caches.redis.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * redis cache 工具类
 *
 */
@Component
public final class RedisUtil implements Cache {
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private static RedisTemplate<Serializable, Object> redisTemplate;
    private String id;
    /*private String redisIp;*/
    private static RedisConnection redisConnection;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Autowired
    public RedisUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisUtil() {
    }

    public RedisUtil(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instance requires an ID");
        }
        logger.debug("create an cache instance with id" + id);
        this.id = "mybatiscache:" +id;
    }
    public String getId() {
        logger.debug("getId    " + id);
        return this.id;
    }

    public void putObject(Object key, Object value) {
        logger.debug("putObject key  " + key + " value  " + value);
        getRedisConnection().hSet(id.toString().getBytes(), key.toString().getBytes(), SerializeUtil.serialize(value));
    }

    public Object getObject(Object key) {
        logger.debug("getObject  id    " + id + " key : " + key);
        return SerializeUtil.unserialize(getRedisConnection().hGet(id.toString().getBytes(), key.toString().getBytes()));
    }

    public Object removeObject(Object key) {
        logger.debug("removeObject id    " + id + " key : " + key);
        return getRedisConnection().hDel(id.toString().getBytes(), key.toString().getBytes());


    }

    public void clear() {
        logger.debug("clear id "+id);
        getRedisConnection().del(id.toString().getBytes());
    }

    public int getSize() {
        logger.debug("getSize id "+id);
        return Long.valueOf(getRedisConnection().hLen(id.toString().getBytes())).intValue();
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisConnection getRedisConnection() {
        return redisConnection!=null&&!redisConnection.isClosed()? redisConnection: (redisConnection =  redisTemplate.getConnectionFactory().getConnection());

    }
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
//		LogUtil.error("-----------------------------redisIp"+redisIp);
//		System.err.println("-----------------------------redisIp"+redisIp);
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
//		LogUtil.error("-----------------------------redisIp"+redisIp);
//		System.err.println("-----------------------------redisIp" + redisIp);
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
//		LogUtil.error("-----------------------------redisIp"+redisIp);
//		System.err.println("-----------------------------redisIp"+redisIp);
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 设置新值，同时返回旧值
     * @param lockKey
     * @param stringOfLockExpireTime
     * @return
     */
    public String getSet(final String lockKey, final String stringOfLockExpireTime) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] bytes = redisConnection.getSet(lockKey.getBytes(), stringOfLockExpireTime.getBytes());
                if(bytes != null) {
                    return new String(bytes);
                }
                return null;
            }
        });
        return result;
    }

    /**
     * 如果不存在key则插入
     * @param lockKey
     * @param stringOfLockExpireTime
     * @return true 插入成功， false 插入失败
     */
    public boolean setnx(final String lockKey, final String stringOfLockExpireTime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.setNX(lockKey.getBytes(), stringOfLockExpireTime.getBytes());
            }
        });
    }

    /**
     * setnx 和 getSet方式插入的数据，调用此方法获取
     * @param key
     * @return
     */
    public String getInExecute(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] bytes = redisConnection.get(key.getBytes());
                if (bytes == null) {
                    return null;
                } else {
                    return new String(bytes);
                }
            }
        });
        return result;
    }

    /**
     * 将缓存保存在map集合中
     * @param redisKey
     * @param mapKey
     * @param mapValue
     * @return
     */
    public boolean putInMap(final String redisKey, String mapKey, Object mapValue) {
        boolean result = false;
        try {
            HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
            operations.put(redisKey, mapKey, mapValue);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object getOneFromMap(final String redisKey, String mapKey) {
        HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
        return operations.get(redisKey, mapKey);
    }

    public Object getAllFromMap(final String redisKey) {
        HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
        return operations.values(redisKey);
    }

    public void removeFromMap(final String redisKey, Object obj) {
        HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
        operations.delete(redisKey, obj);
    }

    public boolean setList(final String key, Object value) {
        boolean result = false;
        try {
            ListOperations<Serializable, Object> listOperations = redisTemplate.opsForList();
            listOperations.leftPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object getList(final String key) {
        ListOperations<Serializable, Object> listOperations = redisTemplate.opsForList();
        return listOperations.range(key,0,listOperations.size(key));
    }


    public boolean add(String key,Object value,Double score){
        boolean result = false;
        try {
            redisTemplate.opsForZSet().add(key,value,score);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean remove(String key,Object value){
        boolean result = false;
        try {
            redisTemplate.opsForZSet().remove(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<ZSetOperations.TypedTuple<Object>> getZSetByIndex(String key,long start,long end){
        return redisTemplate.opsForZSet().rangeWithScores(key, start,end);
    }


    public Long getIncr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        //初始设置过期时间
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
            //单位秒
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

}
