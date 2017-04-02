package com.sean.net.codec;

import com.sean.event.Event;
import io.netty.buffer.ByteBuf;

/**
 * Created by sean on 25/04/16.
 */
public abstract class EventCodec<E extends Event> {

    private final int opcode;

    private final Class<? extends E> eventType;

    public EventCodec(int opcode, Class<? extends E> eventType) {
        this.opcode = opcode;
        this.eventType = eventType;
    }

    public abstract  E decode(ByteBuf frame);

    public abstract ByteBuf encode(E event);

    public Class<? extends E> getEventType() {
        return eventType;
    }

    public int getOpcode() {
        return opcode;
    }
}
