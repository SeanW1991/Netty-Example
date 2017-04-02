package com.sean.net.codec.file;

import com.sean.event.impl.DefaultEventListener;

/**
 * Created by sean on 05/05/16.
 */
public final class FileResponseEventListener implements DefaultEventListener<FileResponseEvent, FileEventContext> {

    @Override
    public EventStage onEvent(FileResponseEvent event, FileEventContext context) {
        context.getChannel().writeAndFlush(event);
        return EventStage.EVENT_STOP;
    }
}
