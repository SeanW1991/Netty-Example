package com.sean.event.impl.async;

import com.sean.event.Event;
import com.sean.event.EventContext;
import com.sean.event.EventListener;

/**
 * Created by sean on 26/03/2016.
 */
public interface AsyncEventListener<E extends Event, C extends EventContext> extends EventListener<E, C, AsyncEventResult> {

}
