package com.task.cache;


import com.task.cache.exception.NotSupportedStrategyException;
import com.task.cache.implementation.LfuCacheExecutor;
import com.task.cache.implementation.LruCacheExecutor;

public class CacheFactory<Key, Value> {

    public CacheExecutor<Key, Value> getCacheExecutor(Strategy type, int size) throws NotSupportedStrategyException {
        if (type.equals(Strategy.LRU))
            return new LruCacheExecutor<Key, Value>(size);
        else if (type.equals(Strategy.LFU))
            return new LfuCacheExecutor<Key, Value>(size);
        else
            throw new NotSupportedStrategyException(type);
    }
}