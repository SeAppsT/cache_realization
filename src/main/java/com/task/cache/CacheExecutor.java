package com.task.cache;

public interface CacheExecutor<Key, Value> {
    public Value put(Key key, Value value);
    public Value get(Object index);
    public void setCacheSize(int size);
}