package com.sean.event.impl.priority;

import com.sean.event.Event;

/**
 * Created by sean on 26/03/2016.
 */
public abstract class PriorityEvent implements Event {

    private final PriorityEventType priorityType;

    protected PriorityEvent(PriorityEventType priorityType) {
        this.priorityType = priorityType;
    }

    public PriorityEventType getPriorityType() {
        return priorityType;
    }

    public static enum PriorityEventType {
        HIGH,
        MEDIUM,
        LOW;
    }

}

