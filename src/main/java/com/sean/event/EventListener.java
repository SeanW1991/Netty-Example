package com.sean.event;

/**
 * Created by sean on 26/03/2016.
 */
public interface EventListener<E extends Event, C extends EventContext, R> {
    public R onEvent(E event, C context);
}
