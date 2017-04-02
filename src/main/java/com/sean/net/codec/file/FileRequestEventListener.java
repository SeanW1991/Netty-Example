package com.sean.net.codec.file;

import com.sean.event.impl.DefaultEventListener;
import com.sean.net.codec.handshake.HandshakeCodec;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.stream.ChunkedFile;

import java.io.File;
import java.io.IOException;


/**
 * Created by sean on 05/05/16.
 */
public final class FileRequestEventListener implements DefaultEventListener<FileRequestEvent, FileEventContext> {

    @Override
    public EventStage onEvent(FileRequestEvent event, FileEventContext context) {
        File file = event.getFile();
        context.getChannel().writeAndFlush(file.exists() ? new FileResponseEvent(FileResponseType.FILE_NOT_EXIST) : new FileResponseEvent(FileResponseType.FILE_TRANSMIT_OK));
        return EventStage.CONTINUE;
    }
}
