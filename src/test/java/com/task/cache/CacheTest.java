package com.task.cache;

import com.task.cache.exception.NotSupportedStrategyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CacheTest {

    @Test
    public void lruTest() throws NotSupportedStrategyException {
        CacheFactory<Integer, String> cacheFactory = new CacheFactory<>();

        CacheExecutor<Integer, String> cacheExecutor
                = cacheFactory.getCacheExecutor(Strategy.LRU, 2);

        cacheExecutor.put(1, "first");
        cacheExecutor.put(2, "second");
        // cache is full
        cacheExecutor.get(1); // priority of "1" element grows
        cacheExecutor.put(3, "thrid"); // "1" element don't deleting
        System.out.println(cacheExecutor.toString());
        Assertions.assertNotNull(cacheExecutor.get(1));
    }

    @Test
    public void lfuTest() throws NotSupportedStrategyException, InterruptedException {
        CacheFactory<Integer, String> cacheFactory = new CacheFactory<>();
        CacheExecutor<Integer, String> cacheExecutor
                = cacheFactory.getCacheExecutor(Strategy.LFU, 2);

        cacheExecutor.put(2, "second");
        cacheExecutor.get(2);
        cacheExecutor.get(2);
        Thread.sleep(1000);
        cacheExecutor.get(2);
        // 3 calls to "2"
        cacheExecutor.put(1, "first");
        cacheExecutor.get(1);
        cacheExecutor.get(1);
        // 2 calls to "1", but for a letter time

        cacheExecutor.put(3, "thrid");
        System.out.println(cacheExecutor.toString());
        Assertions.assertNotNull(cacheExecutor.get(1));
    }
}