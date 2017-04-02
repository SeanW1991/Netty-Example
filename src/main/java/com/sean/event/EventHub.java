package com.sean.event;

/**
 * Created by sean on 26/03/2016.
 */
public abstract class EventHub<E extends Event, C extends EventContext, L extends EventListener<? extends E, ? extends C, ?>, T> {

    public abstract T publish(E event, C eventContext);

    public abstract void subscribe(Class<? extends E> clazz, L listener);

    public abstract void unsubscribe(Class<? extends E> clazz) throws EventException;

    public abstract int size();

    public abstract int size(Class<? extends E> clazz);

}
