package com.mybatis.project1;

import org.apache.ibatis.cache.Cache;

/**
 * 可以替代下面的类，自己实现二级缓存
 * <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
 */
public class MyCache implements Cache {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object o, Object o1) {

    }

    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
