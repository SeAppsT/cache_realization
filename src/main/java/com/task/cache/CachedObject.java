package com.task.cache;

public class CachedObject<Type> {
    private int use;
    private Long timeOfCreation;
    private Type object;

    public CachedObject(Type object) {
        this.object = object;
        this.use = 0;
    }

    public CachedObject<Type> init(){
        this.timeOfCreation = System.currentTimeMillis();
        return this;
    }

    public Type getObject() {
        return object;
    }

    public long getPriority() {
        if (use > 0)
            return timeOfCreation / use;
        else
            return 0;
    }

    public void use(){
        this.use += 1;
    }

    @Override
    public String toString() {
        return this.object.toString();
    }
}