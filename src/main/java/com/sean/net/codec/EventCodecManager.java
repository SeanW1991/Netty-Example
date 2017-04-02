package com.sean.net.codec;

import com.sean.event.Event;

import java.util.Map;

/**
 * Created by sean on 25/04/16.
 */
public final class EventCodecManager {

    private static final int MAXIMUM_CODECS = 256;

    private final Map<Class<? extends Event>, EventCodec<? extends Event>> encodersTable;

    private final EventCodec<? extends Event>[] decoders;

    public EventCodecManager(Map<Class<? extends Event>, EventCodec<? extends Event>> encodersTable, EventCodec<? extends Event>[] decoders) {
        this.encodersTable = encodersTable;
        this.decoders = decoders;
    }

    public void registerCodec(EventCodec<? extends Event> codec) {
        Class<? extends Event> eventType = codec.getEventType();
        int opcode = codec.getOpcode();
        if(opcode < 0 || opcode > MAXIMUM_CODECS) {
            throw new IllegalArgumentException("Error invalid configuration for codec " + codec);
        }
        decoders[opcode] = codec;
        encodersTable.put(eventType, codec);
    }

    public EventCodec<? extends Event> getEncoder(Event event) {

        EventCodec<? extends Event> codec = encodersTable.get(event.getClass());

        if(codec == null) {
            throw new IllegalArgumentException();
        }

        return codec;

    }

    public EventCodec<? extends Event> getDecoder(int opcode) {

        if(opcode < 0 || opcode > MAXIMUM_CODECS) {
            throw new IllegalArgumentException("Error invalid opcode " + opcode);
        }

        EventCodec<? extends Event> codec = decoders[opcode];

        if(codec == null) {
            throw new IllegalArgumentException();
        }

        return codec;
    }
}
