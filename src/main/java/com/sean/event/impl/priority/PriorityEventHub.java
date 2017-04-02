package com.sean.event.impl.priority;

/**
 * Created by sean on 26/03/2016.
 */

import com.sean.event.ContextualEvent;
import com.sean.event.EventContext;
import com.sean.event.EventException;
import com.sean.event.EventHub;
import com.sean.event.impl.DefaultEventHub;
import com.sean.event.impl.DefaultEventListener;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class PriorityEventHub<C extends EventContext> extends EventHub<PriorityEvent, C, DefaultEventListener<? extends PriorityEvent, C>, Void> {

    private final DefaultEventHub<PriorityEvent, C> eventHub;

    private final PriorityQueue<ContextualEvent<PriorityEvent>> eventPriorityQueue = new PriorityQueue<>(10, (Comparator<ContextualEvent<PriorityEvent>>) (event, other) -> {
        int eventOrdinal = event.getEvent().getPriorityType().ordinal();
        int otherOrdinal = other.getEvent().getPriorityType().ordinal();

        if(eventOrdinal > otherOrdinal) {
            return 1;
        } else if (eventOrdinal == otherOrdinal) {
            return 0;
        } else {
            return 2;
        }
    });

    public PriorityEventHub(DefaultEventHub<PriorityEvent, C> eventHub) {
        this.eventHub = eventHub;
    }

    @Override
    public Void publish(PriorityEvent event, C eventContext) {
        eventPriorityQueue.add(new ContextualEvent<>(event, eventContext));
        return null;
    }

    @Override
    public void subscribe(Class<? extends PriorityEvent> clazz, DefaultEventListener<? extends PriorityEvent, C> listener) {
        eventHub.subscribe(clazz, listener);
    }

    @Override
    public void unsubscribe(Class<? extends PriorityEvent> clazz) throws EventException {
        eventHub.unsubscribe(clazz);
    }

    @Override
    public int size() {
        return eventHub.size();
    }

    @Override
    public int size(Class<? extends PriorityEvent> clazz) {
        return eventHub.size(clazz);
    }

    public void poll() throws EventException {

        ContextualEvent<PriorityEvent> element;
        while ((element = eventPriorityQueue.poll()) != null) {
            PriorityEvent event = element.getEvent();
            EventContext context = element.getContext();

            eventHub.publish(event, (C) context);

        }
    }

    public PriorityQueue<ContextualEvent<PriorityEvent>> getEventPriorityQueue() {
        return eventPriorityQueue;
    }
}


