package com.sean.event.impl;

import com.sean.event.Event;
import com.sean.event.EventContext;
import com.sean.event.EventListener;

/**
 * Created by sean on 26/03/2016.
 */
public interface DefaultEventListener<E extends Event, C extends EventContext> extends EventListener<E, C, DefaultEventListener.EventStage> {

    public static enum EventStage {

        CONTINUE,

        EVENT_STOP

    }
}
