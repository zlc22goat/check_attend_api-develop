package com.example.web.Cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.example.core.utils.ApplicationContextUtils;
import com.example.core.utils.JWTUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @author tzy
 * @since 2021/4/8 20:27
 * aim 重写shiro的缓存方法
 */
public class MyRedisCache<K, V> implements Cache<K, V> {

    private String CacheName;

    //定义构造方法
    public MyRedisCache() {
    }

    public MyRedisCache(String CacheName) {
        this.CacheName = CacheName;
    }

    //获取用户名作为Key值
    private String getUserName(K k) {
        String token = k.toString();
        String username = JWTUtils.getUsername(token);
        return username;
    }

    //封装获取RedisTemplate的方法
    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Override
    public V get(K k) throws CacheException {
        String name = getUserName(k);
        System.out.println("进入GET方法==> k = " + name);
        return (V) getRedisTemplate().opsForHash().get(CacheName, name);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        String name = getUserName(k);
        System.out.println("进入PUT方法==> k = " + name + " &&&&& v = " + v);
        getRedisTemplate().opsForHash().put(CacheName, name, v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        String name = getUserName(k);
        System.out.println("进入REMOVE方法==> k = " + name);
        return (V) getRedisTemplate().opsForHash().delete(CacheName, name);
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("进入CLEAR方法");
        getRedisTemplate().delete(this.CacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.CacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.CacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.CacheName);
    }
}
