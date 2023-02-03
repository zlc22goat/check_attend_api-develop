package com.example.web.Cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

//重写redis的CacheManager方法
public class RedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println("缓存名称:"+s);
        return new MyRedisCache<K,V>(s);
    }
}
