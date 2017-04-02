package com.sean.event.impl.async;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.sean.event.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;

/**
 * Created by sean on 26/03/2016.
 */
public final class AsyncEventHub<E extends Event, C extends EventContext> extends EventHub<E, C, AsyncEventListener<? extends E, C>,  ListenableFuture<AsyncEventResult>[]> {

    private final ConcurrentHashMap<Class<? extends E>, ConcurrentLinkedQueue<AsyncEventListener<? extends E, C>>> eventMap;

    private final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    public AsyncEventHub(ConcurrentHashMap<Class<? extends E>, ConcurrentLinkedQueue<AsyncEventListener<? extends E, C>>> eventMap) {
        this.eventMap = eventMap;
    }

    public AsyncEventHub() {
        this(new ConcurrentHashMap<>());
    }

    @Override
    public void subscribe(Class<? extends E> clazz, AsyncEventListener<? extends E, C> listener) {
        ConcurrentLinkedQueue<AsyncEventListener<? extends E, C>> eventHandlerList = eventMap.get(clazz);
        if (eventHandlerList == null) {
            eventHandlerList = new ConcurrentLinkedQueue<>();
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
    public ListenableFuture<AsyncEventResult>[] publish(E event, C eventContext) {
        int index = 0;
        ConcurrentLinkedQueue<AsyncEventListener<? extends E, C>> eventHandlers = Objects.requireNonNull(eventMap.get(event.getClass()));
        ListenableFuture<AsyncEventResult>[] listenableFutures = new ListenableFuture[eventHandlers.size()];
        for (Iterator<AsyncEventListener<? extends E, C>> iterator = eventHandlers.iterator(); iterator.hasNext(); ) {
            AsyncEventListener<? extends E, C> eventHandler = iterator.next();
            AsyncEventListener<E, C> listener = (AsyncEventListener<E, C>) eventHandler;
            ListenableFuture<AsyncEventResult> future = executorService.submit(() -> listener.onEvent(event, eventContext));
            listenableFutures[index] = future;
            index++;
        }
        return Objects.requireNonNull(listenableFutures);
    }

    @Override
    public int size(Class<? extends E> clazz) {
        ConcurrentLinkedQueue<AsyncEventListener<? extends E, C>> eventHandlers = eventMap.get(clazz);
        return Objects.requireNonNull(eventHandlers).size();
    }
}