package com.task.cache.implementation;

import com.task.cache.CacheExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheExecutor<Key, Value>
        extends LinkedHashMap<Key, Value>
        implements CacheExecutor<Key, Value> {

    private int size;

    public LruCacheExecutor(int size){
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    public Value get(Object key) {
        if (!this.containsKey(key))
            return null;
        return super.get(key);
    }

    @Override
    public Value put(Key key, Value value) {
        return super.put(key, value);
    }

    public void setCacheSize(int size) {
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Key, Value> eldest) {
        return this.size() > this.size;
    }
}