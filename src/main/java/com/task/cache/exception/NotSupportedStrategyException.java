package com.task.cache.exception;

import com.task.cache.Strategy;

public class NotSupportedStrategyException extends Throwable {

    private Strategy strategy;

    public NotSupportedStrategyException(Strategy strategy){
        this.strategy = strategy;
    }

    @Override
    public String getMessage() {
        return "Strategy " + this.strategy.toString() + " isn't supported";
    }
}