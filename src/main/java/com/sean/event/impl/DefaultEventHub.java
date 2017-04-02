package com.sean.event.impl;

import com.sean.event.*;
import com.sean.event.EventListener;

import java.util.*;

/**
 * Created by sean on 26/03/2016.
 */
public final class DefaultEventHub<E extends Event, C extends EventContext> extends EventHub<E, C, DefaultEventListener<? extends E, ? extends C>, Void> {

    private final Map<Class<? extends E>, List<DefaultEventListener<? extends E, ? extends C>>> eventMap;

    public DefaultEventHub(Map<Class<? extends E>, List<DefaultEventListener<? extends E, ? extends C>>> eventMap) {
        this.eventMap = eventMap;
    }

    public DefaultEventHub() {
        this(new HashMap<>());
    }

    @Override
    public void subscribe(Class<? extends E> clazz, DefaultEventListener<? extends E, ? extends C> listener) {
        List<DefaultEventListener<? extends E, ? extends C>> eventHandlerList = eventMap.get(clazz);
        if (eventHandlerList == null) {
            eventHandlerList = new LinkedList<>();
            eventMap.put(clazz, eventHandlerList);
        }
        eventHandlerList.add(listener);
    }

    @Override
    public void unsubscribe(Class<? extends E> clazz) throws EventException {
        if(eventMap.get(clazz.getClass()) ==  null) {
            throw new EventException("Event " + clazz + " has no event listeners.");
        }
        eventMap.remove(clazz);
    }

    @Override
    public int size() {
        return eventMap.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Void publish(E event, C eventContext) {
        List<DefaultEventListener<? extends E, ? extends C>> eventHandlers = eventMap.get(event.getClass());
        if(eventHandlers != null) {
            for (DefaultEventListener<? extends E, ? extends C> eventHandler : eventHandlers) {
                DefaultEventListener<E, C> listener = (DefaultEventListener<E, C>) eventHandler;
                if (listener.onEvent(event, eventContext) == DefaultEventListener.EventStage.EVENT_STOP) {
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public int size(Class<? extends E> clazz) {
        List<DefaultEventListener<? extends E, ? extends C>> eventHandlers = eventMap.get(clazz);
        return Objects.requireNonNull(eventHandlers).size();
    }
}