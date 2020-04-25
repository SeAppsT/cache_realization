package com.task.cache.implementation;

import com.task.cache.CacheExecutor;
import com.task.cache.CachedObject;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

public class LfuCacheExecutor<Key, Value>
        implements CacheExecutor<Key, Value> {
    private int size;
    private HashMap<Key, CachedObject<Value>> storage;

    public LfuCacheExecutor(int size){
        this.size = size;
        this.storage = new HashMap<>();
    }

    @Override
    public Value put(Key key, Value value) {
        if (this.storage.size() >= this.size) {
            this.removeObject();
            this.put(key, value);
        }
        this.storage.put(key, new CachedObject<Value>(value).init());
        return value;
    }

    @Override
    public Value get(Object index){
        if (!this.storage.containsKey(index))
            return null;
        CachedObject<Value> object = this.storage.get(index);
        object.use();
        return object.getObject();
    }

    @Override
    public void setCacheSize(int size) {
        this.size = size;
    }

    public void removeObject(){
        Key index = null;
        boolean first = true;
        for (Map.Entry<Key, CachedObject<Value>> iterator: this.storage.entrySet()) {
            if (first)
                index = iterator.getKey();
            else{
                if (iterator.getValue().getPriority() < this.storage.get(index).getPriority())
                    index = iterator.getKey();
            }
            first = false;
        }
        this.storage.remove(index);
    }

    @Override
    public String toString() {
        return this.storage.toString();
    }
}