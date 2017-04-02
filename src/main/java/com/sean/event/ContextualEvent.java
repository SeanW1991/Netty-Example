package com.sean.event;

/**
 * Created by sean on 26/03/2016.
 */
public final class ContextualEvent<E extends Event> {

    private final E event;

    private final EventContext context;

    public ContextualEvent(E event, EventContext context) {
        this.event = event;
        this.context = context;
    }

    public E getEvent() {
        return event;
    }

    public EventContext getContext() {
        return context;
    }

}
